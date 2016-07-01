package com.alibaba.tangtang.a97shouyou.module.home.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;

/**
 * Created by zhaoaiqiu on 2016/7/1.
 */
public class Home_SiGeKuang extends LinearLayout{

    private TextView home_sige_title;
    private TextView home_sige_des;

    public Home_SiGeKuang(Context context){
        super(context);
        initView(context,null);
    }

    public Home_SiGeKuang(Context context, AttributeSet attrs){
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs){
        View view = LayoutInflater.from(context).inflate(R.layout.home_sige_kuang, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Home_SiGeKuang);
        String text1 = (String) typedArray.getText(R.styleable.Home_SiGeKuang_text1);
        String text2 = (String) typedArray.getText(R.styleable.Home_SiGeKuang_text2);


        home_sige_title = (TextView) view.findViewById(R.id.home_sige_title);
        home_sige_des = (TextView) view.findViewById(R.id.home_sige_des);

        home_sige_title.setText(text1);
        home_sige_des.setText(text2);

        typedArray.recycle();
    }
}
