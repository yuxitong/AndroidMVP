package com.youbang.contract;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.ContentFrameLayout;

import com.youbang.base.BasePresenter;
import com.youbang.base.BaseView;
import com.youbang.bean.UserBean;

/**
 * Created by 30884 on 2018/5/16.
 */

public interface MainContract  {
    interface View extends BaseView<Presenter>{
        void tabFragment(int tab);
    }
    interface Presenter extends BasePresenter{
        void initView(FragmentTabHost tabHost, ContentFrameLayout frameLayout);
    }
}
