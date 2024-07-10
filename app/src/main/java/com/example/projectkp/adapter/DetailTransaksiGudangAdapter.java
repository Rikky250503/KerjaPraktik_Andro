package com.example.projectkp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectkp.NumberFormatter;
import com.example.projectkp.R;
import com.example.projectkp.response.DataTampilKeluar;

import java.util.List;

public class DetailTransaksiGudangAdapter extends RecyclerView.Adapter<DetailTransaksiGudangAdapter.VHDetailTransaksiGudang>{

    private Context ctx;
    private List<DataTampilKeluar> ListDetailTG;
    private  String source;

    public DetailTransaksiGudangAdapter(Context ctx, List<DataTampilKeluar> listDetailTransaksiGudang) {
        this.ctx = ctx;
        this.ListDetailTG = listDetailTransaksiGudang;
    }

    @NonNull
    @Override
    public VHDetailTransaksiGudang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_detail_transaksi_gudang, parent, false);
        return new DetailTransaksiGudangAdapter.VHDetailTransaksiGudang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull VHDetailTransaksiGudang holder, int position) {
        DataTampilKeluar MN = ListDetailTG.get(position);
        Double hargasatuan = MN.getHargaBarangKeluar();
        String HargaSatuan = NumberFormatter.formatNumber(hargasatuan);
        holder.tvNamaBarang.setText(MN.getNamaBarang());
        holder.tvKuantitas.setText(String.valueOf(MN.getKuantitas()));
        holder.tvHargaSatuan.setText(HargaSatuan);
    }

    @Override
    public int getItemCount() {
        return ListDetailTG.size();
    }

    public void setData(List<DataTampilKeluar> newData){
        ListDetailTG.clear();
        if (newData != null){
            ListDetailTG.addAll(newData);
        }
        notifyDataSetChanged();
    }

    public static class VHDetailTransaksiGudang extends RecyclerView.ViewHolder{
        TextView tvNamaBarang,tvKuantitas,tvHargaSatuan;
        public VHDetailTransaksiGudang(@NonNull View itemView) {
            super(itemView);
            tvNamaBarang= itemView.findViewById(R.id.tv_isi_nama_barang_detail_transaksi_gudang);
            tvKuantitas = itemView.findViewById(R.id.tv_isi_kuantitas_detail_transaksi_gudang);
            tvHargaSatuan = itemView.findViewById(R.id.tv_isi_harga_detail_transaksi_gudang);
        }
    }
}
