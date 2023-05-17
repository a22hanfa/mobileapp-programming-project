package com.example.mobileapp_programming_projekt;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class inputDecoder {

    private static final String TAG = "inputDecoder";
    private String ID;
    private String name;
    private String location;
    private String category;
    private String auxdata;

    public inputDecoder(JSONObject jsonObject) {
        try {
            Log.d(TAG, "Decoding JSON: " + jsonObject.toString());
            this.ID = jsonObject.getString("ID");
            this.name = jsonObject.getString("name");
            this.location = jsonObject.getString("location");
            this.category = jsonObject.getString("category");
            this.auxdata = jsonObject.getString("auxdata");
        } catch (JSONException e) {
            Log.e(TAG, "Failed to decode JSON: " + jsonObject.toString(), e);
            e.printStackTrace();
        }
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getAuxdata() {
        return auxdata;
    }

    public String getDescription() {
        return "ID: " + ID + "\nName: " + name + "\nLocation: " + location + "\nCategory: " + category;
    }
}
