package com.topekox.aplikasipembayaran.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.topekox.aplikasipembayaran.fragment.CekTagihanFragment;
import com.topekox.aplikasipembayaran.fragment.HomeFragment;
import com.topekox.aplikasipembayaran.fragment.LoginFragment;
import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.fragment.RegisterFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle actionBarDrawerToggle;

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
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerFragment, new HomeFragment()).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmentSelected = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        Log.i("MENU_DRAWER_TAG", "Home item clicked");
                        fragmentSelected = new HomeFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_cek_tagihan:
                        Log.i("MENU_DRAWER_TAG", "Cek Tagihan clicked");
                        fragmentSelected = new CekTagihanFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_login:
                        Log.i("MENU_DRAWER_TAG", "Login item clicked");
                        fragmentSelected = new LoginFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.nav_register:
                        Log.i("MENU_DRAWER_TAG", "Register item clicked");
                        fragmentSelected = new RegisterFragment();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerFragment, fragmentSelected).commit();

                return true;
            }
        });

    }
}