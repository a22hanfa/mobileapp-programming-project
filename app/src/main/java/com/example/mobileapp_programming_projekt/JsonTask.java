package com.example.mobileapp_programming_projekt;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JsonTask {

    public interface JsonTaskListener {
        void onPostExecute(String json);
    }

    private HttpURLConnection connection = null;
    private BufferedReader reader = null;
    private final JsonTaskListener listener;

    private static final String TAG = "JsonTask";

    public JsonTask(JsonTaskListener listener) {
        this.listener = listener;
    }

    public void execute(String urlStr) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        CompletableFuture.supplyAsync(() -> {
                    try {
                        URL url = new URL(urlStr);

                        Log.d(TAG, "Connecting to URL: " + urlStr);

                        connection = (HttpURLConnection) url.openConnection();
                        connection.connect();

                        InputStream stream = connection.getInputStream();
                        reader = new BufferedReader(new InputStreamReader(stream));

                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line).append("\n");
                        }
                        Log.d(TAG, "Received JSON: " + builder.toString());
                        return builder.toString();
                    } catch (MalformedURLException e) {
                        Log.e(TAG, "Malformed URL: " + urlStr, e);
                        e.printStackTrace();
                    } catch (IOException e) {
                        Log.e(TAG, "Failed to get JSON from: " + urlStr, e);
                        e.printStackTrace();
                    } finally {
                        if (connection != null) {
                            connection.disconnect();
                        }
                        try {
                            if (reader != null) {
                                reader.close();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return null;
                }, executor)
                .thenAcceptAsync(json -> listener.onPostExecute(json), executor);
    }
}
