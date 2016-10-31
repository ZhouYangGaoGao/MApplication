package com.gaogao.mapplication.utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class Request {

    public static void post(String url, final String tag, final MListener listener, String... bodys) {
        RequestParams params = new RequestParams(url);
        for (int i = 0; i < bodys.length; i += 2) {
            params.addBodyParameter(bodys[i], bodys[i + 1]);
        }
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                                        listener.onSuccess(tag, result);

                L.i("post.onSuccess<======" + result);
//                try {
//                    JSONObject object = new JSONObject(result);
//                    // 请求成功
//                    if ((result.contains("code") && object.getInt("code") == 200) || (result.contains("status") && object.getInt("status") == 1)) {
//                        listener.onSuccess(tag, result);
//                        // 请求失败
//                    } else {
//                        listener.onException(tag, result);
//                    }
//                } catch (JSONException e) {
//                    // 服务器出现异常
//                    listener.onException(tag, result);
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onFailure(tag, ex.toString());
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                listener.onException(tag, cex.toString());
            }

            @Override
            public void onFinished() {
                listener.onFinish(tag);
            }
        });


    }

    public static void get(String url, final String tag, final MListener listener, String... parameter) {
        RequestParams params = new RequestParams(url);
        if (parameter != null)
            for (int i = 0; i < parameter.length; i += 2) {
                params.addQueryStringParameter(parameter[i], parameter[i + 1]);
            }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                L.i("get.onSuccess<======  " + result);
                listener.onSuccess(tag, result);

//                try {
//                    JSONObject object = new JSONObject(result);
//                    // 请求成功
//
//                    if ((result.contains("code") && object.getInt("code") == 200) || (result.contains("status") && object.getInt("status") == 1)) {
//                        listener.onSuccess(tag, result);
//                        // 请求失败
//                    } else {
//                        listener.onException(tag, result);
//
//                    }
//                } catch (JSONException e) {
//                    // 服务器出现异常
//                    listener.onException(tag, result);
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                listener.onFailure(tag, ex.toString());
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                listener.onException(tag, cex.toString());
            }

            @Override
            public void onFinished() {
                listener.onFinish(tag);
            }
        });


    }


}
