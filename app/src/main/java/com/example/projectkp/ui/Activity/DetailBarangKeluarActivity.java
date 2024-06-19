package com.example.projectkp.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;

public class DetailBarangKeluarActivity extends AppCompatActivity {

    private TextView tvInvoice, tvTanggal,tvCustomer,tvNamaBarang, tvKuantitas, tvHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_barang_keluar);

        tvInvoice=findViewById(R.id.tv_isi_invoice_detail_gudang);
        tvTanggal=findViewById(R.id.tv_isi_tanggal_detail_gudang);
        tvCustomer=findViewById(R.id.tv_isi_customer_detail_gudang);
        tvNamaBarang=findViewById(R.id.tv_isi_nama_barang_detail_gudang);
        tvKuantitas=findViewById(R.id.tv_isi_kuantitas_detail_gudang);
        tvHarga=findViewById(R.id.tv_isi_harga_detail_gudang);

        Intent intent = getIntent();
        String invoice = intent.getStringExtra("varInvoice");
        String tanggal = intent.getStringExtra("varTanggal");
        String customer = intent.getStringExtra("varCustomer");
        String namaBarang = intent.getStringExtra("varNamaBarang");
        String kuantitas = intent.getStringExtra("varKuantitas");
        String harga = intent.getStringExtra("varHarga");

        tvInvoice.setText(invoice);
        tvTanggal.setText(tanggal);
        tvCustomer.setText(customer);
        tvNamaBarang.setText(namaBarang);
        tvKuantitas.setText(kuantitas);
        tvHarga.setText(harga);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}