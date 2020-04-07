package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Categories_activity extends AppCompatActivity {

    ImageView kannada,kodava;
    TextView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_activity);


        kannada = findViewById(R.id.kannada_category);
        kodava = findViewById(R.id.kodava_category);
        //to go back home
        goback=findViewById(R.id.gohomecategory);

        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/en/5/53/Parasite_%282019_film%29.png")
                .fit()
                .centerInside()
                .into(kannada);

        Picasso.get()
                .load("https://upload.wikimedia.org/wikipedia/en/5/53/Parasite_%282019_film%29.png")
                .fit()
                .centerInside()
                .into(kodava);




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
                String cat = "all";
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
