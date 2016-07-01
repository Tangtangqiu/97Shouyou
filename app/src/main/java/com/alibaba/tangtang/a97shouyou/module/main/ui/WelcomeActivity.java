package com.alibaba.tangtang.a97shouyou.module.main.ui;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.se7en.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoaiqiu on 2016/6/27.
 */
public class WelcomeActivity extends BaseActivity{

    private List<ImageView> mlistPics;
    private ViewPager mviewPager;
    private Button welcome_btn;

    private int moldVersion;
    private int mcurrentVersion;
    private ImageView welcome_logo;//从顶部飞入的图片
    private ImageView welcome_text;//从左边飞入的图片

    boolean mbIsLogin = false;//判断是否已经登录



    @Override
    protected int setViewId(){
        return R.layout.layout_welcomeactivity;
    }

    @Override
    protected void findViews(){
        mviewPager = (ViewPager) findViewById(R.id.vpWelcome);
        welcome_btn = (Button) findViewById(R.id.welcome_btn);
        welcome_logo = (ImageView) findViewById(R.id.welcome_logo);
        welcome_text = (ImageView) findViewById(R.id.welcome_text);
    }

    protected void addImageView(int iResid){
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(iResid);
        //让图片铺满
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mlistPics.add(imageView);
    }
    @Override
    protected void init(){
        mbIsLogin = SystemUtil.getSharedBoolean(Constant.LOGIN_FLAG,false);
        mcurrentVersion = SystemUtil.getSystemVersionCode();//当前版本
        moldVersion = SystemUtil.getSharedInt(Constant.VERSION_STRING,-1);//上一次的版本
        //如果是第一次或是升级后的第一次打开App
        if(moldVersion==-1||(mcurrentVersion>moldVersion)){
            mlistPics = new ArrayList<>();
            addImageView(R.mipmap.bg_guide_01);
            addImageView(R.mipmap.bg_guide_02);
            addImageView(R.mipmap.bg_guide_03);
            addImageView(R.mipmap.bg_guide_04);

            welcome_logo.setVisibility(View.INVISIBLE);
            welcome_text.setVisibility(View.INVISIBLE);

            mviewPager.setAdapter(new PagerAdapter(){
                @Override
                public int getCount(){
                    return mlistPics.size();
                }

                @Override
                public Object instantiateItem(ViewGroup container, int position){
                    container.addView(mlistPics.get(position));
                    return mlistPics.get(position);
                }

                @Override
                public boolean isViewFromObject(View view, Object object){
                    return view==object;
                }

                @Override
                public void destroyItem(ViewGroup container, int position, Object object){
                    container.removeView((View) object);
                }
            });
        }else {
            welcome_btn.setVisibility(View.GONE);
            mviewPager.setVisibility(View.GONE);

            welcome_logo.setVisibility(View.INVISIBLE);
            welcome_text.setVisibility(View.VISIBLE);//先显示文字
            showTextAnim();

        }


    }
    //显示动画
    protected void showTextAnim(){
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
        ,-1.0f //相对于父容器的距离
        ,Animation.RELATIVE_TO_PARENT
        ,0f //是控件本身的左上角
        ,Animation.RELATIVE_TO_PARENT
        ,0
        ,Animation.RELATIVE_TO_PARENT
        ,0
        );
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new OvershootInterpolator());//往前冲再回来
        welcome_text.startAnimation(translateAnimation);

        translateAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){

            }

            @Override
            public void onAnimationEnd(Animation animation){
                welcome_logo.setVisibility(View.VISIBLE);
                showLogo();

            }

            @Override
            public void onAnimationRepeat(Animation animation){
            }
        });
    }
    //对Logo的动画设置
    protected void showLogo(){
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT
                ,0 //相对于父容器的距离
                ,Animation.RELATIVE_TO_PARENT
                ,0 //是控件本身的左上角
                ,Animation.RELATIVE_TO_PARENT
                ,-1.0f
                ,Animation.RELATIVE_TO_PARENT
                ,0
        );
        translateAnimation.setDuration(2000);
        translateAnimation.setInterpolator(new BounceInterpolator());//弹球效果
        welcome_logo.startAnimation(translateAnimation);
        translateAnimation.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation){
            }

            @Override
            public void onAnimationEnd(Animation animation){
                //执行完动画就跳转到下一个页面
                showNextActivity();
            }

            @Override
            public void onAnimationRepeat(Animation animation){
            }
        });


    }

    private void showNextActivity(){
        //如果是第一次登录就跳转到红包页面，否则跳转到主页面
        Intent intent = new Intent();
        if(mbIsLogin){
            //为了调试
            intent.setClass(this,MainActivity.class);
        }else {
            intent.setClass(this,RegisterActivity.class);
        }

        startActivity(intent);
        finish();
    }

    @Override
    protected void initEvent(){
        //给ViewPage设置监听
        mviewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            }

            @Override
            public void onPageSelected(int position){
                if(position==mlistPics.size()-1){
                    welcome_btn.setVisibility(View.VISIBLE);
                }else {
                    welcome_btn.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        //给按钮设置监听，点击跳转到HomeActvity中
        welcome_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //记录第一次
                SystemUtil.setSharedInt(Constant.VERSION_STRING,mcurrentVersion);
                showNextActivity();
            }
        });
    }


    @Override
    protected void loadData(){

    }
}
