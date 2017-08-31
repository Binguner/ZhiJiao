package com.example.binguner.zhijiao.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.Grade_Adapter;
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

    private LinearLayoutManager linearLayoutManager;
    private Grade_Adapter grade_adapter;
    private static List<GradesBean.InfoBean> infoBeans = new ArrayList<>();
    private TYUTUtils tyutUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search_grades);
        ButterKnife.bind(this);
        initId();
        firstLoad();

        initView();
    }

    private void initView() {
        linearLayoutManager = new LinearLayoutManager(this);
        grade_recyclerview.setLayoutManager(linearLayoutManager);
        grade_recyclerview.setHasFixedSize(true);
        grade_recyclerview.setAdapter(grade_adapter);
        grade_adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
    }

    private void firstLoad() {
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
