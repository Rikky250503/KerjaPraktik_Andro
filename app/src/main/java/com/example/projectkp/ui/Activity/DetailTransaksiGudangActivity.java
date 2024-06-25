package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.projectkp.adapter.DetailBarangKeluarAdapter;
import com.example.projectkp.adapter.DetailTransaksiGudangAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilKeluarResponse;
import com.example.projectkp.response.UpdateDataTGResponse;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiGudangActivity extends AppCompatActivity {

    private TextView tvInvoice, tvTanggal,tvCustomer;
    private ImageView ivback;
    private Button btnTerima;
    private String id,idStatus, noinvoice,tanggal,customer, token;
    private RecyclerView rvdetailTransaksiGudang;
    private DetailTransaksiGudangAdapter adDetailTransaksiGudang;
    private RecyclerView.LayoutManager lmDetailTransaksiGudang;
    private List<DataTampilKeluar> ListDetailTransaksiGudang = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_detail_transaksi_gudang);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);

        rvdetailTransaksiGudang = findViewById(R.id.rv_detail_transaksi_gudang);

        ivback = findViewById(R.id.iv_back_detail_transaksi_gudang);
        btnTerima = findViewById(R.id.btn_terima_detail_transaksi);

        tvInvoice = findViewById(R.id.tv_isi_invoice_detail_transaksi_gudang);
        tvTanggal = findViewById(R.id.tv_isi_tanggal_detail_transaksi_gudang);
        tvCustomer = findViewById(R.id.tv_isi_customer_detail_transaksi_gudang);

        Intent intent = getIntent();
        id = intent.getStringExtra("id_barang_keluar_transaksi");
        Log.d("token","token = " +token);

        noinvoice = intent.getStringExtra("no_invoice_keluar_transaksi");
        tanggal = intent.getStringExtra("tanggal_keluar_transaksi");
        customer = intent.getStringExtra("nama_pemesan_transaksi");
        idStatus = intent.getStringExtra("nama_status_transaksi");
        Log.d("Rikky", "namastatus "+ idStatus);

        tvInvoice.setText(noinvoice);
        tvTanggal.setText(tanggal);
        tvCustomer.setText(customer);

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailTransaksiGudangActivity.this, GudangActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lmDetailTransaksiGudang = new LinearLayoutManager(this);
        rvdetailTransaksiGudang.setLayoutManager(lmDetailTransaksiGudang);
        adDetailTransaksiGudang = new DetailTransaksiGudangAdapter(this, ListDetailTransaksiGudang);
        rvdetailTransaksiGudang.setAdapter(adDetailTransaksiGudang);

        RetrieveDetailTG();
        btnTerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idStatus.equalsIgnoreCase("Menunggu Dikirim")){
                    idStatus = "2";
                    Log.d("Rikky", "id_status "+ idStatus);
                    UpdateStatus();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void RetrieveDetailTG(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TampilKeluarResponse> proses = ARD.ardTampilDBK(id,"Bearer "+ token);

        proses.enqueue(new Callback<TampilKeluarResponse>() {
            @Override
            public void onResponse(Call<TampilKeluarResponse> call, Response<TampilKeluarResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ListDetailTransaksiGudang = response.body().getData();
                    adDetailTransaksiGudang.setData(ListDetailTransaksiGudang);
                }
            }
            @Override
            public void onFailure(Call<TampilKeluarResponse> call, Throwable t) {
                Toast.makeText(DetailTransaksiGudangActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
                Log.d("DetailTransaksiGudangActivity", "onFailure: " + t.getMessage());

            }
        });
    }
    private void UpdateStatus(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<UpdateDataTGResponse> proses = ARD.ardUpdateBK(id,idStatus,"Bearer "+ token);

        proses.enqueue(new Callback<UpdateDataTGResponse>() {
            @Override
            public void onResponse(Call<UpdateDataTGResponse> call, Response<UpdateDataTGResponse> response) {
                if(response.isSuccessful() && response.body()!= null){
                    Toast.makeText(DetailTransaksiGudangActivity.this,"Berhasil Diterima",Toast.LENGTH_SHORT).show();
                }
                else {
                    // Handle unsuccessful response
                    String errorMessage = "Error: ";
                    if (response.errorBody() != null) {
                        try {
                            errorMessage += response.errorBody().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        errorMessage += "Response body kosong";
                    }
                    Toast.makeText(DetailTransaksiGudangActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UpdateDataTGResponse> call, Throwable t) {
                Toast.makeText(DetailTransaksiGudangActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
                //Log.d("DetailTransaksiGudangActivity", "onFailure: " + t.getMessage());

            }
        });
    }
}