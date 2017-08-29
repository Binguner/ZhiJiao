package com.example.binguner.zhijiao.Adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Entity.AnnouncementBean;
import com.example.binguner.zhijiao.R;

import java.util.List;

/**
 * Created by binguner on 2017/8/20.
 */

public class Work_Announcement_Adapter extends BaseQuickAdapter<AnnouncementBean.InfoBean,Work_Announcement_Adapter.MyViewHolder> {


    public Work_Announcement_Adapter(int layoutResId, @Nullable List<AnnouncementBean.InfoBean> data) {
        super(layoutResId, data);
    }

    /*@Override
    protected void convert(MyViewHolder helper, AnnouncementBean item) {
        int position = helper.getLayoutPosition();
        helper.setText(R.id.card_layout_news_title,item.getInfo().get(position).getTitle())
                .setText(R.id.card_layout_news_time,item.getInfo().get(position).getUpdate());
    }*/

    @Override
    protected void convert(MyViewHolder helper, AnnouncementBean.InfoBean item) {
        helper.setText(R.id.card_layout_news_title,item.getTitle())
                .setText(R.id.card_layout_news_time,item.getUpdate());
        helper.addOnClickListener(R.id.card_layout_news_constraintlayout);
    }

    public class MyViewHolder extends BaseViewHolder{

        private TextView card_layout_news_title,card_layout_news_time;

        public MyViewHolder(View view) {
            super(view);
            card_layout_news_title = view.findViewById(R.id.card_layout_news_title);
            card_layout_news_time = view.findViewById(R.id.card_layout_news_time);
        }
    }
}
