package com.alibaba.tangtang.a97shouyou;

import android.app.Application;

import com.lidroid.xutils.DbUtils;
import com.se7en.utils.SystemUtil;

/**
 * Created by zhaoaiqiu on 2016/6/27.
 */
public class MyApplication extends Application{
    public static DbUtils dbUtils;
    public static MyApplication app;
    public static boolean mbCache = false;
    @Override
    public void onCreate(){
        super.onCreate();
        SystemUtil.setContext(this);
        app = this;
        createDbUtils();
    }

    public void createDbUtils(){
        dbUtils = DbUtils.create(this);
    }
}
