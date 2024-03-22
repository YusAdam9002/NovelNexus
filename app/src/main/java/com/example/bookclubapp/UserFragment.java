package com.example.bookclubapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {
    // Declare UI elements and DBHelper
    TextView profileText;
    Button logout;
    ImageView profilePicture;
    DBHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // Initialize DBHelper and UI elements
        dbHelper = new DBHelper(getActivity());
        logout = view.findViewById(R.id.logoutButton);
        profileText = view.findViewById(R.id.profileText);
        profilePicture = view.findViewById(R.id.profilePicture);

        // Add underline to profile text
        profileText.setPaintFlags(profileText.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // Set click listener for the logout button
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to navigate back to the main activity (logout)
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
                // Display a toast message indicating logout
                Toast.makeText(getContext(), "Logged out", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
