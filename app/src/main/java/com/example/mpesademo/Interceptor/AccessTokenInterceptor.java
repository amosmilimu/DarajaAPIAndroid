package com.example.mpesademo.Interceptor;

import android.util.Base64;

import com.example.mpesademo.BuildConfig;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {
    public AccessTokenInterceptor() {

    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {


        String keys = com.example.mpesademo.BuildConfig.CONSUMER_KEY + ":" + BuildConfig.CONSUMER_SECRET;
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.getBytes(), Base64.NO_WRAP))
                .build();
        return chain.proceed(request);
    }
}
