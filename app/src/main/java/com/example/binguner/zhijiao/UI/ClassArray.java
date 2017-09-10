package com.example.binguner.zhijiao.UI;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.ClassArrayAdapter;
import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.ClassArrangeBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.View.WaveView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassArray extends AppCompatActivity {


    @BindView(R.id.class_array_toolbar) Toolbar toolbar;
    @BindView(R.id.class_array_recyclerview) RecyclerView class_array_recyclerview;
    @BindView(R.id.class_array_waveView1) WaveView class_array_waveView1;
    @BindView(R.id.class_array_waveView2) WaveView class_array_waveView2;
    @BindView(R.id.class_array_refresh) ImageView class_array_refresh;
    @BindView(R.id.isHardLoading) TextView isHardLoading;
    private List<WaveView> waveViews = new ArrayList<>();

    private ClassArrayAdapter classArrayAdapter;
    private LinearLayoutManager linearLayoutManager;
    private static List<ClassArrangeBean.InfoBean> infoBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_class_array);
        ButterKnife.bind(this);
        initViews();
        initId();
        firstLoad();

        setListener();
    }

    private void firstLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences("mUserInfo",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        //Animation animation = AnimationUtils.loadAnimation(this,R.anim.loging_ro);
        //class_array_refresh.startAnimation(animation);
        tyutUtils.getClassArray(username,password);
        tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
            @Override
            public void callBackLoginStats(int stats) {
                if(stats == 1){
                    isHardLoading.setVisibility(View.GONE);
                    class_array_recyclerview.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void setListener() {

    }

    private void initViews() {
        classArrayAdapter = new ClassArrayAdapter(R.layout.card_layout_class_array,infoBeans);
        linearLayoutManager = new LinearLayoutManager(this);
        class_array_recyclerview.setAdapter(classArrayAdapter);
        class_array_recyclerview.setLayoutManager(linearLayoutManager);
        class_array_recyclerview.setHasFixedSize(true);
        classArrayAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
    }

    public static int getSize(){
        return infoBeans.size();
    }

    private void initId() {
        waveViews.add(class_array_waveView1);
        waveViews.add(class_array_waveView2);
        //tyutUtils = new TYUTUtils(classArrayAdapter,this);
        tyutUtils = new TYUTUtils(classArrayAdapter,this,waveViews,class_array_recyclerview);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DeleteAllDatas();
        finish();
    }

    @OnClick(R.id.class_array_back)
    void goBack(){
        DeleteAllDatas();
        finish();
    }

    @OnClick(R.id.class_array_refresh)
    void refresh(){
        DeleteAllDatas();
        class_array_waveView1.setVisibility(View.VISIBLE);
        class_array_waveView2.setVisibility(View.VISIBLE);
        isHardLoading.setVisibility(View.VISIBLE);
        class_array_recyclerview.setVisibility(View.INVISIBLE);
        firstLoad();
    }

    private void DeleteAllDatas() {
        try{
            int size = infoBeans.size();
            for(int i = 0; i< size ;i++){
                classArrayAdapter.notifyItemRemoved(0);
                classArrayAdapter.notifyItemRangeChanged(0,infoBeans.size());
                infoBeans.remove(0);
            }

            classArrayAdapter.deleteAllBeans();
            classArrayAdapter.notifyDataSetChanged();
        }catch (Exception e){

        }
    }


}
