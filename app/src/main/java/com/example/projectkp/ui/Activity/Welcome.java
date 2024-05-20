package com.example.projectkp.ui.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.projectkp.R;
import com.google.gson.Gson;

public class Welcome extends AppCompatActivity {

    Button btn_getStarted;
    TextView tv_welcome;
    String JabatanJson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);

        //getActionBar().hide();
        getSupportActionBar().hide();

        btn_getStarted = findViewById(R.id.btn_getStarted);

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        JabatanJson = sharedPreferences.getString("Jabatan", null);
        btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (JabatanJson == null) {
                    startActivity(new Intent(Welcome.this, LoginActivity.class));
                    finish();
                    return;
                }
                if(JabatanJson.substring(1,2).equals("J"))
                {
                    Intent intent = new Intent(Welcome.this, PenjualanActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(JabatanJson.substring(1,2).equals("G"))
                {

                    Intent intent = new Intent(Welcome.this, GudangActivity.class);
                    startActivity(intent);
                    finish();
                }
                else if(JabatanJson.equals("P"))
                {
                    Intent intent = new Intent(Welcome.this, PimpinanActivity.class);
                    startActivity(intent);
                    finish();
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