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

public class CustomerBaruActivity extends AppCompatActivity {

    EditText etNamaCustomer, etAlamatCustomer, etNoHpCustomer;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                String customerName = etNamaCustomer.getText().toString().trim();
                String customerAddress = etAlamatCustomer.getText().toString().trim();
                String customerPhone = etNoHpCustomer.getText().toString().trim();

                if (customerName.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "Nama Customer belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (customerAddress.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "Alamat belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (customerPhone.isEmpty()) {
                    Toast.makeText(CustomerBaruActivity.this, "No Telp belum diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(CustomerBaruActivity.this, "Customer baru telah disimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CustomerBaruActivity.this, PenjualanActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
