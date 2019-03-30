package com.yxt.base;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 30884 on 2018/5/14.
 */

public abstract class BaseFragment<V, T extends BaseViewPresenter<V>> extends Fragment {
    protected T mPresente;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresente = createPresenter();
        if (mPresente != null)
            mPresente.attachView((V) this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresente != null)
            mPresente.detachView();
    }
    protected abstract T createPresenter();
}
