package com.example.projectkp.api;

import com.example.projectkp.response.LoginResponse;
import com.example.projectkp.response.TambahBMResponse;
import com.example.projectkp.response.TambahDBKResponse;
import com.example.projectkp.response.TambahDBMResponse;
import com.example.projectkp.response.TambahSupplierResponse;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TambahCustomerResponse;
import com.example.projectkp.response.TambahBKResponse;
import com.example.projectkp.response.TampilCustomerResponse;
import com.example.projectkp.response.TampilKeluarResponse;
import com.example.projectkp.response.TampilMasukResponse;
import com.example.projectkp.response.TampilSupplierResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequestData {

    //API Barang
    @GET("api/api/barang")
    Call<TampilBarangResponse> ardRetrieveBarang(
            @Header("Authorization") String token
    );

    //API Customer
    @FormUrlEncoded
    @POST("api/api/customer/daftar")
    Call<TambahCustomerResponse> ardTambahCustomer(
            @Field("nama_pemesan") String nama_pemesan,
            @Field("alamat_pemesan") String alamat_pemesan,
            @Field("no_hp_pemesan") String no_hp_pemesan,
            @Header("Authorization") String token
    );
    @FormUrlEncoded
    @POST("api/api/supplier/daftar")
    Call<TambahSupplierResponse> ardTambahSupplier(
            @Field("nama_supplier") String nama_supplier,
            @Field("no_hp") String no_hp,
            @Field("alamat") String alamat,
            @Header("Authorization") String token
    );
    @GET("api/api/customer")
    Call<TampilCustomerResponse> ardRetrieveCustomer(
            @Header("Authorization") String token
    );
    @GET("api/api/supplier")
    Call<TampilSupplierResponse> ardRetrieveSupplier(
            @Header("Authorization") String token
    );

    //API Barang Keluar
    @GET("api/api/barangkeluar/list")
    Call<TampilKeluarResponse> ardKeluar(
            @Header("Authorization") String token
    );

    @GET("api/api/barangkeluar/list/{tanggal}")
    Call<TampilKeluarResponse> ardKeluarGudang(
            @Path("tanggal") String tanggal,
            @Header("Authorization") String token
    );

    @FormUrlEncoded
    @POST("api/api/barangkeluar/daftar")
    Call<TambahBKResponse> ardTambahBK(
            @Field("tanggal_keluar") String tanggal_keluar,
            @Field("nomor_invoice_keluar") String nomor_invoice_keluar,
            @Field("id_customer") String id_customer,
            @Header("Authorization") String token
    );
    //API DETAIL BARANG KELUAR
    @FormUrlEncoded
    @POST("api/api/detailbarangkeluar/daftar")
    Call<TambahDBKResponse> ardTambahBKDetail(
            @Field("id_barang_keluar") String id_barang_keluar,
            @Field("id_barang") String id_barang,
            @Field("kuantitas") Integer kuantitas,
            @Field("harga_satuan_keluar") Double harga_satuan_keluar,
            @Header("Authorization") String token
    );
    @POST("api/api/detailbarangkeluar/detail/{id}")
    Call<TampilKeluarResponse> ardTampilDBK(
            @Path("id") String id,
            @Header("Authorization") String token
    );


    // API Barang Masuk
    @GET("api/api/barangmasuk/list")
    Call<TampilMasukResponse> ardMasuk(
            @Header("Authorization") String token
    );
    @FormUrlEncoded
    @POST("api/api/barangmasuk/daftar")
    Call<TambahBMResponse> ardTambahBM(
            @Field("nomor_invoice_masuk") String nomor_invoice_masuk,
            @Field("id_supplier") String id_supplier,
            @Header("Authorization") String token
    );
    @FormUrlEncoded
    @POST("api/api/detailbarangmasuk/daftar")
    Call<TambahDBMResponse> ardTambahBMDetail(
            @Field("id_barang_masuk") String id_barang_masuk,
            @Field("id_barang") String id_barang,
            @Field("kuantitas") Integer kuantitas,
            @Field("harga_satuan") Double harga_satuan,
            @Header("Authorization") String token
    );
    @POST("api/api/detailbarangmasuk/detail/{id}")
    Call<TampilMasukResponse> ardTampilDBM(
            @Path("id") String id,
            @Header("Authorization") String token
    );

    //API useradmin
    @FormUrlEncoded
    @POST("api/api/useradmin/login")
    Call<LoginResponse> ardlogin(
            @Field("username") String username,
            @Field("password") String password
    );

}
