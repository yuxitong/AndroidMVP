package com.yxt.presenter;

import android.net.Uri;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpHeaders;
import com.yxt.base.BaseActivity;
import com.yxt.base.BaseApplication;
import com.yxt.base.BaseViewPresenter;
import com.yxt.bean.UserBean;
import com.yxt.contract.LoginContract;
import com.yxt.network.NetUrl;
import com.yxt.network.Observer;

import java.util.HashMap;
import java.util.Map;

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

//        Map<String,String> map = new HashMap<>();
//        map.put("user",username);
//        map.put("password",password);
//        //访问网络
//        this.<UserBean>getApi().post((BaseActivity)view,NetUrl.getNetConnect(1),true,map,UserBean.class)
//                .subscribe(new Observer<UserBean>() {
//                    @Override
//                    protected void onNext(UserBean userBean) {
//                        BaseApplication.sp.putString("token",userBean.getToken());
//                        HttpHeaders headers = new HttpHeaders();
//                        headers.put("token",userBean.getToken());
//                        OkGo.getInstance().addCommonHeaders(headers);
//                        view.loginSuccess(new UserBean());
//                    }
//                });
    }
}
