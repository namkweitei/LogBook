package com.NguyenNam.logbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.NguyenNam.logbook.R;
import com.NguyenNam.logbook.StoreDataActivity;
import com.NguyenNam.logbook.db.entity.Contact;
import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    // 1- Variable
    private Context context;
    private ArrayList<Contact> contactsList;
    private StoreDataActivity storeDataActivity;

    // 2- ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.email = itemView.findViewById(R.id.email);
        }
    }
    //bo sung
    public ContactsAdapter(Context context, ArrayList<Contact> contacts, StoreDataActivity storeDataActivity){
        this.context = context;
        this.contactsList = contacts;
        this.storeDataActivity = storeDataActivity;
    }

    // tạo ra đối tượng ViewHolder, trong nó chứa View hiện thị dữ liệu
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Nạp layout cho View biểu diễn phần tử cu the
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_list_item,parent,false);

        return new MyViewHolder(itemView);
    }

    //chuyển dữ liệu phần tử vào ViewHolder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int positions) {
        final Contact contact = contactsList.get(positions);

        holder.name.setText(contact.getName());
        holder.email.setText(contact.getEmail());
        ////Xử lý khi nút Chi tiết được bấm
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeDataActivity.addAndEditContacts(true,contact,positions);
            }
        });
    }
    //cho biết số phần tử của dữ liệu
    @Override
    public int getItemCount() {
        return contactsList.size();
    }
}
