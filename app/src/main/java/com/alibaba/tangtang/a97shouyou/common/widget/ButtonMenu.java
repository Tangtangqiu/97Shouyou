package com.alibaba.tangtang.a97shouyou.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;

/**
 * Created by zhaoaiqiu on 2016/6/28.
 */
public class ButtonMenu extends LinearLayout{

    int nomalPicRes;
    int pressPicRes;
    private ImageView buttonMenu_image;
    private TextView buttonMenu_text;
    private boolean isselect = false;

    public ButtonMenu(Context context){
        super(context);
        initView(context, null);
    }

    public ButtonMenu(Context context, AttributeSet attrs){
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs){
        View view = LayoutInflater.from(context).inflate(R.layout.buttonmenu, this, true);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ButtonMenu);
        String strTitle = (String) array.getText(R.styleable.ButtonMenu_text);
        nomalPicRes = array.getResourceId(R.styleable.ButtonMenu_normalpic,-1);
        pressPicRes = array.getResourceId(R.styleable.ButtonMenu_prespic,-1);
        buttonMenu_image = (ImageView) view.findViewById(R.id.buttonMenu_image);
        buttonMenu_text = (TextView) view.findViewById(R.id.buttonMenu_text);

        buttonMenu_text.setText(strTitle);
        buttonMenu_image.setImageResource(nomalPicRes);

        array.recycle();
    }

    /**
     * 点击菜单时调用这个方法
     * 同时设置动画
     */
    public void onselect(){

        if(isselect){
            return;
            /*buttonMenu_text.setText("");
            //图片设置动画
            ScaleAnimation scaleAnimation = new ScaleAnimation(1,0.8f,1,0.8f,
                    Animation.RELATIVE_TO_SELF,
                    0.5f,
                    Animation.RELATIVE_TO_SELF,
                    0);
            scaleAnimation.setRepeatMode(ScaleAnimation.REVERSE);
            scaleAnimation.setRepeatCount(1);
            scaleAnimation.setDuration(200);
            scaleAnimation.setFillAfter(true);
            buttonMenu_image.startAnimation(scaleAnimation);
            isselect = false;*/
        }
        isselect = true;
        //文字设置平移
        buttonMenu_image.setImageResource(pressPicRes);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0,
                Animation.RELATIVE_TO_SELF,
                0,
                Animation.RELATIVE_TO_SELF,
                0,
                Animation.RELATIVE_TO_SELF,
                1);
        translateAnimation.setDuration(200);
        translateAnimation.setFillAfter(true);
        buttonMenu_text.startAnimation(translateAnimation);

        //图片设置动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,1.2f,1,1.2f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        buttonMenu_image.startAnimation(scaleAnimation);
    }

    public void unselect(){
        isselect = false;
        //文字设置平移
        buttonMenu_image.setImageResource(nomalPicRes);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0,
                Animation.RELATIVE_TO_SELF,
                0,
                Animation.RELATIVE_TO_SELF,
                1,
                Animation.RELATIVE_TO_SELF,
                0);
        translateAnimation.setDuration(200);
        buttonMenu_text.startAnimation(translateAnimation);

        //图片设置动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,1.2f,1,1.2f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0);
        scaleAnimation.setDuration(200);
        scaleAnimation.setFillAfter(true);
        buttonMenu_image.startAnimation(scaleAnimation);
    }
}
