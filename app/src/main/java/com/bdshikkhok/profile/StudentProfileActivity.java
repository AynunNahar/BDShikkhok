package com.bdshikkhok.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bdshikkhok.R;
import com.bdshikkhok.RetrofitClientInstance;
import com.bdshikkhok.profile.network.ProfileAPIIterface;
import com.bdshikkhok.profile.network.ProfileResponse;
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
    TextInputEditText userName;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(StudentProfileActivity.this);
        progressDialog.setMessage("authenticating");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ProfileAPIIterface profileAPIIterface = RetrofitClientInstance.getRetrofitInstance().create(ProfileAPIIterface.class);
        Call<ProfileResponse> profileResponseCall=profileAPIIterface.me(2);
        profileResponseCall.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if(response.isSuccessful()){
                    stu_firstName.setText(response.body().getFirstName());
                    stu_lastName.setText(response.body().getLastName());
                    //stu_institute.setVisibility(View.VISIBLE);
                   // stu_institute.setText(response.body().getInstitute());
                    //stu_class.setText(response.body().getClass());
                    stu_email.setText(response.body().getEmail());
                    userName.setText(response.body().getUsername());
                    stu_class.setText(response.body().getClass().toString());
                    stu_institute.setText(response.body().getInstitute().toString());
                }
                else {

                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });

    }

    public void setProfileData(){

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

                    Toast.makeText(StudentProfileActivity.this, "clicked", Toast.LENGTH_LONG).show();

                }
            });
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
