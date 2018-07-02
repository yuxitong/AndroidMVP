package com.youbang.presenter;

import com.youbang.base.BaseViewPresenter;
import com.youbang.bean.UserBean;
import com.youbang.contract.LoginContract;
import com.youbang.network.Observer;

/**
 * Created by yxt on 2018/5/11.
 */

public class LoginPresenter extends BaseViewPresenter<LoginContract.View> implements LoginContract.Presenter {


    private LoginContract.View view;
    public LoginPresenter(LoginContract.View view){
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void login(String username, String password) {
        //为了测试 实现登入 正常情况下应该网络访问之后再调用
        view.loginSuccess(new UserBean());

        //访问网络
//        this.<UserBean>getApi().post()
//                .subscribe(new Observer<UserBean>() {
//                    @Override
//                    protected void onNext(UserBean userBean) {
//
//                    }
//                });
    }
}
