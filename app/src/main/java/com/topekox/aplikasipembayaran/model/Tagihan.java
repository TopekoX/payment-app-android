package com.topekox.aplikasipembayaran.model;

import java.math.BigDecimal;
import java.util.Date;

public class Tagihan {
    private String namaProduk;
    private String nomorPelanggan;
    private String namaPelanggan;
    private Date bulanTagihan;
    private Date jatuhTempo;
    private BigDecimal nilai;

    public Tagihan() {
    }

    public Tagihan(String namaProduk, String nomorPelanggan, String namaPelanggan, Date bulanTagihan, Date jatuhTempo, BigDecimal nilai) {
        this.namaProduk = namaProduk;
        this.nomorPelanggan = nomorPelanggan;
        this.namaPelanggan = namaPelanggan;
        this.bulanTagihan = bulanTagihan;
        this.jatuhTempo = jatuhTempo;
        this.nilai = nilai;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public void setNomorPelanggan(String nomorPelanggan) {
        this.nomorPelanggan = nomorPelanggan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public Date getBulanTagihan() {
        return bulanTagihan;
    }

    public void setBulanTagihan(Date bulanTagihan) {
        this.bulanTagihan = bulanTagihan;
    }

    public Date getJatuhTempo() {
        return jatuhTempo;
    }

    public void setJatuhTempo(Date jatuhTempo) {
        this.jatuhTempo = jatuhTempo;
    }

    public BigDecimal getNilai() {
        return nilai;
    }

    public void setNilai(BigDecimal nilai) {
        this.nilai = nilai;
    }
}
