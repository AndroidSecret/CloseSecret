package com.example.closesecret.net;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.example.closesecret.Config;

public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallbck,
                         final FailCallback failCallback, final String ... kvs){

        new AsyncTask<Void,Void,String>(){
            @Override
            protected String doInBackground(Void... voids) {

                StringBuffer paramStr = new StringBuffer();
                for(int i = 2;i<kvs.length;i+=2){//拼接参数对
                    paramStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }

                try {
                    URLConnection uc;

                    switch (method){
                        case POST:
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);//如果是POST方法，则需要往服务端上传参数
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                                    uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramStr.toString());//向服务器端发送参数,先写到缓存中
                            bw.flush();//清空缓存，立即向服务器端发送参数
                            break;
                        default://如果是GET方法，则需要先拼接url字符串,然后打开连接
                            uc = new URL(url+"?"+paramStr.toString()).openConnection();
                            break;
                    }

                    System.out.println("Request url:"+uc.getURL());
                    System.out.println("Request data:"+paramStr);

                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            uc.getInputStream(),Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while((line = br.readLine()) != null){
                        result.append(line);
                    }

                    System.out.println("Result:"+result);
                    return result.toString();//返回结果

                }catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String result) {//在doInBackground方法执行完毕后将它的返回值作为传入参数来执行
                if(result != null){
                    if(successCallbck != null){
                        successCallbck.onSuccess(result);
                    }
                }else{
                    if(failCallback != null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(result);
            }
        }.execute();
    }

    public static interface SuccessCallback{
        void onSuccess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }
}
