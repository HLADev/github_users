package com.hlcsdev.x.application.ui.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.hlcsdev.x.application.repositories.Repository;

@InjectViewState
public class DetailsPresenter extends MvpPresenter<DetailsView> {

    private Repository repository = Repository.getRepository();


    public void setUi(int pos) {
        getViewState().showName(repository.getUser(pos).getLogin());
        getViewState().showImage(repository.getUser(pos).getAvatarUrl());
    }
}
