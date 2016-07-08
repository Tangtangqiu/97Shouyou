package com.alibaba.tangtang.a97shouyou.module.money.dao;

import com.alibaba.tangtang.a97shouyou.base.ListViewCallback;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.alibaba.tangtang.a97shouyou.module.money.bean.TaskGameInfo;
import com.google.gson.Gson;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class MoneyDao{

    public static void getMoneyData(ListViewCallback listviewCallBack){
        Gson gson = new Gson();
        TaskGameInfo taskGameInfo = gson.fromJson(Constant.GAME_TASK_LIST_JSON, TaskGameInfo.class);
        //把json数据解析成一个bean对象之后，通过回调，传给listview来更新条目
        listviewCallBack.updataListview(taskGameInfo.getInfo());
    }

    public static void getAllGameInfo(int iPage,ListViewCallback listviewCallBack){


        Gson gson = new Gson();
        TaskGameInfo taskGameInfo = gson.fromJson(Constant.GAME_TASK_LIST_JSON, TaskGameInfo.class);
        //把json数据解析成一个bean对象之后，通过回调，传给listview来更新条目
        listviewCallBack.updataListview(taskGameInfo.getInfo());
    }

}
