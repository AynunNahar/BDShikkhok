package com.bdshikkhok.auth.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.bdshikkhok.R;

public class LogInFragment extends Fragment
        implements RadioGroup.OnCheckedChangeListener {

    View v;
    private Button mLogin;
    private EditText mEmail, mPassword;
    private RadioGroup radioGroupL;
    private String radioGroupScelect;
    private ProgressDialog progressDialog;

    LinearLayout mpasswordRecoverLayout;
    private String email, password;
    private String select = "empty";


    String takeKey;
    String emailT, uid;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_log_in, container, false);

        mEmail = (EditText) v.findViewById(R.id.email);
        mPassword = (EditText) v.findViewById(R.id.password);
        mLogin = (Button) v.findViewById(R.id.Login);
        radioGroupL = (RadioGroup) v.findViewById(R.id.radioGroupLogin);

        radioGroupL.setOnCheckedChangeListener(this);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logInFunction();
            }
        });

        mpasswordRecoverLayout = (LinearLayout) v.findViewById(R.id.passwordRecoverLayout);
        mpasswordRecoverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = email;
                // Toast.makeText(getActivity(),"Email: "+emailAddress,Toast.LENGTH_LONG).show();

            }
        });

        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_tutors_login:
                //if (checked)
                radioGroupScelect = "tutor";
                Log.e("signUp", radioGroupScelect);
                break;
            case R.id.radio_others_login:
                // if (checked)
                radioGroupScelect = "other";
                Log.e("signUp", radioGroupScelect);
                break;
        }
    }

    public void logInFunction() {
        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if (email.equals("") && password.equals("")) {
            Toast.makeText(getActivity(), "Please Enter Email & Password", Toast.LENGTH_SHORT).show();
        } else if (email.equals("")) {
            Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();

        } else if (radioGroupScelect.equals("")) {
            Toast.makeText(getActivity(), "Please Select Student Or Teacher", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

}
