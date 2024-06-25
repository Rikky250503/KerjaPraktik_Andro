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
import com.example.projectkp.response.DataTampilMasuk;
import com.example.projectkp.ui.Activity.DetailBarangKeluarActivity;
import com.example.projectkp.ui.Activity.DetailTransaksiGudangActivity;


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
        holder.tvIdInvoiceKeluar.setText(M.getId_barang_keluar());
        holder.tvNoInvoice.setText(M.getNomorInvoiceKeluar());
        holder.tvNamaCustomer.setText(M.getNamaPemesan());
        holder.tvJam.setText(M.getJam());
        holder.tvTanggal.setText(M.getTanggalKeluar());
        holder.tvStatus.setText(M.getNamaStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;

                Context context = holder.itemView.getContext();
                intent = new Intent(context, DetailTransaksiGudangActivity.class);

                intent.putExtra("id_barang_keluar_transaksi",M.getId_barang_keluar());
                intent.putExtra("nama_pemesan_transaksi",M.getNamaPemesan());
                intent.putExtra("tanggal_keluar_transaksi",M.getTanggalKeluar());
                intent.putExtra("no_invoice_keluar_transaksi",M.getNomorInvoiceKeluar());
                intent.putExtra("nama_status_transaksi",M.getNamaStatus());
                context.startActivity(intent);
            }
        });
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

        TextView tvIdInvoiceKeluar,tvNamaCustomer, tvNoInvoice,tvTanggal,tvJam,tvStatus;
        public VHbarangKeluarhrini(@NonNull View itemView) {
            super(itemView);
            tvIdInvoiceKeluar = itemView.findViewById(R.id.tv_id_invoice_transaksi);
            tvNoInvoice = itemView.findViewById(R.id.tv_no_invoice_transaksi);
            tvNamaCustomer = itemView.findViewById(R.id.tv_nama_pt_transaksi);
            tvJam = itemView.findViewById(R.id.tv_jam_transaksi);
            tvTanggal = itemView.findViewById(R.id.tv_tanggal_transaksi);
            tvStatus = itemView.findViewById(R.id.tv_status_transaksi);
        }
    }
}
