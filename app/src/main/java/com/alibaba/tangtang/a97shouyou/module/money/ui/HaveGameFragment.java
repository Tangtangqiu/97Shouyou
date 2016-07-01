package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.view.View;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class HaveGameFragment extends BaseFragment{

    private PullToRefreshListView search_haveGame_listView;

    @Override
    protected int setViewId(){
        return R.layout.search_havegame;
    }

    @Override
    protected void findViews(View view){
        search_haveGame_listView = (PullToRefreshListView) view.findViewById(R.id.search_haveGame_listView);
    }

    @Override
    protected void init(){
    }

    @Override
    protected void initEvent(){
    }

    @Override
    protected void loadData(){
    }
}
