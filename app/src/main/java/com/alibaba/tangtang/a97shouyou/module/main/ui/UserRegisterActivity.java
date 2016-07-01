package com.alibaba.tangtang.a97shouyou.module.main.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;

public class UserRegisterActivity extends BaseActivity implements View.OnClickListener{

    private CheckBox user_grgister_checkbox;
    private ImageView goBack;
    private TextView getYanZhengMa;
    private int countTime=10;
    @Override
    protected int setViewId(){
        return R.layout.activity_user_register;
    }

    @Override
    protected void findViews(){
        user_grgister_checkbox = (CheckBox) findViewById(R.id.user_grgister_checkbox);
        user_grgister_checkbox.setMovementMethod(new LinkMovementMethod());//让TextView有超链接功能
        goBack = (ImageView) findViewById(R.id.goBack);
        getYanZhengMa = (TextView) findViewById(R.id.getYanZhengMa);

        //座超链接的另一种方法
        String agreText = "我已经阅读并接受注册协议";
        SpannableString spannableString = new SpannableString(agreText);
        spannableString.setSpan(new ClickableSpan(){
            @Override
            public void onClick(View widget){
                Intent intent = new Intent(UserRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        }, 7, agreText.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        user_grgister_checkbox.setText(spannableString);

    }

    @Override
    protected void init(){
    }

    @Override
    protected void initEvent(){
        goBack.setOnClickListener(this);
        getYanZhengMa.setOnClickListener(this);
    }

    @Override
    protected void loadData(){
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.goBack:
                finish();//关闭当前页面
                break;
            case R.id.getYanZhengMa:
                getYanZhengMa.setEnabled(false);

                //第一个参数为倒计时总时间长度，以毫秒为单位
                //第二个参数是回调onTick的时间间隔
                new CountDownTimer(countTime * 1000, 1000){
                    @Override
                    public void onTick(long millisUntilFinished){
                        countTime--;
                        getYanZhengMa.setText(countTime+"");
                    }

                    @Override
                    public void onFinish(){
                        countTime=10;
                        getYanZhengMa.setEnabled(true);
                        getYanZhengMa.setText("重新获取验证码");
                    }
                }.start();
                break;
        }
    }
}
