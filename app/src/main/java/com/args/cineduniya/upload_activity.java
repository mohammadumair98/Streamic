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
    EditText vid_url;
    Button upload_btn;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    FirebaseDatabase database;
    Upload upload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_activity);

        url = findViewById(R.id.editText_url);
        vid_url = findViewById(R.id.editText_vid_url);
        upload_btn = findViewById(R.id.button);

        upload = new Upload();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("latest");
        databaseReference1 = FirebaseDatabase.getInstance().getReference().child("all");


        upload_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url_image = url.toString();
                String vidio_url = vid_url.toString();

                upload.setmImageUrl(url_image);
                upload.setmName(vidio_url);

                databaseReference.push().setValue(upload);
                databaseReference1.push().setValue(upload);


            }
        });
    }
}
