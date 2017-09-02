package com.example.binguner.zhijiao.UI;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.Class_Table_Adapter;
import com.example.binguner.zhijiao.Adapter.Grade_Adapter;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassTable extends AppCompatActivity {


    @BindView(R.id.class_table_recyclerview)
    RecyclerView class_table_recyclerview;
    private LinearLayoutManager linearLayoutManager;
    private Class_Table_Adapter class_table_adapter;
    private static List<ClassBean.TableBean> tableBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_class_table);
        ButterKnife.bind(this);
        initId();
        firstLoad();
        SaySth();
        intViews();
        setListener();
    }

    private void SaySth() {
        final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"如果数据加载失败，请点击刷新按钮 :)",Snackbar.LENGTH_SHORT);
        snackbar.setAction("Undo", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        }).show();
    }

    private void firstLoad() {
        tyutUtils = new TYUTUtils(class_table_adapter,this);
        tyutUtils.getClass("2016006593","144517");

    }

    private void setListener() {

    }

    public static void addClassTableDatas(List<ClassBean.TableBean> mtableBeans){
        tableBeans.addAll(mtableBeans);
    }

    public static int getSize(){
        return tableBeans.size();
    }

    private void intViews() {
        linearLayoutManager = new LinearLayoutManager(this);
        class_table_recyclerview.setLayoutManager(linearLayoutManager);
        class_table_adapter = new Class_Table_Adapter(R.layout.card_layout_classtable,tableBeans);
        class_table_recyclerview.setHasFixedSize(true);
        class_table_recyclerview.setAdapter(class_table_adapter);
        class_table_adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        //class_table_adapter.isFirstOnly(true);
    }
    @OnClick(R.id.class_table_back)
    void back(){
        finish();;
    }

    @OnClick(R.id.class_table_refresh)
    void refresh(){

    }

    private void initId() {

    }
}
