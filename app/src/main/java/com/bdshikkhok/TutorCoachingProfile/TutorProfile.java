package com.bdshikkhok.TutorCoachingProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import com.bdshikkhok.R;
import com.bdshikkhok.auth.adapter.ViewPagerAdapter;
import com.bdshikkhok.auth.fragment.LogInFragment;
import com.bdshikkhok.auth.fragment.SignUpFragment;
import com.bdshikkhok.dashboard.fragment.CoachingFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TutorProfile extends AppCompatActivity {
  /*  @BindView(R.id.ratingBar)
    RatingBar ratingBar;*/
    @BindView(R.id.tutor_profileVPager)
    ViewPager mViewPager;
    @BindView(R.id.tutor_profile_tabL)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        ButterKnife.bind(this);

        /*ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(TutorProfile.this, "Stars: " +
                        (int) rating, Toast.LENGTH_SHORT).show();
            }
        });
*/
        viewpager();
    }
    private void viewpager() {
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("BD শিক্ষক");
        //getSupportActionBar().setLogo(R.drawable.tutors);

        setupMyViewpager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    void setupMyViewpager(ViewPager v) {
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.myset(new LogInFragment(), "LogIn2");
        vpa.myset(new SignUpFragment(), "SignUp");
        v.setAdapter(vpa);
    }
}
