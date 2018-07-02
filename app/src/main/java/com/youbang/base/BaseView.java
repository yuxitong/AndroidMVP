package com.youbang.base;

public interface BaseView<T> {

    void setPresenter(T presenter);

    boolean isActive();

    void showProgress();

    void dismissProgress();

    void showTip(String message);

}