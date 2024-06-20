package com.example.projectkp.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.projectkp.R;
import com.example.projectkp.response.DataTampilMasuk;
import com.example.projectkp.ui.Activity.DetailBarangMasukActivity;

import java.util.List;

public class PemesananSupplierAdapter extends RecyclerView.Adapter<PemesananSupplierAdapter.VHbarang> {

    private Context ctx;
    private List<DataTampilMasuk> ListBarangMasuk;

    public PemesananSupplierAdapter (Context ctx,List<DataTampilMasuk> listBarangMasuk){
        this.ctx = ctx;
        this.ListBarangMasuk = listBarangMasuk;
    }
    @NonNull
    @Override
    public PemesananSupplierAdapter.VHbarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pemesanan_supplier, parent, false);
        return new PemesananSupplierAdapter.VHbarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull PemesananSupplierAdapter.VHbarang holder, int position) {
        DataTampilMasuk M = ListBarangMasuk.get(position);
        holder.tvIdBarangMasuk.setText(M.getId_barang_masuk());
        holder.tvNoInvoicePemesananSupplier.setText(M.getNomor_invoice_masuk());
        holder.tvNamaSupplier.setText(String.valueOf(M.getNama_supplier()));
        holder.tvTanggalPemesananSupplier.setText(String.valueOf(M.getTanggal_masuk()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                Context context = holder.itemView.getContext();
                intent = new Intent(context, DetailBarangMasukActivity.class);

                intent.putExtra("id_barang_masuk",M.getId_barang_masuk());
                intent.putExtra("nama_supplier",M.getNama_supplier());
                intent.putExtra("tanggal_masuk",M.getTanggal_masuk());
                intent.putExtra("no_invoice_masuk",M.getNomor_invoice_masuk());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListBarangMasuk.size();
    }

    public void setData(List<DataTampilMasuk> newData){
        ListBarangMasuk.clear();
        if (newData != null){
            ListBarangMasuk.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHbarang extends RecyclerView.ViewHolder{

        TextView tvIdBarangMasuk, tvNoInvoicePemesananSupplier, tvNamaSupplier, tvTanggalPemesananSupplier;
        public VHbarang(@NonNull View itemView) {
            super(itemView);
            tvIdBarangMasuk = itemView.findViewById(R.id.tv_id_pemesanan_supplier);
            tvNoInvoicePemesananSupplier = itemView.findViewById(R.id.tv_no_invoice_supplier);
            tvNamaSupplier = itemView.findViewById(R.id.tv_nama_supplier);
            tvTanggalPemesananSupplier = itemView.findViewById(R.id.tv_tanggal_supplier);
        }
    }
}

