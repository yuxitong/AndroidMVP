package com.youbang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youbang.R;
import com.youbang.base.BaseFragment;
import com.youbang.base.BaseViewPresenter;

/**
 * Created by 30884 on 2018/5/16.
 */

public class VehicleFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item,null,false);
    }

    @Override
    protected BaseViewPresenter createPresenter() {
        return null;
    }
}
