package com.yxt.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yxt.R;
import com.yxt.base.BaseActivity;
import com.yxt.bean.UserBean;
import com.yxt.contract.LoginContract;
import com.yxt.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View {

//    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    @Override
    public void initView() {
        navigationBar.setVisibility(View.GONE);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    public void onClickButton(View view) {
        switch (view.getId()) {
            case R.id.login:
                mPresente.login(
                        ((EditText) LoginActivity.this.findViewById(R.id.userEdit)).getText().toString(),
                        ((EditText) LoginActivity.this.findViewById(R.id.passWord)).getText().toString()
                );
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresente = (LoginPresenter) presenter;
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
    public void loginSuccess(UserBean user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
