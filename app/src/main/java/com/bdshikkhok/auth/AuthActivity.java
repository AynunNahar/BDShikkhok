package com.bdshikkhok.auth;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bdshikkhok.R;
import com.bdshikkhok.auth.adapter.ViewPagerAdapter;
import com.bdshikkhok.auth.fragment.LogInFragment;
import com.bdshikkhok.auth.fragment.SignUpFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("BD শিক্ষক");
        getSupportActionBar().setLogo(R.drawable.tutors);

        setupMyViewpager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    void setupMyViewpager(ViewPager v) {
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.myset(new LogInFragment(), "LogIn");
        vpa.myset(new SignUpFragment(), "SignUp");
        v.setAdapter(vpa);
    }
}
