package com.example.bookclubapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MessageFragment extends Fragment {
    // Declare UI elements and DBHelper
    EditText messageEditText;
    LinearLayout messageContainer;
    Button sendMessage;
    DBHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        // Initialize UI elements
        messageEditText = view.findViewById(R.id.messageEditText);
        messageContainer = view.findViewById(R.id.messageContainer);
        sendMessage = view.findViewById(R.id.sendMessageButton);
        dbHelper = new DBHelper(getActivity());

        // Set click listener for the "Send Message" button
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the Message() method to handle sending the message
                Message();
            }
        });

        return view;
    }

    // Method to handle sending a message
    public void Message() {
        // Get the text from the message edit text
        String messageText = messageEditText.getText().toString().trim();

        // Check if the message is not empty
        if (!messageText.isEmpty()) {
            // Create a new TextView to display the message
            TextView messageView = new TextView(getContext());
            messageView.setText(messageText); // Set the text of the message view
            messageContainer.addView(messageView); // Add the message view to the message container
            messageEditText.getText().clear(); // Clear the message edit text
        }
    }
}
