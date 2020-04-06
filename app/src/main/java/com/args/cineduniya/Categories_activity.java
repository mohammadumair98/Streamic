package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Categories_activity extends AppCompatActivity {

    TextView goback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_activity);

        //to go back home
        goback=findViewById(R.id.gohomecategory);


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
