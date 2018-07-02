package com.youbang.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youbang.R;
import com.youbang.utils.ConvertUtils;


/**
 * 导航条
 * Created by Administrator on 2017/3/28 0028.
 */
public class NavigationBar extends RelativeLayout {
    private Context context;
    protected int barTintColor;
    protected int tintColor;
    private LinearLayout leftBarLayout;
    private LinearLayout middleBarLayout;
    private LinearLayout rightBarLayout;
    private TextView titleView;
    private Paint paint;

    //返回导航条中间的控件
    public LinearLayout getMiddleBarLayout() {
        return middleBarLayout;
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public NavigationBar(Context context) {
        super(context);
        this.context = context;
        init();

    }

    //初始化内容
    public void init() {
        //导航栏背景色
        this.barTintColor = 0xFFFFFFFF;
        //导航栏字体颜色
        this.tintColor = 0xFF000000;
        //关闭硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);


//      this.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Float.valueOf(getResources().getDimension(R.dimen.activity_navigationbar_hight)).intValue());
        layoutParams.bottomMargin = 4;
        setPadding(0, Float.valueOf(getResources().getDimension(R.dimen.activity_navigationbar_paddingtop)).intValue(), 0, 0);
        this.setLayoutParams(layoutParams);
        this.setBackgroundColor(this.barTintColor);

        leftBarLayout = new LinearLayout(getContext());
        middleBarLayout = new LinearLayout(getContext());

        rightBarLayout = new LinearLayout(getContext());

        LayoutParams leftLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        leftLayoutParams.alignWithParent = true;
        leftLayoutParams.addRule(ALIGN_PARENT_TOP);
//        leftLayoutParams.topMargin = ConvertUtils.dip2px(getContext(), 20);/
        leftBarLayout.setLayoutParams(leftLayoutParams);
        leftBarLayout.setMinimumWidth(ConvertUtils.dip2px(getContext(), 15));
        leftBarLayout.setGravity(Gravity.TOP);


        LayoutParams middleLayoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        middleBarLayout.setLayoutParams(middleLayoutParams);
        middleBarLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        LayoutParams rightLayoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rightLayoutParams.alignWithParent = true;
        rightLayoutParams.addRule(ALIGN_PARENT_BOTTOM);
        rightLayoutParams.setMargins(0, 0, 0, ConvertUtils.dip2px(context, 15f));
        rightBarLayout.setLayoutParams(rightLayoutParams);
        rightBarLayout.setMinimumWidth(ConvertUtils.dip2px(getContext(), 15));
//        rightBarLayout.setGravity(Gravity.RIGHT);
        rightBarLayout.setGravity(Gravity.BOTTOM);

        this.addView(leftBarLayout);
        this.addView(middleBarLayout);
        this.addView(rightBarLayout);

        titleView = new TextView(getContext());
        titleView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        titleView.setTextColor(tintColor);
        titleView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);

        middleBarLayout.addView(titleView);

        paint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(0xFFF2F2F2);

        paint.setStrokeWidth(2);
        canvas.drawLine(0,canvas.getHeight() - 1 ,canvas.getWidth(),canvas.getHeight() - 1,paint);
    }

    //设置导航条标题
    CharSequence title;

    public void setTitle(CharSequence text) {
        if (titleView.getParent() != middleBarLayout) {
            middleBarLayout.removeAllViews();
            middleBarLayout.addView(titleView);
        }
        title = text;
        titleView.setEms(1);
        titleView.setText(text);
    }

    //返回导航条标题
    public CharSequence getTitle() {
        return title;
    }

    View centerTitleView;

    //在标题中添加控件
    public void setTitleView(View titleView) {
        centerTitleView = titleView;
        middleBarLayout.removeAllViews();
        middleBarLayout.setGravity(Gravity.CENTER);
        middleBarLayout.addView(titleView);
    }

    //返回标题控件
    public View getTitleView() {
        return centerTitleView;
    }

    public void setLeftBarItem(BarButtonItem item) {
        if (item == null) {
            leftBarLayout.removeAllViews();
        } else {
            leftBarLayout.removeAllViews();
            leftBarLayout.addView(item);
            item.setTintColor(tintColor);
        }
    }

    //设置右边标题按钮
    public void setRightBarItem(BarButtonItem item) {
        if (item == null) {
            rightBarLayout.removeAllViews();
        } else {
            rightBarLayout.removeAllViews();
            rightBarLayout.addView(item);
            item.setTintColor(tintColor);
        }
    }

    //设置右边控件
    public void setRightView(View view) {
        if (view == null) {
            rightBarLayout.removeAllViews();
        } else {
            rightBarLayout.removeAllViews();
            rightBarLayout.addView(view);
        }
    }


}
