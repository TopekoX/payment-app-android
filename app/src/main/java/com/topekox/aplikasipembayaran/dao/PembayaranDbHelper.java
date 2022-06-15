package com.topekox.aplikasipembayaran.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * More info: https://developer.android.com/training/data-storage/sqlite
 */
public class PembayaranDbHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Pembayaran.db";

    /* Create table tagihan */
    private static final String SQL_CREATE_TAGIHAN =
            "CREATE TABLE " + SchemaDatabasePembayaran.Tagihan.TABLE_NAME + " (" +
                    SchemaDatabasePembayaran.Tagihan._ID + " INTEGER PRIMARY KEY," +
                    SchemaDatabasePembayaran.Tagihan.PRODUK + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NOMOR_PELANGGAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NAMA_PELANGGAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.BULAN_TAGIHAN + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.TANGGAL_JATUH_TEMPO + " TEXT," +
                    SchemaDatabasePembayaran.Tagihan.NILAI + " REAL" +
                    ")";

    /* Create table produk */
    private static final String SQL_CREATE_PRODUK =
            "CREATE TABLE " + SchemaDatabasePembayaran.Produk.TABLE_NAME + " (" +
                    // SchemaDatabasePembayaran.Produk._ID + " INTEGER PRIMARY KEY," +
                    SchemaDatabasePembayaran.Produk.ID_PRODUK + " INTEGER," +
                    SchemaDatabasePembayaran.Produk.KODE_PRODUK + " TEXT," +
                    SchemaDatabasePembayaran.Produk.NAMA_PRODUK + " TEXT," +
                    "UNIQUE (" + SchemaDatabasePembayaran.Produk.KODE_PRODUK + "," +
                    SchemaDatabasePembayaran.Produk.KODE_PRODUK + ")  ON CONFLICT REPLACE" +
                    ")";

    /* Delete Table tagihan */
    private static final String SQL_DELETE_TAGIHAN =
            "DROP TABLE IF EXISTS " + SchemaDatabasePembayaran.Tagihan.TABLE_NAME;

    /* Delete Table produk */
    private static final String SQL_DELETE_PRODUK =
            "DROP TABLE IF EXISTS " + SchemaDatabasePembayaran.Produk.TABLE_NAME;

    public PembayaranDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TAGIHAN);
        db.execSQL(SQL_CREATE_PRODUK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_TAGIHAN);
        db.execSQL(SQL_DELETE_PRODUK);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
