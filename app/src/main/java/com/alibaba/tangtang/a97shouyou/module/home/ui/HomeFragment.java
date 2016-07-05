package com.alibaba.tangtang.a97shouyou.module.home.ui;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseFragment;
import com.alibaba.tangtang.a97shouyou.base.NetCallback;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.alibaba.tangtang.a97shouyou.common.net.HttpNet;
import com.alibaba.tangtang.a97shouyou.common.utils.CountFormation;
import com.alibaba.tangtang.a97shouyou.common.widget.ButtonMenu;
import com.alibaba.tangtang.a97shouyou.common.widget.MyTransformation;
import com.alibaba.tangtang.a97shouyou.module.home.bean.Home_Banner;
import com.alibaba.tangtang.a97shouyou.module.home.bean.UserInfo;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoaiqiu on 2016/6/29.
 */
public class HomeFragment extends BaseFragment{

    private ImageView home_title_left;
    private TextView home_banner;
    List<String> baners;
    private StringBuilder stringBuilder;
    private ImageView home_head_image;
    private TextView home_head_name;
    private TextView home_yuer;
    private TextView home_ub;

    String total = "1200";
    private TextView home_start_money;

    @Override
    protected int setViewId(){
        return R.layout.layout_fragment_home;
    }

    @Override
    protected void findViews(View view){
        //标题栏左边的图片
        home_title_left = (ImageView) view.findViewById(R.id.home_title_left);
        home_banner = (TextView) view.findViewById(R.id.home_banner);
        //头像
        home_head_image = (ImageView) view.findViewById(R.id.home_head_image);
        //姓名
        home_head_name = (TextView) view.findViewById(R.id.textView2);
        //余额
        home_yuer = (TextView) view.findViewById(R.id.home_yuer);
        //合计U币
        home_ub = (TextView) view.findViewById(R.id.home_ub);
        home_start_money = (TextView) view.findViewById(R.id.home_start_money1);
        //rippleLayout = (MaterialRippleLayout) view.findViewById(R.id.home_start_money);
    }

    @Override
    protected void init(){
        baners = new ArrayList<>();
        //rippleLayout.setRippleDelayClick(true);

    }

    @Override
    protected void initEvent(){
        home_title_left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getActivity(), "聚焦测试", Toast.LENGTH_SHORT).show();
            }
        });
        home_start_money.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ButtonMenu buttonMenu = (ButtonMenu) getActivity().findViewById(R.id.buttonMenu_money);
                if(onButtonClick!=null){
                    onButtonClick.onClick(home_start_money);
                    // buttonMenu.setClickable(true);
                }
            }
        });

        //给波纹设置监听事件

    }
    private OnButtonClick onButtonClick;
    public void setOnButtonClick(OnButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }
    public interface OnButtonClick{
        void onClick(View view);
    }

    @Override
    protected void loadData(){
        //下载广告文字数据
        LoadBanner();
        //下载个人信息
        LoadUserInfo();
    }

    private void LoadUserInfo(){
        Map<String,String> param = new HashMap<>();
        param.put("uid",318529496+"");
        param.put("token","307dPdqRIq4aty44eEYDdxbAIZ777uJdH2oAyp2aadAd4wywvcojvDP9Qs6bMQBTGY+RPn8o0g");
        HttpNet.doHttpRequest("POST", Constant.HOME_USERINFO, param, new NetCallback(){
            @Override
            public void success(String strResult){
                Log.e("loadData","strResult="+strResult);
                Gson gson = new Gson();
                UserInfo userInfo = gson.fromJson(Constant.USER_INFO, UserInfo.class);
                UserInfo.InfoBean info =  userInfo.getInfo();

                Picasso.with(getActivity()).load(info.getHpic())
                        .transform(new MyTransformation())
                        .into(home_head_image);
                home_head_name.setText(info.getNickname());

                home_yuer.setText(CountFormation.getStringFomat1(info.getExpend()));
                home_ub.setText(info.getExpend());


            }

            @Override
            public void fail(String strResult){
            }
        });
    }

    private void LoadBanner(){
        Map<String,String> param = new HashMap<>();
        param.put("platform",2+"");
        HttpNet.doHttpRequest("POST", Constant.HOME_BANER_URL, param, new NetCallback(){
            @Override
            public void success(String strResult){

                //做数据解析
                Gson gson = new Gson();
                Home_Banner home_banners = gson.fromJson(strResult, Home_Banner.class);
                List<Home_Banner.InfoBean> infos = home_banners.getInfo();
                for(int i = 0; i < infos.size(); i++){
                    String content = infos.get(i).getContent();
                    baners.add(content);
                }
                //显示滚动条
                showBanners();
            }

            @Override
            public void fail(String strResult){
                Log.e("loadData","下载失败");
            }
        });
    }

    private void showBanners(){
        stringBuilder = new StringBuilder();
        for(int i = 0; i < baners.size(); i++){
            stringBuilder.append(baners.get(i)).append("            ");
        }
        Log.e("showBanners","stringBuilder = "+stringBuilder);
        home_banner.setText(stringBuilder);
    }
}
