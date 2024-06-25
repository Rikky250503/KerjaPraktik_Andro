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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataDBM;
import com.example.projectkp.response.TambahBMResponse;
import com.example.projectkp.response.TambahDBMResponse;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestockActivity2 extends AppCompatActivity {

    String namaBarang,hargaSatuanString,KuantitasString,idBarang,idBarangMasuk, idBarangMasukR, token;
    Double hargaSatuan;
    Integer kuantitas;

    Context ctx;
    EditText etNamaBarang,ethargaSatuan,etKuantitas;

    ImageView ivcariBarang;

    Button btnSelesaiRestock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock2);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,53);
        Log.d("TEs", "onViewCreated: " + token);

        etNamaBarang = findViewById(R.id.et_namaBarang_restock);
        ethargaSatuan = findViewById(R.id.et_hargaSatuan_restock);
        etKuantitas = findViewById(R.id.et_Kuantitas_restock);
        ivcariBarang = findViewById(R.id.iv_cari_namaBarang_restock);

        Intent intent = getIntent();
        idBarangMasuk = intent.getStringExtra("id_barang_masuk");
        idBarangMasukR = intent.getStringExtra("id_barang_masukr");
        idBarang = intent.getStringExtra("id_barang");
        namaBarang = intent.getStringExtra("nama_barang");
        etNamaBarang.setText(namaBarang);

        ivcariBarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestockActivity2.this,TampilBarangActivity.class);
                intent.putExtra("source","A");
                intent.putExtra("id_barang_masuk",idBarangMasuk);
                intent.putExtra("id_barang_masukr",idBarangMasukR);
                startActivity(intent);
                finish();
            }
        });

        btnSelesaiRestock = findViewById(R.id.btn_selesai_restock);

        btnSelesaiRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaBarang = etNamaBarang.getText().toString();
                hargaSatuanString = ethargaSatuan.getText().toString();
                KuantitasString = etKuantitas.getText().toString();

                if(namaBarang.trim().isEmpty()){
                    etNamaBarang.setError("Nama barang tidak boleh kosong");
                }
                else if(hargaSatuanString.trim().isEmpty()){
                    ethargaSatuan.setError("Harga satuan tidak boleh Kosong");
                }
                else if(KuantitasString.trim().isEmpty()){
                    etKuantitas.setError("Kuantitas tidak boleh Kosong");
                }
                else{
                    kuantitas = Integer.parseInt(KuantitasString);
                    hargaSatuan = Double.parseDouble(hargaSatuanString);
                    //Toast.makeText(RestockActivity2.this, "kuantitas = " + kuantitas + "hargasatuan = " + hargaSatuan, Toast.LENGTH_SHORT).show();
                    new MaterialAlertDialogBuilder(RestockActivity2.this)
                            .setTitle("Apakah Anda yakin ingin keluar dari Tampilan ini?")
//                              .setNeutralButton("Netral"), new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // Respond to neutral button press
//                                    }
//                              })
                            .setNegativeButton("Tambah", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    etNamaBarang.setText(null);
                                    etKuantitas.setText(null);
                                    ethargaSatuan.setText(null);
                                    tambahRestock1();
                                }
                            })
                            .setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    tambahRestock1();
                                    Intent intent = new Intent(RestockActivity2.this,PenjualanActivity.class);
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

    private void tambahRestock1(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);

        Call<TambahDBMResponse> proses = ARD.ardTambahBMDetail(idBarangMasukR ,idBarang,kuantitas,hargaSatuan, "Bearer " + token);
        proses.enqueue(new Callback<TambahDBMResponse>() {
            @Override
            public void onResponse(Call<TambahDBMResponse> call, Response<TambahDBMResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RestockActivity2.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Log.e("RestockActivity2", "Error body: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(RestockActivity2.this, "Gagal menambah nota restock ", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TambahDBMResponse> call, Throwable t) {
                Toast.makeText(RestockActivity2.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}