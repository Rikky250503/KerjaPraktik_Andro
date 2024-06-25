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
import com.example.projectkp.adapter.BarangGudangAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataBarang;

import com.example.projectkp.response.TampilBarangResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StokGudangFragment extends Fragment {

    RecyclerView rv_stok_gudang;

     private String token;
    private ProgressBar pb_stokGudang;
    private ImageView ivLogoutGudang;
    private BarangGudangAdapter adBarang;
    private RecyclerView.LayoutManager lmBarang;
    private List<DataBarang> ListBarang = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_stok_gudang = view.findViewById(R.id.rv_stok_gudang);
        ivLogoutGudang = view.findViewById(R.id.iv_logout_stok_barang);
        pb_stokGudang = view.findViewById(R.id.pb_stok_gudang);

        lmBarang = new LinearLayoutManager(requireContext());
        rv_stok_gudang.setLayoutManager(lmBarang);
        adBarang = new BarangGudangAdapter(requireContext(), ListBarang);
        rv_stok_gudang.setAdapter(adBarang);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);

        retrieveBarang();

        ivLogoutGudang.setOnClickListener(new View.OnClickListener() {
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

    public void retrieveBarang(){
        pb_stokGudang.setVisibility(View.VISIBLE);
        Log.d("TES", "Stock Gudang fragment: "+token);
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilBarangResponse> proses = ARD.ardRetrieveBarang("Bearer " + token);

        proses.enqueue(new Callback<TampilBarangResponse>() {
            @Override
            public void onResponse(Call<TampilBarangResponse> call, Response<TampilBarangResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListBarang = response.body().getDataBarang();

                    adBarang.setData(ListBarang);
                }
                pb_stokGudang.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<TampilBarangResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pb_stokGudang.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stok_gudang, container, false);
    }
}