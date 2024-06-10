package com.example.projectkp.ui.Activity;

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

public class NotaPenjualan2Activity extends AppCompatActivity {

    String namaBarang_nota2,banyakBarang_nota2,hargaSatuan_nota2;

    EditText etNamaBarang_nota2,etBanyakBarang_nota2,etHargaSatuan_nota2;
    Button btnSelesai;
    Integer banyakBarangInt_nota2;
    Double hargaSatuanDoub_nota2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota_penjualan2);

        etNamaBarang_nota2 = findViewById(R.id.et_namaBarang_nota2);
        etBanyakBarang_nota2 = findViewById(R.id.et_banyakBarang);
        etHargaSatuan_nota2 = findViewById(R.id.et_hargaSatuan);

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
//                else{
//                    banyakBarangInt_nota2 = Integer.parseInt(banyakBarang_nota2);
//                    hargaSatuanDoub_nota2 = Double.parseDouble(hargaSatuan_nota2);
//                    detailNota();
//                }
                Toast.makeText(NotaPenjualan2Activity.this, "Tambah data pembelian telah di simpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NotaPenjualan2Activity.this, PenjualanActivity.class);
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//         private void detailNota(){
//        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
//    Call<ModelResponse> proses = ARD.ardCreate(namaBarang_nota2,hargaSatuanDoub_nota2,banyakBarangInt_nota2);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(SupplierActivity.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(SupplierActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}