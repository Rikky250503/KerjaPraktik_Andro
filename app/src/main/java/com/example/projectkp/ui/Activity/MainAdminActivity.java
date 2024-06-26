package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.adapter.AdminAdapter;
import com.example.projectkp.adapter.BarangPenjualanAdapter;
import com.example.projectkp.adapter.PemesananPenjualanAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataUsername;
import com.example.projectkp.response.TampilAdminResponse;
import com.example.projectkp.response.TampilBarangResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdminActivity extends AppCompatActivity {

    private ImageView ivLogoutAdm, ivAddStaf;
    private AdminAdapter adAdmin;
    private RecyclerView rv_tampil_user;
    private RecyclerView.LayoutManager lmAdmin;
    private List<DataUsername> ListTampilAdmin = new ArrayList<>();
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main_admin);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);
        Log.d("TES", "Pesanan Penjualan Fragment: "+token);

        rv_tampil_user = findViewById(R.id.rv_staf);

        lmAdmin = new LinearLayoutManager(this);
        rv_tampil_user.setLayoutManager(lmAdmin);
        adAdmin= new AdminAdapter(this, ListTampilAdmin);
        rv_tampil_user.setAdapter(adAdmin);

        ivLogoutAdm = findViewById(R.id.iv_logout_adm);
        ivLogoutAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(MainAdminActivity.this)
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

                                Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
            }
        });

        ivAddStaf = findViewById(R.id.iv_add_staf);
        ivAddStaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainAdminActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        retrieveTampilAdmin();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void retrieveTampilAdmin() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilAdminResponse> proses = ARD.ardAdmin("Bearer " + token);

        proses.enqueue(new Callback<TampilAdminResponse>() {
            @Override
            public void onResponse(Call<TampilAdminResponse> call, Response<TampilAdminResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListTampilAdmin = response.body().getData();
                    adAdmin.setData(ListTampilAdmin);
                }
            }
            @Override
            public void onFailure(Call<TampilAdminResponse> call, Throwable t) {
                Toast.makeText(MainAdminActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}