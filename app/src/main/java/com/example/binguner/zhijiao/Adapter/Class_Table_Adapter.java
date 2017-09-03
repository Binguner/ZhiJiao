package com.example.binguner.zhijiao.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binguner on 2017/9/1.
 */

public class Class_Table_Adapter extends BaseQuickAdapter<ClassBean.TableBean,Class_Table_Adapter.MyViewHolder>{


    public Class_Table_Adapter(int layoutResId, @Nullable List<ClassBean.TableBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MyViewHolder helper, ClassBean.TableBean item) {
        helper.setText(R.id.card_layout_time,item.getTime())
                .setText(R.id.card_layout_class_table_mon,item.getMonday())
                .setText(R.id.card_layout_class_table_tues,item.getTuesday())
                .setText(R.id.card_layout_class_table_wen,item.getWednesday())
                .setText(R.id.card_layout_class_table_thur,item.getThursday())
                .setText(R.id.card_layout_class_table_fri,item.getFriday())
                .setText(R.id.card_layout_class_table_sat,item.getSaturday())
                .setText(R.id.card_layout_class_table_sun,item.getSunday());
        helper.addOnClickListener(R.id.card_layout_time);
        helper.addOnClickListener(R.id.card_layout_class_table_mon);
        helper.addOnClickListener(R.id.card_layout_class_table_tues);
        helper.addOnClickListener(R.id.card_layout_class_table_wen);
        helper.addOnClickListener(R.id.card_layout_class_table_thur);
        helper.addOnClickListener(R.id.card_layout_class_table_fri);
        helper.addOnClickListener(R.id.card_layout_class_table_sat);
        helper.addOnClickListener(R.id.card_layout_class_table_sun);
    }

    public class MyViewHolder extends BaseViewHolder{

        @BindView(R.id.card_layout_time)  Button card_layout_time;
        @BindView(R.id.card_layout_class_table_mon) Button card_layout_class_table_mon;
        @BindView(R.id.card_layout_class_table_tues) Button card_layout_class_table_tues;
        @BindView(R.id.card_layout_class_table_wen) Button card_layout_class_table_wen;
        @BindView(R.id.card_layout_class_table_thur) Button card_layout_class_table_thur;
        @BindView(R.id.card_layout_class_table_fri) Button card_layout_class_table_fri;
        @BindView(R.id.card_layout_class_table_sat) Button card_layout_class_table_sat;
        @BindView(R.id.card_layout_class_table_sun) Button card_layout_class_table_sun;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
