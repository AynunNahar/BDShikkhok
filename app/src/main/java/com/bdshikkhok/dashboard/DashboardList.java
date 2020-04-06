package com.bdshikkhok.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bdshikkhok.R;
import com.bdshikkhok.auth.adapter.ViewPagerAdapter;
import com.bdshikkhok.auth.fragment.LogInFragment;
import com.bdshikkhok.auth.fragment.SignUpFragment;
import com.bdshikkhok.dashboard.fragment.TutorsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardList extends AppCompatActivity {

    @BindView(R.id.dashViewPager)
    ViewPager dashViewPager;
    @BindView(R.id.dashTabLayout)
    TabLayout dashTabLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_list);
        ButterKnife.bind(this);

        dashBoard();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void dashBoard() {
        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setTitle("BD শিক্ষক");
        //getSupportActionBar().setLogo(R.drawable.tutors);

        setupMyViewpager(dashViewPager);
        dashTabLayout.setupWithViewPager(dashViewPager);
    }

    void setupMyViewpager(ViewPager v) {
        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        vpa.myset(new TutorsFragment(), "Tutors");
        vpa.myset(new SignUpFragment(), "SignUp");
        v.setAdapter(vpa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_id = item.getItemId();

        if(menu_id == R.id.sort){
            Toast.makeText(DashboardList.this,"Sorted",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
