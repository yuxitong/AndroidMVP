package com.youbang.contract;


import com.youbang.base.BasePresenter;
import com.youbang.base.BaseView;
import com.youbang.bean.UserBean;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void loginSuccess(UserBean user);
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }

}
