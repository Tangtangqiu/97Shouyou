package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.alibaba.tangtang.a97shouyou.base.ListViewCallback;
import com.alibaba.tangtang.a97shouyou.common.adapter.CommonAdapter;
import com.alibaba.tangtang.a97shouyou.common.adapter.ViewHolder;
import com.alibaba.tangtang.a97shouyou.common.net.HttpNet;
import com.alibaba.tangtang.a97shouyou.common.utils.CountFormation;
import com.alibaba.tangtang.a97shouyou.module.money.bean.InfoBean;
import com.alibaba.tangtang.a97shouyou.module.money.dao.SearchGameDao;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class TryGameFragment extends BaseFragment{

    private PullToRefreshListView search_tryGame_listView;
    private TextView search_try_circle;
    CommonAdapter<InfoBean> adapter;
    private List<InfoBean> dataLists;

    private int page = 1;
    @Override
    protected int setViewId(){
        return R.layout.search_trygame;
    }

    @Override
    protected void findViews(View view){
        search_tryGame_listView = (PullToRefreshListView) view.findViewById(R.id.search_tryGame_listView);
        search_try_circle = (TextView) getActivity().findViewById(R.id.search_try_circle);
    }

    @Override
    protected void init(){
        //支持两种刷新模式
        search_tryGame_listView.setMode(PullToRefreshBase.Mode.BOTH);

        dataLists = new ArrayList<>();
        adapter = new CommonAdapter<InfoBean>(getActivity(),
                dataLists,
                R.layout.search_item){
            @Override
            public void convert(ViewHolder helper, int position, InfoBean item){
                helper.setText(R.id.search_item_title,item.getName());
                helper.setText(R.id.search_downCount,item.getDl_num()+"人下载");
                helper.setText(R.id.search_size,item.getSize());
                helper.setRating(R.id.search_item_rating,(int)Double.parseDouble(item.getScore()));
                helper.setImageByUrl(R.id.search_item_image,item.getIcon(),getActivity());

                helper.setText(R.id.search_getU,"奖"+ CountFormation.getStringFomat(item.getAll_prize())+"U币");



            }
        };
        search_tryGame_listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent(){
        search_tryGame_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>(){
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
                search_try_circle.setText(dataLists.size()+"");
            }
        });
    }

    @Override
    protected void loadData(){
        //加载数据之前先进行判断，如果有网怎么从网上下载最新数据，否则就从数据库中加载上一次保存额数据
        if(HttpNet.checkNetworkAvailable(getActivity())){//如果网络可用
            SearchGameDao.getAllGameInfo(page, new ListViewCallback(){
                @Override
                public void updataListview(Object object){
                    List<InfoBean> dataList = (List<InfoBean>) object;
                    Log.e("TryGameFragment", "dataList.size():" + dataList.size());

                    dataLists.addAll(dataList);
                    adapter.notifyDataSetChanged();
                    search_tryGame_listView.onRefreshComplete();
                    search_try_circle.setText(dataLists.size()+"");

                    //存储数据



                }
            });
        }else {//网络不可用，就从上一次存储的数据库中加载数据

        }

    }
}
