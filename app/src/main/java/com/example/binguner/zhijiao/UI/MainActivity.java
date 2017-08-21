package com.example.binguner.zhijiao.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binguner.zhijiao.Fragments.AchieveFragment;
import com.example.binguner.zhijiao.Fragments.AnnouncementFragment;
import com.example.binguner.zhijiao.Fragments.WorkFragment;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.Utils.AppBarStateChangeListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView main_menu_btn,fishpic;
    private DrawerLayout main_activity_drawerlayout;
    private TabLayout main_tab_layout;
    private NavigationView main_navigationview;
    private ViewPager main_viewpager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"成绩","助学","公告"};
    private MyPageAdapter myPageAdapter;
    private TextView apptitle;
    private AppBarLayout main_activity_appbarlayou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_Base);
        setContentView(R.layout.activity_main);
        initId();
        initViews();
        setListener();
    }

    private void initViews() {
        View headerView = getLayoutInflater().inflate(R.layout.header_layout,null);
        main_navigationview.addHeaderView(headerView);

        fragments.add(AchieveFragment.newInstance());
        fragments.add(WorkFragment.newInstance());
        fragments.add(AnnouncementFragment.newInstance());

        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        main_viewpager.setAdapter(myPageAdapter);
        main_tab_layout.setupWithViewPager(main_viewpager);

    }

    private void setListener() {
        fishpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"我们只是信息的搬运工",Toast.LENGTH_SHORT).show();
            }
        });
        main_menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_activity_drawerlayout.openDrawer(Gravity.LEFT);
            }
        });

        main_navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.login_logout:
                        Toast.makeText(MainActivity.this,"Login",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                    case R.id.about:
                        Toast.makeText(MainActivity.this,"About",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        main_activity_appbarlayou.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if(state == State.EXPANDED){
                    //展开
                    apptitle.setVisibility(View.INVISIBLE);
                }else if(state == State.COLLAPSED){
                    //折叠
                    apptitle.setVisibility(View.VISIBLE);
                }else if(state == State.IDLE){
                    //中间
                    apptitle.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void initId() {
        fishpic = findViewById(R.id.fishpic);
        apptitle = findViewById(R.id.apptitle);
        main_activity_appbarlayou = findViewById(R.id.main_activity_appbarlayou);
        main_tab_layout = findViewById(R.id.main_tab_layout);
        main_viewpager = findViewById(R.id.main_viewpager);
        main_menu_btn = findViewById(R.id.main_menu_btn);
        main_activity_drawerlayout = findViewById(R.id.main_activity_drawerlayout);
        main_navigationview = findViewById(R.id.main_navigationview);
    }

    public class MyPageAdapter extends FragmentPagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
