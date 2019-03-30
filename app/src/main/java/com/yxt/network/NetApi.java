package com.yxt.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.yxt.bean.BaseBean;
import com.yxt.view.LSAlert;

import java.util.ArrayList;
import java.util.Map;


/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class NetApi<T> {
    //网络访问成功返回
    private String NET_SUCCESS = "2000";
    //json对象数据字段
    private String JSON_BODY = "body";
    Object Tag;

    /**
     * 设置一个Tag 用来取消网络请求的
     *
     * @param tag
     */
    public NetApi setTag(Object tag) {
        Tag = tag;
        return this;
    }

    /**
     * get请求
     *
     * @param context  窗体上下文
     * @param url      访问地址
     * @param isLoding 是否开启动画
     * @param params   访问参数
     * @param cls      解析实体类
     * @return 观察者
     */
    public Observable<T> get(final Context context, final String url, final boolean isLoding, final Map<String, String> params, final Class cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Observer<T> baseBeanObserver) {
                OkGo.<String>get(url)
                        .params(params)
                        .tag(Tag)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                successAnalysis(context, isLoding, cls, baseBeanObserver, response);
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                baseBeanObserver.onError(context, response);
                            }

                            @Override
                            public void onStart(Request<String, ? extends Request> request) {
                                super.onStart(request);
                                baseBeanObserver.onStart(request);
                                if (isLoding) {
                                    LSAlert.showProgressHud(context);
                                }
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                baseBeanObserver.onFinish();
                                if (isLoding) {
                                    LSAlert.dismissProgressHud();
                                }
                            }
                        });
            }
        });


    }


    /**
     * post请求
     *
     * @param context  窗体上下文
     * @param url      访问地址
     * @param isLoding 是否开启动画
     * @param params   访问参数
     * @param cls      解析实体类
     * @return 观察者
     */
    public Observable<T> post(final Context context, final String url, final boolean isLoding, final Map<String, String> params, final Class cls) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(final Observer<T> baseBeanObserver) {
                OkGo.<String>post(url)
                        .params(params)
                        .tag(Tag)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {

                                successAnalysis(context, isLoding, cls, baseBeanObserver, response);
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                baseBeanObserver.onError(context, response);
                            }

                            @Override
                            public void onStart(Request<String, ? extends Request> request) {
                                super.onStart(request);
                                baseBeanObserver.onStart(request);
                                if (isLoding) {
                                    LSAlert.showProgressHud(context);
                                }
                            }

                            @Override
                            public void onFinish() {
                                super.onFinish();
                                baseBeanObserver.onFinish();
                                if (isLoding) {
                                    LSAlert.dismissProgressHud();
                                }
                            }
                        });
            }
        });


    }


    //解析对象
    private static <R> R jsonToBean(JsonElement jsonResult, Class<R> clz) {
        Gson gson = new Gson();
        R t = gson.fromJson(jsonResult, clz);
        return t;
    }

    //解析list
    private static <T> ArrayList<T> fromJsonList(JsonElement json, Class<T> cls) {
        ArrayList<T> mList = new ArrayList<T>();
        JsonArray array = json.getAsJsonArray();
        Gson mGson = new GsonBuilder().create();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    //成功回调解析context,isLoding,cls,response
    private void successAnalysis(Context context, boolean isLoding, Class cls, Observer<T> baseBeanObserver, Response<String> response) {
        if (response == null || response.body() == null || response.body().length() == 0) {
            baseBeanObserver.onCodeError(context, "404", "网络不稳定");
            return;
        }
        Gson g = new GsonBuilder().create();
        BaseBean baseBean = g.fromJson(response.body(), BaseBean.class);
        if (baseBean != null && baseBean.getCode().equals(NET_SUCCESS)) {
            if (isLoding) {
                LSAlert.dismissProgressHud();
            }
            // 将json字符串转换成JsonElement
            JsonParser jsonParser = new JsonParser();

            JsonElement jsonElement = jsonParser.parse(response.body()).getAsJsonObject().get(JSON_BODY);
            JsonElement bodyStr = null;
            T t = null;
            if (jsonElement != null) {
                if (jsonElement.isJsonObject()) {
                    bodyStr = jsonElement.getAsJsonObject();
                    t = (T) jsonToBean(bodyStr, cls);
                } else if (jsonElement.isJsonArray()) {
                    bodyStr = jsonElement.getAsJsonArray();
                    t = (T) fromJsonList(bodyStr, cls);
                } else {
                    baseBeanObserver.onNext2(jsonElement);
                    return;
                }
            }
            baseBeanObserver.onNext(t);
        } else {
            baseBeanObserver.onCodeError(context, baseBean.getCode(), baseBean.getMsg());
        }
    }

    public static void NetCancel(Object tag) {
        OkGo.getInstance().cancelTag(tag);
    }
}
