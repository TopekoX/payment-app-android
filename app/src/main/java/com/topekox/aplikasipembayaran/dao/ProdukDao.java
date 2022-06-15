package com.topekox.aplikasipembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;

import com.topekox.aplikasipembayaran.model.Produk;

import java.util.ArrayList;
import java.util.List;

public class ProdukDao {

    private Context context;
    private PembayaranDbHelper pembayaranDbHelper;
    private static final String TAG = "PRODUK_DAO";

    public ProdukDao(Context context) {
        this.context = context;
    }

    public void insertProduk(@NonNull Produk produk) {
        pembayaranDbHelper = new PembayaranDbHelper(context);

        // Gets the data repository in write mode
        SQLiteDatabase database = pembayaranDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(SchemaDatabasePembayaran.Produk.ID_PRODUK, produk.getId());
        values.put(SchemaDatabasePembayaran.Produk.KODE_PRODUK, produk.getKode());
        values.put(SchemaDatabasePembayaran.Produk.NAMA_PRODUK, produk.getNama());

        // Insert the new row, returning the primary key value of the new row
        // long newRowId = database.insert(SchemaDatabasePembayaran.Produk.TABLE_NAME, null, values);

        // Insert with no conflict
        long newRowId = database.insertWithOnConflict(SchemaDatabasePembayaran.Produk.TABLE_NAME,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
        database.close();
    }

    public List<Produk> produkList() {
        List<Produk> list = new ArrayList<>();
        pembayaranDbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase database = pembayaranDbHelper.getReadableDatabase();

        String[] dataKolom = {
                SchemaDatabasePembayaran.Produk.ID_PRODUK,
                SchemaDatabasePembayaran.Produk.KODE_PRODUK,
                SchemaDatabasePembayaran.Produk.NAMA_PRODUK
        };
        String sortOrder = SchemaDatabasePembayaran.Produk.NAMA_PRODUK + " ASC";

        Cursor cursor = database.query(
                SchemaDatabasePembayaran.Produk.TABLE_NAME,
                dataKolom,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                list.add(new Produk(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2))
                );
            }
        }

        cursor.close();
        return list;
    }
}
