package com.alibaba.tangtang.a97shouyou.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.module.money.bean.TaskGameInfo;

import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/7/1.
 */
public class MyMutilAdapter extends BaseAdapter{

    private Context context;
    private List<TaskGameInfo.InfoBean> datas;

    private final int TYPE_TITLE = 0;
    private final int TYPE_PLATFORM = 1;
    public MyMutilAdapter(List<TaskGameInfo.InfoBean> datas, Context context){
        this.datas = datas;
        this.context = context;
    }
    /**
     * 根据传入的position，返回每个position对应item的布局类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position){
        //第1条和第3条item的类型为TYPE_TILE
        if ((position == 0) || (position == 2)){
            return TYPE_TITLE;
        }else {
            return TYPE_PLATFORM;
        }
    }
    /**
     * 返回adpter当中item类型的数量
     * @return
     */
    @Override
    public int getViewTypeCount(){
        return 2;
    }

    @Override
    public int getCount(){
        return datas.size()+3;
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        int iType = getItemViewType(position);
        if(convertView == null){
            if(iType == TYPE_TITLE){
                convertView = LayoutInflater.from(context).inflate(R.layout.type_title,null);
                TitleHolder titleHolder = new TitleHolder();
                titleHolder.mtvTitleName = (TextView) convertView.findViewById(R.id.type_title);
                if (position == 0){
                    titleHolder.mtvTitleName.setText("官方推荐");
                }else {
                    titleHolder.mtvTitleName.setText("联盟任务");
                }
                convertView.setTag(titleHolder);

            }else {
                convertView = LayoutInflater.from(context).inflate(R.layout.search_item,null);
                PlatformHolder platformHolder = new PlatformHolder();
                platformHolder.mtvPlatformName = (TextView) convertView.findViewById(R.id.search_item_title);
                if (position == 1){
                    platformHolder.mtvPlatformName.setText("游戏任务");
                }else {
                    //因为前面多加了3条数据，所以数据集合里面的第一条数据，要通过postion-3来获取
                    platformHolder.mtvPlatformName.setText(datas.get(position-3).getPlatform_name());
                }


                convertView.setTag(platformHolder);
            }

        }else {
            if (iType == TYPE_TITLE){
                TitleHolder titleHolder = (TitleHolder) convertView.getTag();
                if (position == 0){
                    titleHolder.mtvTitleName.setText("官方推荐");
                }else {
                    titleHolder.mtvTitleName.setText("联盟任务");
                }

            }else {
                PlatformHolder platformHolder = (PlatformHolder) convertView.getTag();
                if (position == 1){
                    platformHolder.mtvPlatformName.setText("游戏任务");
                }else {
                    //因为前面多加了3条数据，所以数据集合里面的第一条数据，要通过postion-3来获取
                    platformHolder.mtvPlatformName.setText(datas.get(position-3).getPlatform_name());
                }

            }


        }
        return convertView;
    }

    //有多少个类型的item，就要对应的定义多少类型的viewholder
    class TitleHolder{
        TextView mtvTitleName;
    }

    class PlatformHolder{
        TextView mtvPlatformName;

    }
}
