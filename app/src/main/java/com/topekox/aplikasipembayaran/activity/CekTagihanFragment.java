package com.topekox.aplikasipembayaran.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.topekox.aplikasipembayaran.fragment.HasilTagihanFragment;
import com.topekox.aplikasipembayaran.R;

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

    private Button buttonCekTagihan;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_cek_tagihan, container, false);

        buttonCekTagihan = fragmentView.findViewById(R.id.buttonCekTagihan);
        buttonCekTagihan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = CekTagihanFragment.this.getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment, new HasilTagihanFragment()).commit();
            }
        });

        return fragmentView;
    }
}