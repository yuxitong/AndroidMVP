package com.youbang.network;


import android.util.Log;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/4/17 0017.
 */
public class ErrorCallBack {

    public static void onError(Call call, Response response, Exception e){

        Log.e("onError",e.toString());
    };

}
