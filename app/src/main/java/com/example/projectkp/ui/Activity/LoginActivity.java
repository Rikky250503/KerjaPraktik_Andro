package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.LoginResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private  EditText et_Username,et_Password;
    private  String username,password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        try{
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_login);

        et_Username = findViewById(R.id.et_username);
        et_Password =findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                moveTaskToBack(true);
                finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                username = et_Username.getText().toString();
                password = et_Password.getText().toString();
                if(username.trim().equals(""))
                {
                    et_Username.setError("username harus diisi");
                }
                if(password.trim().equals(""))
                {
                    et_Password.setError("password harus diisi");
                }
                else {
                    login();
                }
            }

        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void login()
    {
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<LoginResponse> proses = ARD.ardlogin(username,password);
        proses.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                LoginResponse loginResponse = response.body();
                if(response.body() != null)
                {
                    if("Login berhasil".equals(loginResponse.getMessage())){
                        Toast.makeText(LoginActivity.this,"Login Succesfull",Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String Data = gson.toJson(response.body().getData());
                        String Token = gson.toJson(response.body().getToken());
                        String Jabatan = gson.toJson(response.body().getJabatan());
                        editor.putString("Token", Token);
                        editor.putString("Jabatan", Jabatan);
                        editor.putString("id_user",Data);

                        editor.apply();

                        if(response.body().getJabatan().equals("J"))
                        {
                            Intent intent = new Intent(LoginActivity.this, PenjualanActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(response.body().getJabatan().equals("G"))
                        {
                            Intent intent = new Intent(LoginActivity.this, GudangActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else if(response.body().getJabatan().equals("P"))
                        {
                            Intent intent = new Intent(LoginActivity.this, MainAdminActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "Username atau Password salah " , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal Menghubungi Server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}