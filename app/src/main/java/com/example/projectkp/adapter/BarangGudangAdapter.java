package com.example.projectkp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.R;
import com.example.projectkp.response.DataBarang;


import java.util.List;

public class BarangGudangAdapter extends RecyclerView.Adapter<BarangGudangAdapter.VHbarang> {
    private Context ctx;
    private List<DataBarang> ListBarang;

    public BarangGudangAdapter(Context ctx, List<DataBarang> listBarang){
        this.ctx = ctx;
        this.ListBarang = listBarang;
    }

    @NonNull
    @Override
    public VHbarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stok_gudang, parent, false);
        return new VHbarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangGudangAdapter.VHbarang holder, int position) {
        DataBarang MN = ListBarang.get(position);
        holder.tvidBarang.setText(MN.getId_barang());
        holder.tvNamaBarang.setText(MN.getNama_barang());
        holder.tvJumlah.setText(String.valueOf(MN.getKuantitas()));
    }

    @Override
    public int getItemCount() {
        return ListBarang.size();
    }

    public void setData(List<DataBarang> newData){
        ListBarang.clear();
        if (newData != null){
            ListBarang.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public static class VHbarang extends RecyclerView.ViewHolder{

        TextView tvidBarang,tvNamaBarang ,tvJumlah;
        public VHbarang(@NonNull View itemView) {
            super(itemView);
            //nanti di ganti
            tvidBarang = itemView.findViewById(R.id.tv_id_stok_gudang);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_gudang);
            tvJumlah = itemView.findViewById(R.id.jumlah_stok_gudang);
        }
    }
}
