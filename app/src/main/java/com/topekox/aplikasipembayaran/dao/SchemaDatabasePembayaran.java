package com.topekox.aplikasipembayaran.dao;

import android.provider.BaseColumns;

/**
 * Read more: https://developer.android.com/training/data-storage/sqlite
 */
public final class SchemaDatabasePembayaran {

    // make the constructor private
    // cannot instance this constructor
    private SchemaDatabasePembayaran() {
    }

    /* Schema Table Tagihan */
    public static class Tagihan implements BaseColumns {
        public static final String TABLE_NAME = "tagihan";
        public static final String PRODUK = "produk";
        public static final String NOMOR_PELANGGAN = "nomor_pelanggan";
        public static final String NAMA_PELANGGAN = "nama_pelanggan";
        public static final String BULAN_TAGIHAN = "bulan_tagihan";
        public static final String TANGGAL_JATUH_TEMPO = "tanggal_jatuh_tempo";
        public static final String NILAI = "nilai";
    }

    /* Schema Table Produk */
    public static class Produk implements BaseColumns {
        public static final String TABLE_NAME = "produk";
        public static final String ID_PRODUK = "id_produk";
        public static final String KODE_PRODUK = "kode_produk";
        public static final String NAMA_PRODUK = "nama_produk";
    }
}
