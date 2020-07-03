package com.yxt.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
