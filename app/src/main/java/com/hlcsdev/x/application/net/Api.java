package com.hlcsdev.x.application.net;

import com.hlcsdev.x.application.datamodels.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api {

    @GET("users")
    Observable<Response<List<User>>> getUsers(@Query("since") int since);

}
