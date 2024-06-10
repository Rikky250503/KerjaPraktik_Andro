package com.example.projectkp.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataCustomer;
import com.example.projectkp.response.TambahBKResponse;
import com.example.projectkp.response.TambahCustomerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerBaruActivity extends AppCompatActivity {

    EditText etNamaCustomer, etAlamatCustomer, etNoHpCustomer;
    String nama_pemesan,alamat_pemesan,no_hp_pemesan;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_baru);

        etNamaCustomer= findViewById(R.id.et_nama_customer_baru);
        etAlamatCustomer = findViewById(R.id.et_alamat_customer_baru);
        etNoHpCustomer = findViewById(R.id.et_nohp_customer_baru);
        btnNext = findViewById(R.id.btn_next_customer_baru);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 nama_pemesan = etNamaCustomer.getText().toString().trim();
                 alamat_pemesan = etAlamatCustomer.getText().toString().trim();
                 no_hp_pemesan = etNoHpCustomer.getText().toString().trim();

                if (nama_pemesan.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "Nama Customer belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (alamat_pemesan.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "Alamat belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (no_hp_pemesan.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "No Telp belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    tambahcustomer();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void tambahcustomer(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<TambahCustomerResponse> proses = ARD.ardTambahCustomer(nama_pemesan,alamat_pemesan,no_hp_pemesan);

        proses.enqueue(new Callback<TambahCustomerResponse>() {
            @Override
            public void onResponse(Call<TambahCustomerResponse> call, Response<TambahCustomerResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Intent intent = new Intent(CustomerBaruActivity.this, PenjualanActivity.class);
//                    Log.d("Id barang keluar", response.body().getData().getId_barang_keluar());
//                    Log.d("Tanggal", tanggalNota);
//                    Log.d("noInvoice", noInvoiceNota);
//                    Log.d("ID", idCustomer_nota);
                    Toast.makeText(CustomerBaruActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(CustomerBaruActivity.this, "Gagal menambah customer baru ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TambahCustomerResponse> call, Throwable t) {
                Log.d("ResponRikky",t.getMessage());
                Toast.makeText(CustomerBaruActivity.this, "Gagal Menghubungi Server" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
