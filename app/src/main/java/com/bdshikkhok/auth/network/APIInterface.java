package com.bdshikkhok.auth.network;

import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.request.RegisterRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {

    @POST("api/auth/signin")
    Call<AuthResponse> signin(@Body AuthRequest authRequest);

    @POST("api/auth/signup")
    Call<Void> signup(@Body RegisterRequest registerRequest);
}
