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


import java.util.ArrayList;
import java.util.List;

public class BarangGudangAdapter extends RecyclerView.Adapter<BarangGudangAdapter.VHbarang> {
    private Context ctx;
    private List<DataBarang> ListBarang; // Daftar asli
    private List<DataBarang> ListBarangFilter; // Daftar yang difilter

    public BarangGudangAdapter(Context ctx, List<DataBarang> listBarang){
        this.ctx = ctx;
        this.ListBarang = listBarang;
        ListBarangFilter = new ArrayList<>(listBarang);
    }

    @NonNull
    @Override
    public VHbarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_stok_gudang, parent, false);
        return new VHbarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangGudangAdapter.VHbarang holder, int position) {
        DataBarang MN = ListBarangFilter.get(position);
        holder.tvidBarang.setText(MN.getId_barang());
        holder.tvNamaBarang.setText(MN.getNama_barang());
        holder.tvJumlah.setText(String.valueOf(MN.getKuantitas()));
    }

    @Override
    public int getItemCount() {
        return ListBarangFilter.size();
    }

    public void setData(List<DataBarang> newData){
        ListBarang.clear();
        ListBarangFilter.clear();
        if (newData != null){
            ListBarang.addAll(newData);
            ListBarangFilter.addAll(newData);
        }
        notifyDataSetChanged();
    }
    public void filter(String text) {
        ListBarangFilter.clear();
        if (text.isEmpty()) {
            ListBarangFilter.addAll(ListBarang);
        } else {
            text = text.toLowerCase();
            for (DataBarang item : ListBarang) {
                // Cek apakah nama barang dimulai dengan teks yang dimasukkan
                if (item.getNama_barang().toLowerCase().startsWith(text)) {
                    ListBarangFilter.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class VHbarang extends RecyclerView.ViewHolder{

        TextView tvidBarang,tvNamaBarang ,tvJumlah;
        public VHbarang(@NonNull View itemView) {
            super(itemView);
            tvidBarang = itemView.findViewById(R.id.tv_id_stok_gudang);
            tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_gudang);
            tvJumlah = itemView.findViewById(R.id.jumlah_stok_gudang);
        }
    }
}
