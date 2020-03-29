package com.bdshikkhok.auth.network;

import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.request.RegisterRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface {
    //@Headers({"Accept:application/json", "Content-Type:application/json;"})

    @POST("/api/authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);

    @POST("/api/register")
    Call<Void> register(@Body RegisterRequest registerRequest);
}
