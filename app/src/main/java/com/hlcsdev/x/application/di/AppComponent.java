package com.hlcsdev.x.application.di;

import com.hlcsdev.x.application.di.modules.RepositoryModule;
import com.hlcsdev.x.application.ui.mainlist.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = RepositoryModule.class)
public interface AppComponent {

    void inject(MainPresenter presenter);
}
