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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.TambahCustomerResponse;
import com.example.projectkp.response.TambahSupplierResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupplierActivity extends AppCompatActivity {

    String nama_supplier, no_hp, alamat,token;
    Context ctx;
    EditText etNamaSupplier,etNoHP_supplier, etAlamat_supplier;
    ImageView ivBackSupplier;
    Button btnTambahSupplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_supplier);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        token = sharedPreferences.getString("Token", null).substring(1,52);
        Log.d("TEs", "onViewCreated: " + token);

        etNamaSupplier =findViewById(R.id.et_nama_supplier_baru);
        etNoHP_supplier = findViewById(R.id.et_nohp_supplier_baru);
        etAlamat_supplier = findViewById(R.id.et_alamat_supplier_baru);

        ivBackSupplier = findViewById(R.id.iv_back_supplier);
        ivBackSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupplierActivity.this,PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnTambahSupplier = findViewById(R.id.btn_tambah_supplier_baru);

        btnTambahSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_supplier = etNamaSupplier.getText().toString();
                no_hp = etNoHP_supplier.getText().toString();
                alamat = etAlamat_supplier.getText().toString();

                if(nama_supplier.trim().isEmpty()){
                    etNamaSupplier.setError("Nama Supplier tidak boleh Kosong");
                }
                else if(no_hp.trim().isEmpty()){
                    etNoHP_supplier.setError("No Handphone tidak boleh Kosong");
                }
                else if(alamat.trim().isEmpty()){
                    etAlamat_supplier.setError("Alamat tidak boleh Kosong");
                }
                else{
                    tambahSupplier();
                }

                Toast.makeText(SupplierActivity.this, "Supplier baru telah disimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SupplierActivity.this, PenjualanActivity.class);
                startActivity(intent);
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
    private void tambahSupplier(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TambahSupplierResponse> proses = ARD.ardTambahSupplier(nama_supplier,no_hp,alamat, "Bearer "+ token);

        proses.enqueue(new Callback<TambahSupplierResponse>() {
            @Override
            public void onResponse(Call<TambahSupplierResponse> call, Response<TambahSupplierResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(SupplierActivity.this, PenjualanActivity.class);
//                    Log.d("Id barang keluar", response.body().getData().getId_barang_keluar());
//                    Log.d("Tanggal", tanggalNota);
//                    Log.d("noInvoice", noInvoiceNota);
//                    Log.d("ID", idCustomer_nota);
                    Toast.makeText(SupplierActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(SupplierActivity.this, "Gagal menambah customer baru ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TambahSupplierResponse> call, Throwable t) {
                Toast.makeText(SupplierActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}