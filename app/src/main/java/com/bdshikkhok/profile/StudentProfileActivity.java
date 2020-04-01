package com.bdshikkhok.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        ButterKnife.bind(this);

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
