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
import com.bdshikkhok.RetrofitClientInstance;
import com.bdshikkhok.auth.network.APIInterface;
import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInFragment extends Fragment
        implements RadioGroup.OnCheckedChangeListener {

    View fragment_view;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.Login)
    Button mLogin;
    @BindView(R.id.radioGroupLogin)
    RadioGroup radioGroupL;
    @BindView(R.id.passwordRecoverLayout)
    LinearLayout mpasswordRecoverLayout;
    private String radioGroupSelect = "";
    private ProgressDialog progressDialog;


    private String email, password;
    private String select = "empty";


    String takeKey;
    String emailT, uid;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.fragment_log_in, container, false);
        ButterKnife.bind(this, fragment_view);

        radioGroupL.setOnCheckedChangeListener(this);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logInFunction();
            }
        });

        mpasswordRecoverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // FirebaseAuth auth = FirebaseAuth.getInstance();
                String emailAddress = email;
                // Toast.makeText(getActivity(),"Email: "+emailAddress,Toast.LENGTH_LONG).show();

            }
        });

        return fragment_view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_tutors_login:
                //if (checked)
                radioGroupSelect = "tutor";
                Log.e("signUp", radioGroupSelect);
                break;
            case R.id.radio_others_login:
                // if (checked)
                radioGroupSelect = "other";
                Log.e("signUp", radioGroupSelect);
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

        } /*else if (radioGroupSelect.equals("")) {
            Toast.makeText(getActivity(), "Please Select Student Or Teacher", Toast.LENGTH_SHORT).show();
        } */else {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("authenticating");
            progressDialog.setCancelable(false);
            progressDialog.show();

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsername(email);
            authRequest.setRememberMe(true);
            authRequest.setPassword(password);

            APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
            Call<AuthResponse> authResponseCall = apiInterface.authenticate(authRequest);
            authResponseCall.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    progressDialog.hide();
                    Log.e("Auth", "response : " + response.code());
                    Log.e("Auth", "response : " + response.message());
                    if ((response.code() == 200)) {
                        Log.e("Auth", "response : " + response.body().getIdToken());
                        Toast.makeText(getActivity(), response.body().getIdToken(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AuthResponse> call, Throwable t) {
                    progressDialog.hide();
                    Log.e("Auth", "Falure : " + t.getMessage());
                }
            });
        }
    }

}
