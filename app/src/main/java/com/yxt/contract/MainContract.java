package com.yxt.contract;


import com.yxt.base.BasePresenter;
import com.yxt.base.BaseView;

import androidx.appcompat.widget.ContentFrameLayout;
import androidx.fragment.app.FragmentTabHost;

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
