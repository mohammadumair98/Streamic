package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class search_activity extends AppCompatActivity implements ImageAdapter.OnItemClickListener{

    TextView home,category;
    EditText search_movies;
    TextView remove_after_search;
    ProgressBar search_progress;

    //for recycler view
    RecyclerView mRecyclerViewsearch;
    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;
    private ImageAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);

        //for topleft backbutton
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //for the top buttons
        home = findViewById(R.id.go_searchpage_home);
        category= findViewById(R.id.go_searchpage_category);

        //for functionality
        search_movies = findViewById(R.id.search_for_movie);
        search_progress = findViewById(R.id.search_progressbar);
        remove_after_search = findViewById(R.id.search_text_tobe_removed);
        mRecyclerViewsearch = findViewById(R.id.recycler_view_search);

        search_progress.setVisibility(View.VISIBLE);
        //for loading movies
        int number_column = 3;
        mRecyclerViewsearch.setHasFixedSize(true);
        mRecyclerViewsearch.setLayoutManager(new GridLayoutManager(this,number_column));
        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("all");



        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(search_activity.this, mUploads);
                mRecyclerViewsearch.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(search_activity.this);
                search_progress.setVisibility(View.GONE);
                remove_after_search.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {

            }
        });



            //when the top bar button is clicked
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home_page = new Intent(search_activity.this,home_activity.class);
                startActivity(home_page);
                finish();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category_page = new Intent(search_activity.this,Categories_activity.class);
                startActivity(category_page);
                finish();
            }
        });

        //for filtering movies
        search_movies.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {

                ArrayList<Upload> filteredList = new ArrayList<>();
                for(Upload item: mUploads){
                    if(item.getMovie_name().toLowerCase().contains(s.toString().toLowerCase()))
                    {
                        filteredList.add(item);
                    }
                }
                mAdapter.filerList(filteredList);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent home = new Intent(search_activity.this,home_activity.class);
        startActivity(home);
        finish();
        super.onBackPressed();

    }

    @Override
    public void OnItemClick(String video_url,String movie_name,String poster_url, String banner_url, String Description,Float rating,Float release_year,String trailer) {
        Intent vid = new Intent(search_activity.this,Description_activity.class);
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
}
