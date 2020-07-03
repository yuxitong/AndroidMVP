package com.yxt.activity;

import android.os.Bundle;

import com.yxt.R;
import com.yxt.base.BaseActivity;
import com.yxt.contract.MainContract;
import com.yxt.presenter.MainPresenter;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.FragmentTabHost;

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
