package com.example.mpesademo.services;

import com.example.mpesademo.model.AccessToken;
import com.example.mpesademo.model.STKPush;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface STKPushService {
    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPush(@Body stkPush:STKPush ):Call<STKPush>

    @GET("oauth/v1/generate?grant_type=client_credentials")
    fun getAccessToken():Call<AccessToken>
}
