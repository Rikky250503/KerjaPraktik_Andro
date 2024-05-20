package com.example.projectkp.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;

public class NotaPenjualanActivity extends AppCompatActivity {

    String tanggalNota,noInvoiceNota,namaCustomer_nota,idCustomer_nota;

    EditText etTanggalNota,etNoInvoiceNota,etNamaCustomer_nota;
    TextView tvIdCustomer_nota;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nota_penjualan);

        etTanggalNota = findViewById(R.id.et_tglPesan_nota);
        etNoInvoiceNota = findViewById(R.id.et_invoice_nota);
        tvIdCustomer_nota = findViewById(R.id.id_customer_nota);
        etNamaCustomer_nota = findViewById(R.id.et_customer_nota);

        btnNext = findViewById(R.id.btn_next_nota);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tanggalNota = etTanggalNota.getText().toString();
                noInvoiceNota = etNoInvoiceNota.getText().toString();
                idCustomer_nota = tvIdCustomer_nota.getText().toString();
                namaCustomer_nota = etNamaCustomer_nota.getText().toString();

                if(tanggalNota.trim().isEmpty()){
                    etTanggalNota.setError("Tanggal nota tidak boleh Kosong");
                }
                else if(noInvoiceNota.trim().isEmpty()){
                    etNoInvoiceNota.setError("No invoice tidak boleh Kosong");
                }
                else if(namaCustomer_nota.trim().isEmpty()) {
                    etNamaCustomer_nota.setError("Nama customer tidak boleh Kosong");
                }
//              else{
//                    tambahNota();
//               }

                Toast.makeText(NotaPenjualanActivity.this, "Customer baru telah disimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NotaPenjualanActivity.this, PenjualanActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
//         private void tambahNota(){
//        RequestData ARD = RetroServer.konekRetrofit().create(RequestData.class);
//    Call<ModelResponse> proses = ARD.ardCreate(tanggalNota,noInvoiceNota,namaCustomer_nota);
//
//        proses.enqueue(new Callback<ModelResponse>() {
//            @Override
//            public void onResponse(Call<ModelResponse> call, Response<ModelResponse> response) {
//                String kode = response.body().getKode();
//                String pesan = response.body().getPesan();
//                Toast.makeText(NotaPenjualanActivity.this,"Kode: " + kode + " Pesan: " + pesan, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ModelResponse> call, Throwable t) {
//                Toast.makeText(NotaPenjualanActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}