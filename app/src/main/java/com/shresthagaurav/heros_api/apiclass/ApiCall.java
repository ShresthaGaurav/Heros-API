package com.shresthagaurav.heros_api.apiclass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCall {
    public  UserAPI calls(){
         String base_url = "http://10.0.2.2:3000/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
UserAPI userAPI=retrofit.create(UserAPI.class);
return userAPI;
    }
    }
