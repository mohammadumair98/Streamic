package com.args.cineduniya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password_activity extends AppCompatActivity {
    EditText email;
    Button forgot_btn;
    TextView login;
    FirebaseAuth mfirebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_activity);

        email = findViewById(R.id.forgot_email);
        forgot_btn = findViewById(R.id.forgot_button);
        login = findViewById(R.id.login_forgot);
        progressBar = findViewById(R.id.progress_forgot);

        mfirebaseAuth= FirebaseAuth.getInstance();

        progressBar.setVisibility(View.GONE);

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id = email.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                if(email_id.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    email.setError("email is required");
                    email.requestFocus();
                }
                else if (!(email_id.isEmpty())){
                    mfirebaseAuth.sendPasswordResetEmail(email_id).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                email.setText("");
                                Toast.makeText(forgot_password_activity.this, "password reset link sent to your email", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }
                            else
                            {
                                Toast.makeText(forgot_password_activity.this, "Account not found", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    });
                }
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(forgot_password_activity.this, Login_activity.class);
                startActivity(login);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}

