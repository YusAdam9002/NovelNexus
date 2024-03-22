package com.example.bookclubapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BookFragment extends Fragment {
    // Declaring variables
    RecyclerView recyclerView;
    FloatingActionButton add_readbook;
    ArrayList<String> bookStatus;
    TextView readingTitle;
    ArrayList<Book> Books;
    DBHelper myDB;
    CustomAdapter customAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflating the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);

        // Initializing UI elements
        recyclerView = view.findViewById(R.id.readRecycle);
        add_readbook = view.findViewById(R.id.add_read);
        readingTitle = view.findViewById(R.id.TitleRead);

        // Underlining the TextView for reading title
        readingTitle.setPaintFlags(readingTitle.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // Setting click listener for the floating action button to add a book
        add_readbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating intent to navigate to AddBook activity
                Context context = requireContext();
                Intent intent = new Intent(context, AddBook.class);
                context.startActivity(intent);
            }
        });

        // Initializing database helper
        myDB = new DBHelper(requireContext());

        // Initializing ArrayLists to store book data
        Books = new ArrayList<>();
        bookStatus = new ArrayList<>();

        // Retrieving data from the database and populating the RecyclerView
        storeData();

        // Initializing and setting custom adapter for the RecyclerView
        customAdapter = new CustomAdapter(getActivity(), Books);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        return view;
    }

    // Method to retrieve book data from the database
    void storeData() {
        Cursor cursor = myDB.BookData();
        if (cursor != null && cursor.getCount() > 0) {
            // Iterating through the cursor to retrieve book data
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("book_title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("book_author"));
                String isbn = cursor.getString(cursor.getColumnIndexOrThrow("book_isbn"));
                // Creating Book object and adding it to the ArrayList
                Book book = new Book(id, title, author, isbn);
                Books.add(book);
            }
        } else {
            // Displaying toast message if no data found in the database
            Toast.makeText(requireContext(), "No data found.", Toast.LENGTH_SHORT).show();
        }
        if (cursor != null) {
            cursor.close();
        }
    }
}
