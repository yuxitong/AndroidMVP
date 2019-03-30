package com.yxt.base;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yxt.utils.StatusBarUtils;
import com.yxt.view.BarButtonItem;
import com.yxt.view.NavigationBar;

import java.util.List;

/**
 * Created by 30884 on 2018/5/11.
 */

public abstract class BaseActivity<V, T extends BaseViewPresenter<V>> extends FragmentActivity {
    protected T mPresente;
    public Handler handler;
    public NavigationBar navigationBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresente = createPresenter();
        if (mPresente != null)
            mPresente.attachView((V) this);
//        //开启沉浸式状态栏
        StatusBarUtils.openImmerseStatasBarMode(this);
//        //状态栏字体颜色变为深色
        StatusBarUtils.StatusBarLightMode(this);
        if (savedInstanceState == null || navigationBar == null) {
            setNavigationBar(new NavigationBar(this));
        }
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
//        LSAlert.showAlert(this,"提示",String.format("%2d",tasks.get(0).numActivities));
        if (tasks.get(0).numActivities > 1) {
            BarButtonItem backButtonItem = BarButtonItem.backBarButtonItem(this);
            getNavigationBar().setLeftBarItem(backButtonItem);
            backButtonItem.titleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
//        initView();
    }

    public abstract void initView();
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (title.length() > 9) {
            getNavigationBar().setTitle(title.subSequence(0, 9) + "...");
        } else {
            getNavigationBar().setTitle(title);
        }
    }

    public void setTitleView(View view) {
        getNavigationBar().setTitleView(view);
    }

    public void setLeftNavigationItem(String title, View.OnClickListener listener) {
        BarButtonItem barButtonItem = new BarButtonItem(this, title);
        barButtonItem.setOnClickListener(listener);
        getNavigationBar().setLeftBarItem(barButtonItem);
    }

    public void setRightNavigationItem(String title, View.OnClickListener listener) {
        BarButtonItem barButtonItem = new BarButtonItem(this, title);
        barButtonItem.setOnClickListener(listener);
        getNavigationBar().setRightBarItem(barButtonItem);
    }

    public NavigationBar getNavigationBar() {
        return navigationBar;
    }

    public void setNavigationBar(NavigationBar navigationBar) {
        this.navigationBar = navigationBar;
        onResume();

    }

    @Override
    public void setContentView(int layoutResID) {
        this.setContentView(View.inflate(this, layoutResID, null));
    }

    @Override
    public void setContentView(View view) {
        this.setContentView(view, null);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        LinearLayout layoutView = new LinearLayout(this);
        layoutView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layoutView.setOrientation(LinearLayout.VERTICAL);
        layoutView.addView(getNavigationBar());
        layoutView.addView(view);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (params != null) {
            super.setContentView(layoutView, params);
        } else {
            super.setContentView(layoutView);
        }
    }

    public void runUiThread(Runnable run) {
        if (handler == null)
            handler = new Handler(Looper.getMainLooper());
        handler.post(run);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacksAndMessages(null);
        if (mPresente != null)
            mPresente.detachView();
    }

    protected abstract T createPresenter();
}
