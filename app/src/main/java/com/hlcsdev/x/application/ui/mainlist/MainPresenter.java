package com.hlcsdev.x.application.ui.mainlist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.hlcsdev.x.application.app.App;
import com.hlcsdev.x.application.datamodels.User;
import com.hlcsdev.x.application.repositories.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Repository repository;

    private CompositeDisposable compositeDisposable;

    MainPresenter() {
        App.getAppComponent().inject(this);
    }


    @Override
    protected void onFirstViewAttach() {
        compositeDisposable = new CompositeDisposable();
        setList();
    }


    public void setList() {
        getViewState().showProgress(true);

        Disposable disposable = repository.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userList -> {
                    if (userList != null) {
                        getViewState().showProgress(false);
                        getViewState().showList(userList);
                    }
                }, throwable -> {
                    getViewState().showToast(throwable.getMessage());
                });
        compositeDisposable.add(disposable);
    }


    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
