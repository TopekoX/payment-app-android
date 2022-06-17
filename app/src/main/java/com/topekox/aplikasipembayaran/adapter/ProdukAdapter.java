package com.topekox.aplikasipembayaran.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.topekox.aplikasipembayaran.model.Produk;

import java.util.List;

public class ProdukAdapter extends ArrayAdapter<Produk> {

    public ProdukAdapter(@NonNull Context context, int resource, @NonNull List<Produk> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return buatLabel(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return buatLabel(position);
    }

    private TextView buatLabel(int position) {
        Produk produk = getItem(position);
        TextView textView = new TextView(getContext());
        textView.setText(produk.getNama());
        return textView;
    }
}
