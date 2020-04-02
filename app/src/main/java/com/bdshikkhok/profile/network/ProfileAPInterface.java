package com.bdshikkhok.profile.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileAPInterface {
    @GET("api/users/me")
    Call<ProfileResponse> me(@Header("Authorization") String token);

    //Update profile
    @PUT("api/users/me")
    Call<UpdateProfileResponse> me(@Header("Authorization") String token,@Body UpdateProfileRequest updateProfileRequest);

}
