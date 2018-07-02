package com.youbang.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.youbang.R;
import com.youbang.utils.EasyUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
@SuppressLint("AppCompatCustomView")
public class Button extends android.widget.Button {

    protected TextPosition mTextPosition = TextPosition.TextPositionRight;
    protected float imageHeight = 40;
    protected Rect imageBounds;

    public TextPosition getTextPosition() {
        return mTextPosition;
    }

    public void setTextPosition(TextPosition mTextPosition) {
        this.mTextPosition = mTextPosition;
    }

    protected boolean isHighlight = false;
    protected boolean isSelected = false;
    protected List<ButtonContent> mButtonContents;

    public Button(Context context) {
        super(context);
        this.init();
        ButtonContent buttonContent = mButtonContents.get(ButtonStatus.ButtonStatusNormal.ordinal());
        buttonContent.titleColor = Color.BLUE;
        buttonContent.background = Color.TRANSPARENT;
//        super.setBackgroundResource(Color.TRANSPARENT);
        this.refresh();
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Button);

        ButtonContent buttonContent = mButtonContents.get(ButtonStatus.ButtonStatusNormal.ordinal());
        buttonContent.title = array.getString(R.styleable.Button_title_normal);
        buttonContent.title = getText().toString();
        buttonContent.titleColor = array.getColor(R.styleable.Button_title_color_normal, 0);
        if (buttonContent.titleColor == -1) {
            buttonContent.titleColor = this.getTextColors().getDefaultColor();
//			 Color.BLUE
        }
        buttonContent.background = array.getResourceId(R.styleable.Button_background_normal, -1);

        buttonContent = mButtonContents.get(ButtonStatus.ButtonStatusHighlight.ordinal());
        buttonContent.title = array.getString(R.styleable.Button_title_highlight);
        buttonContent.titleColor = array.getColor(R.styleable.Button_title_color_highlight, 0);
        buttonContent.background = array.getResourceId(R.styleable.Button_background_highlight, -1);


        buttonContent = mButtonContents.get(ButtonStatus.ButtonStatusSelected.ordinal());
        buttonContent.title = array.getString(R.styleable.Button_title_selected);
        buttonContent.titleColor = array.getColor(R.styleable.Button_title_color_selected, 0);
        buttonContent.background = array.getResourceId(R.styleable.Button_background_selected, -1);

