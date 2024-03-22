package com.example.bookclubapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateBookActivity extends AppCompatActivity {
    // Declare UI elements and variables
    EditText titleInput, authorInput, ISBNInput;
    Button updateButton;
    String id, title, author, ISBN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialize UI elements
        titleInput = findViewById(R.id.input_Title2);
        authorInput = findViewById(R.id.input_Author2);
        ISBNInput = findViewById(R.id.input_ISBN2);
        updateButton = findViewById(R.id.updateReadBook);

        // Get data passed from previous activity
        getIntentData();

        // Set click listener for the "Update" button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create DBHelper instance
                DBHelper myDB = new DBHelper(UpdateBookActivity.this);

                // Get new values for title, author, and ISBN from input fields
                ISBN = ISBNInput.getText().toString().trim();
                title = titleInput.getText().toString().trim();
                author = authorInput.getText().toString().trim();

                // Update the current library book with new values
                myDB.updateLibrary(id, title, author, ISBN);
            }
        });
    }

    // Method to retrieve data passed from the previous activity
    void getIntentData() {
        // Check if intent contains necessary data
        if (getIntent().hasExtra("id") &&
                getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") &&
                getIntent().hasExtra("ISBN")) {
            // Retrieve data from intent extras
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            ISBN = getIntent().getStringExtra("ISBN");
        } else {
            // If no data found, display a toast message
            Toast.makeText(this, "No Data.", Toast.LENGTH_SHORT).show();
        }
    }
}
