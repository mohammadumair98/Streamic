package com.args.cineduniya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Account_activity extends AppCompatActivity {

    FirebaseAuth mfirebaseauth;
    Button logout;
    TextView user_name;
    FirebaseUser mfireuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_activity);

        logout = findViewById(R.id.logout_button);
        user_name = findViewById(R.id.usernamedisplay);
        mfirebaseauth = FirebaseAuth.getInstance();
        mfireuser = mfirebaseauth.getCurrentUser();
        String user_email = mfireuser.getEmail();
        user_name.setText(user_email);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mfirebaseauth.signOut();
                Intent logout = new Intent(Account_activity.this, Login_activity.class);
                startActivity(logout);
                finish();
            }
        });
    }
}
