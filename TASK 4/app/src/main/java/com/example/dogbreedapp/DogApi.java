package com.example.dogbreedapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DogApi {
    @GET("api/breeds/list/all")
    Call<Root> getRoot();
}
