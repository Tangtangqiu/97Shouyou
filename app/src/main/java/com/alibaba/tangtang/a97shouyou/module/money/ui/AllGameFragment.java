package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.view.View;
import android.widget.ListView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.alibaba.tangtang.a97shouyou.base.ListViewCallback;
import com.alibaba.tangtang.a97shouyou.common.adapter.CommonAdapter;
import com.alibaba.tangtang.a97shouyou.common.adapter.ViewHolder;
import com.alibaba.tangtang.a97shouyou.common.utils.CountFormation;
import com.alibaba.tangtang.a97shouyou.module.money.bean.Search_Game;
import com.alibaba.tangtang.a97shouyou.module.money.dao.SearchGameDao;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class AllGameFragment extends BaseFragment{

    private PullToRefreshListView search_allGame_listView;

    CommonAdapter<Search_Game.InfoBean> adapter;
    private List<Search_Game.InfoBean> dataLists;

    private int page = 0;
    @Override
    protected int setViewId(){
        return R.layout.search_allgame;
    }

    @Override
    protected void findViews(View view){
        search_allGame_listView = (PullToRefreshListView) view.findViewById(R.id.search_allGame_listView);

    }

    @Override
    protected void init(){
        //支持两种刷新模式
        search_allGame_listView.setMode(PullToRefreshBase.Mode.BOTH);

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
        search_allGame_listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent(){


        search_allGame_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
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
        });
    }

    @Override
    protected void loadData(){

        SearchGameDao.getAllGameInfo(page, new ListViewCallback(){
            @Override
            public void updataListview(Object object){
                List<Search_Game.InfoBean> dataList = (List<Search_Game.InfoBean>) object;
                dataLists.addAll(dataList);
                adapter.notifyDataSetChanged();
                search_allGame_listView.onRefreshComplete();
            }
        });
    }


}
