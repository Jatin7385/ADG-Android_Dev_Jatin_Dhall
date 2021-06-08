package com.example.dogbreedapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImageApi {
    @GET("api/breed/{breed}/images/random")
    Call<Images> getImage(@Path("breed") String breed);
}
