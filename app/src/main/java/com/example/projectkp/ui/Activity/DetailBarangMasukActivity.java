package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.adapter.DetailBarangMasukAdapter;
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

public class DetailBarangMasukActivity extends AppCompatActivity {

    private TextView tvInvoice, tvTanggal,tvSupplier;
    private ImageView ivback;
    private String id, invoice,tanggal,supplier, token;
    RecyclerView rvdetailBMPenjualan;
    private DetailBarangMasukAdapter adDetailBarangMasuk;
    private RecyclerView.LayoutManager lmDetailbarangMasuk;
    private List<DataTampilMasuk> ListDetailBarangMasuk = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_barang_masuk);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);

        rvdetailBMPenjualan = findViewById(R.id.rv_detail_bm_penjualan);

        ivback = findViewById(R.id.iv_back_detail_barang_masuk);

        tvInvoice = findViewById(R.id.tv_isi_invoice_detail_masuk_penjualan);
        tvTanggal = findViewById(R.id.tv_isi_tanggal_masuk_detail_penjualan);
        tvSupplier = findViewById(R.id.tv_isi_supplier_detail_penjualan);


        Intent intent = getIntent();
        id = intent.getStringExtra("id_barang_masuk");

        invoice = intent.getStringExtra("no_invoice_masuk");
        tanggal = intent.getStringExtra("tanggal_masuk");
        supplier = intent.getStringExtra("nama_supplier");

        tvInvoice.setText(invoice);
        tvTanggal.setText(tanggal);
        tvSupplier.setText(supplier);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBarangMasukActivity.this,PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lmDetailbarangMasuk = new LinearLayoutManager(this);
        rvdetailBMPenjualan.setLayoutManager(lmDetailbarangMasuk);
        adDetailBarangMasuk = new DetailBarangMasukAdapter(this, ListDetailBarangMasuk);
        rvdetailBMPenjualan.setAdapter(adDetailBarangMasuk);

        RetrieveDetailBM();


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void RetrieveDetailBM(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilMasukResponse> proses = ARD.ardTampilDBM(id, "Bearer "+ token);

        proses.enqueue(new Callback<TampilMasukResponse>() {
            @Override
            public void onResponse(Call<TampilMasukResponse> call, Response<TampilMasukResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ListDetailBarangMasuk = response.body().getData();
                    adDetailBarangMasuk.setData(ListDetailBarangMasuk);
                }
            }
            @Override
            public void onFailure(Call<TampilMasukResponse> call, Throwable t) {
                Toast.makeText(DetailBarangMasukActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}