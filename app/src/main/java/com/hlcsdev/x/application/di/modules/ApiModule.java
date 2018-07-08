package com.hlcsdev.x.application.di.modules;

import com.hlcsdev.x.application.net.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module(includes = RetrofitModule.class)
public class ApiModule {

    @Provides
    @Singleton
    public Api provideApi(Retrofit retrofit){
        return retrofit.create(Api.class);
    }
}
