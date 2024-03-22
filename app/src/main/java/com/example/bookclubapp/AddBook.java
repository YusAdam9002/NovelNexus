package com.example.bookclubapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddBook extends AppCompatActivity {
    // Declaring EditText and Button variables
    EditText titleInput, authorInput, ISBNInput;
    Button addReadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_read_book);

        // Initializing EditText and Button variables
        titleInput = findViewById(R.id.input_Title);
        authorInput = findViewById(R.id.input_Author);
        ISBNInput = findViewById(R.id.input_ISBN);
        addReadButton = findViewById(R.id.addReadBook);

        // Setting click listener for the "Add Read Book" button
        addReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating an instance of DBHelper to interact with the database
                DBHelper myDB = new DBHelper(AddBook.this);

                // Calling the addBook method in DBHelper to add the book to the database
                myDB.addBook(
                        titleInput.getText().toString().trim(),  // Getting the title input
                        authorInput.getText().toString().trim(), // Getting the author input
                        ISBNInput.getText().toString().trim()   // Getting the ISBN input
                );
            }
        });
    }
}
