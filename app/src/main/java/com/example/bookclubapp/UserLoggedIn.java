package com.example.bookclubapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class UserLoggedIn extends AppCompatActivity {
    // Declare UI elements and fragments
    BottomNavigationView bottomNavigationView;
    MessageFragment messageFragment = new MessageFragment();
    BookFragment bookFragment = new BookFragment();
    SearchBooksFragment searchFragment = new SearchBooksFragment();
    UserFragment userFragment = new UserFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initialize bottom navigation view
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set listener for bottom navigation view
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // Handle item selection
                int itemId = menuItem.getItemId();
                if (itemId == R.id.message) {
                    // Replace fragment with MessageFragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, messageFragment).commit();
                    return true;
                } else if (itemId == R.id.books) {
                    // Replace fragment with BookFragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, bookFragment).commit();
                    return true;
                } else if (itemId == R.id.search) {
                    // Replace fragment with SearchBooksFragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                    return true;
                } else if (itemId == R.id.user) {
                    // Replace fragment with UserFragment
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment).commit();
                    return true;
                }
                return false;
            }
        });

        // Set initial fragment to MessageFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, messageFragment).commit();
    }
}
