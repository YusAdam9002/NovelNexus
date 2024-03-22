package com.example.bookclubapp;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import java.util.ArrayList;

public class SearchBooksFragment extends Fragment {
    // Declare UI elements and DBHelper
    SearchView searchView;
    CustomAdapter customAdapter;
    RecyclerView SearchRecycler;
    ArrayList<Book> books;
    DBHelper myDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize UI elements and DBHelper
        searchView = view.findViewById(R.id.bookListSearchView);
        SearchRecycler = view.findViewById(R.id.allBookRecycler);
        myDB = new DBHelper(requireContext());
        customAdapter = new CustomAdapter(getActivity(), books);
        customAdapter.setSearchFragment(this);

        // Retrieve all books initially
        retrieveAllBooks("Enter Title");

        // Set up search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String userInput) {
                // Filter books based on user input
                filterBooks(userInput);
                return true;
            }
        });

        return view;
    }

    // Method to filter books based on user input
    private void filterBooks(String newText) {
        retrieveAllBooks(newText);
    }

    // Method to retrieve all books from the database
    void retrieveAllBooks(String searchText) {
        // Retrieve books from the database based on the search text
        ArrayList<Book> bookList = myDB.libraryBookSearch(searchText);

        // Create a new custom adapter with the retrieved book list
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), bookList);

        // Set up RecyclerView with the custom adapter
        SearchRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        SearchRecycler.setAdapter(customAdapter);
    }
}
