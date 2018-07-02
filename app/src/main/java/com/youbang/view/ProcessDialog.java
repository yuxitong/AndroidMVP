package com.youbang.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;

import com.youbang.R;


/**
 * Created by yxt on 2016/3/3.
 */
public class ProcessDialog extends Dialog {
    public ProcessDialog(Context context) {
        this(context, R.style.Translucent_NoTitle);
    }

    public ProcessDialog(Context context, int theme) {
        super(context, theme);

        setContentView(R.layout.process_dialog_layout);
        getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
        }
    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        }catch (Exception e){
        }
    }

    @Override
    public void cancel() {
        super.cancel();
    }
}
