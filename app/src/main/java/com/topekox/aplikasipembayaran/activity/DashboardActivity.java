package com.topekox.aplikasipembayaran.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.dao.TagihanDao;
import com.topekox.aplikasipembayaran.model.Tagihan;
import com.topekox.aplikasipembayaran.fragment.DashboardFragment;
import com.topekox.aplikasipembayaran.service.RegistrasiFCMService;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String token;

    private final String TAG = "DASHBOARD_ACTIVITY";

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_layout_dashboard);
        navigationView = findViewById(R.id.navigationViewDashboard);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Firebase Action
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        token = task.getResult();
                        String email = getIntent().getStringExtra("EMAIL");
                        // Menjalankan Service Registrasi FCM
                        Log.w(TAG, "Mulai Running Registrasi FCM Service...");
                        Intent intent = new Intent(DashboardActivity.this, RegistrasiFCMService.class);
                        intent.putExtra("EMAIL", email);
                        intent.putExtra("TOKEN", token);
                        Log.w(TAG, "email: " + email +", token: " + token);
                        startService(intent);

                        // Log and toast
                        // String msg = getString(R.string.msg_token_fmt, token);
                        Log.d(TAG, "Token: " + token);
                        // Toast.makeText(DashboardActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        insertDummyData();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragmentDashboard, new DashboardFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home_dashboard:
                        Log.i("MENU_DRAWER_DB_TAG", "Home item clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_cek_tagihan_dashboard:
                        Log.i("MENU_DRAWER_DB_TAG", "Cek Tagihan clicked");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });
    }

    public void insertDummyData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Tagihan> listTagihan = new ArrayList<>();
        TagihanDao tagihanDao = new TagihanDao(this);

        try {
            listTagihan.add(new Tagihan("PLN PASCABAYAR", "00000001", "Uzumaki Naruto",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("100000.00")));

            listTagihan.add(new Tagihan("PDAM", "00000022", "Uciha Sasuke",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("80000.00")));

            listTagihan.add(new Tagihan("TELKOM", "090909", "Sikamaru Nara",
                    dateFormat.parse("2022-01-01"), dateFormat.parse("2022-01-20"),
                    new BigDecimal("420000.00")));

            listTagihan.add(new Tagihan("TELKOMSEL", "090909", "Abdul Kendeng Bin La Baco",
                    dateFormat.parse("2022-05-01"), dateFormat.parse("2022-05-20"),
                    new BigDecimal("420000.00")));

            listTagihan.add(new Tagihan("TELKOMSEL", "090909", "Ridwan Kamil",
                    dateFormat.parse("2022-05-01"), dateFormat.parse("2022-05-20"),
                    new BigDecimal("1000000.00")));
        } catch (ParseException e) {
            Log.w(TAG, e.getMessage());
        } finally {
            for (Tagihan tagihan : listTagihan) {
                tagihanDao.insertTagihan(tagihan);
            }
        }
    }
}