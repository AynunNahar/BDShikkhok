package com.bdshikkhok.auth.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.bdshikkhok.R;
import com.bdshikkhok.RetrofitClientInstance;
import com.bdshikkhok.auth.network.APIInterface;
import com.bdshikkhok.auth.network.request.RegisterRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment  {
    @BindView(R.id.email)
    EditText registerEmail;
    @BindView(R.id.password)
    EditText registerPassword;
    @BindView(R.id.first_name)
    EditText registerFirstName;
    @BindView(R.id.last_name)
    EditText registerLastName;
    @BindView(R.id.textInputEditTextName)
    EditText registerName;
    @BindView(R.id.textInputEditTextMobail)
    EditText registerMobile;
    @BindView(R.id.signupbutton)
    Button registerButton;


    private static final String TAG = SignUpFragment.class.getSimpleName();

    String name, email, mobile, password, takeKey,f_name,l_name;

    View v;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp();

            }
        });

        return v;
    }

    public void signUp() {
        email = registerEmail.getText().toString();
        password = registerPassword.getText().toString();
        name = registerName.getText().toString();
        mobile = registerMobile.getText().toString();
        f_name=registerFirstName.getText().toString();
        l_name=registerLastName.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest();

        registerRequest.setEmail(email);
        registerRequest.setPhone(mobile);
        registerRequest.setPassword(password);
        registerRequest.setUsername(name);
        registerRequest.setLastName(l_name);
        registerRequest.setFirstName(f_name);
        registerRequest.setUserType("student");

        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call<Void>  registerResponseCall = apiInterface.signup(registerRequest);

        registerResponseCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("Auth", "response : " + response.code());
                Log.e("Auth", "response : " + response.message());
                if ((response.code() == 201)) {
                    Log.e("Auth", "response : " + "Successfully");
                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("Auth", "Falure : " + t.getMessage());

            }
        });

    }

}





