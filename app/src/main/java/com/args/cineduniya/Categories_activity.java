package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Categories_activity extends AppCompatActivity {

    ImageView kannada,kodava,all,other;
    TextView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_activity);


        kannada = findViewById(R.id.kannada_category);
        kodava = findViewById(R.id.kodava_category);
        all = findViewById(R.id.all_category);
        other = findViewById(R.id.other_category);
        //to go back home
        goback=findViewById(R.id.gohomecategory);

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
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2FAll-1.jpg?alt=media&token=94cfec6e-9f57-470b-80bd-d69748cc45d2")
                .fit()
                .centerInside()
                .into(all);

        Picasso.get()
                .load("https://firebasestorage.googleapis.com/v0/b/cine-duniya.appspot.com/o/TYPES%20CATEGORY%2Fother.jpg?alt=media&token=6e3b5c11-08c5-449c-ad75-0c95dc77b68c")
                .fit()
                .centerInside()
                .into(other);




        kannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kannadaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "kannada";
                kannadaintent.putExtra("category",cat);
                startActivity(kannadaintent);
            }
        });

        kodava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kodavaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "kodava";
                kodavaintent.putExtra("category",cat);
                startActivity(kodavaintent);
            }
        });

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kodavaintent = new Intent(Categories_activity.this,kannada_activity.class);
                String cat = "all";
                kodavaintent.putExtra("category",cat);
                startActivity(kodavaintent);
            }
        });

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent home = new Intent(Categories_activity.this,home_activity.class);
        startActivity(home);
        finish();
    }
}
