package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class SignInActivity extends AppCompatActivity {
    private Button btnRegister_in;
    private Button btnSignIn_acti;
    private EditText textEmail;
    private EditText textPassword;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_activity);

        auth = FirebaseAuth.getInstance();

        btnRegister_in = (Button)findViewById(R.id.btnRegister_in);
        btnSignIn_acti = (Button)findViewById(R.id.btnSignIn_acti);
        textEmail = findViewById(R.id.textEmail);
        textPassword = findViewById(R.id.textPassword);

        // event btn
        btnSignIn_acti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                final String password = textPassword.getText().toString();


                //check empty
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(SignInActivity.this, "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(SignInActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Authentication user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        textPassword.setError("Password very short!Please input more than 6 digits");
                                    } else {
                                        Toast.makeText(SignInActivity.this, "Authentication failed, please check email or password again", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                Intent intent = new Intent(SignInActivity.this, EmotionActivity.class);
                startActivity(intent);
            }
        });

        btnRegister_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
