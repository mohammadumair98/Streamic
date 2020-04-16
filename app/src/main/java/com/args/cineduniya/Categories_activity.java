package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Categories_activity extends AppCompatActivity {

    ImageView kannada,kodava,all,other,short_movie,originals;
    TextView goback,gosearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_activity);


        //for the top left back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        kannada = findViewById(R.id.kannada_category);
        kodava = findViewById(R.id.kodava_category);
        all = findViewById(R.id.all_category);
        other = findViewById(R.id.other_category);
        short_movie = findViewById(R.id.short_movie_category);
        originals = findViewById(R.id.documentory_activity);
        //to go back home
        goback=findViewById(R.id.gohomecategory);
        gosearch = findViewById(R.id.gohomesearch);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2Fkannada%20(1).jpg?alt=media&token=9817abc2-940e-459f-8cdf-2565a8c1b731")
                .fit()
                .centerInside()
                .into(kannada);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2Fkodava%20(1).jpg?alt=media&token=160bb998-cee7-4e7c-91b9-1d2355f6a43a")
                .fit()
                .centerInside()
                .into(kodava);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2FSHORT%20FILMS.jpg?alt=media&token=05fd0827-7940-4b5d-a258-4625236b6c92")
                .fit()
                .centerInside()
                .into(short_movie);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2FORIGINALS.jpg?alt=media&token=4c100c54-1ecb-45d5-9215-8ba2ab55a902")
                .fit()
                .centerInside()
                .into(originals);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2FALL.jpg?alt=media&token=58786c93-4a64-4938-a6a8-53da3b4028d9")
                .fit()
                .centerInside()
                .into(all);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2Fother.jpg?alt=media&token=6e3b5c11-08c5-449c-ad75-0c95dc77b68c")
                .fit()
                .centerInside()
                .into(other);




        //for kannada movies
        kannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kannadaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "kannada";
                kannadaintent.putExtra("category",cat);
                startActivity(kannadaintent);
            }
        });

        //for kodava movies
        kodava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kodavaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "kodava";
                kodavaintent.putExtra("category",cat);
                startActivity(kodavaintent);
            }
        });

        //for short movie
        short_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent short_intent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "short";
                short_intent.putExtra("category",cat);
                startActivity(short_intent);
            }
        });

        //for originals
        originals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent documentory_intent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "originals";
                documentory_intent.putExtra("category",cat);
                startActivity(documentory_intent);
            }
        });

        //for all movies
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kodavaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "all";
                kodavaintent.putExtra("category",cat);
                startActivity(kodavaintent);
            }
        });

        //for other langauge movies
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kodavaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "other";
                kodavaintent.putExtra("category",cat);
                startActivity(kodavaintent);
            }
        });

        //when go home button is clicked
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Categories_activity.this,home_activity.class);
                startActivity(home);
                finish();
            }
        });

        gosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(Categories_activity.this,search_activity.class);
                startActivity(search);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home = new Intent(Categories_activity.this,home_activity.class);
        startActivity(home);
        finish();
    }
}
