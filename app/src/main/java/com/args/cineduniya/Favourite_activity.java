package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Favourite_activity extends AppCompatActivity {

    ImageView coming_soon_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_activity);

        //for the top left back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coming_soon_image = findViewById(R.id.coming_soon);

       Glide.with(Favourite_activity.this)
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/coming-soon-flat-circle-icon-vector-soon4128518.png?alt=media&token=b43dbf28-573d-4b2a-a14f-ac3deaa95008")
                .centerCrop()
                .into(coming_soon_image);

    }
}
