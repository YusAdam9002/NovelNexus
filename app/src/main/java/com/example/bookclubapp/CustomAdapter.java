package com.example.bookclubapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    private final ArrayList<Book> Books;
    private final Activity activity;
    private Fragment searchFragment;

    // Constructor to initialize the adapter
    CustomAdapter(Activity activity, ArrayList<Book> books){
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.Books = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating layout for each item in RecyclerView
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.book_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        // Binding data to each item
        Book addedBook = Books.get(position);
        holder.bookTitleText.setText(addedBook.getTitle());
        holder.bookAuthorText.setText(addedBook.getAuthor());
        holder.bookISBNText.setText(addedBook.getIsbn());

        // Setting click listener for each item in RecyclerView
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating intent to navigate to UpdateBookActivity with book details
                Intent intent = new Intent(context, UpdateBookActivity.class);
                intent.putExtra("id", String.valueOf(addedBook.getId()));
                intent.putExtra("title", addedBook.getTitle());
                intent.putExtra("author", addedBook.getAuthor());
                intent.putExtra("ISBN", addedBook.getIsbn());
                context.startActivity(intent);
            }
        });
    }

    // Setter method to set search fragment
    public void setSearchFragment(Fragment fragment){
        this.searchFragment = fragment;
    }

    @Override
    public int getItemCount() {
        return Books.size();
    }

    // ViewHolder class to hold references to UI elements
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView bookIdText, bookTitleText, bookAuthorText, bookISBNText;
        CardView mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdText = itemView.findViewById(R.id.bookIdText);
            bookTitleText = itemView.findViewById(R.id.bookTitleText);
            bookAuthorText = itemView.findViewById(R.id.bookAuthorText);
            bookISBNText = itemView.findViewById(R.id.bookISBNText);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
