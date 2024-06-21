package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.TambahBKResponse;
import com.example.projectkp.response.TambahDBKResponse;
import com.example.projectkp.response.TambahDBMResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotaPenjualan2Activity extends AppCompatActivity {

    String idBarang,namaBarang_nota2,banyakBarang_nota2,hargaSatuan_nota2,idBarangKeluar,idBarangKeluarR, token;

    EditText etNamaBarang_nota2,etBanyakBarang_nota2,etHargaSatuan_nota2;
    Button btnSelesai;
    ImageView ivCariBarang;
    Integer kuantitas;
    Double hargaSatuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota_penjualan2);

        SharedPreferences sharedPreferences =getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,52);

        etNamaBarang_nota2 = findViewById(R.id.et_namaBarang_nota2);
        etBanyakBarang_nota2 = findViewById(R.id.et_banyakBarang);
        etHargaSatuan_nota2 = findViewById(R.id.et_hargaSatuan);

        ivCariBarang = findViewById(R.id.iv_cari_namaBarang_nota2);

        Intent intent = getIntent();
        idBarang = intent.getStringExtra("id_barang");
        namaBarang_nota2 = intent.getStringExtra("nama_barang");

        idBarangKeluar = intent.getStringExtra("id_barang_keluar");
        idBarangKeluarR = intent.getStringExtra("id_barang_keluarR");
        etNamaBarang_nota2.setText(namaBarang_nota2);

        ivCariBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotaPenjualan2Activity.this,TampilBarangActivity.class);
                intent.putExtra("source","B");
                intent.putExtra("id_barang_keluar",idBarangKeluar);
                intent.putExtra("id_barang_keluarR",idBarangKeluarR);
                startActivity(intent);
                finish();
            }
        });

        btnSelesai = findViewById(R.id.btn_selesai);

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaBarang_nota2 = etNamaBarang_nota2.getText().toString();
                banyakBarang_nota2 = etBanyakBarang_nota2.getText().toString();
                hargaSatuan_nota2 = etHargaSatuan_nota2.getText().toString();

                if(namaBarang_nota2.trim().isEmpty()){
                    etNamaBarang_nota2.setError("Nama Barang tidak boleh Kosong");
                }
                else if(banyakBarang_nota2.trim().isEmpty()){
                    etBanyakBarang_nota2.setError("Banyak barang tidak boleh Kosong");
                }
                else if(hargaSatuan_nota2.trim().isEmpty()){
                    etHargaSatuan_nota2.setError("Harga satuan jual tidak boleh Kosong");
                }
                else{
                    kuantitas = Integer.parseInt(banyakBarang_nota2);
                    hargaSatuan = Double.parseDouble(hargaSatuan_nota2);
                    new MaterialAlertDialogBuilder(NotaPenjualan2Activity.this)
                            .setTitle("Apakah Anda yakin ingin menyelesaikan tampilan ini?")

                            .setNegativeButton("Tambah", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    etNamaBarang_nota2.setText(null);
                                    etBanyakBarang_nota2.setText(null);
                                    etHargaSatuan_nota2.setText(null);
                                    TambahNota1();
                                }
                            })
                            .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TambahNota1();
                                    Intent intent = new Intent(NotaPenjualan2Activity.this,PenjualanActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();
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

    private void TambahNota1(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);

        Call<TambahDBKResponse> proses = ARD.ardTambahBKDetail(idBarangKeluarR ,idBarang,kuantitas,hargaSatuan, "Bearer " + token);
            proses.enqueue(new Callback<TambahDBKResponse>() {
                 @Override
                 public void onResponse(Call<TambahDBKResponse> call, Response<TambahDBKResponse> response) {
                     if (response.isSuccessful() && response.body() != null) {
                         Toast.makeText(NotaPenjualan2Activity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                     } else {
                         Toast.makeText(NotaPenjualan2Activity.this, "Gagal menambah daftar pembelian barang di nota ", Toast.LENGTH_SHORT).show();
                     }
                 }

                 @Override
                 public void onFailure(Call<TambahDBKResponse> call, Throwable t) {
                     Toast.makeText(NotaPenjualan2Activity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
                 }
             });
         }
}