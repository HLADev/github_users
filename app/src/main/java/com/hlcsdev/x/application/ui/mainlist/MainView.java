package com.hlcsdev.x.application.ui.mainlist;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.hlcsdev.x.application.datamodels.User;

import java.util.List;


public interface MainView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void showProgress(boolean b);

    void showList(List<User> users);
}
