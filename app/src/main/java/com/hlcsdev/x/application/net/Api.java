package com.hlcsdev.x.application.net;


import com.hlcsdev.x.application.datamodels.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

    @GET("users")
    Call<List<User>> getUsers(@Query("since") int since);


}
