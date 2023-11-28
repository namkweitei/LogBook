package com.NguyenNam.logbook;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewImagesActivity extends AppCompatActivity {

    // Declare ImageView and Buttons
    private ImageView imageView;
    private Button btnPrevious, btnNext;

    // Array of image resources
    private int[] imageResources = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
            // Add more image resources as needed
    };

    // Index to track the currently displayed image
    private int currentImageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);

        // Initialize ImageView and Buttons by finding their IDs
        imageView = findViewById(R.id.imageView);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Update the ImageView with the initial image
        updateImageView();

        // Set OnClickListener for the "Previous" button
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to show the previous image
                showPreviousImage();
            }
        });

        // Set OnClickListener for the "Next" button
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call method to show the next image
                showNextImage();
            }
        });
    }

    // Method to show the previous image
    private void showPreviousImage() {
        // Check if there is a previous image
        if (currentImageIndex > 0) {
            // Decrement the index and update the ImageView
            currentImageIndex--;
            updateImageView();
        }
    }

    // Method to show the next image
    private void showNextImage() {
        // Check if there is a next image
        if (currentImageIndex < imageResources.length - 1) {
            // Increment the index and update the ImageView
            currentImageIndex++;
            updateImageView();
        }
    }

    // Method to update the ImageView with the current image
    private void updateImageView() {
        // Set the image resource for the ImageView based on the current index
        imageView.setImageResource(imageResources[currentImageIndex]);
    }
}
