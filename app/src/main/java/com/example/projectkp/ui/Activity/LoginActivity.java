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

public class LoginActivity extends AppCompatActivity {

    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                EditText etRole = findViewById(R.id.tv_role);
                String role = etRole.getText().toString().toLowerCase();

                if (role.equals("pimpinan")) {
                    Intent intent = new Intent(LoginActivity.this, PimpinanActivity.class);
                    startActivity(intent);
                } else if (role.equals("penjualan")) {
                    Intent intent = new Intent(LoginActivity.this, PenjualanActivity.class);
                    startActivity(intent);
                } else if (role.equals("gudang")) {
                    Intent intent = new Intent(LoginActivity.this, GudangActivity.class);
                    startActivity(intent);
                } else {
                   Toast.makeText(LoginActivity.this, "Peran tidak valid", Toast.LENGTH_SHORT).show();
                }
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}