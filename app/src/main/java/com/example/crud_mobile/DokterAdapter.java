package com.example.crud_mobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DokterAdapter extends RecyclerView.Adapter<DokterAdapter.ViewHolderSaya> {

    private Context context;
    private ArrayList dokter_id, dokter_nama, dokter_spesialis, dokter_jadwal;

    DokterAdapter(
            Context context,
            ArrayList dokter_id,
            ArrayList dokter_nama,
            ArrayList dokter_spesialis,
            ArrayList dokter_jadwal
    ){
        this.context    = context;
        this.dokter_id    = dokter_id;
        this.dokter_nama = dokter_nama;
        this.dokter_spesialis = dokter_spesialis;
        this.dokter_jadwal   = dokter_jadwal;

    }

    @NonNull
    @Override
    public ViewHolderSaya onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflaterKita = LayoutInflater.from(context);
        View viewSaya = inflaterKita.inflate(R.layout.indahsriwahyuni8020180234, parent, false);
        return new ViewHolderSaya(viewSaya);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSaya holder, @SuppressLint("RecyclerView") int position) {
        holder.txt_id_dokter.setText(String.valueOf(dokter_id.get(position)));
        holder.txt_nama_dokter.setText(String.valueOf(dokter_nama.get(position)));
        holder.txt_dokter_spesialis.setText(String.valueOf(dokter_spesialis.get(position)));
        holder.txt_dokter_jadwal.setText(String.valueOf(dokter_jadwal.get(position)));
        holder.layoutUtama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKita = new Intent(context, UbahDokterActivity.class);
                intentKita.putExtra("id", String.valueOf(dokter_id.get(position)));
                intentKita.putExtra("nama", String.valueOf(dokter_nama.get(position)));
                intentKita.putExtra("spesialis", String.valueOf(dokter_spesialis.get(position)));
                intentKita.putExtra("jadwal", String.valueOf(dokter_jadwal.get(position)));

                context.startActivity(intentKita);
            }
        });
    }

    public int getItemCount() {
        return dokter_id.size();
    }

    public class ViewHolderSaya extends RecyclerView.ViewHolder {

        TextView txt_id_dokter, txt_nama_dokter, txt_dokter_spesialis, txt_dokter_jadwal;
        LinearLayout layoutUtama;

        public ViewHolderSaya(@NonNull View itemView) {
            super(itemView);

            txt_id_dokter        = itemView.findViewById(R.id.txt_dokter_id);
            txt_nama_dokter      = itemView.findViewById(R.id.txt_dokter_nama);
            txt_dokter_spesialis  = itemView.findViewById(R.id.txt_dokter_spesialis);
            txt_dokter_jadwal       = itemView.findViewById(R.id.txt_dokter_jadwal);
            layoutUtama         = itemView.findViewById(R.id.layout_utama);
        }
    }
}
