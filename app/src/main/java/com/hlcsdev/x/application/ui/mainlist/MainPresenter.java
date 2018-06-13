package com.hlcsdev.x.application.ui.mainlist;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.hlcsdev.x.application.repositories.Repository;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Repository repository = Repository.getRepository();


    @Override
    protected void onFirstViewAttach() {
        setList();
    }


    public void setList() {
        getViewState().showProgress(true);
        repository.getUsers(users -> {
            getViewState().showProgress(false);
            getViewState().showList(users);
        });
    }

}
