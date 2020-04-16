package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Description_activity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    ImageView banner, poster;
    TextView movie_name,description,rating_of_movie,release_year;
    Button movie_play,trailer_play;
    RecyclerView mRecyclerViewdescription;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ImageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_activity);

        //for the top left back button
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        banner = findViewById(R.id.movie_banner);
        poster = findViewById(R.id.movie_poster_description);
        movie_name = findViewById(R.id.movie_name_description);
        description = findViewById(R.id.movie_description_description);
        movie_play = findViewById(R.id.play_movie_description);
        trailer_play = findViewById(R.id.movie_trailer_description);
        rating_of_movie = findViewById(R.id.rating_description);
        release_year = findViewById(R.id.release_description);

        mRecyclerViewdescription = findViewById(R.id.recycler_view_description);
        //for getting the movie name
        movie_name.setText(getIntent().getExtras().getString("movie_name"));
        //for retreiving movie url
        final String videourl = getIntent().getExtras().getString("message");
        //for retreiving trailer_url
        final String trailer_url = getIntent().getExtras().getString("trailer");
        //for retreiving poster url
        String poster_url = getIntent().getExtras().getString("poster");
        //for loading the banner url
        String banner_url = getIntent().getExtras().getString("banner");

        Picasso.get()
                .load(banner_url)
                .fit()
                .centerCrop()
                .into(banner);
        //for loading description
        description.setText(getIntent().getExtras().getString("description"));

        //for loading description
        rating_of_movie.setText(""+(int) getIntent().getExtras().getFloat("rating")+"/5");

        //release date of the movie
        release_year.setText(""+(int) getIntent().getExtras().getFloat("release"));
        //picasso for loading banner



        //picasso for loading poster
        Picasso.get()
                .load(poster_url)
                .fit()
                .centerInside()
                .into(poster);

        movie_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play_movie = new Intent(Description_activity.this,video_player_activity.class);
                String name_of_movie = movie_name.getText().toString();
                play_movie.putExtra("movie_name",name_of_movie);
                play_movie.putExtra("message",videourl);
                startActivity(play_movie);
            }
        });

        trailer_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent play_movie = new Intent(Description_activity.this,video_player_activity.class);
                String name_of_movie = movie_name.getText().toString();
                play_movie.putExtra("movie_name",name_of_movie);
                play_movie.putExtra("message",trailer_url);
                startActivity(play_movie);
            }
        });



        mRecyclerViewdescription.setHasFixedSize(true);
        //to reverese just replace the false with true and all the line below
        mRecyclerViewdescription.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));


        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("all");




        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(Description_activity.this, mUploads);

                mRecyclerViewdescription.setAdapter(mAdapter);

                mRecyclerViewdescription.scrollToPosition(mRecyclerViewdescription.getAdapter().getItemCount()-1);
                mAdapter.setOnItemClickListener(Description_activity.this);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Description_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

    }

    @Override
    public void OnItemClick(String video_url,String movie_name,String poster_url, String banner_url, String Description,Float rating,Float release_year,String trailer) {
        Intent vid = new Intent(Description_activity.this,Description_activity.class);
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
