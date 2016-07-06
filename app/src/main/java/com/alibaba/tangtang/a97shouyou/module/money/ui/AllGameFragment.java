package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.view.View;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.alibaba.tangtang.a97shouyou.base.NetCallback;
import com.alibaba.tangtang.a97shouyou.common.adapter.MyRecycleViewAdapter;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.alibaba.tangtang.a97shouyou.common.net.HttpNet;
import com.alibaba.tangtang.a97shouyou.module.money.bean.Search_Game;
import com.google.gson.Gson;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class AllGameFragment extends BaseFragment{

    //private PullToRefreshListView search_allGame_listView;
    private PullLoadMoreRecyclerView search_allGame_listView;

    //CommonAdapter<Search_Game.InfoBean> adapter;
    private List<Search_Game.InfoBean> dataLists  = new ArrayList<>();

    private int page = 1;
    private MyRecycleViewAdapter adapter;

    @Override
    protected int setViewId(){
        return R.layout.search_allgame;
    }

    @Override
    protected void findViews(View view){
        //search_allGame_listView = (PullToRefreshListView) view.findViewById(R.id.search_allGame_listView1);
        search_allGame_listView = (PullLoadMoreRecyclerView) view.findViewById(R.id.search_allGame_listView1);
        /**
         * 很重要的一句话
         */
        search_allGame_listView.setLinearLayout();
    }

    @Override
    protected void init(){
        /**
         * PullLoadMoreRecyclerView
         */

        adapter = new MyRecycleViewAdapter(dataLists,getActivity());
        search_allGame_listView.setAdapter(adapter);
        //支持两种刷新模式
        /*search_allGame_listView.setMode(PullToRefreshBase.Mode.BOTH);

        dataLists = new ArrayList<>();
        adapter = new CommonAdapter<Search_Game.InfoBean>(getActivity(),
                dataLists,
                R.layout.search_item){
            @Override
            public void convert(ViewHolder helper, int position, Search_Game.InfoBean item){
                helper.setText(R.id.search_item_title,item.getName());
                helper.setText(R.id.search_downCount,item.getDl_num()+"人下载");
                helper.setText(R.id.search_size,item.getSize());
                helper.setRating(R.id.search_item_rating,(int)Double.parseDouble(item.getScore()));
                helper.setImageByUrl(R.id.search_item_image,item.getIcon(),getActivity());

                helper.setText(R.id.search_getU,"奖"+ CountFormation.getStringFomat(item.getAll_prize())+"U币");



            }
        };
        search_allGame_listView.setAdapter(adapter);*/
    }

    @Override
    protected void initEvent(){

        search_allGame_listView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener(){
            @Override
            public void onRefresh(){
                //下拉刷新
                page = 1;
                dataLists.clear();
                loadData();
            }

            @Override
            public void onLoadMore(){
                //上拉刷新
                page++;
                //dataLists.clear();
                loadData();
            }
        });

        /*search_allGame_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView){
                page = 1;
                dataLists.clear();
                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView){
                page++;
                loadData();
            }
        });*/
    }

    @Override
    protected void loadData(){
        //HttpNet.doHttpRequest();

        Map<String,String> param = new HashMap<>();
        param.put("platform","2");//平台参数类型，1位IOS，2位Android
        param.put("page",page+"");
        HttpNet.doHttpRequest("POST", Constant.MONEY_URL, param, new NetCallback(){
            @Override
            public void success(String strResult){
                Gson gson = new Gson();
                Search_Game search_game = gson.fromJson(strResult, Search_Game.class);
                List<Search_Game.InfoBean> infoBeanList = search_game.getInfo();
                dataLists.addAll(infoBeanList);

                adapter.notifyDataSetChanged();

                search_allGame_listView.setPullLoadMoreCompleted();
            }

            @Override
            public void fail(String strResult){
            }
        });
        /*SearchGameDao.getAllGameInfo(page, new ListViewCallback(){
            @Override
            public void updataListview(Object object){
                List<Search_Game.InfoBean> dataList = (List<Search_Game.InfoBean>) object;
                dataLists.addAll(dataList);
                adapter.notifyDataSetChanged();

                *//*adapter.notifyDataSetChanged();
                search_allGame_listView.onRefreshComplete();*//*
            }
        });*/
    }


}
