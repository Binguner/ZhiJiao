package com.example.binguner.zhijiao.UI;

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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassArray extends AppCompatActivity {


    @BindView(R.id.class_array_toolbar) Toolbar toolbar;
    @BindView(R.id.class_array_recyclerview) RecyclerView class_array_recyclerview;

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
        tyutUtils.getClassArray("2016006593","144517");
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
        tyutUtils = new TYUTUtils(this,classArrayAdapter);
    }

    @OnClick(R.id.class_array_back)
    void goBack(){
        onBackPressed();
    }
}
