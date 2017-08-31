package com.example.binguner.zhijiao.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Entity.GradesBean;
import com.example.binguner.zhijiao.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binguner on 2017/8/30.
 */

public class Grade_Adapter extends BaseQuickAdapter<GradesBean.InfoBean,Grade_Adapter.MyViewHolder> {


    public Grade_Adapter(int layoutResId, @Nullable List<GradesBean.InfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MyViewHolder helper, GradesBean.InfoBean item) {
        helper.setText(R.id.card_layout_grade_name,item.getName())
                .setText(R.id.card_layout_grade_score,item.getScore())
                .setText(R.id.card_layout_grade_credit,item.getCredit())
                .setText(R.id.card_layout_grade_property,item.getProperty());
    }

    public class MyViewHolder extends BaseViewHolder{

        @BindView(R.id.card_layout_grade_name)TextView card_layout_grade_name;  //大学物理
        @BindView(R.id.card_layout_grade_score)TextView card_layout_grade_score;    //100
        @BindView(R.id.card_layout_grade_credit)TextView card_layout_grade_credit;  //  2.5
        @BindView(R.id.card_layout_grade_property)TextView card_layout_grade_property;  //通识必修
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
