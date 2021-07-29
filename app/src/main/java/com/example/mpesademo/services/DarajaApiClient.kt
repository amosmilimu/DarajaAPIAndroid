package com.example.mpesademo.services;

import com.example.mpesademo.Constants.*
import com.example.mpesademo.interceptor.AccessTokenInterceptor;
import com.example.mpesademo.interceptor.AuthInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class DarajaApiClient {
    private lateinit var retrofit:Retrofit
    private var isDebug:Boolean = false
    private var isGetAccessToken:Boolean = false
    private var mAuthToken:String = ""
    private val httpLoggingInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor();


    fun setIsDebug(isDebug:Boolean):DarajaApiClient{
        this.isDebug = isDebug;
        return this;
    }
    fun setAuthToken(authToken:String):DarajaApiClient{
        mAuthToken = authToken;
        return this;
    }
    fun setGetAccessToken(getAccessToken:Boolean):DarajaApiClient{
        isGetAccessToken = getAccessToken;
        return this;
    }
    private fun okHttpClient():OkHttpClient.Builder {
        val okHttpClient:OkHttpClient.Builder =  OkHttpClient.Builder();
        okHttpClient
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);

        return okHttpClient;
    }
    private fun getRestAdapter():Retrofit {

        val builder:Retrofit.Builder = Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());

        if (isDebug) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

         val okhttpBuilder:OkHttpClient.Builder = okHttpClient();

        if (isGetAccessToken) {
            okhttpBuilder.addInterceptor( AccessTokenInterceptor());
        }

        if (!mAuthToken.isEmpty()) {
            okhttpBuilder.addInterceptor( AuthInterceptor(mAuthToken));
        }

        builder.client(okhttpBuilder.build());

        retrofit = builder.build();

        return retrofit;
    }
    fun mpesaService():STKPushService {
        return getRestAdapter().create(STKPushService::class.java)
    }
}
