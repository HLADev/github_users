package com.hlcsdev.x.application.repositories;

import com.hlcsdev.x.application.app.App;
import com.hlcsdev.x.application.datamodels.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Repository {

    private static Repository repository;

    private int since;
    private List<User> users = new ArrayList<>();


    private Repository() {
    }

    public static Repository getRepository() {
        if (repository == null) {
            repository = new Repository();
        }
        return repository;
    }


    public interface LoadUsersCallback {
        void onLoad(List<User> users);
    }


    public void getUsers(LoadUsersCallback loadUserCallback) {
        App.getRetrofit().getUsers(since).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.body() != null) {
                    users.addAll(response.body());

                    loadUserCallback.onLoad(users);
                }
                // TODO убрать потом
                else {
                    for (int i = 0; i < 30; i++) {
                        users.add(new User("Вася " + users.size()));
                    }

                    loadUserCallback.onLoad(users);
                }

                since += 30;
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
            }
        });
    }


    public User getUser(int pos) {
        return users.get(pos);
    }

}
