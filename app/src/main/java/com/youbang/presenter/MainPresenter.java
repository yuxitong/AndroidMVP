package com.youbang.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.youbang.R;
import com.youbang.base.BaseViewPresenter;
import com.youbang.contract.MainContract;
import com.youbang.fragment.MineFragment;
import com.youbang.fragment.SafetyFragment;
import com.youbang.fragment.VehicleFragment;

/**
 * Created by 30884 on 2018/5/16.
 */

public class MainPresenter extends BaseViewPresenter<MainContract.View> implements MainContract.Presenter {
    private MainContract.View view;

    //Fragment数组界面
    private Class mFragmentArray[] = { VehicleFragment.class, SafetyFragment.class,
            MineFragment.class};
    //选项卡文字
    private String mTextArray[] = { "首页", "消息", "好友" };
    //存放图片数组
    private int mImageArray[] = { R.drawable.icon_tab_vehicle,R.drawable.icon_tab_safety,R.drawable.icon_tab_mine };

    public MainPresenter(MainContract.View view){
        this.view = view;
        view.setPresenter(this);
    }
    @Override
    public void initView(FragmentTabHost tabHost, ContentFrameLayout frameLayout) {
        tabHost.setup(((FragmentActivity) mViewRef.get()),
                ((FragmentActivity) mViewRef.get()).getSupportFragmentManager(),
                R.id.frameLayout);

        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每一个Tabbutton设置图标、文字和内容
            Log.e("123333","123333");

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mTextArray[i]).setIndicator(getTabItemView(i));
            // 将Tabbutton加入进Tab选项卡中
            tabHost.addTab(tabSpec, mFragmentArray[i], null);//第二个參数就是选项卡相应页面的详细内容
            // 设置Tabbutton的背景
//            tabHost.getTabWidget().getChildAt(i)
//                    .setBackgroundResource(R.mipmap.login_log);
        }
    }
    /**
     *
     * 给每一个Tabbutton设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = LayoutInflater.from((FragmentActivity) mViewRef.get()).inflate(R.layout.tab_item_view, null,false);//tab的动态布局
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(mImageArray[index]);//设置图标
        return view;
    }
}
