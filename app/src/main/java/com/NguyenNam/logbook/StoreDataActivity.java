package com.NguyenNam.logbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.NguyenNam.logbook.adapter.ContactsAdapter;
import com.NguyenNam.logbook.db.DatabaseHelper;
import com.NguyenNam.logbook.db.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StoreDataActivity extends AppCompatActivity {

    private ContactsAdapter contactsAdapter;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHelper db;
    private ImageView contactImage;
    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_view);

        // Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Favorite Contacts");

        // Initialize UI elements
        recyclerView = findViewById(R.id.recycler_view_contacts);
        db = new DatabaseHelper(this);

        // Load existing contacts from the database
        contactArrayList.addAll(db.getAllContacts());

        // Set up the RecyclerView and its adapter
        contactsAdapter = new ContactsAdapter(this, contactArrayList, StoreDataActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactsAdapter);

        // Set up FloatingActionButton for adding new contacts
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditContacts(false, null, -1);
            }
        });
    }

    // Method to handle adding and editing contacts
    public void addAndEditContacts(final boolean isUpdated, final Contact contact, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.layout_add_contact, null);

        contactImage = view.findViewById(R.id.contact_image);
        Button btnSelectImage = view.findViewById(R.id.btn_select_image);

        // Set OnClickListener for selecting contact image
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start an image selection activity
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        // Build the AlertDialog for adding/editing contacts
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);

        TextView contactTitle = view.findViewById(R.id.new_contact_title);
        final EditText newContact = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        // Set the title and pre-fill fields if editing
        contactTitle.setText(!isUpdated ? "Add New Contact" : "Edit Contact");

        if (isUpdated && contact != null) {
            newContact.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
            // Set the image from the saved URI in Contact (if any)
            if (!TextUtils.isEmpty(contact.getImageUri())) {
                contactImage.setImageURI(Uri.parse(contact.getImageUri()));
                contactImage.setTag(Uri.parse(contact.getImageUri()));
            }
        }

        // Configure positive and negative buttons
        alertDialogBuilder.setCancelable(false).setPositiveButton(isUpdated ? "Update" : "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Check for valid name input
                if (TextUtils.isEmpty(newContact.getText().toString())) {
                    Toast.makeText(StoreDataActivity.this, "Please Enter a Name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dialogInterface.dismiss();
                }

                // Get the image path from the selected image
                String imagePath = getPathFromImageView(contactImage);

                // Perform update or save based on the operation
                if (isUpdated && contact != null) {
                    UpdateContact(newContact.getText().toString(), contactEmail.getText().toString(), imagePath, position);
                } else {
                    CreateContact(newContact.getText().toString(), contactEmail.getText().toString(), imagePath);
                }
            }
        }).setNegativeButton(isUpdated ? "Delete" : "Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Perform delete if editing, otherwise, cancel
                        if (isUpdated) {
                            DeleteContact(contact, position);
                        } else {
                            dialogInterface.cancel();
                        }
                    }
                });

        // Create and show the AlertDialog
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    // Method to handle contact deletion
    private void DeleteContact(Contact contact, int position) {
        contactArrayList.remove(position);
        db.deleteContact(contact);
        contactsAdapter.notifyDataSetChanged();
    }

    // Method to handle contact update
    private void UpdateContact(String name, String email, String imagePath, int position) {
        Contact contact = contactArrayList.get(position);
        contact.setName(name);
        contact.setEmail(email);
        contact.setImageUri(imagePath);
        db.updateContact(contact);
        contactArrayList.set(position, contact);
        contactsAdapter.notifyDataSetChanged();
    }

    // Method to handle contact creation
    private void CreateContact(String name, String email, String imagePath) {
        long id = db.insertContact(name, email, imagePath);
        Contact contact = db.getContact(id);
        if (contact != null) {
            contact.setImageUri(imagePath);
            contactArrayList.add(0, contact);
            contactsAdapter.notifyDataSetChanged();
        }
    }

    // Method to handle the result of image selection
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            if (uri != null) {
                contactImage.setImageURI(uri);
                contactImage.setTag(uri);
            }
        }
    }

    // Method to get the path from an ImageView's tag (assumed to be a URI)
    private String getPathFromImageView(ImageView imageView) {
        Uri imageUri = (Uri) imageView.getTag();
        if (imageUri != null) {
            return getPathFromURI(imageUri);
        }
        return null;
    }

    // Method to get the path from a URI using content resolver
    private String getPathFromURI(Uri contentUri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);
        cursor.close();
        return imagePath;
    }

    // Method for handling menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Method for handling menu item selection
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings){
            // Handle settings action
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}