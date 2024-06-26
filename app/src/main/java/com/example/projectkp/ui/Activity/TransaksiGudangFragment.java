package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.DialogInterface;
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
import com.example.projectkp.response.UpdateResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransaksiGudangFragment extends Fragment {
    RecyclerView rv_transaksi_gudang;
    private String tanggal_hari_ini = null, token,id_user,status;

    private ProgressBar pbTransaksi;
    private ImageView ivLogoutTransaksiGudang,ivDeleteAcc;
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

        rv_transaksi_gudang = view.findViewById(R.id.rv_transaksi_gudang);

        pbTransaksi = view.findViewById(R.id.pb_transaksi_gudang);

        lmTransaksi = new LinearLayoutManager(getContext());
        rv_transaksi_gudang.setLayoutManager(lmTransaksi);
        adTransaksi = new TransaksiGudangAdapter(requireContext(),ListTransaksi);
        rv_transaksi_gudang.setAdapter(adTransaksi);

        ivDeleteAcc = view.findViewById(R.id.iv_delete_acc_tg_fragment);
        ivLogoutTransaksiGudang = view.findViewById(R.id.iv_logout_transaksi_gudang);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);
        id_user = sharedPreferences.getString("id_user",null).substring(1,37);

        retrieveBarangKeluarHrini();

        ivLogoutTransaksiGudang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Apakah Anda yakin ingin Logout?")

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("Token");
                                editor.remove("Jabatan");
                                editor.remove("id_user");
                                editor.apply();

                                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
        ivDeleteAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status = "nonaktif";

                new MaterialAlertDialogBuilder(getActivity())
                        .setTitle("Apakah Anda yakin ingin menghapus Account?")

                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                UpdateAcc();
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.remove("Token");
                                editor.remove("Jabatan");
                                editor.remove("id_user");
                                editor.apply();

                                Intent intent = new Intent(requireActivity(), LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }

    private void UpdateAcc() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<UpdateResponse> proses = ARD.ardUpdate(id_user, status, "Bearer " + token);

        proses.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    if (isAdded()) {
                        Context context = requireContext();
                        Toast.makeText(context, "Berhasil Menghapus Account", Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case when the fragment is not attached
                        Log.w("StokGudangFragment", "Fragment not attached to context");
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                if (isAdded()) {
                    Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                } else {
                    // Handle the case when the fragment is not attached
                    Log.w("StokGudangFragment", "Fragment not attached to context");
                }
            }
        });
    }

    public void retrieveBarangKeluarHrini(){
        pbTransaksi.setVisibility(View.VISIBLE);

        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardKeluarGudang(tanggal_hari_ini, "Bearer " + token);

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {

                if (response.isSuccessful() && response.body() != null)
                {
                    ListTransaksi = response.body().getData();
                    adTransaksi.setData(ListTransaksi);
                }
                else {
                    Toast.makeText(requireContext(),"Tidak ada Transaksi pada tanggal hari ini", Toast.LENGTH_SHORT).show();
                }
                pbTransaksi.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<TampilKeluarResponse> call, Throwable t) {
                Toast.makeText(requireContext(), "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
                pbTransaksi.setVisibility(View.GONE);
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