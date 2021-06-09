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
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private EditText textEmail_up, textPassword_up;
    private Button btnSignIn_up;
    private Button btnRegister_acti;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newaccount_activity);
        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        textEmail_up = findViewById(R.id.textEmail_up);
        textPassword_up = findViewById(R.id.textPassword_up);
        btnSignIn_up = (Button)findViewById(R.id.btnSignIn_up);
        btnRegister_acti = (Button)findViewById(R.id.btnRegister_acti);

        btnSignIn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        btnRegister_acti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail_up.getText().toString().trim();
                String password = textPassword_up.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter your email!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter your password!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(getApplicationContext(),"Password must bigger than 6 digits!",Toast.LENGTH_SHORT).show();
                    return;
                }

                //create user
                auth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(RegisterActivity.this, "CreateUserWithEmail:OnComplete" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if(!task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Authentication failed!"+task.getException(),Toast.LENGTH_SHORT).show();
                                }else{
                                    Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                                    startActivity(intent);
                                }
                        }
                    });
            }
        });
    }
}