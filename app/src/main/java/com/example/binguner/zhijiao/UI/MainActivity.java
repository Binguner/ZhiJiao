package com.example.binguner.zhijiao.UI;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binguner.zhijiao.CallBack.CallBackType;
import com.example.binguner.zhijiao.Fragments.AchieveFragment;
import com.example.binguner.zhijiao.Fragments.AnnouncementFragment;
import com.example.binguner.zhijiao.Fragments.WorkFragment;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.Utils.AppBarStateChangeListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView main_menu_btn, fishpic, work_for_more_btn;
    private TextView work_type_name;
    private DrawerLayout main_activity_drawerlayout;
    private TabLayout main_tab_layout;
    private CoordinatorLayout main_coordinatorlayout;
    private CollapsingToolbarLayout main_activity_collapsingToolbarLayout;
    private NavigationView main_navigationview;
    private ViewPager main_viewpager;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private String[] titles = {"成绩", "助学", "公告"};
    private MyPageAdapter myPageAdapter;
    private TextView apptitle;
    private static CallBackType callBackType;
    private AppBarLayout main_activity_appbarlayou;
    private int anim_flag;

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
        try{
            String path = this.getCacheDir().toString();
            Log.d("filename1",path);
            SharedPreferences sharedPreferences = this.getSharedPreferences("cachePath", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cachepath",path);
            editor.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        Log.d("filename",this.getCacheDir()+"");
        View headerView = getLayoutInflater().inflate(R.layout.header_layout, null);
        main_navigationview.addHeaderView(headerView);

        fragments.add(AchieveFragment.newInstance());
        fragments.add(WorkFragment.newInstance());
        fragments.add(AnnouncementFragment.newInstance());

        myPageAdapter = new MyPageAdapter(getSupportFragmentManager());
        main_viewpager.setAdapter(myPageAdapter);
        main_tab_layout.setupWithViewPager(main_viewpager);


        //main_activity_collapsingToolbarLayout.setTitle("知教");

       // main_activity_collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.START);
        //main_activity_collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER_VERTICAL);
        //main_activity_collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.START);
        //main_activity_collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.);
        //main_activity_collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.getAbsoluteGravity(Gravity.START,100));
     //   main_activity_collapsingToolbarLayout.setCollapsed
        //main_activity_collapsingToolbarLayout.setExpandedTitleGravity(Gravity.LEFT);
        //main_activity_collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        //main_activity_collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));
    }

    private void setListener() {
        work_type_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.worklist, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.regular_profession:
                                callBackType.CallBackWorkType(1);
                                // Snackbar.make(main_coordinatorlayout, "固定岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.temporary_profession:
                                callBackType.CallBackWorkType(2);
                                // Snackbar.make(main_coordinatorlayout, "临时岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.special_profession:
                                callBackType.CallBackWorkType(3);
                                //  Snackbar.make(main_coordinatorlayout, "专业技术岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.off_campus_profession:
                                try {
                                    callBackType.CallBackWorkType(4);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                //  Snackbar.make(main_coordinatorlayout, "校外岗", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.interview_notify:
                                Snackbar.make(main_coordinatorlayout, "面试通知", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.admit_notify:
                                Snackbar.make(main_coordinatorlayout, "录用公告", Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        work_for_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.worklist, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.regular_profession:
                                callBackType.CallBackWorkType(1);
                               // Snackbar.make(main_coordinatorlayout, "固定岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.temporary_profession:
                                callBackType.CallBackWorkType(2);
                               // Snackbar.make(main_coordinatorlayout, "临时岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.special_profession:
                                callBackType.CallBackWorkType(3);
                              //  Snackbar.make(main_coordinatorlayout, "专业技术岗位", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.off_campus_profession:
                                try {
                                    callBackType.CallBackWorkType(4);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                              //  Snackbar.make(main_coordinatorlayout, "校外岗", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.interview_notify:
                                Snackbar.make(main_coordinatorlayout, "面试通知", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.admit_notify:
                                Snackbar.make(main_coordinatorlayout, "录用公告", Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        fishpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"我们只是信息的搬运工",Toast.LENGTH_SHORT).show();
                Snackbar.make(main_coordinatorlayout, "我们只是信息的搬运工", Snackbar.LENGTH_SHORT).show();
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
                switch (item.getItemId()) {
                    case R.id.login_logout:
                        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.about:
                        Toast.makeText(MainActivity.this, "About", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(MainActivity.this,AboutActivity.class);
                        startActivity(intent1);
                        break;
                }
                return true;
            }
        });

        main_activity_appbarlayou.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, AppBarStateChangeListener.State state) {
                if (state == State.EXPANDED) {
                     //展开
                    apptitle.setVisibility(View.VISIBLE);
                    anim_flag = 0;
                } else if (state == State.COLLAPSED) {
                    //折叠
                    apptitle.setVisibility(View.VISIBLE);
                    anim_flag = 1;
                } else if (state == State.IDLE) {
                    //中间
                    //Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.transparent_show_up);
                   // Animation animation1 = AnimationUtils.loadAnimation(MainActivity.this,R.anim.transparent_hide_in);
                    if(anim_flag == 0){
                       // apptitle.setAnimation(animation);
                    }else if(anim_flag == 1){
                       // apptitle.setAnimation(animation1);
                    }
                   // apptitle.setAnimation(animation);
                    apptitle.setVisibility(View.VISIBLE);
                }
            }
        });


        main_tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TabTag", "第" + tab.getPosition() + "个");
                //work_for_more_btn.setVisibility(View.VISIBLE);
                /*switch (tab.getPosition()){
                    case 0:

                        work_for_more_btn.setVisibility(View.INVISIBLE);
                    case 1:
                        Log.d("TabTag1","第"+tab.getPosition()+"个");
                        work_for_more_btn.setVisibility(View.VISIBLE);
                    case 2:
                        work_for_more_btn.setVisibility(View.INVISIBLE);
                }*/
                if (tab.getPosition() == 1) {
                    work_type_name.setVisibility(View.VISIBLE);
                    work_for_more_btn.setVisibility(View.VISIBLE);
                } else {
                    work_type_name.setVisibility(View.INVISIBLE);
                    work_for_more_btn.setVisibility(View.INVISIBLE);
                }
                // Snackbar.make(main_coordinatorlayout,"点击助学按钮获取更多信息",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("TabTag", "changed");
                //work_for_more_btn.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                //Snackbar.make(main_coordinatorlayout,"clicked",Snackbar.LENGTH_SHORT).show();
                //View view = ().inflate(R.id.main_tab_layout);
                /*PopupMenu popupMenu = new PopupMenu(MainActivity.this,tab.getCustomView());
                popupMenu.getMenuInflater().inflate(R.menu.worklist,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.regular_profession:
                                Snackbar.make(main_coordinatorlayout,"固定岗位",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.temporary_profession:
                                Snackbar.make(main_coordinatorlayout,"临时岗位",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.special_profession:
                                Snackbar.make(main_coordinatorlayout,"专业技术岗位",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.off_campus_profession:
                                Snackbar.make(main_coordinatorlayout,"校外岗",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.interview_notify:
                                Snackbar.make(main_coordinatorlayout,"面试通知",Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.admit_notify:
                                Snackbar.make(main_coordinatorlayout,"录用公告",Snackbar.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                try {
                    popupMenu.show();
                }catch (Exception e){
                    e.printStackTrace();
                }*/
            }
        });
    }

    public static void setCallBack(CallBackType callBack) {
        callBackType = callBack;
    }

    private void initId() {
        main_activity_collapsingToolbarLayout = findViewById(R.id.main_activity_collapsingToolbarLayout);
        work_type_name = findViewById(R.id.work_type_name);
        work_for_more_btn = findViewById(R.id.work_for_more_btn);
        main_coordinatorlayout = findViewById(R.id.main_coordinatorlayout);
        fishpic = findViewById(R.id.fishpic);
        apptitle = findViewById(R.id.apptitle);
        main_activity_appbarlayou = findViewById(R.id.main_activity_appbarlayou);
        main_tab_layout = findViewById(R.id.main_tab_layout);
        main_viewpager = findViewById(R.id.main_viewpager);
        main_menu_btn = findViewById(R.id.main_menu_btn);
        main_activity_drawerlayout = findViewById(R.id.main_activity_drawerlayout);
        main_navigationview = findViewById(R.id.main_navigationview);
    }

    public class MyPageAdapter extends FragmentPagerAdapter {

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
