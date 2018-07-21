package com.hlcsdev.x.application.repositories;

import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.net.Api;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;


public class Repository {

    private int since;
    private List<User> userList = new ArrayList<>();

    private Api api;

    public Repository(Api api) {
        this.api = api;
    }


    public Observable<List<User>> getUsers() {
        return api.getUsers(since)
                .flatMap(response -> {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            userList.addAll(response.body());
                            since += 30;
                            return Observable.just(userList);
                        }
                    }
                    return null;
                });
    }

}
