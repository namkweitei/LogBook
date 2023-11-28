package com.NguyenNam.logbook.db.entity;

// Entity class representing a contact in the database
public class Contact {

    // Constants for the database table
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "contact_id";
    public static final String COLUMN_NAME = "contact_name";
    public static final String COLUMN_EMAIL = "contact_email";
    public static final String COLUMN_IMAGE = "contact_image";

    // Variables representing contact attributes
    private String name;
    private String email;
    private String imageUri;
    private int id;

    // Default constructor
    public Contact() {

    }

    // Parameterized constructor for creating a contact with specific values
    public Contact(String name, String email, int id, String imageUri) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.imageUri = imageUri;
    }

    // Getters and setters for contact attributes

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    // SQL query for creating the contacts table
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_IMAGE + " TEXT "
                    + ")";
}
