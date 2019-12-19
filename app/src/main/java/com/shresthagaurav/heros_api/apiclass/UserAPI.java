package com.shresthagaurav.heros_api.apiclass;

import com.shresthagaurav.heros_api.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {

    @POST("users/signup")
    Call<Void> register(@Body User cud);

    @POST("users/login")
    Call<Void> login(@Body User cud);

}
