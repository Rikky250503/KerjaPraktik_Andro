package com.example.projectkp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.response.DataTampilKeluar;
import com.example.projectkp.response.DataTampilMasuk;


import java.util.List;

public class TransaksiGudangAdapter extends RecyclerView.Adapter<TransaksiGudangAdapter.VHbarangKeluarhrini> {

    private Context ctx;
    private List<DataTampilKeluar> ListBarangKeluarHrini;

    public TransaksiGudangAdapter (Context ctx,List<DataTampilKeluar> listBarangKeluarHrini){
        this.ctx = ctx;
        this.ListBarangKeluarHrini = listBarangKeluarHrini;
    }
    @NonNull
    @Override
    public TransaksiGudangAdapter.VHbarangKeluarhrini onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaksi_gudang, parent, false);
        return new TransaksiGudangAdapter.VHbarangKeluarhrini(varView);
    }
    @Override
    public void onBindViewHolder(@NonNull TransaksiGudangAdapter.VHbarangKeluarhrini holder, int position) {
        DataTampilKeluar M = ListBarangKeluarHrini.get(position);
        holder.tvIdBarangKeluar.setText(M.getIdBarangKeluar());
        holder.tvNamaCustomer.setText(M.getNamaPemesan());
        holder.tvNamaBarang.setText(M.getNamaBarang());
    }
    @Override
    public int getItemCount() {
        return ListBarangKeluarHrini.size();
    }

    public void setData(List<DataTampilKeluar> newData){
        ListBarangKeluarHrini.clear();
        if (newData != null){
            ListBarangKeluarHrini.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public static class VHbarangKeluarhrini extends RecyclerView.ViewHolder{

        TextView tvNamaCustomer, tvNamaBarang,tvIdBarangKeluar;
        public VHbarangKeluarhrini(@NonNull View itemView) {
            super(itemView);
            tvIdBarangKeluar = itemView.findViewById(R.id.tv_id_stok_gudang);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_gudang);
            tvNamaCustomer = itemView.findViewById(R.id.tv_nama_customer_gudang);
        }
    }
}
