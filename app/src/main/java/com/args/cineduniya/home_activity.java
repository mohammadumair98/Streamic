package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class home_activity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {


    private ImageAdapter mAdapter;
    private ImageAdapter mAdapter2;
    private ImageAdapter mAdapter3;
    private ImageAdapter mAdapter4;

    private List<Upload> mUploads;
    private List<Upload> mUploads2;
    private List<Upload> mUploads3;
    private List<Upload> mUploads4;

    private DatabaseReference mDatabaseRef;
    private DatabaseReference mDatabaseRef2;
    private DatabaseReference mDatabaseRef3;
    private DatabaseReference mDatabaseRef4;

    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView2;
    private RecyclerView mRecyclerView3;
    private RecyclerView mRecyclerView4;


    TextView categories,search;
    ProgressBar progressBar;



    //for slide show
    private ImageView imageView1,imageView2,imageView3,imageView4;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);

        //for recyclerview
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView2 = findViewById(R.id.recycler_view_classic);
        mRecyclerView3 = findViewById(R.id.recycler_view_all);
        mRecyclerView4 = findViewById(R.id.recycler_view_kodava);

        //for slideshow
        imageView1 = findViewById(R.id.viewflipperImage1);
        imageView2 = findViewById(R.id.viewflipperImage2);
        imageView3 = findViewById(R.id.viewflipperImage3);
        imageView4 = findViewById(R.id.viewflipperImage4);


        //for categories tab
        categories = findViewById(R.id.go_categories);
        search = findViewById(R.id.go_search);

        //progressbar
        progressBar= findViewById(R.id.progressBarhome);


        progressBar.setVisibility(View.VISIBLE);

        //for slideshow updation
        update();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,true));

        //2nd recycler view
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //3rd recycler view
        mRecyclerView3.setHasFixedSize(true);
        mRecyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        //for 4th recycler view
        mRecyclerView4.setHasFixedSize(true);
        mRecyclerView4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));



        mUploads = new ArrayList<>();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("latest");




        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads.add(upload);
                }

                mAdapter = new ImageAdapter(home_activity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
                mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount()-1);

                mAdapter.setOnItemClickListener(home_activity.this);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


        //2nd list
        mUploads2 = new ArrayList<>();
        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference("hollywood");

        mDatabaseRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads2.add(upload);
                }

                mAdapter2 = new ImageAdapter(home_activity.this, mUploads2);

                mRecyclerView2.setAdapter(mAdapter2);

                mAdapter2.setOnItemClickListener(home_activity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


        //3dr all section
        mUploads3 = new ArrayList<>();
        mDatabaseRef3 = FirebaseDatabase.getInstance().getReference("all");

        mDatabaseRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads3.add(upload);
                }

                mAdapter3 = new ImageAdapter(home_activity.this, mUploads3);

                mRecyclerView3.setAdapter(mAdapter3);

                mAdapter3.setOnItemClickListener(home_activity.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


        //kodava recycler view
        mUploads4 = new ArrayList<>();
        mDatabaseRef4 = FirebaseDatabase.getInstance().getReference("horror");

        mDatabaseRef4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads4.add(upload);
                }

                mAdapter4 = new ImageAdapter(home_activity.this, mUploads4);

                mRecyclerView4.setAdapter(mAdapter4);

                mAdapter4.setOnItemClickListener(home_activity.this);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });


        //for going to categories tab
        categories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category = new Intent(home_activity.this,Categories_activity.class);
                startActivity(category);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent seach_all_movies = new Intent(home_activity.this,search_activity.class);
                startActivity(seach_all_movies);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override// to display menu items
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override//when menu items are clicked
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.account:
                Intent acc = new Intent(home_activity.this, Account_activity.class);
                startActivity(acc);
                return true;


            case R.id.favorite://when about is clicked
                Intent about = new Intent(home_activity.this, Favourite_activity.class);
                startActivity(about);
                return true;

            case R.id.privacy_policy://when about is clicked
                Intent pripol = new Intent(home_activity.this, PrivacyPolicy_activity.class);
                startActivity(pripol);
                return true;

            case R.id.about_us:
                Intent about_2 = new Intent(home_activity.this,About_activity.class);
                startActivity(about_2);
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }




    //for slideshow
    public void update(){
        DocumentReference user = db.collection("FILES").document("images");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {


            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    //image1
                    StringBuilder image1 = new StringBuilder("");
                    image1.append(doc.get("image1"));
                    String imageurl1 = image1.toString();
                    Glide.with(home_activity.this).load(imageurl1).into(imageView1);

                    //image 2
                    StringBuilder image2 = new StringBuilder("");
                    image2.append(doc.get("image2"));
                    String imageurl2 = image2.toString();
                    Glide.with(home_activity.this).load(imageurl2).into(imageView2);

                    //image 3
                    StringBuilder image3 = new StringBuilder("");
                    image3.append(doc.get("image3"));
                    String imageurl3 = image3.toString();
                    Glide.with(home_activity.this).load(imageurl3).into(imageView3);

                    //image 4
                    StringBuilder image4 = new StringBuilder("");
                    image4.append(doc.get("image4"));
                    String imageurl4 = image4.toString();
                    Glide.with(home_activity.this).load(imageurl4).into(imageView4);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(home_activity.this, "Failed"+e, Toast.LENGTH_SHORT).show();
            }
        });
    }


    //when an movie is clicked
    @Override
    public void OnItemClick(String video_url,String movie_name,String poster_url, String banner_url, String Description,Float rating,Float release_year, String trailer_url) {
        //Toast.makeText(this, ""+trailer_url, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, ""+release_year, Toast.LENGTH_SHORT).show();
        Intent vid = new Intent(home_activity.this,Description_activity.class);
        String message = video_url;
        String trailer = trailer_url;
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
