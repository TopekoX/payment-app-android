package com.topekox.aplikasipembayaran.restclient;

import android.util.Log;

import com.topekox.aplikasipembayaran.exception.LoginFailedException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PembayaranClient {

    private static final String URL_SERVER = "https://pembayaran-app-backend.herokuapp.com/";
    private final String LOG = "PEMBAYARAN_APP_CLIENT";

    public void getLogin(String username, String password) throws LoginFailedException, IOException {
        Map<String, Object> loginData = new HashMap<>();
        loginData.put("username", username);
        loginData.put("password", password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        PembayaranClientService service = retrofit.create(PembayaranClientService.class);
        PembayaranClientRequest request = PembayaranClientRequest.builder()
                .setUsername(username)
                .setPassword(password)
                .build();

        Call<PembayaranClientResponse> call = service.postLogin(request);
        PembayaranClientResponse response = call.execute().body();

        Log.w(LOG, "Request Username : " + request.getUsername());
        Log.w(LOG, "Request Password : " + request.getPassword());

        Log.w(LOG, "Response Message : " + response);

        if (response == null) {
            throw new LoginFailedException("Response Invalid");
        }

        if (response.isSuccess() == false) {
            throw new LoginFailedException("Username/Password Salah");
        }
    }
}
