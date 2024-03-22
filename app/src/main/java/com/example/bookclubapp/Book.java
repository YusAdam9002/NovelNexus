package com.example.bookclubapp;

import androidx.annotation.NonNull;

public class Book {
    // Declaring private member variables
    private String id;
    private String title;
    private String author;
    private String ISBN;

    // Constructor to initialize the Book object with provided values
    public Book(int id, String title, String author, String ISBN){
        // Converting id to String and assigning it
        this.id = String.valueOf(id);
        // Assigning title, author, and ISBN
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
    }

    // Getter method for id
    public String getId() {
        return id;
    }

    // Setter method for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter method for title
    public String getTitle(){
        return title;
    }

    // Setter method for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter method for author
    public String getAuthor() {
        return author;
    }

    // Setter method for author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter method for ISBN
    public String getIsbn() {
        return ISBN;
    }

    // Setter method for ISBN
    public void setIsbn(String isbn) {
        this.ISBN = isbn;
    }

    // Overriding toString method to provide a string representation of the Book object
    @NonNull
    @Override
    public String toString() {
        return "Book(" + "id = " + id + ", title = " + title + ", author = " + author + ", ISBN = " + ISBN + ")";
    }
}
