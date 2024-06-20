package com.example.projectkp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.ui.Activity.DetailBarangKeluarActivity;

import java.util.List;

public class PemesananPenjualanAdapter extends RecyclerView.Adapter<PemesananPenjualanAdapter.VHbarang> {
    private Context ctx;
    private List<DataTampilKeluar> ListBarang;

    public PemesananPenjualanAdapter(Context ctx, List<DataTampilKeluar> listBarang){
        this.ctx = ctx;
        this.ListBarang = listBarang;
    }

    @NonNull
    @Override
    public PemesananPenjualanAdapter.VHbarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pesanan, parent, false);
        return new PemesananPenjualanAdapter.VHbarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananPenjualanAdapter.VHbarang holder, int position) {
        DataTampilKeluar MN = ListBarang.get(position);
        holder.tvIdPemesan.setText(MN.getId_barang_keluar());
        holder.tvNoInvoice.setText(MN.getNomorInvoiceKeluar());
        holder.tvNamaPT.setText(String.valueOf(MN.getNamaPemesan()));
        holder.tvTanggal.setText(String.valueOf(MN.getTanggalKeluar()));
        holder.tvStatus.setText(MN.getNamaStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                Context context = holder.itemView.getContext();
                intent = new Intent(context, DetailBarangKeluarActivity.class);

                intent.putExtra("id_barang_keluar",MN.getId_barang_keluar());
                intent.putExtra("nama_pemesan",MN.getNamaPemesan());
                intent.putExtra("tanggal_keluar",MN.getTanggalKeluar());
                intent.putExtra("no_invoice_keluar",MN.getNomorInvoiceKeluar());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListBarang.size();
    }

    public void setData(List<DataTampilKeluar> newData){
        ListBarang.clear();
        if (newData != null){
            ListBarang.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHbarang extends RecyclerView.ViewHolder{

        TextView tvIdPemesan, tvNoInvoice, tvNamaPT, tvTanggal, tvStatus;
        public VHbarang(@NonNull View itemView) {
            super(itemView);
            tvIdPemesan = itemView.findViewById(R.id.tv_id_pemesanan);
            tvNoInvoice  = itemView.findViewById(R.id.tv_no_invoice);
            tvNamaPT = itemView.findViewById(R.id.tv_nama_pt);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}

