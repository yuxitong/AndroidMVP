package com.youbang.utils;/**
 * Created by 30884 on 2017/12/6.
 */

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.youbang.base.BaseApplication;

/**
 * --------------------------
 * 作者：鱼欲渔于鱼
 * 时间：2017/12/6
 * 备注：七牛云上传工具类
 * --------------------------
 */
public class QiniuUpDataUtils {


    public static void upVideo(String videoUrl, String vehicleId, String type, long time) {
//        String accessKey = "9F8pf1XH4IgXyg9EFcCgm-yMen0xQBMyh2xBNfLG";
//        String secretKey = "-4-YXABj2oMB-VR_EpaOC8Da2REydhjBGV4x5Wsu";
//        String bucket = "youbang";
//        final String domain = "http://ou1mhxxx9.bkt.clouddn.com/";
//        Auth auth = Auth.create(accessKey, secretKey);
//        String uploadToken = auth.uploadToken(bucket);
//
//        Log.e("abcdefg",type+"   "+videoUrl);
//
//        UploadManager uploadManager = new UploadManager();
//
//        String key = BaseApplication.sp.getString("license","")+TimeUtils.milliseconds2String(time)+".mp4";
//        uploadManager.put(videoUrl, key, uploadToken, new UpCompletionHandler() {
//
//            @Override
//            public void complete(String key, ResponseInfo info, JSONObject response) {
//                try {
//                    String path = response.getString("key");
//                    String pathDate = domain + path;
        OkGo.<String>post(BaseApplication.netConnect + "/upload_vehicle_video")
                .params("vehicle_id", vehicleId)
                .params("type", type)
                .params("start", TimeUtils.milliseconds2String(time))
                .params("url", videoUrl)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.e("abcdefg", "上传成功 " + response.code() + "  " + response.message() + "   " + response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Log.e("abcdefg", "失败 " + response.code() + "  " + response.message() + "   " + response.body());
                    }

                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        Log.e("abcdefg", "开始 " + request.getParams().toString());
                    }
                });
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, new UploadOptions(null, null, false, new UpProgressHandler() {
//            @Override
//            public void progress(String key, double percent) {
//                Log.e("abcdefg", percent + " ");
//            }
//        }, null));
    }
}
