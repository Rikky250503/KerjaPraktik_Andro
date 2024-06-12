package com.example.projectkp.ui.Activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.adapter.BarangRestockAdapter;
import com.example.projectkp.adapter.CustomerAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataCustomer;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TampilCustomerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilBarangActivity extends AppCompatActivity {

    RecyclerView rv_tampil_barangr;
    private BarangRestockAdapter adBarangRestcok;
    private RecyclerView.LayoutManager lmTampilBarang;
    private List<DataBarang> ListTampilBarang = new ArrayList<>();
    private String source;
    private  String idBarangMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_tampil_barang);

        source = getIntent().getStringExtra("source");
        idBarangMasuk = getIntent().getStringExtra("id_barang_masuk");
        Toast.makeText(TampilBarangActivity.this, idBarangMasuk, Toast.LENGTH_SHORT).show();

        rv_tampil_barangr= findViewById(R.id.rv_tampil_barang);

        lmTampilBarang = new LinearLayoutManager(this);
        rv_tampil_barangr.setLayoutManager(lmTampilBarang);
        adBarangRestcok = new BarangRestockAdapter(this, ListTampilBarang,source,idBarangMasuk);
        rv_tampil_barangr.setAdapter(adBarangRestcok);

        retrieveTampilBarang();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void retrieveTampilBarang() {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilBarangResponse> proses = ARD.ardRetrieveBarang();

        proses.enqueue(new Callback<TampilBarangResponse>() {
            @Override
            public void onResponse(Call<TampilBarangResponse> call, Response<TampilBarangResponse> response) {
                if (response.isSuccessful() && response.body() != null)
                {
                    ListTampilBarang = response.body().getDataBarang();
                    adBarangRestcok.setData(ListTampilBarang);
                }
            }
            @Override
            public void onFailure(Call<TampilBarangResponse> call, Throwable t) {
                Toast.makeText(TampilBarangActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}