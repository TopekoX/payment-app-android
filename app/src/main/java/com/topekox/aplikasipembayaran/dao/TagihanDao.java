package com.topekox.aplikasipembayaran.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.topekox.aplikasipembayaran.domain.Tagihan;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Read More: https://developer.android.com/training/data-storage/sqlite
 */
public class TagihanDao {

    private Context context;
    private PembayaranDbHelper pembayaranDbHelper;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private final String TAG = "TAG_DB";

    public TagihanDao(Context context) {
        this.context = context;
    }

    public void insertTagihan(Tagihan tagihan) {
        pembayaranDbHelper = new PembayaranDbHelper(context);

        // Gets the data repository in write mode
        SQLiteDatabase database = pembayaranDbHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(SchemaDatabasePembayaran.Tagihan.PRODUK, tagihan.getNamaProduk());
        values.put(SchemaDatabasePembayaran.Tagihan.NOMOR_PELANGGAN, tagihan.getNomorPelanggan());
        values.put(SchemaDatabasePembayaran.Tagihan.NAMA_PELANGGAN, tagihan.getNamaPelanggan());
        values.put(SchemaDatabasePembayaran.Tagihan.BULAN_TAGIHAN, dateFormat.format(tagihan.getBulanTagihan()));
        values.put(SchemaDatabasePembayaran.Tagihan.TANGGAL_JATUH_TEMPO, dateFormat.format(tagihan.getJatuhTempo()));
        values.put(SchemaDatabasePembayaran.Tagihan.NILAI, tagihan.getNilai().doubleValue());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = database.insert(SchemaDatabasePembayaran.Tagihan.TABLE_NAME, null, values);
    }

    public List<Tagihan> daftarTagihan() {

        List<Tagihan> list = new ArrayList<>();
        pembayaranDbHelper = new PembayaranDbHelper(context);
        SQLiteDatabase database = pembayaranDbHelper.getReadableDatabase();

        String[] dataKolom = {
                SchemaDatabasePembayaran.Tagihan.PRODUK,
                SchemaDatabasePembayaran.Tagihan.NOMOR_PELANGGAN,
                SchemaDatabasePembayaran.Tagihan.NAMA_PELANGGAN,
                SchemaDatabasePembayaran.Tagihan.BULAN_TAGIHAN,
                SchemaDatabasePembayaran.Tagihan.TANGGAL_JATUH_TEMPO,
                SchemaDatabasePembayaran.Tagihan.NILAI
        };

        String sortOrder = SchemaDatabasePembayaran.Tagihan.TANGGAL_JATUH_TEMPO + " ASC";

        Cursor cursor = database.query(
                SchemaDatabasePembayaran.Tagihan.TABLE_NAME,
                dataKolom,
                null,
                null,
                null,
                null,
                sortOrder
        );

        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                try {
                    list.add(new Tagihan(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            dateFormat.parse(cursor.getString(3)),
                            dateFormat.parse(cursor.getString(4)),
                            new BigDecimal(cursor.getString(5)))
                    );
                } catch (ParseException e) {
                    Log.w(TAG, e.getMessage());
                }
            }
        }

        cursor.close();
        return list;
    }

}
