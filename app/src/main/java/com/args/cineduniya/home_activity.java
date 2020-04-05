package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class home_activity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;

    private DatabaseReference mDatabaseRef;
    private List<Upload> mUploads;

    private RecyclerView mRecyclerView2;
    private ImageAdapter mAdapter2;
    private List<Upload> mUploads2;
    private DatabaseReference mDatabaseRef2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activity);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView2 = findViewById(R.id.recycler_view_classic);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        //2nd recycler view
        mRecyclerView2.setHasFixedSize(true);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));




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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }


        });

        mUploads2 = new ArrayList<>();
        mDatabaseRef2 = FirebaseDatabase.getInstance().getReference("classic");

        mDatabaseRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    Upload upload = postSnapshot.getValue(Upload.class);
                    mUploads2.add(upload);
                }

                mAdapter2 = new ImageAdapter(home_activity.this, mUploads2);

                mRecyclerView2.setAdapter(mAdapter2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(home_activity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

            case R.id.extras:
                Intent up = new Intent(home_activity.this, upload_activity.class);
                startActivity(up);
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }

    @Override
    public void place_order(int position, String vid_url) {
        Toast.makeText(this, ""+vid_url, Toast.LENGTH_SHORT).show();
        Intent video = new Intent(home_activity.this,video_player_activity.class);
        startActivity(video);
    }

    @Override
    public void OnItemClick(int position) {
        Toast.makeText(this, ""+position, Toast.LENGTH_SHORT).show();
    }


}
