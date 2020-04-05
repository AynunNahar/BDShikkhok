package com.bdshikkhok.auth.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.bdshikkhok.R;
import com.bdshikkhok.RetrofitClientInstance;
import com.bdshikkhok.auth.network.APIInterface;
import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;
import com.bdshikkhok.profile.StudentProfileActivity;
import com.bdshikkhok.util.PreferencesManager;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInFragment extends Fragment {

    View fragment_view;
    @BindView(R.id.email)
    EditText mEmail;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.Login)
    Button mLogin;
    @BindView(R.id.passwordRecoverLayout)
    LinearLayout mpasswordRecoverLayout;
    /*@BindView(R.id.write_email)
    TextView writeEmail;
    @BindView(R.id.write_password)
    TextView writePassword;*/
    @BindView(R.id.text_input_layout)
    TextInputLayout til;
    private String radioGroupSelect = "";
    private ProgressDialog progressDialog;


    private String emailOrUsername, password;
    private String select = "empty";


    String takeKey;
    String emailT, uid,checkSignIn="empty";


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.fragment_log_in, container, false);
        ButterKnife.bind(this, fragment_view);

        //writeEmail.setVisibility(View.INVISIBLE);
        //writePassword.setVisibility(View.INVISIBLE);


        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //writeEmail.setVisibility(View.INVISIBLE);
               //writePassword.setVisibility(View.INVISIBLE);
                logInFunction();
            }
        });

        mpasswordRecoverLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getActivity(),"Email: "+emailAddress,Toast.LENGTH_LONG).show();

            }
        });

        return fragment_view;
    }


    public void logInFunction() {
        emailOrUsername = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if (emailOrUsername.equals("") && password.equals("")) {
            //writeEmail.setVisibility(View.VISIBLE);
            //writePassword.setVisibility(View.VISIBLE);
            til.setErrorEnabled(true);
            til.setError("You need to enter a name");
            //Toast.makeText(getActivity(), "Please Enter Email & Password", Toast.LENGTH_SHORT).show();
        } else if (emailOrUsername.equals("")) {
            //writeEmail.setVisibility(View.VISIBLE);
           // Toast.makeText(getActivity(), "Please Enter Email", Toast.LENGTH_SHORT).show();
        } else if (password.equals("")) {
            //Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
            //writePassword.setVisibility(View.VISIBLE);

        } /*else if (radioGroupSelect.equals("")) {
            Toast.makeText(getActivity(), "Please Select Student Or Teacher", Toast.LENGTH_SHORT).show();
        } */else {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("authenticating");
            progressDialog.setCancelable(false);
            progressDialog.show();

            AuthRequest authRequest = new AuthRequest();
            authRequest.setUsernameOrEmail(emailOrUsername);
            //authRequest.setRememberMe(true);
            authRequest.setPassword(password);
            PreferencesManager.initializeInstance(getActivity());

            APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
            Call<AuthResponse> authResponseCall = apiInterface.signin(authRequest);
            authResponseCall.enqueue(new Callback<AuthResponse>() {
                @Override
                public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                    progressDialog.hide();
                    Log.e("Auth", "response : " + response.code());
                    //Log.e("Auth", "response : " + response.message());
                    if (response.code() == 200) {
                        //Log.e("Auth", "response : " + response.body().getAccessToken());
                        startActivity(new Intent(getActivity(), StudentProfileActivity.class));
                        //checkSignIn=response.body().getUserName();

                        PreferencesManager.getInstance().setToken(response.body().getAccessToken());
                        Log.e("Auth", "response : " + response.body().getAccessToken());
                        Toast.makeText(getActivity(), response.body().getUserName(), Toast.LENGTH_SHORT).show();
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
