package com.alibaba.tangtang.a97shouyou;

import android.app.Application;

import com.se7en.utils.SystemUtil;

/**
 * Created by zhaoaiqiu on 2016/6/27.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate(){
        super.onCreate();
        SystemUtil.setContext(this);
    }
}
