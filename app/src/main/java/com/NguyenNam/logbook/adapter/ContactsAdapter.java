package com.NguyenNam.logbook.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.NguyenNam.logbook.R;
import com.NguyenNam.logbook.StoreDataActivity;
import com.NguyenNam.logbook.db.entity.Contact;

import java.util.ArrayList;

// Adapter class for the RecyclerView in StoreDataActivity
public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    // Variable
    private Context context_s;
    private ArrayList<Contact> contacts_List;
    private StoreDataActivity storeDataActivity;

    // ViewHolder class to hold the views for each item in the RecyclerView
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_Contac;
        public TextView email_Contac;
        public ImageView image_Contac;

        // Constructor to initialize the views
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name_Contac = itemView.findViewById(R.id.name);
            this.email_Contac = itemView.findViewById(R.id.email);
            this.image_Contac = itemView.findViewById(R.id.image);
        }
    }

    // Adapter constructor
    public ContactsAdapter(Context context, ArrayList<Contact> contacts, StoreDataActivity storeDataActivity) {
        this.context_s = context;
        this.contacts_List = contacts;
        this.storeDataActivity = storeDataActivity;
    }

    // Create a ViewHolder and inflate the layout for each item in the RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    // Bind the data for each item to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Contact contact = contacts_List.get(position);

        // Set the image if available, otherwise set a default image
        if (contact.getImageUri() != null) {
            Uri imageUri = Uri.parse(contact.getImageUri());
            holder.image_Contac.setImageURI(imageUri);
        } else {
            holder.image_Contac.setImageResource(R.drawable.image1); // Default image when no image is available
        }

        holder.name_Contac.setText(contact.getName());
        holder.email_Contac.setText(contact.getEmail());

        // Set click listener for the item to open the add and edit dialog
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDataActivity.addAndEditContacts(true, contact, position);
            }
        });
    }

    // Return the total number of items in the data set
    @Override
    public int getItemCount() {
        return contacts_List.size();
    }
}
