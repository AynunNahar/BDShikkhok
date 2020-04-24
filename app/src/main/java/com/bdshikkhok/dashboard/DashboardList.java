package com.bdshikkhok.dashboard;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bdshikkhok.R;
import com.bdshikkhok.auth.adapter.ViewPagerAdapter;
import com.bdshikkhok.auth.fragment.SignUpFragment;
import com.bdshikkhok.dashboard.fragment.CoachingFragment;
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
    private Context mContext;
    private Activity mActivity;
    private PopupWindow mPopupWindow;
    boolean click = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_list);
        ButterKnife.bind(this);

        mContext = getApplicationContext();
        mActivity = DashboardList.this;


        dashBoard();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //Custom popUp window
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.filter_pop_up_window, null);
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT
                );

                if (Build.VERSION.SDK_INT >= 21) {
                    mPopupWindow.setElevation(5.0f);
                }
                ImageView closeButton = (ImageView) customView.findViewById(R.id.ib_close);
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPopupWindow.dismiss();
                    }
                });
                mPopupWindow.showAtLocation(fab, Gravity.BOTTOM, 100, 0);


               /* PopupMenu popupMenu = new PopupMenu(mContext, fab);
                popupMenu.getMenuInflater().inflate(R.menu.profile_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.student_updateProfile:
                                Toast.makeText(mActivity, "okay", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.signOut:
                                Toast.makeText(mActivity, "Clicked", Toast.LENGTH_SHORT).show();
                                return true;

                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();*/

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
        vpa.myset(new CoachingFragment(), "Coaching");
        v.setAdapter(vpa);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_id = item.getItemId();

        if (menu_id == R.id.sort) {
            Toast.makeText(DashboardList.this, "Sorted", Toast.LENGTH_SHORT).show();

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.sort_pop_up_window, null);
            mPopupWindow = new PopupWindow(
                    customView,
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.MATCH_PARENT
            );

            if (Build.VERSION.SDK_INT >= 21) {
                mPopupWindow.setElevation(5.0f);
            }
            ImageView closeButton = (ImageView) customView.findViewById(R.id.ib_close);
            TextView sort_discount = (TextView) customView.findViewById(R.id.discount);
            TextView sort_rating = (TextView) customView.findViewById(R.id.rating);
            TextView sort_priceHL = (TextView) customView.findViewById(R.id.priceHL);
            TextView sort_priceLH = (TextView) customView.findViewById(R.id.priceLH);
            sort_discount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            sort_rating.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            sort_priceHL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            sort_priceLH.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPopupWindow.dismiss();
                }
            });
            mPopupWindow.showAtLocation(fab, Gravity.TOP, 100, 0);

        }
        return super.onOptionsItemSelected(item);
    }
}
