package com.topekox.aplikasipembayaran.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.activity.CekTagihanFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HasilTagihanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HasilTagihanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button buttonBack;

    public HasilTagihanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HasilTgaihanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HasilTagihanFragment newInstance(String param1, String param2) {
        HasilTagihanFragment fragment = new HasilTagihanFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_hasil_tagihan, container, false);

        buttonBack = fragmentView.findViewById(R.id.buttonFragmentHasilTagihanBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = HasilTagihanFragment.this.getActivity()
                        .getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.containerFragment, new CekTagihanFragment()).commit();
            }
        });

        return fragmentView;
    }
}