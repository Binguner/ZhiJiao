package com.example.binguner.zhijiao.UI;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewDebug;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.Utils.AppBarStateChangeListener;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends AppCompatActivity {

    @BindView(R.id.about_back_btn)
    ImageView about_back_btn;
    @BindView(R.id.about_share)
    ImageView about_share;
    @BindView(R.id.about_toolbar)
    Toolbar about_toolbar;
    @BindView(R.id.about_appbarlayout)
    AppBarLayout about_appbarlayout;
    @BindView(R.id.app_logo)
    ImageView app_logo;
    @BindView(R.id.app_des)
    TextView app_des;
    @BindView(R.id.app_version)
    TextView app_version;
    @BindView(R.id.about_collapsingtoolbarlayout)
    CollapsingToolbarLayout about_collapsingtoolbarlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        initId();
        initViews();
        setListener();
    }

    private void setListener() {
        about_appbarlayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(state == State.EXPANDED){
                    //展开
                    Animation animation = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.transparent_show_up);
                    //app_des.setAnimation(animation);
                    //app_logo.setAnimation(animation);
                    //app_version.setAnimation(animation);
                    app_logo.setVisibility(View.VISIBLE);
                    app_des.setVisibility(View.VISIBLE);
                    app_version.setVisibility(View.VISIBLE);
                }else if(state == State.COLLAPSED){
                    //折叠
                    app_logo.setVisibility(View.INVISIBLE);
                    app_des.setVisibility(View.INVISIBLE);
                    app_version.setVisibility(View.INVISIBLE);

                }else if(state == State.IDLE){
                    //中间
                    //Animation animation = AnimationUtils.loadAnimation(AboutActivity.this,R.anim.transparent_show_up);
                    //app_des.setAnimation(animation);
                    //app_logo.setAnimation(animation);
                    //app_version.setAnimation(animation);
                    app_logo.setVisibility(View.VISIBLE);
                    app_des.setVisibility(View.VISIBLE);
                    app_version.setVisibility(View.VISIBLE);

                }
            }
        });
    }






    @OnClick(R.id.about_checkupdate_btn)
    void checkUpDate(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse("https://fir.im/ZhiJiao");
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.about_share)
    void goToShare(){
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT,"我正在使用「知教」，随时随地访问教务处，获取勤工助学，学校通知等即使信息，完全开源！欢迎使用 \n https://fir.im/ZhiJiao");
        startActivity(Intent.createChooser(textIntent,"分享"));
    }

    @OnClick(R.id.about_atar_btn)
    void goToStar(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri uri = Uri.parse("https://github.com/Nenguou/ZhiJiao");
        intent.setData(uri);
        startActivity(intent);
    }
   /* @OnClick(R.id.about_share)is
    void shareApp(View view){
        //Snackbar.make(view,"Share",Snackbar.LENGTH_SHORT).show();
    }*/

    @OnClick(R.id.about_back_btn)
    void back(){
        finish();
    }

    private void initViews() {
        about_collapsingtoolbarlayout.setTitle("「知教」");
        about_collapsingtoolbarlayout.setCollapsedTitleGravity(Gravity.LEFT);
        about_collapsingtoolbarlayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorWhite));
        about_collapsingtoolbarlayout.setExpandedTitleColor(getResources().getColor(R.color.colorTransparent));
    }

    private void initId() {
    }

}
