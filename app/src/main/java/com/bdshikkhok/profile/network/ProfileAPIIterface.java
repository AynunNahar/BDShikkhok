package com.bdshikkhok.profile.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileAPIIterface {
    @GET("api/users/me")
    Call<ProfileResponse> me(@Path("id")int id);
}
