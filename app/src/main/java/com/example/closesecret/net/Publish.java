package com.example.closesecret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.closesecret.Config;

public class Publish {


    public Publish(String phone_md5,String token,String msg,final SuccessCallback successCallback,final FailCallback failCallback) {

        StringBuilder request_url = new StringBuilder();
        request_url.append(Config.SERVER_URL).append(Config.ACTION_PUBLISH);
        new NetConnection(request_url.toString(), HttpMethod.POST, new NetConnection.SuccessCallback() {

            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    switch (jsonObject.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            if (successCallback!=null) {
                                successCallback.onSuccess();
                            }
                            break;
                        case Config.RESULT_STATUS_INVALID_TOKEN:
                            if (failCallback!=null) {
                                failCallback.onFail(Config.RESULT_STATUS_INVALID_TOKEN);
                            }
                            break;
                        default:
                            if (failCallback!=null) {
                                failCallback.onFail(Config.RESULT_STATUS_FAIL);
                            }
                            break;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    if (failCallback!=null) {
                        failCallback.onFail(Config.RESULT_STATUS_FAIL);
                    }
                }
            }
        }, new NetConnection.FailCallback() {

            @Override
            public void onFail() {
                if (failCallback!=null) {
                    failCallback.onFail(Config.RESULT_STATUS_FAIL);
                }
            }
        }, Config.KEY_ACTION,Config.ACTION_PUBLISH,
                Config.KEY_PHONE_MD5,phone_md5,
                Config.KEY_TOKEN,token,
                Config.KEY_MSG,msg);
    }

    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail(int errorCode);
    }
}

