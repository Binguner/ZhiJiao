package com.example.binguner.zhijiao.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.Class_Table_Adapter;
import com.example.binguner.zhijiao.Adapter.Grade_Adapter;
import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;
import com.example.binguner.zhijiao.View.WaveView;

import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClassTable extends AppCompatActivity {


    @BindView(R.id.class_table_recyclerview) RecyclerView class_table_recyclerview;
    @BindView(R.id.class_table_isHardLoading) TextView class_table_isHardLoading;
    private LinearLayoutManager linearLayoutManager;
    private Class_Table_Adapter class_table_adapter;
    private static List<ClassBean.TableBean> tableBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    private static int flag = 0;
    private List<WaveView> waveViews = new ArrayList<>();
    @BindView(R.id.classTable_waveView1) WaveView classTable_waveView1;
    @BindView(R.id.classTable_waveView2) WaveView classTable_waveView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_class_table);
        ButterKnife.bind(this);
        intViews();
        initId();
        firstLoad();
        SaySth();


    }

    private void SaySth() {
        if(flag == 0){
            final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"如果数据加载失败，请点击刷新按钮 :)",Snackbar.LENGTH_SHORT);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snackbar.dismiss();
                }
            }).show();
            flag = 1;
        }

    }

    private void firstLoad() {
        SharedPreferences sharedPreferences = getSharedPreferences("mUserInfo",MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        tyutUtils.getClass(username,password);
        tyutUtils.setCallBack(null, null, new CallBackSuccedLogin() {
            @Override
            public void callBackLoginStats(int stats) {
                if(stats == 1){
                    class_table_isHardLoading.setVisibility(View.INVISIBLE);
                }else if(stats == 2){
                    final Snackbar snackbar = Snackbar.make(getWindow().getDecorView(),"请检查网络",Snackbar.LENGTH_SHORT);
                    snackbar.setAction("Check", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                            snackbar.dismiss();
                        }
                    }).show();
                }
            }
        });
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
        class_table_adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //oast.makeText(ClassTable.this,"第"+tableBeans.get(position).getFriday(),Toast.LENGTH_SHORT).show();
            }
        });

        waveViews.add(classTable_waveView1);
        waveViews.add(classTable_waveView2);
    }

    @OnClick(R.id.class_table_back)
    void back(){
        finish();
    }

    @OnClick(R.id.class_table_refresh)
    void refresh(){
        class_table_isHardLoading.setVisibility(View.VISIBLE);
        class_table_recyclerview.setVisibility(View.INVISIBLE);
        classTable_waveView1.setVisibility(View.VISIBLE);
        classTable_waveView2.setVisibility(View.VISIBLE);

        try{
            int size = tableBeans.size();
            for(int i = 0;i<size;i++){
                class_table_adapter.remove(0);
            }
            class_table_adapter.deleteAllBeans();
            tableBeans.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
       // tyutUtils = new TYUTUtils(class_table_adapter,this);
        firstLoad();


    }



    private void initId() {
        tyutUtils = new TYUTUtils(class_table_adapter,this,waveViews,class_table_recyclerview);
    }
}
