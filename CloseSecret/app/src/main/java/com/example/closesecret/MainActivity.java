package com.example.closesecret;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.closesecret.atys.AtyHome;
import com.example.closesecret.atys.AtyLogin;
import com.example.closesecret.atys.AtyTimeline;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        String token = Config.getCachedToken(this);
        String phone_num = Config.getCachedPhoneNum(this);
        //判断token和手机号是否存在
        if (token!=null&&phone_num!=null) {
            Intent i =new Intent(this, AtyTimeline.class);
            i.putExtra(Config.KEY_TOKEN, token);
            i.putExtra(Config.KEY_PHONE_NUM, phone_num);
            startActivity(i);
        }else{
            //不存在则打开登录页面
            startActivity(new Intent(this, AtyLogin.class));
        }

        finish();
    }


}
