package com.example.projectkp.ui.Activity;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.adapter.LaporanAdapter;
import com.example.projectkp.adapter.PemesananPenjualanAdapter;
import com.example.projectkp.api.APIRequestData;
import com.example.projectkp.api.RetroServer;
import com.example.projectkp.response.DataBarang;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.TampilBarangResponse;
import com.example.projectkp.response.TampilKeluarResponse;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaporanActivity extends AppCompatActivity {

    final static int REQUEST_CODE = 1232;
    private String currentMonthYear,fileName,baseFileName;
    private int index = 1;
    private ProgressBar pbLaporan;
    private RecyclerView rvLaporan;
    private LaporanAdapter adLaporan;
    private RecyclerView.LayoutManager lmLaporan;
    private List<DataBarang> ListLaporan = new ArrayList<>();
    private ImageView ivback;
    private TextView tvBulanLaporan;
    private String token;
    private Button btnPrint;
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_laporan);
        askPermissions();

        SharedPreferences sharedPreferences = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("Token", null).substring(1,53);

        pbLaporan = findViewById(R.id.pb_laporan);
        rvLaporan = findViewById(R.id.rv_laporan);
        ivback = findViewById(R.id.iv_back_laporan);
        btnPrint = findViewById(R.id.btn_print);
        tvBulanLaporan = findViewById(R.id.tv_isi_bln_laporan);

        rvLaporan.setLayoutManager(new LinearLayoutManager(this));
        adLaporan = new LaporanAdapter(this, ListLaporan);
        rvLaporan.setAdapter(adLaporan);

        // Set up file path with current month and year
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());
        currentMonthYear = dateFormat.format(calendar.getTime());

        tvBulanLaporan.setText(currentMonthYear);
        retrieveLaporan();

        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaporanActivity.this,PenjualanActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnPrint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                generatePdf();
            }
        });

    }
    private void askPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE);
    }
    @Override
    public void onBackPressed() {
    }

        private void retrieveLaporan() {
            pbLaporan.setVisibility(View.VISIBLE);

            APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<TampilBarangResponse> call = apiRequestData.ardRetrieveBarang("Bearer " + token);

            call.enqueue(new Callback<TampilBarangResponse>() {
                @Override
                public void onResponse(Call<TampilBarangResponse> call, Response<TampilBarangResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ListLaporan = response.body().getDataBarang();
                        adLaporan.setData(ListLaporan);
                    } else {
                        Toast.makeText(LaporanActivity.this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
                    }
                    pbLaporan.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<TampilBarangResponse> call, Throwable t) {
                    Toast.makeText(LaporanActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                    pbLaporan.setVisibility(View.GONE);
                }
            });
        }
    private void generatePdf() {
        // Check permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Izin ditolak, tidak dapat membuat PDF", Toast.LENGTH_SHORT).show();
            return;
        }

        baseFileName = "Laporan Stok Barang " + currentMonthYear;
        fileName = baseFileName + ".pdf";
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File file = new File(path,fileName);

        // Check if file with the same name already exists

        while (file.exists()) {
            fileName = baseFileName + " (" + index + ").pdf";
            file = new File(path, fileName);
            index++;
        }

        try {
            // Initialize PDF writer
            PdfWriter writer = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add title to PDF
            Paragraph title = new Paragraph("Laporan Stok Barang");
            title.setTextAlignment(TextAlignment.CENTER); // Menengahkan teks
            document.add(title);

            // Add Periode
            Paragraph periode = new Paragraph("Periode: " + currentMonthYear);
            periode.setTextAlignment(TextAlignment.CENTER); // Menengahkan teks
            document.add(periode);

            // Add empty line for spacing
            document.add(new Paragraph("\n"));

            // Create table with column widths in percentage
            Table table = new Table(new float[]{10, 30, 20}); // Kolom: No., Nama Barang, Jumlah

            // Add header row
            table.addCell(new Cell().add(new Paragraph("No.")));
            table.addCell(new Cell().add(new Paragraph("Nama Barang")));
            table.addCell(new Cell().add(new Paragraph("Jumlah")));

            // Add data rows from ListLaporan
            for (int i = 0; i < ListLaporan.size(); i++) {
                DataBarang barang = ListLaporan.get(i);
                table.addCell(String.valueOf(i + 1));
                table.addCell(barang.getNama_barang());
                table.addCell(String.valueOf(barang.getKuantitas()));
            }

            // Center-align the table
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);

            // Add table to document
            document.add(table);

            // Close the document
            document.close();

            Toast.makeText(this, "PDF berhasil dibuat: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}