package com.hlcsdev.x.application.ui.details;

import com.arellomobile.mvp.MvpView;

public interface DetailsView extends MvpView {

    void showName(String name);
    void showImage(String url);
}
