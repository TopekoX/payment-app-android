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

    /* Inner class that defines the table contents */
    public static class Tagihan implements BaseColumns {
        public static final String TABLE_NAME = "tagihan";
        public static final String PRODUK = "produk";
        public static final String NOMOR_PELANGGAN = "nomor_pelanggan";
        public static final String NAMA_PELANGGAN = "nama_pelanggan";
        public static final String BULAN_TAGIHAN = "bulan_tagihan";
        public static final String TANGGAL_JATUH_TEMPO = "tanggal_jatuh_tempo";
        public static final String NILAI = "nilai";
    }
}
