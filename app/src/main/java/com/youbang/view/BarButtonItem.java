package com.youbang.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youbang.R;
import com.youbang.utils.ConvertUtils;


/**
 * Created by lessu on 14-7-31.
 *
 *
 * edited by lessu on 15-3-4
 * v1.1
 * support image bar item
 * custom view is not tested
 *
 */
public class BarButtonItem extends LinearLayout {

    private String title;
    private float imageSizeDp = 16;

//    - (id)initWithImage:(UIImage *)image style:(UIBarButtonItemStyle)style target:(id)target action:(SEL)action;
//    - (id)initWithBarButtonSystemItem:(UIBarButtonSystemItem)systemItem target:(id)target action:(SEL)action;
//    - (id)initWithCustomView:(UIView *)customView;

    //    @property(nonatomic)         UIBarButtonItemStyle style;            // default is UIBarButtonItemStylePlain
    protected float              width;            // default is 0.0
    //    @property(nonatomic,copy)    NSSet               *possibleTitles;   // default is nil
    protected View customView;


    private void barButtonItemInit(){
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT));


        titleButton = new Button(getContext());
        titleButton.setTextSize(14);

        titleButton.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        titleButton.setBackgroundColor(0x00000000);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT);
        layoutParams.leftMargin  = ConvertUtils.dip2px(getContext(), 12);
        layoutParams.rightMargin = ConvertUtils.dip2px(getContext(), 12);
        titleButton.setLayoutParams(layoutParams);

//        titleButton.setImageBounds(new Rect(0,
//				DensityUtil.sharedInstance().dp2px(0),
//				DensityUtil.dp2px(getContext(), getImageWidthDp()),
//				DensityUtil.dp2px(getContext(), getImageHeightDp())));
        titleButton.setImageHeight(ConvertUtils.dip2px(getContext(),imageSizeDp));
        titleButton.setTitleColor(Color.BLUE, Button.ButtonStatus.ButtonStatusHighlight);

        this.addView(titleButton);
    }
    public BarButtonItem(Context context, String title) {
        super(context);
        barButtonItemInit();

        this.title = title;
        titleButton.setTitle(title, Button.ButtonStatus.ButtonStatusNormal);
    }

    public BarButtonItem(Context context, int imageResourceId){
        super(context);
        barButtonItemInit();
        titleButton.setTitle(null);
        titleButton.setImage(imageResourceId);

        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) titleButton.getLayoutParams();
        layoutParams.width = titleButton.getImageBounds().right;
        titleButton.setLayoutParams(layoutParams);
    }

    public void setOnClickMethod(Object receiver, String method){
        titleButton.setOnClickMethod(receiver,method);
    }
    @Override
    public void setOnClickListener(View.OnClickListener l) {
        titleButton.setOnClickListener(l);
    }

    public void setCustomView(View customView) {

        if (customView != null){
            this.addView(customView);
            this.removeView(titleButton);
        }else{
            this.removeView(customView);
            this.addView(titleButton);
        }
        this.customView = customView;

    }
    public View getCustomView() {
        return customView;
    }
//
// Appearance modifiers
//

/* Send these messages to the [UIBarButtonItem appearance] proxy to customize all buttons, or, to customize a specific button, send them to a specific UIBarButtonItem instance, which may be used in all the usual places in a UINavigationItem (backBarButtonItem, leftBarButtonItem, rightBarButtonItem) or a UIToolbar.
 */

/* In general, you should specify a value for the normal state to be used by other states which don't have a custom value set.

 Similarly, when a property is dependent on the bar metrics (on the iPhone in landscape orientation, bars have a different height from standard), be sure to specify a value for UIBarMetricsDefault.

 This sets the background image for buttons of any style.
 */
//    - (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (UIImage *)backgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

/* This sets the background image for buttons with a specific style. When calling this on a UIBarButtonItem instance, the style argument must match the button's style; when calling on the UIAppearance proxy, any style may be passed.
 */
//    - (void)setBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(6_0) UI_APPEARANCE_SELECTOR;
//    - (UIImage *)backgroundImageForState:(UIControlState)state style:(UIBarButtonItemStyle)style barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(6_0) UI_APPEARANCE_SELECTOR;

//    @property(nonatomic,retain) UIColor *tintColor NS_AVAILABLE_IOS(5_0);

/* For adjusting the vertical centering of bordered bar buttons within the bar
 */
//    - (void)setBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (CGFloat)backgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

/* For adjusting the position of a title (if any) within a bordered bar button
 */
//    - (void)setTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (UIOffset)titlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

/* The remaining appearance modifiers apply solely to UINavigationBar back buttons and are ignored by other buttons.
 */
/*
 backgroundImage must be a resizable image for good results.
 */
//    - (void)setBackButtonBackgroundImage:(UIImage *)backgroundImage forState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (UIImage *)backButtonBackgroundImageForState:(UIControlState)state barMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

//    - (void)setBackButtonTitlePositionAdjustment:(UIOffset)adjustment forBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (UIOffset)backButtonTitlePositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

/* For adjusting the vertical centering of bordered bar buttons within the bar
 */
//    - (void)setBackButtonBackgroundVerticalPositionAdjustment:(CGFloat)adjustment forBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;
//    - (CGFloat)backButtonBackgroundVerticalPositionAdjustmentForBarMetrics:(UIBarMetrics)barMetrics NS_AVAILABLE_IOS(5_0) UI_APPEARANCE_SELECTOR;

    public Button getTitleButton() {
        return titleButton;
    }

    //view
    public Button titleButton;


    protected int tintColor;
    public int getTintColor() {
        return tintColor;
    }

    public void setTintColor(int tintColor) {
        this.tintColor = tintColor;
        titleButton.setTitleColor(tintColor, Button.ButtonStatus.ButtonStatusNormal);
    }

    public void setTitle(String title){
        this.title = title;
        titleButton.setTitle(title, Button.ButtonStatus.ButtonStatusNormal);
    }

    public String getTitle(){
        return title;
    }

    public static BarButtonItem backBarButtonItem(Context context){
        BarButtonItem backItem = new BarButtonItem(context,"返回");
//        BarButtonItem backItem = new BarButtonItem(context,"");
        backItem.titleButton.setImage(R.mipmap.icon_arrow_back);
        //设置回退图片大小
        backItem.titleButton.setImageBounds(new Rect(0,0, ConvertUtils.dip2px(context,7f),ConvertUtils.dip2px(context,14f)));

        return backItem;
    }


}
