package com.bdshikkhok.auth.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.fragment.app.Fragment;

import com.bdshikkhok.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpFragment extends Fragment implements
        RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.email)
    EditText sEmail;
    @BindView(R.id.password)
    EditText sPassword;
    @BindView(R.id.textInputEditTextName)
    EditText sName;
    @BindView(R.id.textInputEditTextMobail)
    EditText sMobile;
    @BindView(R.id.signupbutton)
    Button sSignUp;
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

        sSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = sName.getText().toString();
                mobile = sMobile.getText().toString();

                signUp();

            }
        });

        return v;
    }

    public void signUp() {
        final String mEmail = sEmail.getText().toString();
        password = sPassword.getText().toString();

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





