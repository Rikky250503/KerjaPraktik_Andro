package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.os.Bundle;
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

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.LoginResponse;
import com.example.projectkp.response.RegisterResponse;
import com.example.projectkp.response.TambahBMResponse;
import com.google.gson.Gson;

import kotlin.text.RegexOption;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUserBaru,etPassBaru, etJabatanBaru, etNamaBaru;
    private  String userBaru,passBaru,namaBaru, jabatanBaru;
    Button btn_register;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_register);

        etUserBaru =findViewById(R.id.et_user_baru);
        etPassBaru = findViewById(R.id.et_pass_baru);
        etNamaBaru = findViewById(R.id.et_nama_baru);
        etJabatanBaru = findViewById(R.id.et_jabatan_baru);
        iv_back = findViewById(R.id.iv_back_register);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userBaru = etUserBaru.getText().toString();
                passBaru = etPassBaru.getText().toString();
                namaBaru = etNamaBaru.getText().toString();
                jabatanBaru = etJabatanBaru.getText().toString();

                if (userBaru != null && passBaru != null && namaBaru != null && jabatanBaru != null) {
                    userBaru = passBaru.trim();
                    passBaru = passBaru.trim();
                    namaBaru = namaBaru.trim();
                    jabatanBaru = jabatanBaru.trim();
                } else {
                    if (userBaru == null) {
                        userBaru = "";
                    }
                    if (passBaru == null) {
                        passBaru = "";
                    }
                    if (namaBaru == null) {
                        namaBaru = "";
                    }
                    if (jabatanBaru == null) {
                        jabatanBaru = "";
                    }
                }

                if(userBaru.trim().equals(""))
                {
                    etUserBaru.setError("username harus diisi");
                }
                if(passBaru.trim().equals(""))
                {
                    etPassBaru.setError("password harus diisi");
                }
                if(namaBaru.trim().equals(""))
                {
                    etNamaBaru.setError("nama harus diisi");
                }
                if(jabatanBaru.trim().equals(""))
                {
                    etJabatanBaru.setError("Jabatan harus diisi");
                }

                else {
                    register();
                }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, MainAdminActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void register(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<RegisterResponse> proses = ARD.ardRegister(userBaru, passBaru, namaBaru, jabatanBaru);

        proses.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(RegisterActivity.this, "Gagal menambah user baru ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Menghubungi Server" , Toast.LENGTH_SHORT).show();
            }
        });
    }
}