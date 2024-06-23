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
import com.example.projectkp.adapter.CustomerAdapter;
import com.example.projectkp.adapter.DetailBarangKeluarAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataCustomer;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TambahBMResponse;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TampilKeluarResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarangKeluarActivity extends AppCompatActivity {

    private TextView tvInvoice, tvTanggal,tvCustomer;
    private ImageView ivback;
    private String id, invoice,tanggal,customer, token;
    RecyclerView rvdetailBKPenjualan;
    private DetailBarangKeluarAdapter adDetailBarangKeluar;
    private RecyclerView.LayoutManager lmDetailbarangKeluar;
    private List<DataTampilKeluar> ListDetailBarangKeluar = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_barang_keluar);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,52);

        rvdetailBKPenjualan = findViewById(R.id.rv_detail_bk_penjualan);

        ivback = findViewById(R.id.iv_back_detail_barang_keluar);

        tvInvoice = findViewById(R.id.tv_isi_invoice_detail_penjualan);
        tvTanggal = findViewById(R.id.tv_isi_tanggal_detail_penjualan);
        tvCustomer = findViewById(R.id.tv_isi_customer_detail_penjualan);

        Intent intent = getIntent();
        id = intent.getStringExtra("id_barang_keluar");

        invoice = intent.getStringExtra("no_invoice_keluar");
        tanggal = intent.getStringExtra("tanggal_keluar");
        customer = intent.getStringExtra("nama_pemesan");

        tvInvoice.setText(invoice);
        tvTanggal.setText(tanggal);
        tvCustomer.setText(customer);
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBarangKeluarActivity.this, PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lmDetailbarangKeluar = new LinearLayoutManager(this);
        rvdetailBKPenjualan.setLayoutManager(lmDetailbarangKeluar);
        adDetailBarangKeluar = new DetailBarangKeluarAdapter(this, ListDetailBarangKeluar);
        rvdetailBKPenjualan.setAdapter(adDetailBarangKeluar);

        RetrieveDetailBK();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void RetrieveDetailBK(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardTampilDBK(id,"Bearer "+ token);

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ListDetailBarangKeluar = response.body().getData();
                    adDetailBarangKeluar.setData(ListDetailBarangKeluar);
                }
            }

            @Override
            public void onFailure(Call<TampilKeluarResponse> call, Throwable t) {
                Toast.makeText(DetailBarangKeluarActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}