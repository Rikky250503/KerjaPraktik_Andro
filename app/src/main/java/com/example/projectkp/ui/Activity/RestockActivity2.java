package com.example.projectkp.ui.Activity;

import android.content.Context;
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

public class RestockActivity2 extends AppCompatActivity {

    String namaBarang,hargaSatuanString,KuantitasString;
    Double hargaSatuan;
    Integer kuantitas;
    Context ctx;
    EditText etNamaBarang,ethargaSatuan,etKuantitas;

    Button btnSelesaiRestock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restock2);

        etNamaBarang = findViewById(R.id.et_namaBarang_restock);
        ethargaSatuan = findViewById(R.id.et_hargaSatuan_restock);
        etKuantitas = findViewById(R.id.et_Kuantitas_restock);

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
//                else{
//                    Kuantitas = Integer.parseInt(KuantitasString);
//                    hargaSatuan = Double.parseDouble(hargaSatuanString);
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
//    private void tambahRestock1(){
//        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
//        Call<ModelResponse> proses = ARD.ardCreate(namaBarang,hargaSatuan,kuantitas);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(RestockActivity2.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(RestockActivity2.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}