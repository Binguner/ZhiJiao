package com.example.binguner.zhijiao.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.binguner.zhijiao.UI.MainActivity;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by binguner on 2017/8/29.
 */

public class ReceivedCookiesInterceptor implements Interceptor{

    public ReceivedCookiesInterceptor(Context context){
        this.context = context;
    }
    private Context context;
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            SharedPreferences.Editor config = context.getSharedPreferences("config", Context.MODE_PRIVATE/*getContext().MODE_PRIVATE*/)
                    .edit();
            config.putStringSet("cookie", cookies);
            config.commit();
        }

        return originalResponse;
    }
}
