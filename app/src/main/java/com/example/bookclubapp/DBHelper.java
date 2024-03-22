package com.example.bookclubapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private final Context context;

    // Database constants
    private static final String DATABASE_NAME = "BookClubDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LIBRARY = "LibraryTable";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "booktitle";
    private static final String COLUMN_AUTHOR = "bookauthor";
    private static final String COLUMN_ISBN = "bookisbn";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_USER_PASSWORD = "password";

    // Constructor
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Method called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create users table
        String CreateUserTable = "CREATE TABLE " + TABLE_USERS + " (" + COLUMN_USERNAME + " TEXT PRIMARY KEY, " + COLUMN_USER_PASSWORD + " TEXT)";

        // Create library table
        String CreateLibraryTable = "CREATE TABLE " + TABLE_LIBRARY + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_TITLE + " TEXT, " + COLUMN_AUTHOR + " TEXT, " + COLUMN_ISBN + " TEXT)";

        // Execute SQL commands to create tables
        sqLiteDatabase.execSQL(CreateUserTable);
        sqLiteDatabase.execSQL(CreateLibraryTable);
    }

    // Method to insert user data into the database
    public boolean insertUserData(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_USER_PASSWORD, password);
        long result = myDB.insert(TABLE_USERS, null, contentValues);
        return result != -1;
    }

    // Method to check if a user exists in the database
    public boolean checkUser(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from " + TABLE_USERS + " where username=? and password=?", new String[]{username, password});
        return cursor.getCount() > 0;
    }

    // Method to add a book to the library
    public void addBook(String title, String author, String ISBN) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_ISBN, ISBN);

        long result = myDB.insert(TABLE_LIBRARY, null, cv);
    }

    // Method to retrieve all books from the library
    public Cursor BookData() {
        String query = "SELECT * FROM " + TABLE_LIBRARY;
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor cursor = null;
        if (myDB != null) {
            cursor = myDB.rawQuery(query, null);
        }
        return cursor;
    }

    // Method to search for books in the library by title
    public ArrayList<Book> libraryBookSearch(String searchText) {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LIBRARY + " WHERE " + COLUMN_TITLE + " LIKE ?";
        String[] selectionArgs = new String[]{"%" + searchText + "%"};
        Cursor cursor = myDB.rawQuery(query, selectionArgs);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex(COLUMN_ID);
                    int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
                    int authorIndex = cursor.getColumnIndex(COLUMN_AUTHOR);
                    int isbnIndex = cursor.getColumnIndex(COLUMN_ISBN);
                    if (idIndex >= 0 && titleIndex >= 0 && authorIndex >= 0 && isbnIndex >= 0) {
                        int id = cursor.getInt(idIndex);
                        String title = cursor.getString(titleIndex);
                        String author = cursor.getString(authorIndex);
                        String isbn = cursor.getString(isbnIndex);

                        Book book = new Book(id, title, author, isbn);
                        bookList.add(book);
                    }
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            myDB.close();
        }
        return bookList;
    }

    // Method to update book details in the library
    public void updateLibrary(String row_id, String title, String author, String ISBN) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_ISBN, ISBN);

        long rowsAffected = myDB.update(TABLE_LIBRARY, cv, COLUMN_ID + "=?", new String[]{row_id});
    }

    // Method called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop existing tables if they exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_LIBRARY);
        // Recreate the tables
        onCreate(sqLiteDatabase);
    }
}
