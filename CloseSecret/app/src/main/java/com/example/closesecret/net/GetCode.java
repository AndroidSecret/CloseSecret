package com.example.closesecret.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.closesecret.Config;

//获取验证码
public class GetCode {
    //参数，手机号，成功失败回调
    public GetCode(String phone, final SuccessCallback successCallback, final FailCallback failCallback){

        StringBuilder request_url = new StringBuilder();
        //向服务器传的url
        request_url.append(Config.SERVER_URL).append(Config.ACTION_GET_CODE);
        new NetConnection(request_url.toString(),HttpMethod.POST,new NetConnection.SuccessCallback(){
            @Override
            public void onSuccess(String result) {

                try {
                    //传递内容，以JSON的方式
                    JSONObject jsonObj = new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)){
                        case Config.RESULT_STATUS_SUCCESS:
                            if(successCallback != null){
                                successCallback.onSuccess();
                            }
                            break;
                        default:
                            if(failCallback != null){
                                failCallback.onFail();
                            }
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if(failCallback != null){
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if(failCallback != null){
                    failCallback.onFail();
                }
            }
        },Config.KEY_ACTION,Config.ACTION_GET_CODE,Config.KEY_PHONE_NUM,phone);

    }

    public static interface SuccessCallback{
        void onSuccess();
    }

    public static interface FailCallback{
        void onFail();
    }
}



