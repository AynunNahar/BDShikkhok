package com.bdshikkhok.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bdshikkhok.R;
import com.bdshikkhok.RetrofitClientInstance;
import com.bdshikkhok.profile.network.ProfileAPInterface;
import com.bdshikkhok.profile.network.ProfileResponse;
import com.bdshikkhok.profile.network.UpdateProfileRequest;
import com.bdshikkhok.profile.network.UpdateProfileResponse;
import com.bdshikkhok.util.PreferencesManager;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_image)
    ImageView stu_profile_img;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.student_first_name)
    TextInputEditText stu_firstName;
    @BindView(R.id.student_last_name)
    TextInputEditText stu_lastName;
    @BindView(R.id.student_institute)
    TextInputEditText stu_institute;
    @BindView(R.id.student_class)
    TextInputEditText stu_class;
    @BindView(R.id.student_contact_number)
    TextInputEditText stu_contactNumber;
    @BindView(R.id.student_email)
    TextInputEditText stu_email;
    @BindView(R.id.student_edit_profile)
    Button stu_editProfile;
    private ProgressDialog progressDialog;

    String update_FirstName,update_LastName,update_institute,update_class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(StudentProfileActivity.this);
        progressDialog.setMessage("authenticating");
        progressDialog.setCancelable(false);
        progressDialog.show();

        PreferencesManager.initializeInstance(StudentProfileActivity.this);
        ProfileAPInterface profileAPInterface = RetrofitClientInstance.getRetrofitInstance().create(ProfileAPInterface.class);
        Call<ProfileResponse> profileResponseCall = profileAPInterface.me("Bearer " + PreferencesManager.getInstance().getToken());
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                progressDialog.dismiss();
                if (response.code() == 200) {
                    stu_firstName.setText(response.body().getFirstName());
                    stu_lastName.setText(response.body().getLastName());
                    stu_email.setText(response.body().getEmail());
                    userName.setText(response.body().getUsername());
                    stu_class.setText(response.body().getClass().toString());
                    //stu_institute.setText(response.body().getInstitute().toString());
                } else {
                    Log.e("Auth", "Falure : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                progressDialog.dismiss();
                Log.e("Auth", "Falure : " + t.getMessage());
            }
        });

    }

    public void editProfileData() {

        update_FirstName = stu_firstName.getText().toString();
        update_LastName = stu_lastName.getText().toString();
        update_institute = stu_institute.getText().toString();
        update_class = stu_class.getText().toString();

        UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();
        updateProfileRequest.setFirstName(update_FirstName);
        updateProfileRequest.setLastName(update_LastName);
        updateProfileRequest.setInstitute(update_institute);
        //updateProfileRequest.setC

        ProfileAPInterface profileInterface = RetrofitClientInstance.getRetrofitInstance()
                .create(ProfileAPInterface.class);
        Call<UpdateProfileResponse> myProfileResponseCall = profileInterface.me("Bearer " + PreferencesManager.getInstance().getToken(),updateProfileRequest);
        myProfileResponseCall.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                Log.e("Auth", "response : " + response.code());
                Log.e("Auth", "response : " + response.message());
                if ((response.code() == 201)) {
                    Log.e("Auth", "response : " + "Successfully");
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                Log.e("Auth", "Failure : " + t.getMessage());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.passwordC) {
            return true;
        }

        if (id == R.id.signOut) {

        }
        if (id == R.id.student_updateProfile) {
            stu_firstName.setEnabled(true);
            stu_lastName.setEnabled(true);
            stu_institute.setEnabled(true);
            stu_class.setEnabled(true);
            stu_editProfile.setVisibility(View.VISIBLE);
            stu_editProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    editProfileData();
                    Toast.makeText(StudentProfileActivity.this, "clicked", Toast.LENGTH_LONG).show();

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
