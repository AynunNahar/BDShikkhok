package com.bdshikkhok.auth.network;

import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.request.RegisterRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
    //@Headers({"Accept:application/json", "Content-Type:application/json;"})

    @POST("api/auth/signin")
    Call<AuthResponse> signin(@Body AuthRequest authRequest);

    @POST("api/auth/signup")
    Call<Void> signup(@Body RegisterRequest registerRequest);
}
