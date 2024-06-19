package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.TransaksiGudangAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;

import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilKeluarResponse;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiGudangFragment extends Fragment {
    RecyclerView rv_transaksi_gudang;
    String tanggal_hari_ini = null;

    private ProgressBar pbTransaksi;
    private ImageView ivLogoutTransaksiGudang;
    private TransaksiGudangAdapter adTransaksi;
    private RecyclerView.LayoutManager lmTransaksi;
    private List<DataTampilKeluar> ListTransaksi = new ArrayList<>();

    public TransaksiGudangFragment() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
        }

        // Memformat tanggal
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            tanggal_hari_ini = today.format(formatter);
        }
                    Log.d("Tanggal_Hari ini", tanggal_hari_ini);

        rv_transaksi_gudang = view.findViewById(R.id.rv_transaksi_gudang);

        pbTransaksi = view.findViewById(R.id.pb_transaksi_gudang);

        lmTransaksi = new LinearLayoutManager(getContext());
        rv_transaksi_gudang.setLayoutManager(lmTransaksi);
        adTransaksi = new TransaksiGudangAdapter(requireContext(),ListTransaksi);
        rv_transaksi_gudang.setAdapter(adTransaksi);


        ivLogoutTransaksiGudang = view.findViewById(R.id.iv_logout_transaksi_gudang);

        retrieveBarangKeluarHrini();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("Jabatan", null);

        ivLogoutTransaksiGudang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("Token");
                editor.remove("Jabatan");
                editor.apply();
                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }
    public void retrieveBarangKeluarHrini(){
        pbTransaksi.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardKeluarGudang(tanggal_hari_ini);

        // Log URL lengkap yang digunakan oleh Retrofit
        String fullUrl = RetroServer.alamat_server + "endpoint_yang_digunakan_dalam_API/" + tanggal_hari_ini;
        Log.d("Full_API_URL", fullUrl);

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {

                // Log response code
                Log.d("API_Response_Code", String.valueOf(response.code()));

                if (response.isSuccessful() && response.body() != null)
                {
                    ListTransaksi = response.body().getData();
                    adTransaksi.setData(ListTransaksi);

                    // Log untuk memeriksa data yang diterima
                    Log.d("Data_API", ListTransaksi.toString());
                }else {
                    // Log jika response tidak berhasil atau body null
                    Log.d("API_Error", "Response tidak berhasil atau body null");

                    if (response.body() == null) {
                        Log.d("API_Error_Body", "Response body is null");
                    } else {
                        Log.d("API_Error_Body", response.body().toString());
                    }

                    // Log error message jika ada
                    if (response.errorBody() != null) {
                        try {
                            Log.d("API_Error_Message", response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                pbTransaksi.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TampilKeluarResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbTransaksi.setVisibility(View.GONE);
                //Log untuk kesalahan
                Log.d("API_Failure", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaksi_gudang, container, false);
    }
}