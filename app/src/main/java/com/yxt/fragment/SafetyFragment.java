package com.yxt.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yxt.R;
import com.yxt.base.BaseActivity;
import com.yxt.base.BaseFragment;
import com.yxt.base.BaseViewPresenter;

import androidx.annotation.Nullable;

/**
 * Created by 30884 on 2018/5/16.
 */

public class SafetyFragment extends BaseFragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_item,null,false);
        ((BaseActivity) getActivity()).navigationBar.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).navigationBar.setTitle("222");
        ((BaseActivity) getActivity()).navigationBar.setElevation(1);
        ((TextView)view.findViewById(R.id.textView)).setText("222");
        return view;
    }

    @Override
    protected BaseViewPresenter createPresenter() {
        return null;
    }
}
