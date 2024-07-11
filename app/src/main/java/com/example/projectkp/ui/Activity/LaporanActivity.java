package com.example.projectkp.ui.Activity;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.adapter.LaporanAdapter;
import com.example.projectkp.adapter.PemesananPenjualanAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TampilKeluarResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {

    private ProgressBar pbLaporan;
    private RecyclerView rvLaporan;
    private LaporanAdapter adLaporan;
    private RecyclerView.LayoutManager lmLaporan;
    private List<DataBarang> ListLaporan = new ArrayList<>();
    private ImageView ivback;
    private String token;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_laporan);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", null).substring(1,53);

        pbLaporan = findViewById(R.id.pb_pesanan);
        rvLaporan = findViewById(R.id.rv_laporan);
        ivback = findViewById(R.id.iv_back_laporan);

        rvLaporan.setLayoutManager(new LinearLayoutManager(this));
        adLaporan = new LaporanAdapter(this, ListLaporan);
        rvLaporan.setAdapter(adLaporan);

        retrieveLaporan();

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaporanActivity.this,PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
    }

        private void retrieveLaporan() {
            pbLaporan.setVisibility(View.VISIBLE);

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<TampilBarangResponse> call = apiRequestData.ardRetrieveBarang("Bearer " + token);

            call.enqueue(new Callback<TampilBarangResponse>() {
                @Override
                public void onResponse(Call<TampilBarangResponse> call, Response<TampilBarangResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ListLaporan = response.body().getDataBarang();
                        adLaporan.setData(ListLaporan);
                    } else {
                        Toast.makeText(LaporanActivity.this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                    }
                    pbLaporan.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<TampilBarangResponse> call, Throwable t) {
                    Toast.makeText(LaporanActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                    pbLaporan.setVisibility(View.GONE);
                }
            });
        }

    }