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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.PemesananSupplierAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataTampilMasuk;
import com.example.projectkp.response.TampilMasukResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemesananSupplierFragment extends Fragment {

    private ImageView ivTambahPemesananSupplier;
    private RecyclerView rv_PemesananSupplier;
    private String token;
    private ProgressBar pbRestock;
    private PemesananSupplierAdapter adPemesananSupplier;
    private RecyclerView.LayoutManager lmPemesananSupplier;
    private List<DataTampilMasuk> ListBarangMasuk = new ArrayList<>();

    public PemesananSupplierFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);

        rv_PemesananSupplier= view.findViewById(R.id.rv_pemesanan_supplier);

        pbRestock = view.findViewById(R.id.pb_restock);

        lmPemesananSupplier = new LinearLayoutManager(requireContext());
        rv_PemesananSupplier.setLayoutManager(lmPemesananSupplier);
        adPemesananSupplier = new PemesananSupplierAdapter(requireContext(), ListBarangMasuk);
        rv_PemesananSupplier.setAdapter(adPemesananSupplier);


        ivTambahPemesananSupplier = view.findViewById(R.id.iv_tambah_pemesanan_supplier);
        ivTambahPemesananSupplier.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireActivity(), RestockActivity.class);
                startActivity(intent);
            }
        });

        retrieveBarangMasuk();
    }

    public void retrieveBarangMasuk(){
        pbRestock.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilMasukResponse> proses = ARD.ardMasuk("Bearer " + token);

        proses.enqueue(new Callback<TampilMasukResponse>() {
            @Override
            public void onResponse(Call<TampilMasukResponse> call, Response<TampilMasukResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListBarangMasuk = response.body().getData();

                    adPemesananSupplier.setData(ListBarangMasuk);
                }
                pbRestock.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<TampilMasukResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbRestock.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pemesanan_supplier, container, false);
    }
}