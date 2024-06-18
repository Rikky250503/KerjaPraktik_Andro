package com.example.projectkp.ui.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.BarangKeluarPenjualanAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilKeluarResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesananPenjualanFragment extends Fragment {

//    ImageView ivLogoutPesananPenjualanFragment;

    RecyclerView rv_pesanan_penjualan;
    private BarangKeluarPenjualanAdapter adBarangKeluar;
    private RecyclerView.LayoutManager lmBarang;
    private List<DataTampilKeluar> ListBarangKeluar = new ArrayList<>();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_pesanan_penjualan= view.findViewById(R.id.rv_pesanan);

        lmBarang = new LinearLayoutManager(requireContext());
        rv_pesanan_penjualan.setLayoutManager(lmBarang);
        adBarangKeluar = new BarangKeluarPenjualanAdapter(requireContext(), ListBarangKeluar);
        rv_pesanan_penjualan.setAdapter(adBarangKeluar);

        retrieveBarangKeluar();
    }

    public void retrieveBarangKeluar(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardKeluar();

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListBarangKeluar = response.body().getData();

                    adBarangKeluar.setData(ListBarangKeluar);
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

        return inflater.inflate(R.layout.fragment_pesanan_penjualan, container, false);
    }
}