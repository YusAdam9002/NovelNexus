package com.example.bookclubapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Declare DBHelper and UI elements
    com.example.bookclubapp.DBHelper dbHelper;
    Button btnLogin, goToReg;
    EditText editUserName, editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DBHelper and UI elements
        dbHelper = new DBHelper(this);
        editUserName = findViewById(R.id.userName);
        editPass = findViewById(R.id.password);
        goToReg = findViewById(R.id.goToRegister);
        btnLogin = findViewById(R.id.logButton);

        // Set click listener for the "Go to Register" button
        goToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the registration activity
                Intent intent = new Intent(MainActivity.this, ActivitySignUp.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "Login" button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve username and password input
                String username = editUserName.getText().toString().trim();
                String password = editPass.getText().toString().trim();

                // Validate username and password (optional)
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter username and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check user credentials
                boolean isLogged = dbHelper.checkUser(username, password);
                if (isLogged) {
                    // If login successful, navigate to the user logged in activity
                    Intent intent = new Intent(MainActivity.this, UserLoggedIn.class);
                    startActivity(intent);
                } else {
                    // If login failed, display a toast message
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
