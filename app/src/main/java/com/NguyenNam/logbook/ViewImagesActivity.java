package com.NguyenNam.logbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewImagesActivity extends AppCompatActivity {

    private ImageView imageView;
    private Button btnPrevious, btnNext;

    private int[] imageResources = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
            // Add more image resources as needed
    };

    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);

        imageView = findViewById(R.id.imageView);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        updateImageView();

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousImage();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextImage();
            }
        });
    }

    private void showPreviousImage() {
        if (currentImageIndex > 0) {
            currentImageIndex--;
            updateImageView();
        }
    }

    private void showNextImage() {
        if (currentImageIndex < imageResources.length - 1) {
            currentImageIndex++;
            updateImageView();
        }
    }

    private void updateImageView() {
        imageView.setImageResource(imageResources[currentImageIndex]);
    }
}
