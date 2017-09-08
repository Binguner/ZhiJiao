package com.example.binguner.zhijiao.Adapter;

import android.icu.text.IDNA;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.binguner.zhijiao.Entity.ClassArrangeBean;
import com.example.binguner.zhijiao.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by binguner on 2017/9/3.
 */

public class ClassArrayAdapter extends BaseQuickAdapter<ClassArrangeBean.InfoBean,ClassArrayAdapter.MyViewHolder> {

    private List<ClassArrangeBean.InfoBean> infoBeans = new ArrayList<ClassArrangeBean.InfoBean>();

    public ClassArrayAdapter(int layoutResId, @Nullable List<ClassArrangeBean.InfoBean> data) {
        super(layoutResId, data);
        this.infoBeans = data;
    }

    public void deleteAllBeans(){
        this.infoBeans.clear();
    }

    public void addBeans(List<ClassArrangeBean.InfoBean> minfoBeans){
        this.infoBeans.addAll(minfoBeans);
    }

    @Override
    protected void convert(MyViewHolder helper, ClassArrangeBean.InfoBean item) {
        int position = helper.getLayoutPosition();
        try {
            Log.d("holyshirt","Now_inAdapternow");
            Log.d("holyshirt",""+infoBeans.size());
            Log.d("holyshirt",item.getAddress());
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            if (infoBeans.get(position).getName().isEmpty()){
                helper.card_layout_clas_array_parent.setVisibility(View.INVISIBLE);

            }else {
                helper.setText(R.id.class_array_name,infoBeans.get(position).getName());
                helper.setText(R.id.class_array_teacher,infoBeans.get(position).getTeacher());

                helper.setText(R.id.class_array_weekDay1,infoBeans.get(position).getWeekDay());
                helper.setText(R.id.class_array_starAndEnd1,infoBeans.get(position).getStarAndEnd());
                helper.setText(R.id.class_array_address1,infoBeans.get(position).getAddress());
                helper.setText(R.id.class_array_classRoom1,infoBeans.get(position).getClassRoom());
                Log.d("psotionTag",(position+1)+"个");
                if(infoBeans.get(position+1).getName().isEmpty()){
                    Log.d("psotionTag",(position+1)+"个");
                    helper.setText(R.id.class_array_weekDay2,infoBeans.get(position+1).getWeekDay());
                    helper.setText(R.id.class_array_starAndEnd2,infoBeans.get(position+1).getStarAndEnd());
                    helper.setText(R.id.class_array_address2,infoBeans.get(position+1).getAddress());
                    helper.setText(R.id.class_array_classRoom2,infoBeans.get(position+1).getClassRoom());
                }
                if(infoBeans.get(position+2).getName().isEmpty()){
                    helper.setText(R.id.class_array_weekDay3,infoBeans.get(position+2).getWeekDay());
                    helper.setText(R.id.class_array_starAndEnd3,infoBeans.get(position+2).getStarAndEnd());
                    helper.setText(R.id.class_array_address3,infoBeans.get(position+2).getAddress());
                    helper.setText(R.id.class_array_classRoom3,infoBeans.get(position+2).getClassRoom());
                }
            }
        }catch (Exception e){

        }

    }

    public class MyViewHolder extends BaseViewHolder{

        @BindView(R.id.class_array_name) TextView class_array_name;
        @BindView(R.id.class_array_teacher) TextView class_array_teacher;
        @BindView(R.id.card_layout_clas_array_parent) ConstraintLayout card_layout_clas_array_parent;
        @BindView(R.id.card_layout_class_array_constraintlayout1) ConstraintLayout card_layout_class_array_constraintlayout1;
        @BindView(R.id.card_layout_class_array_constraintlayout2) ConstraintLayout card_layout_class_array_constraintlayout2;
        @BindView(R.id.card_layout_class_array_constraintlayout3) ConstraintLayout card_layout_class_array_constraintlayout3;
        @BindView(R.id.class_array_weekDay1) TextView class_array_weekDay1;
        @BindView(R.id.class_array_weekDay2) TextView class_array_weekDay2;
        @BindView(R.id.class_array_weekDay3) TextView class_array_weekDay3;
        @BindView(R.id.class_array_starAndEnd1) TextView class_array_starAndEnd1;
        @BindView(R.id.class_array_starAndEnd2) TextView class_array_starAndEnd2;
        @BindView(R.id.class_array_starAndEnd3) TextView class_array_starAndEnd3;
        @BindView(R.id.class_array_address1) TextView class_array_address1;
        @BindView(R.id.class_array_address2) TextView class_array_address2;
        @BindView(R.id.class_array_address3) TextView class_array_address3;
        @BindView(R.id.class_array_classRoom1) TextView class_array_classRoom1;
        @BindView(R.id.class_array_classRoom2) TextView class_array_classRoom2;
        @BindView(R.id.class_array_classRoom3) TextView class_array_classRoom3;
        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
