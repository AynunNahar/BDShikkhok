package com.bdshikkhok.auth.network;

import com.bdshikkhok.auth.network.request.AuthRequest;
import com.bdshikkhok.auth.network.response.AuthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("authenticate")
    Call<AuthResponse> authenticate(@Body AuthRequest authRequest);
}
