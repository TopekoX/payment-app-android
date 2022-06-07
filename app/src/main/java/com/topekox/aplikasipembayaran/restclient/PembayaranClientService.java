package com.topekox.aplikasipembayaran.restclient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PembayaranClientService {

    @POST("/api/login")
    Call<PembayaranClientResponse> postLogin(@Body PembayaranClientRequest request);

}
