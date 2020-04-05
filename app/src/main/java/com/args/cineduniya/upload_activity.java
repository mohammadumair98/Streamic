package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class upload_activity extends AppCompatActivity {

    EditText url;
    Button upload_btn;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Upload upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_activity);

        url = findViewById(R.id.editText_url);
        upload_btn = findViewById(R.id.button);

        upload = new Upload();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("classic");


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url_image = url.toString();

                upload.setmImageUrl(url_image);

                databaseReference.push().setValue(upload);

            }
        });
    }
}
