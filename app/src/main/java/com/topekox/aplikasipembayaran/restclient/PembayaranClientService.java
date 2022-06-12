package com.topekox.aplikasipembayaran.restclient;

import com.topekox.aplikasipembayaran.model.PembayaranClientRequest;
import com.topekox.aplikasipembayaran.model.PembayaranClientResponse;
import com.topekox.aplikasipembayaran.model.UserTokenRequest;
import com.topekox.aplikasipembayaran.model.UserTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PembayaranClientService {

    @POST("/api/login")
    Call<PembayaranClientResponse> postLogin(@Body PembayaranClientRequest request);

    @POST("/api/user/token")
    Call<UserTokenResponse> postUserToken(@Body UserTokenRequest request);

}
