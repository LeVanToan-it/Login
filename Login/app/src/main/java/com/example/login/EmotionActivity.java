package com.example.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class EmotionActivity extends AppCompatActivity {
    private ImageButton imageButtonFun, imageButtonNormal, imageButtonSad;
    private Button btnFinish;
    private FirebaseAuth auth;
    private int fun=0, normal=0, sad=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emotion_activity);
        auth = FirebaseAuth.getInstance();

        imageButtonFun = (ImageButton)findViewById(R.id.imgButton_Fun);
        imageButtonNormal = (ImageButton)findViewById(R.id.imgButton_normal);
        imageButtonSad = (ImageButton)findViewById(R.id.imgButton_sad);
        btnFinish = (Button)findViewById(R.id.btnFinish);

        imageButtonFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fun++;
                Toast.makeText(EmotionActivity.this,"FUN",Toast.LENGTH_SHORT).show();
            }
        });
        imageButtonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal++;
                Toast.makeText(EmotionActivity.this,"NORMAL",Toast.LENGTH_SHORT).show();
            }
        });
        imageButtonSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sad++;
                Toast.makeText(EmotionActivity.this,"SAD",Toast.LENGTH_SHORT).show();
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
