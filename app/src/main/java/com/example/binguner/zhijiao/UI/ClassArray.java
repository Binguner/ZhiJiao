package com.example.binguner.zhijiao.UI;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.ClassArrayAdapter;
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
        tyutUtils.getClassArray(username,password);
    }

    private void setListener() {

    }

    public static void addClassArrayDatas(List<ClassArrangeBean.InfoBean> minfoBeans){
        infoBeans = minfoBeans;
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

    @OnClick(R.id.class_array_back)
    void goBack(){
        finish();
    }

    @OnClick(R.id.class_array_refresh)
    void refresh(){
        DeleteAllDatas();
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
        }catch (Exception e){

        }
    }


}
