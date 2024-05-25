package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;

public class SupplierActivity extends AppCompatActivity {

    String namaSupplier, noHP_supplier, alamat_supplier;
    Context ctx;
    EditText etNamaSupplier,etNoHP_supplier, etAlamat_supplier;
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

        etNamaSupplier =findViewById(R.id.et_nama_supplier_baru);
        etNoHP_supplier = findViewById(R.id.et_nohp_supplier_baru);
        etAlamat_supplier = findViewById(R.id.et_alamat_supplier_baru);

        btnTambahSupplier = findViewById(R.id.btn_tambah_supplier_baru);

        btnTambahSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaSupplier = etNamaSupplier.getText().toString();
                noHP_supplier = etNoHP_supplier.getText().toString();
                alamat_supplier = etAlamat_supplier.getText().toString();

                if(namaSupplier.trim().isEmpty()){
                    etNamaSupplier.setError("Nama Supplier tidak boleh Kosong");
                }
                else if(noHP_supplier.trim().isEmpty()){
                    etNoHP_supplier.setError("No Handphone tidak boleh Kosong");
                }
                else if(alamat_supplier.trim().isEmpty()){
                    etAlamat_supplier.setError("Alamat tidak boleh Kosong");
                }
//                else{
//                    tambahSupplier();
//                }

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
        super.onBackPressed();

        Intent intent = new Intent(this,PesananPenjualanFragment.class);
        startActivity(intent);

        finish();
    }
//    private void tambahSupplier(){
//        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
//    Call<ModelResponse> proses = ARD.ardCreate(namaSupplier,noHP_supplier,alamat_supplier);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(SupplierActivity.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//                onBackPressed();
//            }
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(SupplierActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}