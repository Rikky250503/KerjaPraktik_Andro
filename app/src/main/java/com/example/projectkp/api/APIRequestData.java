package com.example.projectkp.api;

import com.example.projectkp.response.LoginResponse;
import com.example.projectkp.response.PengirimanDataBarangResponse;
import com.example.projectkp.response.TampilKeluarResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @GET("api/api/barang")
    Call<PengirimanDataBarangResponse> ardRetrieve();

    @GET("api/api/barangkeluar/list")
    Call<TampilKeluarResponse> ardKeluar();

    @FormUrlEncoded
    @POST("api/api/useradmin/login")
    Call<LoginResponse> ardlogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
