package com.example.mockapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mockapi.api.apiService;
import com.example.mockapi.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView text_ID, text_Name, text_Age;
    private Button btnCallAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_ID = findViewById(R.id.text_ID);
        text_Name = findViewById(R.id.text_Name);
        text_Age = findViewById(R.id.text_Age);
        btnCallAPI = (Button)findViewById(R.id.btnCallAPI);

        btnCallAPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCallAPI();
            }
        });
    }

// link api: https://60b0a77a1f26610017ffecfc.mockapi.io/api/v1/user
    private void clickCallAPI(){
        apiService.ApiService.getUser("user").enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this,"Call api successfull!",Toast.LENGTH_SHORT).show();
                User user = response.body();
                System.out.println(user);
                if(user != null) {
                    text_ID.setText(user.getId());
                    text_Name.setText(user.getName());
                    text_Age.setText(user.getAge());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Call api error",Toast.LENGTH_SHORT).show();
            }
        });
    }
}