package com.example.binguner.zhijiao.RxUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.binguner.zhijiao.Adapter.ClassArrayAdapter;
import com.example.binguner.zhijiao.Adapter.Class_Table_Adapter;
import com.example.binguner.zhijiao.CallBack.CallBackGrades;
import com.example.binguner.zhijiao.CallBack.CallBackSuccedLogin;
import com.example.binguner.zhijiao.Entity.AnnouncementBean;
import com.example.binguner.zhijiao.Entity.ClassArrangeBean;
import com.example.binguner.zhijiao.Entity.ClassBean;
import com.example.binguner.zhijiao.Entity.GradesBean;
import com.example.binguner.zhijiao.Entity.LoginBean;
import com.example.binguner.zhijiao.Entity.WorkBean;
import com.example.binguner.zhijiao.BuildConfig;
import com.example.binguner.zhijiao.CallBack.CallBackStatus;
import com.example.binguner.zhijiao.Fragments.AnnouncementFragment;
import com.example.binguner.zhijiao.Fragments.WorkFragment;
import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.Services.TYUTservices;
import com.example.binguner.zhijiao.UI.ClassArray;
import com.example.binguner.zhijiao.UI.ClassTable;
import com.example.binguner.zhijiao.UI.LoginActivity;
import com.example.binguner.zhijiao.UI.NewClassTable;
import com.example.binguner.zhijiao.UI.SearchGrades;
import com.example.binguner.zhijiao.Utils.AddCookiesInterceptor;
import com.example.binguner.zhijiao.Utils.NetworkUtils;
import com.example.binguner.zhijiao.Utils.ReceivedCookiesInterceptor;

