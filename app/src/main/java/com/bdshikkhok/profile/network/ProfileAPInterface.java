package com.bdshikkhok.profile.network;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProfileAPInterface {
    @GET("api/users/me")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);

    @PUT("api/users/{username}")
    Call<UpdateProfileResponse> updateProfile(@Header("Authorization") String token,
                                              @Path(value = "username", encoded = true) String username,
                                              @Body UpdateProfileRequest updateProfileRequest);
}
