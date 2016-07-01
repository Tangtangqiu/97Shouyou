package com.alibaba.tangtang.a97shouyou.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class MyPageAdapter extends FragmentPagerAdapter{
    private List<Fragment> listViewList;

    public MyPageAdapter(FragmentManager fm,List listViewList){
        super(fm);
        this.listViewList = listViewList;
    }

    @Override
    public Fragment getItem(int position){
        return listViewList.get(position);
    }

    @Override
    public int getCount(){
        return listViewList.size();
    }
}
