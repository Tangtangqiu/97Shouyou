package com.alibaba.tangtang.a97shouyou.module.money.dao;

import com.alibaba.tangtang.a97shouyou.base.ListViewCallback;
import com.alibaba.tangtang.a97shouyou.base.NetCallback;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.alibaba.tangtang.a97shouyou.common.net.HttpNet;
import com.alibaba.tangtang.a97shouyou.module.money.bean.Search_Game;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoaiqiu on 2016/6/30.
 */
public class SearchGameDao{

    public static void getAllGameInfo(int page, final ListViewCallback listViewCallback){
        Map<String,String> param = new HashMap<>();
        param.put("platform","2");//平台参数类型，1位IOS，2位Android
        param.put("page",page+"");
        HttpNet.doHttpRequest("POST", Constant.MONEY_URL, param, new NetCallback(){
            @Override
            public void success(String strResult){
                Gson gson = new Gson();
                Search_Game search_game = gson.fromJson(strResult, Search_Game.class);
                List<Search_Game.InfoBean> infoBeanList = search_game.getInfo();

                listViewCallback.updataListview(infoBeanList);
            }

            @Override
            public void fail(String strResult){
            }
        });
    }
}
