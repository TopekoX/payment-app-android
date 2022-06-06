package com.topekox.aplikasipembayaran.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * More info: https://developer.android.com/training/data-storage/sqlite
 */
public class PembayaranDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pembayaran.db";

    /* Create table */
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SchemaDatabasePembayaran.Tagihan.TABLE_NAME + " (" +
                    SchemaDatabasePembayaran.Tagihan._ID + " INTEGER PRIMARY KEY," +
                    SchemaDatabasePembayaran.Tagihan.PRODUK + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NOMOR_PELANGGAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NAMA_PELANGGAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.BULAN_TAGIHAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.TANGGAL_JATUH_TEMPO + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NILAI + " REAL" +
                    ")";

    /* Delete Table */
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SchemaDatabasePembayaran.Tagihan.TABLE_NAME;

    public PembayaranDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
