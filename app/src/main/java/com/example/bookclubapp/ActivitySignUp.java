package com.example.bookclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivitySignUp extends AppCompatActivity {

    // Declaring variables for UI elements
    EditText editUser, editPass, editRepass;
    Button buttonRegister, buttonLogin;

    // Database helper class instance
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initializing UI elements
        editUser = findViewById(R.id.userName);
        editPass = findViewById(R.id.password);
        editRepass = findViewById(R.id.retypePassword);
        buttonRegister = findViewById(R.id.regButton);
        buttonLogin = findViewById(R.id.goToLogin);

        // Initializing database helper
        dbHelper = new DBHelper(this);

        // Setting click listener for login button
        buttonLogin.setOnClickListener(v -> goToMainActivity());

        // Setting click listener for register button
        buttonRegister.setOnClickListener(view -> registerUser());
    }

    // Method to navigate to the main activity
    private void goToMainActivity() {
        Intent intent = new Intent(ActivitySignUp.this, MainActivity.class);
        startActivity(intent);
    }

    // Method to register a new user
    private void registerUser() {
        // Getting input from EditText fields
        String user = editUser.getText().toString();
        String pass = editPass.getText().toString();
        String repass = editRepass.getText().toString();

        // Checking if passwords match
        if (pass.equals(repass)) {
            // Inserting user data into the database
            boolean registeredSuccess = dbHelper.insertUserData(user, pass);

            // Displaying appropriate message based on registration success or failure
            if (registeredSuccess) {
                showToast("User registered Successfully");
                finish();
            } else {
                showToast("User registration Failed. Please try again.");
            }
        } else {
            // Displaying message if passwords do not match
            showToast("Passwords do not match.");
        }
    }

    // Method to display toast messages
    private void showToast(String message) {
        Toast.makeText(ActivitySignUp.this, message, Toast.LENGTH_LONG).show();
    }
}
