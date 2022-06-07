package com.topekox.aplikasipembayaran.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.topekox.aplikasipembayaran.R;
import com.topekox.aplikasipembayaran.activity.DashboardActivity;
import com.topekox.aplikasipembayaran.exception.LoginFailedException;
import com.topekox.aplikasipembayaran.restclient.PembayaranClient;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private final String LOG = "PEMBAYARAN_APP_LOGIN";
    private String message = null;

    private Button buttonLogin;
    private ProgressBar progressBar;

    private PembayaranClient pembayaranClient = new PembayaranClient();

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);

        buttonLogin = fragmentView.findViewById(R.id.buttonFragmenLogin);
        progressBar = fragmentView.findViewById(R.id.progressBarLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ((EditText) fragmentView.findViewById(R.id.editTextLoginUserEmail))
                        .getText().toString().trim();
                String password = ((EditText) fragmentView.findViewById(R.id.editTextLoginUserPassword))
                        .getText().toString().trim();

                // AsyncTask Alternate
                ExecutorService executor = Executors.newSingleThreadExecutor();

                // Do something in background
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.w(LOG, "Starting Executing Backend Login Process...");

                        // onPreExecute method
                        LoginFragment.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                                buttonLogin.setEnabled(false);
                            }
                        });

                        // Login Process
                        try {
                            pembayaranClient.getLogin(username, password);
                            LoginFragment.this.getActivity().finish();
                            Intent intent = new Intent(LoginFragment.this.getActivity(), DashboardActivity.class);
                            startActivity(intent);
                            message = "Login Success";
                            Log.w(LOG, message);
                        } catch (LoginFailedException e) {
                            message = e.getMessage();
                            Log.w(LOG, message);
                        } catch (IOException e) {
                            message = e.getMessage();
                            Log.w(LOG, message);
                        } finally {
                            Log.w(LOG, "Finishing Executed Backend Login Process...");
                        }

                        // doPostExecute method
                        LoginFragment.this.getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                buttonLogin.setEnabled(true);
                                Toast.makeText(LoginFragment.this.getActivity(), message, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
            }
        });

        return fragmentView;
    }

}
