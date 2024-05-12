package com.example.projectkp.api;

import com.example.projectkp.response.PengirimanResponse;

import retrofit2.Call;
import retrofit2.http.GET;
public interface APIRequestData {
    @GET("api/api/status")
    Call<PengirimanResponse> ardRetrieve();
}
