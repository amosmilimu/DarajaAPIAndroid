package com.example.mpesademo.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class AuthInterceptor(private var mAuthToken:String):Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request:Request   = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $mAuthToken")
            .build();
        return chain.proceed(request);
    }
}
