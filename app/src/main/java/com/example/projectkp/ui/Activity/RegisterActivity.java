package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etUserBaru,etPassBaru , etNamaBaru;
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

        Spinner spinner = (Spinner) findViewById(R.id.spinner_select_jabatan);
        spinner.setOnItemSelectedListener(this);

        //etJabatanBaru = findViewById(R.id.et_jabatan_baru);

        iv_back = findViewById(R.id.iv_back_register);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userBaru = etUserBaru.getText().toString();
                passBaru = etPassBaru.getText().toString();
                namaBaru = etNamaBaru.getText().toString();
                //jabatanBaru = etJabatanBaru.getText().toString();

                if(userBaru.trim().isEmpty())
                {
                    etUserBaru.setError("usename harus di isi");
                }
                if(passBaru.trim().isEmpty())
                {
                    etPassBaru.setError("Password harus di isi");
                }
                if(namaBaru.trim().isEmpty())
                {
                    etNamaBaru.setError("Nama harus di isi");
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.select_jabatan,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void onBackPressed() {
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        jabatanBaru = parent.getItemAtPosition(pos).toString();
        if (jabatanBaru.equalsIgnoreCase("Penjualan"))
        {
            jabatanBaru = "J";
        } else if (jabatanBaru.equalsIgnoreCase("Gudang")) {
            jabatanBaru = "G";
        } else if (jabatanBaru.equalsIgnoreCase("Pimpinan")) {
            jabatanBaru = "P";
        }
        Log.d("RegisterActivity", "jabatanBaru selected: " + jabatanBaru);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback.
    }

    private void register(){
        APIRequestData ARD = RetroServer.konekRetrofit().create(APIRequestData.class);

        Call<RegisterResponse> proses = ARD.ardRegister(userBaru, passBaru, namaBaru, jabatanBaru);

        proses.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(RegisterActivity.this,  response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this,MainAdminActivity.class);
                    startActivity(intent);
                    finish();

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