package com.hlcsdev.x.application.di.modules;

import com.hlcsdev.x.application.net.Api;
import com.hlcsdev.x.application.repositories.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = ApiModule.class)
public class RepositoryModule {

    @Provides
    @Singleton
    public Repository provideRepository(Api api) {
        return new Repository(api);
    }
}
