package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class kannada_activity extends AppCompatActivity implements  ImageAdapter.OnItemClickListener {
    RecyclerView mRecyclerViewdescription;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ImageAdapter mAdapter;
    LinearLayout progress_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kannada_activity);

        //for the top left back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecyclerViewdescription = findViewById(R.id.recycler_view_kannada);
        progress_layout = findViewById(R.id.linear_progress);

        progress_layout.setVisibility(View.VISIBLE);

        int number_column = 3;
        mRecyclerViewdescription.setHasFixedSize(true);
        mRecyclerViewdescription.setLayoutManager(new GridLayoutManager(this,number_column));

        mUploads = new ArrayList<>();
        String category = getIntent().getExtras().getString("category");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference(""+category);




        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(kannada_activity.this, mUploads);

                mRecyclerViewdescription.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(kannada_activity.this);

                progress_layout.setVisibility(View.GONE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(kannada_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });
    }

    @Override
    public void OnItemClick(String video_url,String movie_name,String poster_url, String banner_url, String Description,Float rating,Float release_year,String trailer)
    {
        Intent vid = new Intent(kannada_activity.this,Description_activity.class);
        String message = video_url;
        vid.putExtra("trailer",trailer);
        vid.putExtra("release",release_year);
        vid.putExtra("rating",rating);
        vid.putExtra("banner",banner_url);
        vid.putExtra("poster",poster_url);
        vid.putExtra("message",message);
        vid.putExtra("description",Description);
        String name_of_movie = movie_name;
        vid.putExtra("movie_name",name_of_movie);
        startActivity(vid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
