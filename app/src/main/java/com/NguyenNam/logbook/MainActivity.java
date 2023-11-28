package com.NguyenNam.logbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.NguyenNam.logbook.db.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
    Button buttonEx1, buttonEx2, buttonEx3;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonEx1 = findViewById(R.id.buttonEx1);
        buttonEx2 = findViewById(R.id.buttonEx2);
        buttonEx3 = findViewById(R.id.buttonEx3);
        databaseHelper = new DatabaseHelper(this);
        buttonEx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(intent);
            }
        });
        buttonEx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewImagesActivity.class);
                startActivity(intent);
            }
        });
        buttonEx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StoreDataActivity.class);
                startActivity(intent);
            }
        });
    }
}