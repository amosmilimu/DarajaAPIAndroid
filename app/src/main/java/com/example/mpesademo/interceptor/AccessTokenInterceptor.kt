package com.example.mpesademo.interceptor;

import android.util.Base64;

import com.example.mpesademo.BuildConfig;
import com.example.mpesademo.Constants
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AccessTokenInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val keys:String = Constants.DARAJA_CONSUMER_KEY + ":" + Constants.DARAJA_CONSUMER_SECRET;
        val request: Request = chain.request().newBuilder()
            .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
            .build();
        return chain.proceed(request);
    }
}
