package com.example.binguner.zhijiao.Adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binguner on 2017/9/1.
 */

public class Class_Table_Adapter extends BaseQuickAdapter<ClassBean.TableBean,Class_Table_Adapter.MyViewHolder>{

    private List<ClassBean.TableBean> tableBeans = new ArrayList<>();
    public Class_Table_Adapter(int layoutResId, @Nullable List<ClassBean.TableBean> data) {
        super(layoutResId, data);
    }

    public void deleteAllBeans(){
        this.tableBeans.clear();
    }

    public void addBeans(List<ClassBean.TableBean> tableBeans){
        this.tableBeans.addAll(tableBeans);
        Log.d("classtatatata",tableBeans.get(0).getMonday());
        Log.d("classtatatata",tableBeans.get(1).getMonday());
        Log.d("classtatatata",tableBeans.get(2).getMonday());
        Log.d("classtatatata",tableBeans.get(3).getMonday());
        Log.d("classtatatata",tableBeans.get(4).getMonday());
        Log.d("classtatatata",tableBeans.get(5).getMonday());
        Log.d("classtatatata",tableBeans.get(6).getMonday());
        Log.d("classtatatata",tableBeans.get(7).getMonday());
        Log.d("classtatatata",tableBeans.get(8).getMonday());
        Log.d("classtatatata",tableBeans.get(9).getMonday());
        Log.d("classtatatata",tableBeans.get(10).getMonday());
    }
    @Override
    protected void convert(MyViewHolder helper, ClassBean.TableBean item) {
        int position = helper.getLayoutPosition();
        helper.setIsRecyclable(false);
        try {
            // 如果前一节的课和这一节的课一样，
            // 如果这一节为空
            // 则将这一节课设为不可见
            /*if(tableBeans.get(position).getMonday().contains("   ") || tableBeans.get(position).getMonday().equals(tableBeans.get(position-1).getMonday())){
                Log.d("classtatatata1",position+" "+"Monday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getTuesday().contains("   ") || tableBeans.get(position).getTuesday().equals(tableBeans.get(position-1).getTuesday())){
                Log.d("classtatatata1",position+" "+"Tuesday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getWednesday().contains("   ") || tableBeans.get(position).getWednesday().equals(tableBeans.get(position-1).getWednesday())){
                Log.d("classtatatata1",position+" "+"Wednesday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getThursday().contains("   ") || tableBeans.get(position).getThursday().equals(tableBeans.get(position-1).getThursday())){
                Log.d("classtatatata1",position+" "+"Thursday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getFriday().contains("   ") || tableBeans.get(position).getFriday().equals(tableBeans.get(position-1).getFriday())){
                Log.d("classtatatata1",position+" "+"Friday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getSaturday().contains("   ") || tableBeans.get(position).getSaturday().equals(tableBeans.get(position-1).getSaturday())){
                Log.d("classtatatata1",position+" "+"Saturday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }
            if(tableBeans.get(position).getSunday().contains("   ") || tableBeans.get(position).getSunday().equals(tableBeans.get(position-1).getSunday())){
                Log.d("classtatatata1",position+" "+"Sunday");
                helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);
            }*/
            try{
                if(position == 0){
                    helper.card_layout_class_table_mon.setHeight(500);
                }
                /*if(tableBeans.get(position).getMonday().equals(tableBeans.get(position+1).getMonday())){
                    Log.d("TableBeanTango1", "convert: "+position);
                    helper.card_layout_class_table_mon.setHeight(400);
                }*/
                Log.d("TableBeanTango","第"+position+"个");
                if(tableBeans.get(position).getMonday().contains("   ") ||  tableBeans.get(position).getMonday().equals(tableBeans.get(position-1).getMonday()) ){
                    helper.card_layout_class_table_mon.setVisibility(View.INVISIBLE);

                }
                if(tableBeans.get(position).getTuesday().contains("   ") ||  tableBeans.get(position).getTuesday().equals(tableBeans.get(position-1).getTuesday()) ){
                    helper.card_layout_class_table_tues.setVisibility(View.INVISIBLE);
                }
                if(tableBeans.get(position).getWednesday().contains("   ") ||  tableBeans.get(position).getWednesday().equals(tableBeans.get(position-1).getWednesday()) ){
                    helper.card_layout_class_table_wen.setVisibility(View.INVISIBLE);
                }
                if(tableBeans.get(position).getThursday().contains("   ") ||  tableBeans.get(position).getThursday().equals(tableBeans.get(position-1).getThursday())){
                    helper.card_layout_class_table_thur.setVisibility(View.INVISIBLE);
                }
                if(tableBeans.get(position).getFriday().contains("   ") ||  tableBeans.get(position).getFriday().equals(tableBeans.get(position-1).getFriday())){
                    helper.card_layout_class_table_fri.setVisibility(View.INVISIBLE);
                }
                if(tableBeans.get(position).getSaturday().contains("   ") ||  tableBeans.get(position).getSaturday().equals(tableBeans.get(position-1).getSaturday())){
                    helper.card_layout_class_table_sat.setVisibility(View.INVISIBLE);
                }
                if(tableBeans.get(position).getSunday().contains("   ") ||  tableBeans.get(position).getSunday().equals(tableBeans.get(position-1).getSunday())){
                    helper.card_layout_class_table_sun.setVisibility(View.INVISIBLE);
                }
            }catch (Exception e){

            }
            helper.setText(R.id.card_layout_time,tableBeans.get(position).getTime())
                    .setText(R.id.card_layout_class_table_mon,tableBeans.get(position).getMonday())
                    .setText(R.id.card_layout_class_table_tues,tableBeans.get(position).getTuesday())
                    .setText(R.id.card_layout_class_table_wen,tableBeans.get(position).getWednesday())
                    .setText(R.id.card_layout_class_table_thur,tableBeans.get(position).getThursday())
                    .setText(R.id.card_layout_class_table_fri,tableBeans.get(position).getFriday())
                    .setText(R.id.card_layout_class_table_sat,tableBeans.get(position).getSaturday())
                    .setText(R.id.card_layout_class_table_sun,tableBeans.get(position).getSunday());
        }catch (Exception e){
            //e.printStackTrace();
        }

    }

    /*@Override
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
    }*/

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
