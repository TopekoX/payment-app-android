package com.topekox.aplikasipembayaran.restclient;

import com.topekox.aplikasipembayaran.model.Produk;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdukClientService {

    @GET("/api/produk")
    Call<ArrayList<Produk>> getListProduk();

}
