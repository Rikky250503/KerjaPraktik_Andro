package com.example.projectkp.ui.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.TransaksiGudangAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;

import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilKeluarResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiGudangFragment extends Fragment {
    RecyclerView rv_transaksi_gudang;
    String tanggal_hari_ini = null;

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

        lmTransaksi = new LinearLayoutManager(getContext());
        rv_transaksi_gudang.setLayoutManager(lmTransaksi);
        adTransaksi = new TransaksiGudangAdapter(requireContext(),ListTransaksi);
        rv_transaksi_gudang.setAdapter(adTransaksi);


        ivLogoutTransaksiGudang = view.findViewById(R.id.iv_logout_transaksi_gudang);

        retrieveBarangKeluarHrini();

    }
    public void retrieveBarangKeluarHrini(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardKeluarGudang(tanggal_hari_ini);

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListTransaksi = response.body().getData();

                    // Log untuk memeriksa data yang diterima
                    Log.d("Data_API", ListTransaksi.toString());

                    adTransaksi.setData(ListTransaksi);
                }
            }

            @Override
            public void onFailure(Call<TampilKeluarResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
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