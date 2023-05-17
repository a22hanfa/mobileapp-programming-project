package com.example.mobileapp_programming_projekt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Get data from Intent
        String id = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("Name");
        String location = getIntent().getStringExtra("Location");
        String category = getIntent().getStringExtra("Category");
        String auxdata = getIntent().getStringExtra("Auxdata");

        // Populate TextViews
        TextView tvId = findViewById(R.id.tv_id);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvLocation = findViewById(R.id.tv_location);
        TextView tvCategory = findViewById(R.id.tv_category);
        TextView tvAuxdata = findViewById(R.id.tv_auxdata);

        // Set the text
        tvId.setText(id);
        tvName.setText(name);
        tvLocation.setText(location);
        tvCategory.setText(category);
        tvAuxdata.setText(auxdata);
    }
}
