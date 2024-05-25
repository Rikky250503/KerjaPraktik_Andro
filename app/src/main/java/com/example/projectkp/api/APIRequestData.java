package com.example.projectkp.api;

import com.example.projectkp.response.LoginResponse;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TambahCustomerResponse;
import com.example.projectkp.response.TambahBKResponse;
import com.example.projectkp.response.TampilCustomerResponse;
import com.example.projectkp.response.TampilKeluarResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    //API Barang
    @GET("api/api/barang")
    Call<TampilBarangResponse> ardRetrieveBarang();

    //API Customer
    @POST("api/api/customer/daftar")
    Call<TambahCustomerResponse> ardTambahCustomer(
            @Field("nama_pemesan") String nama_pemesan,
            @Field("alamat_pemesan") String alamat_pemesan,
            @Field("no_hp_pemesan") String no_hp_pemesan
    );
    @GET("api/api/customer")
    Call<TampilCustomerResponse> ardRetrieveCustomer();

    //API Barang Keluar
    @GET("api/api/barangkeluar/list")
    Call<TampilKeluarResponse> ardKeluar();
    @FormUrlEncoded
    @POST("api/api/barangkeluar/daftar")
    Call<TambahBKResponse> ardTambahBK(
            @Field("tanggal_keluar") String tanggal_keluar,
            @Field("nomor_invoice_keluar") String nomor_invoice_keluar,
            @Field("id_customer") String id_customer
    );

    //API useradmin
    @FormUrlEncoded
    @POST("api/api/useradmin/login")
    Call<LoginResponse> ardlogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
