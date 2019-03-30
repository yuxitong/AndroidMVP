package com.yxt.view;


import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yxt.R;
import com.yxt.utils.ConvertUtils;


/**
 * Created by yxt on 2016/3/3.
 */
public class LSAlert {
    public interface AlertCallback {
        void onConfirm();
    }

    public interface DialogCallback {
        void onConfirm();

        void onCancel();
    }


    public static void showAlert(Context context, String detail) {
        if (context != null) {
            LSAlert.showAlert(context, true, "提示", detail, "确认", null);
        }
    }

    public static void showAlert(Context context, String title, String detail) {
        if (context != null) {
            LSAlert.showAlert(context, true, title, detail, "确认", null);
        }
    }

    public static void showAlert(Context context, final boolean cancelable, String title, String detail, String confirmButtonTitle, final AlertCallback callback) {
        if (context != null) {
            final AlertDialog alertDialog = new AlertDialog.Builder(context)

//                    .setMessage(detail)
                    .create();
            Window win = alertDialog.getWindow();
            win.setBackgroundDrawableResource(R.drawable.bg_transparent);

            alertDialog.setCancelable(cancelable);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_alert_view,null,false);
            alertDialog.setView(view,0,0,0,0);
            ((TextView) view.findViewById(R.id.textTextViewTitle)).setText(title);
            if(confirmButtonTitle!=null)
            ((TextView) view.findViewById(R.id.positiveButton)).setText(confirmButtonTitle);

            if(detail == null){
                ((TextView) view.findViewById(R.id.textTextView)).setVisibility(View.GONE);
            }else{
                ((TextView) view.findViewById(R.id.textTextView)).setText(detail);
            }
            View.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.positiveButton) {
//                        if (cancelable) {
                        alertDialog.dismiss();
//                        }
                        if (callback != null) {
                            callback.onConfirm();
                        }
                    }
                }
            };
            view.findViewById(R.id.positiveButton).setOnClickListener(ocl);
                alertDialog.show();
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = ConvertUtils.dip2px(context, 281f);
            win.setAttributes(lp);
        }
    }

    //String confirmButtonTitle,暂时没用的字段

    /**
     * 带2个按钮的对话框
     *
     * @param context             上下文
     * @param cancelable          外部点击是否可以关闭对话框
     * @param title               设置内容
     * @param cancelButtonTitle   设置取消按钮显示文本
     * @param positiveButtonTitle 设置确定按钮显示文本
     * @param callback            确定和取消按钮的点击回调接口
     */
    public static void showDialog(Context context, boolean cancelable, String title, String cancelButtonTitle, String positiveButtonTitle, final DialogCallback callback) {

        if (context != null) {
            final AlertDialog alertDialog = new AlertDialog.Builder(context)
                    .create();
            Window win = alertDialog.getWindow();
            win.setBackgroundDrawableResource(R.drawable.bg_transparent);
//            win.getDecorView().setPadding(0, 0, 0, 0);

//            alertDialog.setContentView(R.layout.dialog_select_prompt_view);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_select_prompt_view,null,false);
            alertDialog.setView(view,0,0,0,0);

            ((TextView) view.findViewById(R.id.textTextViewTitle)).setText(title);
            alertDialog.setCancelable(cancelable);

            ((TextView) view.findViewById(R.id.cancelButton)).setText(cancelButtonTitle);
            ((TextView) view.findViewById(R.id.positiveButton)).setText(positiveButtonTitle);
            View.OnClickListener ocl = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.cancelButton) {
                        alertDialog.dismiss();
                        if (callback != null) {
                            callback.onCancel();
                        }
                    } else {
                        if (v.getId() == R.id.positiveButton) {
                            alertDialog.dismiss();
                            if (callback != null) {
                                callback.onConfirm();
                            }
                        }
                    }
                }
            };
            view.findViewById(R.id.cancelButton).setOnClickListener(ocl);
            view.findViewById(R.id.positiveButton).setOnClickListener(ocl);
            alertDialog.show();

            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = ConvertUtils.dip2px(context, 281f);
            win.setAttributes(lp);
        }
    }


    public static ProcessDialog progressDialog;

    public static void setProgressDialog(ProcessDialog progress) {

        progressDialog = progress;

    }

    public static int progressHudCount = 0;

    public static synchronized void showProgressHud(Context context) {
        if (context != null) {
            if (progressHudCount == 0) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                progressDialog = new ProcessDialog(context);
                //点击其他地方不可关闭
                progressDialog.setCancelable(false);
                progressDialog.show();
                progressHudCount = 1;
//            progressDialog = new ProgressDialog(context);
//            progressDialog.setInverseBackgroundForced(false);
            } else {
            }
        }
    }

    public static synchronized void dismissProgressHud() {
        if (progressHudCount == 0) return;
        if (progressHudCount == 1) {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = null;
            progressHudCount = 0;
        } else {
            try {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
            } catch (Exception e) {
            }
            progressHudCount = 0;
        }

    }
}
