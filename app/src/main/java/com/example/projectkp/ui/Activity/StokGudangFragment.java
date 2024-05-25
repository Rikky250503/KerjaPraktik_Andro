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
import android.widget.Toast;

import com.example.projectkp.R;
import com.example.projectkp.adapter.BarangAdapter;
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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    RecyclerView rv_stok_gudang;

    private ImageView ivLogoutGudang;
    private BarangAdapter adBarang;
    private RecyclerView.LayoutManager lmBarang;
    private List<DataBarang> ListBarang = new ArrayList<>();

//    public StokGudangFragment() {
//        // Required empty public constructor
//    }


//    public static StokGudangFragment newInstance(String param1, String param2) {
//        StokGudangFragment fragment = new StokGudangFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_stok_gudang = view.findViewById(R.id.rv_stok_gudang);
        ivLogoutGudang = view.findViewById(R.id.iv_logout_stok_barang);

        lmBarang = new LinearLayoutManager(requireContext());
        rv_stok_gudang.setLayoutManager(lmBarang);
        adBarang = new BarangAdapter(requireContext(), ListBarang);
        rv_stok_gudang.setAdapter(adBarang);

        retrieveBarang();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userJson = sharedPreferences.getString("Jabatan", null);
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
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilBarangResponse> proses = ARD.ardRetrieveBarang();

        proses.enqueue(new Callback<TampilBarangResponse>() {
            @Override
            public void onResponse(Call<TampilBarangResponse> call, Response<TampilBarangResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListBarang = response.body().getDataBarang();

                    for (DataBarang item: ListBarang)
                    {
                        Log.d("API DATA","Nama Barang:"+ item.getNama_barang());
                        Log.d("API DATA","Jumlah:"+item.getKuantitas());
                    }
                    adBarang.setData(ListBarang);
                }
            }

            @Override
            public void onFailure(Call<TampilBarangResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
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