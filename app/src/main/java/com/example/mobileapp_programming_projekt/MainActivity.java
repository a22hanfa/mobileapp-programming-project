package com.example.mobileapp_programming_projekt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener{

    public final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22hanfa";

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonTask jsonTask = new JsonTask(this);
        jsonTask.execute(JSON_URL);

        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }


    @Override
    public void onPostExecute(String json) {
        runOnUiThread(() -> {
            Log.d(TAG, "Received JSON: " + json);
            // Parse the JSON data
            try {
                JSONArray jsonArray = new JSONArray(json);
                ArrayList<inputDecoder> items = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.d(TAG, "In for loop, iteration: " + i);
                    items.add(new inputDecoder(jsonArray.getJSONObject(i)));
                    Log.d(TAG, "After adding item");
                }

                // Pass the data to the RecyclerView
                RecyclerView recyclerView = findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, items, item -> {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    // Pass data to the DetailsActivity
                    intent.putExtra("ID", item.getID());
                    intent.putExtra("Name", item.getName());
                    intent.putExtra("Location", item.getLocation());
                    intent.putExtra("Category", item.getCategory());
                    intent.putExtra("Auxdata", item.getAuxdata());
                    startActivity(intent);
                    Log.d(TAG, "Start Intent");
                });
                Log.d(TAG, "Before adapter");
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                Log.e(TAG, "Failed to parse JSON: " + json, e);
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(TAG, "Unexpected exception", e);
                e.printStackTrace();
            }
        });
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
