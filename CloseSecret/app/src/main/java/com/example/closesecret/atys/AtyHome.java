package com.example.closesecret.atys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.closesecret.AbstractMyActivityGroup;
import com.example.closesecret.Config;
import com.example.closesecret.R;
import com.example.closesecret.atys.AtyLogin;
import com.example.closesecret.atys.AtyMessage;
import com.example.closesecret.atys.AtyMessageCommentListAdapter;
import com.example.closesecret.atys.AtyPublish;
import com.example.closesecret.atys.AtyTimeline;
import com.example.closesecret.tools.MD5Tool;


import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
public class AtyHome extends AbstractMyActivityGroup {
//加载的Activity的名字，LocalActivityManager就是通过这些名字来查找对应的Activity的。

    private static final String CONTENT_ACTIVITY_NAME_0 = "contentActivity0";
    private static final String CONTENT_ACTIVITY_NAME_1 = "contentActivity1";
    private static final String CONTENT_ACTIVITY_NAME_2 = "contentActivity2";
    private String phone_num,token,phone_md5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.CustomTitleBarTheme);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.commmenttitlebtn);
        setContentView(R.layout.t);
        super.onCreate(savedInstanceState);

        ((RadioButton) findViewById(R.id.radio_button0)).setChecked(true);

        phone_num = getIntent().getStringExtra(Config.KEY_PHONE_NUM);
        token = getIntent().getStringExtra(Config.KEY_TOKEN);
        phone_md5 = MD5Tool.md5(phone_num);
    }

    /**
     * 找到自定义id的加载Activity的View
     */
    @Override
    protected ViewGroup getContainer() {
        return (ViewGroup) findViewById(R.id.container);
    }

    /**
     * 初始化按钮
     */
    @Override
    protected void initRadioBtns() {
        initRadioBtn(R.id.radio_button0);
        initRadioBtn(R.id.radio_button1);
        initRadioBtn(R.id.radio_button2);
    }

    /**
     * 导航按钮被点击时，具体发生的变化
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.radio_button0:

                    setContainerView(CONTENT_ACTIVITY_NAME_2, AtyMessage.class);
                    break;
                case R.id.radio_button1:
                    //setContainerView(CONTENT_ACTIVITY_NAME_1, AtyPublish.class);
                    Intent i = new Intent(this, AtyPublish.class);
                    i.putExtra(Config.KEY_PHONE_MD5, phone_md5);
                    i.putExtra(Config.KEY_TOKEN, token);
			        startActivityForResult(i,0);
                    break;
                case R.id.radio_button2:
//                    Intent y = new Intent(this, AtyTimeline.class);
//                    y.putExtra(Config.KEY_PHONE_MD5, phone_md5);
//                    y.putExtra(Config.KEY_TOKEN, token);
//                    startActivityForResult(y,0);
                    setContainerView(CONTENT_ACTIVITY_NAME_0, AtyTimeline.class);
                    break;
                default:
                    break;
            }
        }
    }
}