import com.example.binguner.zhijiao.Utils.UserUtil;
import com.example.binguner.zhijiao.View.WaveView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
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
    private BaseQuickAdapter baseQuickAdapter2;
    private BaseQuickAdapter baseQuickAdapter3;
    private CallBackStatus callBackStatus;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<WorkBean.InfoBean> workInfoBeans = null;
    private String path;
    private CallBackGrades callBackGrades;
    private CallBackSuccedLogin callBackSuccedLogin;
    private ClassArrayAdapter classArrayAdapter;
    private List<WaveView> waveViews = new ArrayList<>();
    //private WaveView waveView;
    private RecyclerView recyclerView;
    private Class_Table_Adapter class_table_adapter;
    // private String BigcookleStr;
    private static UserUtil userUtil = new UserUtil();


    public TYUTUtils(Context context) {
        this.context = context;
    }

    public TYUTUtils(BaseQuickAdapter baseQuickAdapter, Context context) {
        this.baseQuickAdapter1 = baseQuickAdapter;
        this.context = context;
    }

    public TYUTUtils(BaseQuickAdapter baseQuickAdapter, Context context,List<WaveView> waveViews, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.baseQuickAdapter1 = baseQuickAdapter;
        this.waveViews = waveViews;
        this.context = context;
    }

    public TYUTUtils(Class_Table_Adapter class_table_adapter, Context context,List<WaveView> waveViews, RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.class_table_adapter = class_table_adapter;
        this.waveViews = waveViews;
        this.context = context;
    }

    public TYUTUtils(BaseQuickAdapter baseQuickAdapter, SwipeRefreshLayout swipeRefreshLayout, Context context) {
        this.baseQuickAdapter = baseQuickAdapter;
        this.swipeRefreshLayout = swipeRefreshLayout;
        this.context = context;
    }

    public TYUTUtils(ClassArrayAdapter classArrayAdapter, Context context,List<WaveView> waveViews,RecyclerView recyclerView) {
        this.waveViews = waveViews;
        this.recyclerView = recyclerView;
        this.classArrayAdapter = classArrayAdapter;
        this.context = context;
    }

    public TYUTUtils(Context context, BaseQuickAdapter baseQuickAdapter,List<WaveView> waveViews,RecyclerView recyclerView) {
        this.context = context;
        this.waveViews = waveViews;
        this.recyclerView = recyclerView;
        this.baseQuickAdapter2 = baseQuickAdapter;
    }

    public void setCallBack(@Nullable CallBackStatus callBackStatus, @Nullable CallBackGrades callBackGrades,@Nullable CallBackSuccedLogin callBackSuccedLogin) {
        this.callBackStatus = callBackStatus;
        this.callBackGrades = callBackGrades;
        this.callBackSuccedLogin = callBackSuccedLogin;
    }

    Gson gson = new GsonBuilder()
            .create();


    private OkHttpClient getNewCilent(Context mContext) {


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }
        //SharedPreferences sharedPreferences = context.getSharedPreferences("cachePath",Context.MODE_PRIVATE);
        path = "/data/user/0/com.example.binguner.zhijiao/cache";
        Log.d("tetetetet", path);
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
                    Log.d("tetete", "unAvailable");
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);

                if (NetworkUtils.isAvailable(context)) {
                    Log.d("tetete", "isAvailable");
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
                //.addInterceptor(new AddCookiesInterceptor(context))
                //.addInterceptor(new ReceivedCookiesInterceptor(context))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .cache(cache)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

       /* builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                List<String> cookies = response.headers("Set-Cookie");
                JSESSIONID=1C01E919EA03FC3D4ED35F71A8DEFB71;path=/;HttpOnly
                String cookieStr = "";
                if (cookies != null && cookies.size() > 0) {
                    for (int i = 0; i < cookies.size(); i++) {
                        cookieStr += cookies.get(i);
                    }
                }
                Log.d("cookieStrString1", cookieStr);
                //BigcookleStr = cookieStr;
                //SharedPreferences sharedPreferences = context.getSharedPreferences("cookieStr",Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                //editor.putString("cookie",null);
                //editor.commit();
                userUtil.saveUserCookieId(cookieStr);
                try {
                    Log.d("cookieStrString2", userUtil.getCookieId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return response;
            }
        });

        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("cookieStrString", "c");
                String cookieStr = userUtil.getCookieId();
                //String cookieStr = BigcookleStr;
                //Log.d("cookieStrString",cookieStr);
                // SharedPreferences sharedPreferences = context.getSharedPreferences("cookieStr",Context.MODE_PRIVATE);

                //String cookieStr = sharedPreferences.getString("cookie",null);
                try {
                    Log.d("cookieStrString", cookieStr + "");
                   // Log.d("cookieStrString3", userUtil.getCookieId());
                    Log.d("cookieStrString", "d");
                } catch (Exception e) {
                    Log.d("cookieStrString3", e.toString());
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(cookieStr)) {
                    return chain.proceed(chain.request().newBuilder().header("Cookie", cookieStr).build());
                }
                return chain.proceed(chain.request());
            }
        });*/
        return builder.build();
    }

    Retrofit retrofit = new Retrofit
            .Builder()
            .client(getNewCilent(context))
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
//                        callBackSuccedLogin.callBackLoginStats(1);
                        Log.d("adasadadaddsads","asd");
                        /*try{
                            callBackSuccedLogin.callBackLoginStats(1);
                        }catch (Exception e){
                            Log.d("adasadadaddsads",e.toString());
                        }*/
                        if(!NetworkUtils.isAvailable(context)){
                            Log.d("adasadadaddsads","网没了");
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            callBackSuccedLogin.callBackLoginStats(1);
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.d("adasadadaddsads",e.toString());
                        if(!NetworkUtils.isAvailable(context)){
                            Toast.makeText(context,"请检查网络",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "加载失败，请重试 :)", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onNext(AnnouncementBean announcementBean) {
                        try {
                            AnnouncementFragment.AddDatas(announcementBean.getInfo());
                            baseQuickAdapter.notifyDataSetChanged();
                            baseQuickAdapter.notifyItemInserted(AnnouncementFragment.getSize());
                            //callBackStatus.callBackRefreshing(1);
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
                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            callBackSuccedLogin.callBackLoginStats(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else{
                            Toast.makeText(context, "加载失败，请重试 :)", Toast.LENGTH_SHORT).show();
                        }
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
                        baseQuickAdapter1.notifyDataSetChanged();
                        baseQuickAdapter1.notifyItemInserted(WorkFragment.getSize());


                    }
                });
    }

    public void firstLogin(final String username, final String password) {
        services.FirsrLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("LoginTag", "Compleded");
                        SharedPreferences sharedPreferences = context.getSharedPreferences("mUserInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("password", password);
                        editor.commit();
                        Toast.makeText(context,"登陆成功",Toast.LENGTH_SHORT).show();
                        callBackSuccedLogin.callBackLoginStats(1);
                        //1 succeed    0 failed
                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            callBackSuccedLogin.callBackLoginStats(1);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBackSuccedLogin.callBackLoginStats(0);
                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            Toast.makeText(context, "请检查用户名密码，服务器出错了..或者有可能教务处挂了，请重试，或者联系我⬇️ :(", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("LoginTag", "onError: " + e.toString());
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        try {
                            Log.d("LoginTag", loginBean.getUsername());
                            Log.d("LoginTag", loginBean.getMessage());
                            SharedPreferences sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", loginBean.getUsername());
                            editor.commit();
                        }catch (HttpException e){
                            Toast.makeText(context,"请检查网络(",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void GetGrades(final String username, final String password/*final String username, String password*/) {
        services.GetGrades(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GradesBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("LoginTag", "Completed");
                        for(int i = 0; i<waveViews.size();i++){
                            waveViews.get(i).setVisibility(View.INVISIBLE);
                        }
                        recyclerView.setVisibility(View.VISIBLE);

                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            callBackSuccedLogin.callBackLoginStats(1);
                        }
                        //callBackGrades.callBackGrades(1);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            Toast.makeText(context, "加载失败，请重试 :)", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("LoginTag", e.toString());
                        // callBackGrades.callBackGrades(0);
                    }

                    @Override
                    public void onNext(GradesBean gradesBean) {
                        Log.d("finallytest", gradesBean.getMessage());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getCredit());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getName());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getProperty());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getScore());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getCredit());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getName());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getProperty());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getScore());
                        SearchGrades.addGradeDatas(gradesBean.getInfo());
                        baseQuickAdapter2.notifyItemInserted(SearchGrades.getSize());

                    }
                });

        /*services.FirsrLogin(username, password)
                .flatMap(new Func1<LoginBean, Observable<GradesBean>>() {
                    @Override
                    public Observable<GradesBean> call(LoginBean loginBean) {
                        if (loginBean.getMessage().contains("登陆成功")) {
                            return services.GetGrades(username,password);

                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GradesBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("finallytest", "Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("finallytest", "onError:" + e.toString());
                    }

                    @Override
                    public void onNext(GradesBean gradesBean) {
                        Log.d("finallytest", gradesBean.getMessage());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getCredit());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getName());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getProperty());
                        Log.d("finallytest", gradesBean.getInfo().get(0).getScore());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getCredit());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getName());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getProperty());
                        Log.d("finallytest", gradesBean.getInfo().get(1).getScore());
                        SearchGrades.addGradeDatas(gradesBean.getInfo());
                        baseQuickAdapter2.notifyItemInserted(SearchGrades.getSize());
                    }
                });*/
    }

    public void getClass(String username, String password) {
        services.GetClass(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassBean>() {
                    @Override
                    public void onCompleted() {
                        /*for(int i = 0; i< waveViews.size();i++){
                            waveViews.get(i).setVisibility(View.INVISIBLE);
                        }
                        recyclerView.setVisibility(View.VISIBLE);*/
                       // if(!NetworkUtils.isAvailable(context)){
                         //   callBackSuccedLogin.callBackLoginStats(2);
                        //}else {
                            callBackSuccedLogin.callBackLoginStats(1);
                       // }

                        //Log.d("getClassTag", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                       /* if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            Toast.makeText(context, "加载失败，请重试 :)", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("getClassTag", "onError: " + e.toString());*/
                    }

                    @Override
                    public void onNext(ClassBean classBean) {
                        NewClassTable.setBeans(classBean.getTable());
                        /*class_table_adapter.addBeans(classBean.getTable());
                        ClassTable.addClassTableDatas(classBean.getTable());
                        Log.d("ClassTableTafg",classBean.getTable().get(0).getMonday());
                        Log.d("ClassTableTafg",classBean.getTable().get(0).getWednesday().toString());
                        if(classBean.getTable().get(0).getSunday().contains("   ")){
                            Log.d("ClassTableTafg","isEmpty");
                        }
                        class_table_adapter.notifyDataSetChanged();
                        class_table_adapter.notifyItemInserted(classBean.getTable().size());*/
                    }
                });
    }

    public void getClassArray(String username, String password) {
        services.GetClassArray(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ClassArrangeBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("classArrayTag", "onComlpeted");
                        for(int i = 0; i<waveViews.size();i++){
                            waveViews.get(i).setVisibility(View.INVISIBLE);
                        }
                        recyclerView.setVisibility(View.VISIBLE);

                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            callBackSuccedLogin.callBackLoginStats(1);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(!NetworkUtils.isAvailable(context)){
                            callBackSuccedLogin.callBackLoginStats(2);
                        }else {
                            Toast.makeText(context, "加载失败，请重试 :)", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("classArrayTag", "onError: " + e.getLocalizedMessage());
                        Log.d("classArrayTag", "onError: " + e.getMessage());
                        Log.d("classArrayTag", "onError: " + e.getCause());
                        Log.d("classArrayTag", "onError: " + e.toString());
                        Log.d("classArrayTag", "onError: " + e.getStackTrace());
                    }

                    @Override
                    public void onNext(ClassArrangeBean classArrangeBean) {
                        //Log.d("holyshirt", "fitst");
                        classArrayAdapter.addBeans(classArrangeBean.getInfo());
                        classArrayAdapter.notifyDataSetChanged();
                        //ClassArray.addClassArrayDatas(classArrangeBean.getInfo());
                        //Log.d("holyshirt", "beforeNotifyItemInsered");
                        classArrayAdapter.notifyItemInserted(classArrangeBean.getInfo().size());
                        //Log.d("holyshirt", "afterNotifyItemInsered:" + ClassArray.getSize());
                    }
                });
    }

    public static String addFree(String s){

        String s1 = new String();

        return s1;
    }


}
