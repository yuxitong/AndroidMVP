package com.yxt.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yxt.utils.NotificationCenter;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public abstract class Observer<T> {
    protected abstract void onNext(T t);
    //如果返回结果只有一个值时；既： JsonElement 既不属于对象也不属于数组
    protected void onNext2(JsonElement resule){}
    protected void onError(Context context, Response<String> response){
        switch (response.code()) {
            case 404:
                Log.e("NetApi","404");
                break;
            default:
                Log.e("NetApi",response.code() + "   " + response.body());
                break;
        }
    };

    protected void onCodeError(Context context, String code, String msg){
        if(code.equals("4001")){
            NotificationCenter.defaultCenter.postNotification("exitUser",null);
        }else{
            Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    };

    protected void onStart(Request<String, ? extends Request> request){};
    protected void onFinish(){};
}
