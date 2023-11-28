package com.NguyenNam.logbook.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.NguyenNam.logbook.db.entity.Contact;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact_db";

    // Constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create the contact table
        sqLiteDatabase.execSQL(Contact.CREATE_TABLE);
    }

    // Called when the database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // Drop the existing table and recreate it
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contact.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Get a specific contact by its ID
    public Contact getContact(long id){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Contact.TABLE_NAME + " WHERE " + Contact.COLUMN_ID + " = " + id;

        try (Cursor cursor = db.rawQuery(selectQuery, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                return new Contact(
                        cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_IMAGE))
                );
            }
        }

        return null;
    }

    // Get all contacts from the database
    public ArrayList<Contact> getAllContacts(){
        ArrayList<Contact> contacts = new ArrayList<>();

        // Query to select all contacts, ordered by ID in descending order
        String selectQuery = "SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY "+
                Contact.COLUMN_ID + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                // Create a contact object for each row and add it to the list
                Contact contact = new Contact();
                contact.setId(cursor.getInt(cursor.getColumnIndexOrThrow(Contact.COLUMN_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_NAME)));
                contact.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_EMAIL)));
                contact.setImageUri(cursor.getString(cursor.getColumnIndexOrThrow(Contact.COLUMN_IMAGE)));
                contacts.add(contact);

            }while(cursor.moveToNext());
        }

        db.close();

        return contacts;
    }

    // Insert a new contact into the database
    public long insertContact(String name, String email,  String imageResource){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Contact.COLUMN_NAME, name);
        values.put(Contact.COLUMN_EMAIL, email);
        values.put(Contact.COLUMN_IMAGE, imageResource);

        // Insert the new contact and get its ID
        long id = db.insert(Contact.TABLE_NAME, null, values);

        db.close();

        return id;
    }

    // Update an existing contact in the database
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Contact.COLUMN_NAME, contact.getName());
        values.put(Contact.COLUMN_EMAIL, contact.getEmail());
        values.put(Contact.COLUMN_IMAGE, contact.getImageUri());

        // Update the contact based on its ID
        int rowsUpdated = db.update(Contact.TABLE_NAME, values, Contact.COLUMN_ID + " = ? ",
                new String[]{String.valueOf(contact.getId())});

        db.close(); // Close the database

        return rowsUpdated;
    }

    // Delete a contact from the database
    public void deleteContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Contact.TABLE_NAME, Contact.COLUMN_ID + " = ?",
                new String[]{String.valueOf(contact.getId())}
        );
        db.close();
    }
}
