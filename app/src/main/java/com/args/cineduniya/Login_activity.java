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
import com.google.firebase.auth.FirebaseUser;

public class Login_activity extends AppCompatActivity {

    EditText email,password;
    Button login_btn;
    TextView new_acc,forgot_password;
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mfirebaselistener;
    FirebaseUser user;
    ProgressBar progressBar_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_button);

        progressBar_login = findViewById(R.id.progress_login);

        forgot_password= findViewById(R.id.forgot_password);
        new_acc= findViewById(R.id.new_account);

        mfirebaseAuth = FirebaseAuth.getInstance();

        progressBar_login.setVisibility(View.GONE);

        mfirebaselistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = mfirebaseAuth.getCurrentUser();
                if(user!= null){
                    Intent loggedin = new Intent(Login_activity.this,home_activity.class);
                    startActivity(loggedin);
                    finish();
                }
            }
        };

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = email.getText().toString();
                String pwd = password.getText().toString();
                progressBar_login .setVisibility(View.VISIBLE);

                if(emailID.isEmpty()){
                    progressBar_login.setVisibility(View.GONE);
                    email.setError("Email must be filled");
                    email.requestFocus();
                }
                else if(pwd.isEmpty()){
                    progressBar_login.setVisibility(View.GONE);
                    password.setError("password must be filled");
                    password.requestFocus();
                }
                else if(emailID.isEmpty() && pwd.isEmpty()){
                    progressBar_login.setVisibility(View.GONE);
                    Toast.makeText(Login_activity.this,"Both fields are empty !",Toast.LENGTH_SHORT).show();

                }
                else if(!(emailID.isEmpty() && pwd.isEmpty())){
                    mfirebaseAuth.signInWithEmailAndPassword(emailID,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressBar_login.setVisibility(View.GONE);
                                Toast.makeText(Login_activity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                                Intent home= new Intent(Login_activity.this, home_activity.class);
                                startActivity(home);
                                finish();
                            }
                            else{
                                progressBar_login.setVisibility(View.GONE);
                                Toast.makeText(Login_activity.this, "Error occurred.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

        new_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newaccount = new Intent(Login_activity.this, SignUp_activity.class);
                startActivity(newaccount);
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgot = new Intent(Login_activity.this,forgot_password_activity.class);
                startActivity(forgot);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mfirebaseAuth.addAuthStateListener(mfirebaselistener);
    }
}


