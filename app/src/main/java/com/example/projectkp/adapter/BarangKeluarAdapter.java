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

import java.util.List;

public class BarangKeluarAdapter  extends RecyclerView.Adapter<BarangKeluarAdapter.VHbarang> {
    private Context ctx;
    private List<DataTampilKeluar> ListBarang;

    public BarangKeluarAdapter(Context ctx,List<DataTampilKeluar> listBarang){
        this.ctx = ctx;
        this.ListBarang = listBarang;
    }

    @NonNull
    @Override
    public BarangKeluarAdapter.VHbarang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View varView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pesanan, parent, false);
        return new BarangKeluarAdapter.VHbarang(varView);
    }

    @Override
    public void onBindViewHolder(@NonNull BarangKeluarAdapter.VHbarang holder, int position) {
        DataTampilKeluar MN = ListBarang.get(position);
        holder.tvIdPemesan.setText(MN.getIdBarangKeluar());
        holder.tvNoInvoice.setText(MN.getNomorInvoiceKeluar());
        holder.tvNamaPT.setText(String.valueOf(MN.getNamaPemesan()));
        holder.tvTanggal.setText(String.valueOf(MN.getTanggalKeluar()));
        holder.tvStatus.setText(MN.getNamaStatus());
//        if(MN.getIdStatus() == 1) {
//            holder.tvStatus.setText(String.valueOf("Menunggu Dikirim"));
//        }else if(MN.getIdStatus() == 2) {
//            holder.tvStatus.setText(String.valueOf("Pesanan Dikirim"));
//        }if(MN.getIdStatus() == 3) {
//            holder.tvStatus.setText(String.valueOf("Pesanan Diterima"));
//        }
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

