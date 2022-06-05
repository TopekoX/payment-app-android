package com.topekox.aplikasipembayaran.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.domain.Tagihan;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

// view list adapter
// menampilkan data ke list
public class TagihanAdapter extends ArrayAdapter<Tagihan> {


    public TagihanAdapter(@NonNull Context context, int resource,  @NonNull List<Tagihan> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_view_daftar_tagihan, parent, false);
        }

        Tagihan tagihan = getItem(position);

        TextView txtNamaProduk = convertView.findViewById(R.id.textViewProduk);
        TextView txtNomorPelanggan = convertView.findViewById(R.id.textViewNoPelanggan);
        TextView txtNamaPelanggan = convertView.findViewById(R.id.textViewNamaPelanggan);
        TextView txtBulanTagihan = convertView.findViewById(R.id.textViewTanggalBulanTagihan);
        TextView txtTanggalJatuhTempo = convertView.findViewById(R.id.textViewTanggalJatuhTempo);
        TextView txtTotal = convertView.findViewById(R.id.textViewJumlahTotal);

        txtNamaProduk.setText(tagihan.getNamaProduk());
        txtNamaPelanggan.setText(tagihan.getNamaPelanggan());
        txtNomorPelanggan.setText(tagihan.getNomorPelanggan());
        txtBulanTagihan.setText(new SimpleDateFormat("MMMM yyyy").format(tagihan.getBulanTagihan()));
        txtTanggalJatuhTempo.setText(new SimpleDateFormat("dd MMMM yyyy").format(tagihan.getJatuhTempo()));
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "id"));
        txtTotal.setText(currencyFormat.format(tagihan.getNilai()));

        return convertView;
    }
}
