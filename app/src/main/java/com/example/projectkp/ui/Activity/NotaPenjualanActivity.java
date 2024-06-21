package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TambahBKResponse;
import com.example.projectkp.response.TampilKeluarResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotaPenjualanActivity extends AppCompatActivity {

    String tanggalNota,noInvoiceNota,namaCustomer_nota,idCustomer_nota,token;

    EditText etNoInvoiceNota,etNamaCustomer_nota;

    ImageView ivCariCustomerNota,ivBackNota;
    private List<DataTampilKeluar> ListBarangKeluar = new ArrayList<>();
    private List<DataTampilKeluar> listBarangKeluar = new ArrayList<DataTampilKeluar>();

    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota_penjualan);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,52);

        etNoInvoiceNota = findViewById(R.id.et_invoice_nota);
        etNamaCustomer_nota = findViewById(R.id.et_customer_nota);
        ivCariCustomerNota = findViewById(R.id.iv_cari_customer_nota);
        ivBackNota = findViewById(R.id.iv_back_notapenjualan);

        Intent intent = getIntent();
        idCustomer_nota = intent.getStringExtra("id_customer");
        namaCustomer_nota = intent.getStringExtra("nama_customer");
        etNamaCustomer_nota.setText(namaCustomer_nota);

        ivCariCustomerNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotaPenjualanActivity.this,TampilCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivBackNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotaPenjualanActivity.this,PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnNext = findViewById(R.id.btn_next_nota);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInvoiceNota = etNoInvoiceNota.getText().toString();
                namaCustomer_nota = etNamaCustomer_nota.getText().toString();

                if(noInvoiceNota.trim().isEmpty()){
                    etNoInvoiceNota.setError("No invoice tidak boleh Kosong");
                }
                else if(namaCustomer_nota.trim().isEmpty()) {
                    etNamaCustomer_nota.setError("Nama customer tidak boleh Kosong");
                }
              else{
                    tambahNota();
               }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onBackPressed() {
    }

    private void tambahNota(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TambahBKResponse> proses = ARD.ardTambahBK(tanggalNota,noInvoiceNota,idCustomer_nota, "Bearer " + token);

        proses.enqueue(new Callback<TambahBKResponse>() {
            @Override
            public void onResponse(Call<TambahBKResponse> call, Response<TambahBKResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(NotaPenjualanActivity.this, NotaPenjualan2Activity.class);
                    intent.putExtra("id_barang_keluar", response.body().getData().getId_barang_keluar());

                    Toast.makeText(NotaPenjualanActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(NotaPenjualanActivity.this, "Gagal menambah nota penjualan ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TambahBKResponse> call, Throwable t) {
                Toast.makeText(NotaPenjualanActivity.this, "Gagal Menghubungi Server" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}