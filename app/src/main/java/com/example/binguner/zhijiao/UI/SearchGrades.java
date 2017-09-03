package com.example.binguner.zhijiao.UI;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.Grade_Adapter;
import com.example.binguner.zhijiao.CallBack.CallBackGrades;
import com.example.binguner.zhijiao.Entity.GradesBean;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.RxUtils.TYUTUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGrades extends AppCompatActivity {

    @BindView(R.id.grade_recyclerview)
    RecyclerView grade_recyclerview;
    //private int flag = 0;
    @BindView(R.id.grade_refresh)
    ImageView grade_refresh;

    private LinearLayoutManager linearLayoutManager;
    private Grade_Adapter grade_adapter;
    private static List<GradesBean.InfoBean> infoBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    private static int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_grades);
        ButterKnife.bind(this);
        initId();
        firstLoad();
        SaySth();
        initView();
    }

    private void SaySth() {
        if(flag == 0) {
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

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        grade_recyclerview.setLayoutManager(linearLayoutManager);
        grade_recyclerview.setHasFixedSize(true);
        grade_recyclerview.setAdapter(grade_adapter);
        grade_adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            int size = infoBeans.size();
            for(int i = 0;i < size; i++){
                grade_adapter.remove(0);
            }
            infoBeans.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void firstLoad() {


        /*tyutUtils.setCallBack(null, new CallBackGrades() {
            @Override
            public void callBackGrades(int status) {
                if(status == 1){

                }
            }
        });
        if(flag == 0){*/
            tyutUtils.GetGrades("2016006593","144517");
            /*flag = 1;
        }else if (flag == 1){
            // Do noting;
        }*/
    }

    @OnClick(R.id.grade_refresh)
    void refresh(){
        try{
            int size = infoBeans.size();
            for(int i = 0;i < size; i++){
                grade_adapter.remove(0);
            }
            infoBeans.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
        tyutUtils.GetGrades("2016006593","144517");
    }

    private void initId() {
        grade_adapter = new Grade_Adapter(R.layout.card_layout_grades,infoBeans);
        tyutUtils = new TYUTUtils(this,grade_adapter);
    }

    public static void addGradeDatas(List<GradesBean.InfoBean> minfoBeans){
        infoBeans.addAll(minfoBeans);
    }

    public static int getSize(){
        return infoBeans.size();
    }
    @OnClick(R.id.grades_back)
    void back(){
        finish();
    }
}
