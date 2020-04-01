package com.bdshikkhok.profile;

import androidx.appcompat.app.AppCompatActivity;

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

import butterknife.BindView;

public class StudentProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_image)
    ImageView stu_profile_img;
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.student_first_name)
    EditText stu_firstName;
    @BindView(R.id.student_last_name)
    EditText stu_lastName;
    @BindView(R.id.student_institute)
    EditText stu_institute;
    @BindView(R.id.student_class)
    EditText stu_class;
    @BindView(R.id.student_contact_number)
    EditText stu_contactNumber;
    @BindView(R.id.student_email)
    EditText stu_email;
    @BindView(R.id.student_edit_profile)
    Button stu_editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);


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
            startActivity(new Intent(this, StudentProfileActivity.class));
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
