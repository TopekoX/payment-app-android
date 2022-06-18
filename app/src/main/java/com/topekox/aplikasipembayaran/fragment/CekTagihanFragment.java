package com.topekox.aplikasipembayaran.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.topekox.aplikasipembayaran.PembayaranConstans;
import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.adapter.ProdukAdapter;
import com.topekox.aplikasipembayaran.dao.ProdukDao;
import com.topekox.aplikasipembayaran.model.Produk;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CekTagihanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CekTagihanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "CEK_TAGIHAN";

    private Button buttonCekTagihan;
    private Spinner spinnerProduk;
    private EditText editTextNoPelanggan;
    private BroadcastReceiver broadcastReceiver;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CekTagihanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CekTagihanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CekTagihanFragment newInstance(String param1, String param2) {
        CekTagihanFragment fragment = new CekTagihanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateSpinner();
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(getContext())
                .registerReceiver(broadcastReceiver,
                        new IntentFilter(PembayaranConstans.INTENT_UPDATE_PRODUK));
    }

    @Override
    public void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(getContext())
                .unregisterReceiver(broadcastReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_cek_tagihan, container, false);

        editTextNoPelanggan = fragmentView.findViewById(R.id.editTextNomorPelanggan);
        spinnerProduk = fragmentView.findViewById(R.id.spinnerProduk);
        buttonCekTagihan = fragmentView.findViewById(R.id.buttonCekTagihan);
        buttonCekTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produk produkDipilih = (Produk) spinnerProduk.getSelectedItem();
                String nomorPelanggan = editTextNoPelanggan.getText().toString();

                Log.w(TAG, "Produk: " + produkDipilih.getKode());
                Log.w(TAG, "Nomor Pelanggan: " + nomorPelanggan);

                FragmentTransaction fragmentTransaction = CekTagihanFragment.this.getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment, new HasilTagihanFragment()).commit();
            }
        });

        return fragmentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateSpinner();
    }

    private void updateSpinner() {
        List<Produk> produkList = new ProdukDao(getContext()).produkList();
        spinnerProduk.setAdapter(new ProdukAdapter(
                CekTagihanFragment.this.getActivity(),
                android.R.layout.simple_spinner_item,
                produkList));
    }
}