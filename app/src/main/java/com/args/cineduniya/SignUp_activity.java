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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_activity extends AppCompatActivity {

    EditText email,password;
    Button signup_btn;
    TextView already_have_acc;
    FirebaseAuth mfirebaseAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_activity);

        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        signup_btn = findViewById(R.id.signup_button);
        already_have_acc = findViewById(R.id.have_account);
        progressBar = findViewById(R.id.progressBar);
        mfirebaseAuth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.GONE);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_id = email.getText().toString();
                String pwd = password.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                if(email_id.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    email.setError("email must be filled");
                    email.requestFocus();
                }
                else if(pwd.isEmpty())
                {
                    progressBar.setVisibility(View.GONE);
                    password.setError("password must be filled");

                }
                else if(email_id.isEmpty() && pwd.isEmpty()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUp_activity.this, "both fields must be filled", Toast.LENGTH_SHORT).show();

                }
                else if(!(email_id.isEmpty() && pwd.isEmpty())){
                    mfirebaseAuth.createUserWithEmailAndPassword(email_id,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignUp_activity.this, "user created successfully!", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(SignUp_activity.this,home_activity.class);
                                startActivity(home);
                                finish();

                            }else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(SignUp_activity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

        already_have_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent have_acc = new Intent(SignUp_activity.this,Login_activity.class);
                startActivity(have_acc);
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
