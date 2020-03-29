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

public class SignUpFragment extends Fragment implements
        RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.email)
    EditText registerEmail;
    @BindView(R.id.password)
    EditText registerPassword;
    @BindView(R.id.textInputEditTextName)
    EditText registerName;
    @BindView(R.id.textInputEditTextMobail)
    EditText registerMobile;
    @BindView(R.id.signupbutton)
    Button registerButton;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private String radioButtonSelect;
    private static final String TAG = SignUpFragment.class.getSimpleName();

    String name, email, mobile, password, takeKey;

    View v;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_sign_up, container, false);
        ButterKnife.bind(this, v);

        radioGroup.setOnCheckedChangeListener(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = registerName.getText().toString();
                mobile = registerMobile.getText().toString();

                signUp();

            }
        });

        return v;
    }

    public void signUp() {
        email = registerEmail.getText().toString();
        password = registerPassword.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setActivated(false);
        registerRequest.setAuthorities(new ArrayList<>());
        registerRequest.setCreatedBy("");
        registerRequest.setCreatedDate("");
        registerRequest.setEmail(email);
        registerRequest.setFirstName(name);
        registerRequest.setId(0);
        registerRequest.setImageUrl("");
        registerRequest.setLangKey("");
        registerRequest.setLastModifiedBy("");
        registerRequest.setLastModifiedDate("");
        registerRequest.setLastName("");
        registerRequest.setPassword(password);
        registerRequest.setLogin("");


        APIInterface apiInterface = RetrofitClientInstance.getRetrofitInstance().create(APIInterface.class);
        Call  registerResponseCall = apiInterface.register(registerRequest);

        registerResponseCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.e("Auth", "response : " + response.code());
                Log.e("Auth", "response : " + response.message());
                if ((response.code() == 201)) {
                    Log.e("Auth", "response : " + "Successfully loaded");
                    Toast.makeText(getActivity(), "Successfull", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Auth", "Falure : " + t.getMessage());

            }
        });

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radio_tutors:
                //if (checked)
                radioButtonSelect = "tutor";
                Log.e("signUp", radioButtonSelect);
                break;
            case R.id.radio_others:
                // if (checked)
                radioButtonSelect = "other";
                Log.e("signUp", radioButtonSelect);
                break;
        }
    }
}





