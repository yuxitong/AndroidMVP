package com.yxt.contract;


import com.yxt.base.BasePresenter;
import com.yxt.base.BaseView;
import com.yxt.bean.UserBean;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void loginSuccess(UserBean user);
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
    }

}
