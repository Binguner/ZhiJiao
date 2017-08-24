package com.example.binguner.zhijiao.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Bean.WorkBean;
import com.example.binguner.zhijiao.R;

import java.util.List;

/**
 * Created by binguner on 2017/8/23.
 */

public class WorkInfo_Adapter extends BaseQuickAdapter<WorkBean.InfoBean, WorkInfo_Adapter.MyViewHolder> {

    private Context context;

    public WorkInfo_Adapter(int layoutResId, @Nullable List<WorkBean.InfoBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    public void removeAllDatas(){

    }

    @Override
    public void remove(int position) {
        super.remove(position);
        //getData().remove(position);
        Log.d("WorkFre","adapter中还有"+getData().size()+"个");
    }

    @Override
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener, RecyclerView recyclerView) {
        super.setOnLoadMoreListener(requestLoadMoreListener, recyclerView);
    }

    @Override
    protected void convert(MyViewHolder helper, WorkBean.InfoBean item) {
        int position = helper.getLayoutPosition();
        Log.d("wtwtwtwtw", "here");
        try {
            if (item.getApplyStatus().contains("正在报名")) {

               // helper.card_layout_work_info_type.setTextColor(R.color.colorGreen);
                helper.card_layout_work_info_type.setTextColor(context.getResources().getColor(R.color.colorGreen));
                helper.card_layout_work_info_peoplelimit.setTextColor(context.getResources().getColor(R.color.colorGreen));
                helper.card_layout_work_info_title.setTextColor(context.getResources().getColor(R.color.colorBlack));
                helper.card_layout_work_info_time.setTextColor(context.getResources().getColor(R.color.colorBlue));
                Log.d("wtwtwtwtw", "here1");
                helper.setText(R.id.card_layout_work_info_type, item.getType())
                        .setText(R.id.card_layout_work_info_title, item.getTitle())
                        .setText(R.id.card_layout_work_info_time, item.getUptime())
                        .setText(R.id.card_layout_work_info_peoplelimit, item.getStationStatus());

                if (item.getSexlimit().endsWith("不限制") || item.getSexlimit().endsWith(" 不限制 ") || item.getSexlimit().endsWith("不限制 ") || item.getSexlimit().endsWith(" 不限制")) {
                    helper.card_layout_work_info_boyicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_girlicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_boyicon.setImageResource(R.mipmap.boy_blue_48);
                    helper.card_layout_work_info_girlicon.setImageResource(R.mipmap.girl_blue_48);
                } else if (item.getSexlimit().endsWith("男") || item.getSexlimit().endsWith(" 男 ") || item.getSexlimit().endsWith("男 ") || item.getSexlimit().endsWith(" 男")) {
                    helper.card_layout_work_info_boyicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_boyicon.setImageResource(R.mipmap.boy_blue_48);
                    helper.card_layout_work_info_girlicon.setVisibility(View.INVISIBLE);
                } else if (item.getSexlimit().endsWith("女") || item.getSexlimit().endsWith(" 女 ") || item.getSexlimit().endsWith("女 ") || item.getSexlimit().endsWith(" 女")) {
                    helper.card_layout_work_info_girlicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_girlicon.setImageResource(R.mipmap.girl_blue_48);
                    helper.card_layout_work_info_boyicon.setVisibility(View.INVISIBLE);
                }

            } else if (item.getApplyStatus().endsWith("报名已截止")) {
                Log.d("wtwtwtwtw", "here2");
                helper.setText(R.id.card_layout_work_info_type, item.getType())
                        .setText(R.id.card_layout_work_info_title, item.getTitle())
                        .setText(R.id.card_layout_work_info_time, item.getUptime())
                        .setText(R.id.card_layout_work_info_peoplelimit, item.getStationStatus());
                helper.card_layout_work_info_type.setTextColor(context.getResources().getColor(R.color.colorGray));
                helper.card_layout_work_info_title.setTextColor(context.getResources().getColor(R.color.colorGray));
                helper.card_layout_work_info_time.setTextColor(context.getResources().getColor(R.color.colorGray));
                helper.card_layout_work_info_peoplelimit.setTextColor(context.getResources().getColor(R.color.colorGray));
                if (item.getSexlimit().endsWith("不限制") || item.getSexlimit().endsWith(" 不限制 ") || item.getSexlimit().endsWith("不限制 ") || item.getSexlimit().endsWith(" 不限制")) {
                    helper.card_layout_work_info_boyicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_girlicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_boyicon.setImageResource(R.mipmap.boy_grey_48);
                    helper.card_layout_work_info_girlicon.setImageResource(R.mipmap.girl_grey_48);
                } else if (item.getSexlimit().endsWith("男") || item.getSexlimit().endsWith(" 男 ") || item.getSexlimit().endsWith("男 ") || item.getSexlimit().endsWith(" 男")) {
                    helper.card_layout_work_info_boyicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_boyicon.setImageResource(R.mipmap.boy_grey_48);
                    helper.card_layout_work_info_girlicon.setVisibility(View.INVISIBLE);
                } else if (item.getSexlimit().endsWith("女") || item.getSexlimit().endsWith(" 女 ") || item.getSexlimit().endsWith("女 ") || item.getSexlimit().endsWith(" 女")) {
                    helper.card_layout_work_info_girlicon.setVisibility(View.VISIBLE);
                    helper.card_layout_work_info_girlicon.setImageResource(R.mipmap.girl_grey_48);
                    helper.card_layout_work_info_girlicon.setVisibility(View.INVISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        helper.addOnClickListener(R.id.card_layout_work_info_constraintlayout);

    }

    public class MyViewHolder extends BaseViewHolder {
        private TextView card_layout_work_info_type, card_layout_work_info_title, card_layout_work_info_time, card_layout_work_info_peoplelimit;
        private ImageView card_layout_work_info_boyicon, card_layout_work_info_girlicon;

        public MyViewHolder(View view) {
            super(view);
            card_layout_work_info_girlicon = view.findViewById(R.id.card_layout_work_info_girlicon);
            card_layout_work_info_boyicon = view.findViewById(R.id.card_layout_work_info_boyicon);
            card_layout_work_info_type = view.findViewById(R.id.card_layout_work_info_type);
            card_layout_work_info_title = view.findViewById(R.id.card_layout_work_info_title);
            card_layout_work_info_time = view.findViewById(R.id.card_layout_work_info_time);
            card_layout_work_info_peoplelimit = view.findViewById(R.id.card_layout_work_info_peoplelimit);
        }
    }
}
