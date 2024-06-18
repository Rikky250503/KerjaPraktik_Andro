package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.TambahBMResponse;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestockActivity extends AppCompatActivity {

    String noInvoiceMasuk,namaSupplier,createdBy,id_supplier_restock;
    Context ctx;
    ImageView ivCariSupplierRestock,ivBackRestock;
    EditText etNoInvoiceMasuk,etNamaSupplier,etCreatedBy;
    MaterialButton btnNextRestock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_restock);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String TokenJson = sharedPreferences.getString("Token", null);

        etNoInvoiceMasuk = findViewById(R.id.et_invoice_restock);
        etNamaSupplier = findViewById(R.id.et_namaSupplier_restock);
        etCreatedBy = findViewById(R.id.et_createdBy_restock);
        ivCariSupplierRestock = findViewById(R.id.iv_cari_supplier_restock);
        ivBackRestock = findViewById(R.id.iv_back_restock);

        Intent intent = getIntent();
        id_supplier_restock = intent.getStringExtra("id_supplier");
        namaSupplier = intent.getStringExtra("nama_supplier");
        etNamaSupplier.setText(namaSupplier);

        ivCariSupplierRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestockActivity.this, TampilSupplierActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ivBackRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Buat instance dari Fragment yang ingin dituju
                Fragment fragment = new PemesananSupplierFragment();

                // Lakukan FragmentTransaction untuk mengganti fragment_container dengan fragment
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.pemesanan_supplier, fragment);
                transaction.addToBackStack(null); // Menambahkan transaksi ke back stack (opsional)
                transaction.commit();
            }
        });

        btnNextRestock = findViewById(R.id.btn_next_restock);

        btnNextRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInvoiceMasuk = etNoInvoiceMasuk.getText().toString();
                namaSupplier = etNamaSupplier.getText().toString();
                //createdBy = etCreatedBy.getText().toString();

                if(noInvoiceMasuk.trim().isEmpty()){
                    etNoInvoiceMasuk.setError("Nomor Invoice Masuk tidak boleh kosong");
                }

                else if(namaSupplier.trim().isEmpty()){
                    etNamaSupplier.setError("Nama Supplier tidak boleh Kosong");
                }
//                else if(createdBy.trim().isEmpty()){
//                    etCreatedBy.setError("Created by tidak boleh Kosong");
//                }
              else{
                    tambahRestock();
               }

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onBackPressed() {
        // Tidak melakukan apa-apa sehingga tombol back tidak berfungsi
        // Tidak memanggil super.onBackPressed() untuk menonaktifkan tombol back
    }

    private void tambahRestock(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TambahBMResponse> proses = ARD.ardTambahBM(noInvoiceMasuk,id_supplier_restock);

        proses.enqueue(new Callback<TambahBMResponse>() {
            @Override
            public void onResponse(Call<TambahBMResponse> call, Response<TambahBMResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(RestockActivity.this, RestockActivity2.class);
                    intent.putExtra("id_barang_masuk", response.body().getData().getId_barang_masuk());
//                    Log.d("Id barang keluar", response.body().getData().getId_barang_keluar());
//                    Log.d("Tanggal", tanggalNota);
//                    Log.d("noInvoice", noInvoiceNota);
//                    Log.d("ID", idCustomer_nota);
                    Toast.makeText(RestockActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RestockActivity.this, "Gagal menambah nota restock ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TambahBMResponse> call, Throwable t) {
                Toast.makeText(RestockActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}