package com.topekox.aplikasipembayaran.restclient;

import android.content.Context;
import android.util.Log;

import com.topekox.aplikasipembayaran.dao.ProdukDao;
import com.topekox.aplikasipembayaran.exception.LoginFailedException;
import com.topekox.aplikasipembayaran.exception.RegisterTokenFailedException;
import com.topekox.aplikasipembayaran.model.PembayaranClientRequest;
import com.topekox.aplikasipembayaran.model.PembayaranClientResponse;
import com.topekox.aplikasipembayaran.model.Produk;
import com.topekox.aplikasipembayaran.model.UserTokenRequest;
import com.topekox.aplikasipembayaran.model.UserTokenResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PembayaranClient {

    private static final String URL_SERVER = "https://pembayaran-app-backend-dev.herokuapp.com";
    private final String TAG = "PEMBAYARAN_APP_CLIENT";
    private List<Produk> daftarProduk = new ArrayList<>();

    public void getLogin(String email, String password) throws LoginFailedException, IOException {
        Map<String, Object> loginData = new HashMap<>();
        loginData.put("email", email);
        loginData.put("password", password);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        PembayaranClientService service = retrofit.create(PembayaranClientService.class);
        PembayaranClientRequest request = PembayaranClientRequest.builder()
                .email(email)
                .password(password)
                .build();

        Call<PembayaranClientResponse> call = service.postLogin(request);
        PembayaranClientResponse response = call.execute().body();

        Log.w(TAG, "Request Username : " + request.getEmail());
        Log.w(TAG, "Response Message : " + response);

        if (response == null) {
            throw new LoginFailedException("Response Invalid");
        }

        if (response.isSuccess() == false) {
            throw new LoginFailedException("Username/Password Salah");
        }
    }

    public void registrasiToken(String email, String token) throws IOException, RegisterTokenFailedException {
        Map<String, String> data = new HashMap<>();
        data.put("email", email);
        data.put("token", token);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        PembayaranClientService service = retrofit.create(PembayaranClientService.class);
        UserTokenRequest request = UserTokenRequest.builder()
                .email(email)
                .token(token)
                .build();

        Call<UserTokenResponse> call = service.postUserToken(request);
        UserTokenResponse response = call.execute().body();

        Log.w(TAG, "Request User Email: " + request.getEmail());
        Log.w(TAG, "Request User Token: " + request.getToken());
        Log.w(TAG, "Response User Token Message : " + response);

        if (response == null) {
            throw new RegisterTokenFailedException("Tidak ada respon dari server");
        }
    }

    public void updateDataProduk(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_SERVER)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ProdukClientService service = retrofit.create(ProdukClientService.class);
        Call<ArrayList<Produk>> call = service.getListProduk();
        ProdukDao produkDao = new ProdukDao(context);

        call.enqueue(new Callback<ArrayList<Produk>>() {
            @Override
            public void onResponse(Call<ArrayList<Produk>> call, Response<ArrayList<Produk>> response) {
                daftarProduk = response.body();
                Log.w(TAG, "Response Server: " + daftarProduk.size());

                for (Produk p : daftarProduk) {
                    Log.w(TAG, "Daftar Produk " + p.getNama());
                    Log.w(TAG, "Menyimpan Produk ke database: " + p.getNama());
                    produkDao.insertProduk(p);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Produk>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
