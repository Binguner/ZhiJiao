package com.example.binguner.zhijiao.RxUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Bean.AnnouncementBean;
import com.example.binguner.zhijiao.Bean.WorkBean;
import com.example.binguner.zhijiao.BuildConfig;
import com.example.binguner.zhijiao.CallBack.CallBackStatus;
import com.example.binguner.zhijiao.Fragments.AnnouncementFragment;
import com.example.binguner.zhijiao.Fragments.WorkFragment;
import com.example.binguner.zhijiao.Services.TYUTservices;
import com.example.binguner.zhijiao.UI.MainActivity;
import com.example.binguner.zhijiao.Utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by binguner on 2017/8/20.
 */

public class TYUTUtils {

    private Context context;
    private BaseQuickAdapter baseQuickAdapter;
    private BaseQuickAdapter baseQuickAdapter1;
    private CallBackStatus callBackStatus;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<WorkBean.InfoBean> workInfoBeans = null;
    private String path;
    public TYUTUtils(Context context) {
        this.context = context;
    }

    public TYUTUtils(BaseQuickAdapter baseQuickAdapter,Context context) {
        this.baseQuickAdapter1 = baseQuickAdapter;
        this.context = context;
    }

    public TYUTUtils(BaseQuickAdapter baseQuickAdapter, SwipeRefreshLayout swipeRefreshLayout,Context context) {
        this.baseQuickAdapter = baseQuickAdapter;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.context = context;
    }

    public void setCallBack(CallBackStatus callBackStatus) {
        this.callBackStatus = callBackStatus;
    }

    Gson gson = new GsonBuilder()
            .create();


    private OkHttpClient getNewCilent() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        //SharedPreferences sharedPreferences = context.getSharedPreferences("cachePath",Context.MODE_PRIVATE);
        path = "/data/user/0/com.example.binguner.zhijiao/cache";
        Log.d("tetetetet",path);
        //设置缓存
        // File cacheFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECT)
        File cacheFile = new File(path, "ZhiJiao");
        //生成缓存 10m
        final Cache cache = new Cache(cacheFile, 1024 * 1024 * 10);
        //缓存拦截器
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtils.isAvailable(context)) {
                    Log.d("tetete","dsdsdssd");
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                if (NetworkUtils.isAvailable(context)) {
                    Log.d("tetete","sfsfs");
                    int maxAge = 0;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxAge)
                            .build();

                }
                return response;
            }
        };


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
        .build();

        OkHttpClient okHttpClient = builder.build();


        return okHttpClient;
    }

    Retrofit retrofit = new Retrofit
            .Builder()
            .client(getNewCilent())
            .baseUrl("http://tyut.ngrok.cc/notice/list/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();


    TYUTservices services = retrofit.create(TYUTservices.class);

    public void getAnnouncements(int page) {
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
                        try {
                            Log.d("TESTTAG", announcementBean.getStatus());
                            Log.d("TESTTAG", announcementBean.getInfo().get(0).getTitle());
                            Log.d("TESTTAG", announcementBean.getInfo().get(0).getUpdate());
                            Log.d("TESTTAG", announcementBean.getInfo().get(1).getUpdate());

                            AnnouncementFragment.AddDatas(announcementBean.getInfo());
                            baseQuickAdapter.notifyItemInserted(AnnouncementFragment.getSize());
                            callBackStatus.callBackRefreshing(1);
                        } catch (Exception e) {
                            Log.d("TESTTAG", e.toString());
                        }
                        //swipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    public void getWorkInfo(int type, int page) {
        services.GetWorkInfo(type, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                /*.filter(new Func1<WorkBean, Boolean>() {
                    @Override
                    public Boolean call(WorkBean workBean) {
                        int size = workBean.getInfo().size();
                        for(int i = 0;i < size;i++){
                            if(workBean.getInfo().get(i).getTitle().isEmpty()){
                                return false;
                            }
                        }
                        return true;
                    }
                })*/
                .subscribe(new Subscriber<WorkBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("workTag", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("workTag", "onError : " + e.toString());
                    }

                    @Override
                    public void onNext(WorkBean workBean) {
                        try {
                            workInfoBeans = new ArrayList<>();
                           /* Log.d("workTag", workBean.getStatus());
                            Log.d("workTag", workBean.getType());
                            Log.d("workTag", workBean.getInfo().get(0).getTitle());
                            Log.d("workTag", workBean.getInfo().get(1).getTitle());
                            Log.d("workTag", workBean.getInfo().get(2).getTitle());*/
                            for (int i = 0; i < workBean.getInfo().size(); i += 2) {
                               /* Log.d("workTag","第"+i+"个");
                                Log.d("workTag", workBean.getInfo().get(i).getTitle());*/
                                //if(workBean.getInfo().get(i).getTitle().){

                                //}else {
                                workInfoBeans.add(workBean.getInfo().get(i));
                                //}

                            }
                        } catch (Exception e) {
                            Log.d("workTag", "Exception : " + e.toString());
                        }

                        for (int i = 0; i < workInfoBeans.size(); i++) {
                            //Log.d("workTag","第"+i+"个");
                            //Log.d("workTag",workInfoBeans.get(i).getTitle());
                        }
                        Log.d("whasdad", "add ");
                        WorkFragment.addWorkDatas(workInfoBeans);
                        baseQuickAdapter1.notifyItemInserted(WorkFragment.getSize());


                    }
                });
    }

}
