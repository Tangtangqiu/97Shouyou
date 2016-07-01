package com.alibaba.tangtang.a97shouyou.module.main.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;

/**
 * Created by zhaoaiqiu on 2016/6/27.
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private View view;
    private PopupWindow popupWindow = null;
    private Button register;
    private TextView loGin;

    /**
     * activity回调到这个方法才是真正创建完毕
     * 创建并显示的popwindow应该显示在这里
     * 而不是oncreate里面
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(popupWindow==null){
            setAlpha(0.5f);
            popupWindow = new PopupWindow(view,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    false);//不设置popWindow为焦点，设置Activity为焦点
            popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);
        }

    }

    /**
     *设置Activity的透明度
     * @param alpha
     */
    protected void setAlpha(float alpha){
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = alpha;
        getWindow().setAttributes(params);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(popupWindow!=null){//防止出现溢出错误
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    @Override
    protected int setViewId(){
        return R.layout.layout_registeractivity;
    }

    @Override
    protected void findViews(){


    }

    @Override
    protected void init(){
        view = LayoutInflater.from(this).inflate(R.layout.layout_pw_rb,null);
        register = (Button) view.findViewById(R.id.register);
        loGin = (TextView) view.findViewById(R.id.dengLu);

    }

    @Override
    protected void initEvent(){
        register.setOnClickListener(this);
        loGin.setOnClickListener(this);
    }

    @Override
    protected void loadData(){
    }
    //点击注册和登录使用使用
    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.register:
                //跳转到注册页面
                Intent intent = new Intent(RegisterActivity.this,UserRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.dengLu://登录界面
                //跳转到登录页面
                Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
