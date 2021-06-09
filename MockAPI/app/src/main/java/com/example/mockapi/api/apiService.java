package com.example.mockapi.api;

import com.example.mockapi.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd hh:mm:ss").create();

    // link api: https://60b0a77a1f26610017ffecfc.mockapi.io/api/v1/user
    apiService ApiService = new Retrofit.Builder()
            .baseUrl("https://60b0a77a1f26610017ffecfc.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(apiService.class);

    @GET("api/v1")
    Call<User> getUser(@Query("access_key") String access_key);
}
