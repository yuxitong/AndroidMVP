package com.youbang.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.ContentFrameLayout;

import com.youbang.R;
import com.youbang.base.BaseActivity;
import com.youbang.base.BaseFragment;
import com.youbang.base.BaseViewPresenter;
import com.youbang.contract.MainContract;
import com.youbang.presenter.MainPresenter;

public class MainActivity extends BaseActivity<MainContract.View,MainPresenter> implements MainContract.View {


    private ContentFrameLayout frameLayout;
    private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void initView() {
        frameLayout = findViewById(R.id.frameLayout);
        tabHost = findViewById(android.R.id.tabhost);
        mPresente.initView(tabHost,frameLayout);
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dismissProgress() {

    }

    @Override
    public void showTip(String message) {

    }

    @Override
    public void tabFragment(int tab) {

    }
}