        array.recycle();
        this.refresh();
    }

    public void init() {
        this.setPadding(0, 0, 0, 0);
        mButtonContents = new ArrayList<ButtonContent>(3);
        mButtonContents.add(new ButtonContent());
        mButtonContents.add(new ButtonContent());
        mButtonContents.add(new ButtonContent());
        final Button self = this;

        setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        self.onButtonTouchDown();
                        //			       setImageResource(m_touch_state_image);
                        break;
                    case MotionEvent.ACTION_UP:
                        self.onButtonTouchUp();
                        //			       setImageResource(m_normal_state_image);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        self.onButtonTouchUp();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void setOnClickMethod(Object receiver, String method) {
        EasyUI.setButtonClickMethod(this, receiver, method);
    }

    public void refresh() {
        ButtonStatus status = this.getButtonStatus();
        ButtonContent buttonContent = mButtonContents.get(status.ordinal());
        ButtonContent normalStatus = mButtonContents.get(ButtonStatus.ButtonStatusNormal.ordinal());
        String title = buttonContent.title != null ? buttonContent.title : normalStatus.title;

        int image = buttonContent.image != 0 ? buttonContent.image : normalStatus.image;

        int background = buttonContent.background != 0 ? buttonContent.background : normalStatus.background;
        int titleColor = buttonContent.titleColor != 0 ? buttonContent.titleColor : normalStatus.titleColor;

        //		if(title != null){
        super.setText(title);
        //		}
        if (titleColor != 0) {
            super.setTextColor(titleColor);
        }

        if (background > 0) {
            super.setBackgroundResource(background);
        } else {
            //			super.setBackgroundColor(Color.TRANSPARENT);
            if (this.getBackground() == null) {
                super.setBackgroundColor(Color.TRANSPARENT);
            }
        }
        Drawable imageDrawable = null;
        if (image != 0) {
            imageDrawable = getResources().getDrawable(image);
        }

        if (imageDrawable != null && imageBounds != null) {
            imageDrawable.setBounds(imageBounds);
        } else if (imageDrawable != null) {
            float ratio = 1.f * imageDrawable.getIntrinsicWidth() / imageDrawable.getIntrinsicHeight();
            imageBounds = new Rect(0, 0, (int) (ratio * getImageHeight()), (int) getImageHeight());
            imageDrawable.setBounds(imageBounds);
        }

        super.setCompoundDrawables(
                (mTextPosition == TextPosition.TextPositionRight) ? imageDrawable : null,
                (mTextPosition == TextPosition.TextPositionBottom) ? imageDrawable : null,
                (mTextPosition == TextPosition.TextPositionLeft) ? imageDrawable : null,
                (mTextPosition == TextPosition.TextPositionTop) ? imageDrawable : null
        );
        this.postInvalidate();

    }

    public void onButtonTouchDown() {
        isHighlight = true;

        this.refresh();
    }

    public void onButtonTouchUp() {
        isHighlight = false;

        this.refresh();
    }

    public boolean isHighlight() {
        return isHighlight;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        this.refresh();
    }

    public ButtonStatus getButtonStatus() {
        if (isHighlight) {
            return ButtonStatus.ButtonStatusHighlight;
        } else {
            if (isSelected) {
                return ButtonStatus.ButtonStatusSelected;
            } else {
                return ButtonStatus.ButtonStatusNormal;
            }
        }
    }

    public void setImage(int resourceId) {
        this.setImage(resourceId, ButtonStatus.ButtonStatusNormal);
    }

    public void setImage(int resourceId, ButtonStatus status) {
        ButtonContent buttonContents = mButtonContents.get(status.ordinal());
        buttonContents.image = resourceId;
        this.refresh();
    }

    public void setTitle(String title) {
        this.setTitle(title, ButtonStatus.ButtonStatusNormal);
    }

    public void setTitle(String title, ButtonStatus status) {
        ButtonContent buttonContents = mButtonContents.get(status.ordinal());
        buttonContents.title = title;
        this.refresh();
    }

    public void setTitleColor(int color) {
        this.setTitleColor(color, ButtonStatus.ButtonStatusNormal);
    }

    public void setTitleColor(int color, ButtonStatus status) {
        ButtonContent buttonContents = mButtonContents.get(status.ordinal());
        buttonContents.titleColor = color;
        this.refresh();
    }

    public void setButtonBackground(Drawable background) {
        this.setBackground(background, ButtonStatus.ButtonStatusNormal);
        this.refresh();
    }

    public void setBackground(Drawable background, ButtonStatus status) {
        ButtonContent buttonContents = mButtonContents.get(status.ordinal());
//		buttonContents.titleColor 	 = color;
        this.refresh();
    }

    public void setButtonBackground(int resourceId) {
        this.setBackground(resourceId, ButtonStatus.ButtonStatusNormal);
        this.refresh();
    }

    public void setBackground(int resourceId, ButtonStatus status) {
        ButtonContent buttonContents = mButtonContents.get(status.ordinal());
        buttonContents.background = resourceId;
        this.refresh();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        ButtonStatus status = this.getButtonStatus();
        ButtonContent buttonContent = mButtonContents.get(status.ordinal());
        if (buttonContent.background <= 0) {
            if (this.getBackground() != null) {
                if (isHighlight) {
                    this.getBackground().setColorFilter(0xFF888888, PorterDuff.Mode.MULTIPLY);
                } else {
                    this.getBackground().clearColorFilter();
                }
            }
        }
        super.onDraw(canvas);
    }

    public Rect getImageBounds() {
        return imageBounds;
    }

    public void setImageBounds(Rect imageBounds) {
        this.imageBounds = imageBounds;
        refresh();
    }

    public float getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(float imageHeight) {
        this.imageHeight = imageHeight;
        refresh();
    }

    protected static class ButtonContent {
        public String title;
        public int image;
        public int background;
        public int titleColor;
    }

    public enum ButtonStatus {
        ButtonStatusNormal,
        ButtonStatusHighlight,
        ButtonStatusSelected,
    }

    public enum TextPosition {
        TextPositionTop,
        TextPositionLeft,
        TextPositionRight,
        TextPositionBottom
    }
}
