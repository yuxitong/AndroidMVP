package com.yxt.base;

import com.yxt.network.NetApi;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BaseViewPresenter<T> {
    protected Reference<T> mViewRef;
    private NetApi api;

    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    protected T getView() {
        return mViewRef.get();
    }

    protected <V> NetApi<V> getApi() {
        if (api == null)
            api = new NetApi<>();
        api.setTag(mViewRef);
        return api;
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        NetApi.NetCancel(mViewRef);
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

}
