package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.alibaba.tangtang.a97shouyou.base.ListViewCallback;
import com.alibaba.tangtang.a97shouyou.common.adapter.MyMutilAdapter;
import com.alibaba.tangtang.a97shouyou.module.money.bean.TaskGameInfo;
import com.alibaba.tangtang.a97shouyou.module.money.dao.MoneyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class MoneyFragment extends BaseFragment{

    private boolean isLoadData;
    private PopupWindow popupWindow;
    private View view;
    private View mviewFragment;
    private ListView money_listView;

//    private CommonAdapter<TaskGameInfo.InfoBean> adapter;
    private MyMutilAdapter adapter;
    private List<TaskGameInfo.InfoBean> dataList;
    //当点击在加载数据的时候，显示此对话框；若已经完成，则把此对话框消失
    public void showLoadDialog(){

        if(!isLoadData&&(popupWindow==null)){

            setAlpha(0.5f);
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    false);
            popupWindow.showAtLocation(mviewFragment, Gravity.CENTER,0,0);


        }
    }
    /**
     *设置Activity的透明度
     * @param alpha
     */
    protected void setAlpha(float alpha){
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = alpha;
        getActivity().getWindow().setAttributes(params);
    }
    @Override
    protected int setViewId(){
        return R.layout.layout_fragment_money;
    }

    @Override
    protected void findViews(View view){
        mviewFragment = view;
        money_listView = (ListView) view.findViewById(R.id.money_listView);
    }

    @Override
    protected void init(){
        view = LayoutInflater.from(this.getActivity()).inflate(R.layout.loaddata_popw, null);

        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.money_list_top,null);
        money_listView.addHeaderView(headView);

        //给头部添加点击事件
        headView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //当点击后就打开一个Activity
                Intent intent = new Intent(getActivity(),SearchGameActivity.class);
                startActivity(intent);
            }
        });
        //有问题
        dataList = new ArrayList<>();
        adapter = new MyMutilAdapter(dataList,getActivity());
        /*adapter = new CommonAdapter<TaskGameInfo.InfoBean>(getActivity(),
                dataList,
                R.layout.layout_money_item){
            @Override
            public void convert(ViewHolder helper, int position, TaskGameInfo.InfoBean item){
                helper.setText(R.id.money_item_title,item.getPlatform_name());
                helper.setRating(R.id.money_item_rating,Integer.parseInt(item.getRank()));
                helper.setImageByUrl(R.id.money_item_image,item.getAd_img(),getActivity());
                helper.setText(R.id.money_item_btn,item.getReward());
                helper.setText(R.id.money_item_des,item.getDesc());
            }
        };*/

        money_listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent(){

        money_listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                
            }
        });
    }

    @Override
    protected void loadData(){

        MoneyDao.getMoneyData(new ListViewCallback(){
            @Override
            public void updataListview(Object object){
                List<TaskGameInfo.InfoBean> datas = (List<TaskGameInfo.InfoBean>) object;
                dataList.addAll(datas);
                adapter.notifyDataSetChanged();
                isLoadData = true;

                if (popupWindow != null){
                    popupWindow.dismiss();
                    popupWindow = null;
                }
            }
        });
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(popupWindow!=null){//防止出现溢出错误
            popupWindow.dismiss();
            popupWindow = null;
        }
    }


}
