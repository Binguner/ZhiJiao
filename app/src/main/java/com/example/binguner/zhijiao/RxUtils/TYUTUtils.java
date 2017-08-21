package com.example.binguner.zhijiao.RxUtils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Bean.AnnouncementBean;
import com.example.binguner.zhijiao.CallBack.CallBackStatus;
import com.example.binguner.zhijiao.Fragments.AnnouncementFragment;
import com.example.binguner.zhijiao.Services.TYUTservices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by binguner on 2017/8/20.
 */

public class TYUTUtils {

    private BaseQuickAdapter baseQuickAdapter;
    private CallBackStatus callBackStatus;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TYUTUtils(){}
    public TYUTUtils(BaseQuickAdapter baseQuickAdapter,SwipeRefreshLayout swipeRefreshLayout){
        this.baseQuickAdapter = baseQuickAdapter;
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public void setCallBack(CallBackStatus callBackStatus){
        this.callBackStatus = callBackStatus;
    }

    Gson gson = new GsonBuilder()
            .create();

    Retrofit retrofit = new Retrofit
            .Builder()
            .baseUrl("http://tyut.ngrok.cc/notice/list/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    TYUTservices services = retrofit.create(TYUTservices.class);

    public void getAnnouncements(int page){
        services.GetAnnouncement(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AnnouncementBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AnnouncementBean announcementBean) {
                        try{
                            Log.d("TESTTAG",announcementBean.getStatus());
                            Log.d("TESTTAG",announcementBean.getInfo().get(0).getTitle());
                            Log.d("TESTTAG",announcementBean.getInfo().get(0).getUpdate());
                            Log.d("TESTTAG",announcementBean.getInfo().get(1).getUpdate());

                            AnnouncementFragment.AddDatas(announcementBean.getInfo());
                            baseQuickAdapter.notifyItemInserted(AnnouncementFragment.getSize());
                            callBackStatus.callBackRefreshing(1);
                        }catch (Exception e){
                            Log.d("TESTTAG",e.toString());
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

}
