package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public int SPLASH_TIME = 1000;

    DatabaseReference databaseReference_all;
    DatabaseReference  databaseReference_latest;
    DatabaseReference databaseReference_hollywood;
    DatabaseReference databaseReference_horror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        databaseReference_all = FirebaseDatabase.getInstance().getReference().child("all");
        databaseReference_latest = FirebaseDatabase.getInstance().getReference().child("latest");
        databaseReference_hollywood = FirebaseDatabase.getInstance().getReference().child("hollywood");
        databaseReference_horror = FirebaseDatabase.getInstance().getReference().child("horror");

        databaseReference_all.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this, Login_activity.class);
                startActivity(login);
                finish();
            }
        },SPLASH_TIME);
    }
}
