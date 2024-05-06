package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;

public class RestockActivity extends AppCompatActivity {

    String noInvoiceMasuk,tanggalMasuk,namaSupplier,createdBy;
    Context ctx;
    EditText etNoInvoiceMasuk,etTanggalMasuk,etNamaSupplier,etCreatedBy;
    Button btnNextRestock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock);

        etNoInvoiceMasuk = findViewById(R.id.et_invoice_restock);
        etTanggalMasuk = findViewById(R.id.et_tgl_restock);
        etNamaSupplier = findViewById(R.id.et_namaSupplier_restock);
        etCreatedBy = findViewById(R.id.et_createdBy_restock);

        btnNextRestock = findViewById(R.id.btn_next_restock);

        btnNextRestock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInvoiceMasuk = etNoInvoiceMasuk.getText().toString();
                tanggalMasuk = etTanggalMasuk.getText().toString();
                namaSupplier = etNamaSupplier.getText().toString();
                createdBy = etCreatedBy.getText().toString();

                if(noInvoiceMasuk.trim().isEmpty()){
                    etNoInvoiceMasuk.setError("Nomor Invoice Masuk tidak boleh kosong");
                }
                else if(tanggalMasuk.trim().isEmpty()){
                    etTanggalMasuk.setError("Tanggal Masuk tidak boleh Kosong");
                }
                else if(namaSupplier.trim().isEmpty()){
                    etNamaSupplier.setError("Nama Supplier tidak boleh Kosong");
                }
                else if(createdBy.trim().isEmpty()){
                    etCreatedBy.setError("Created by tidak boleh Kosong");
                }
//                else{
//                    tambahRestock();
//                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//    private void tambahRestock(){
//        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
//        Call<ModelResponse> proses = ARD.ardCreate(noInvoiceMasuk,tanggalMasuk,namaSupplier,createdBy);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(RestockActivity.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(RestockActivity.this,RestockActivity2.class);
//                startActivity(intent);
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(RestockActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}