package com.alibaba.tangtang.a97shouyou.module.main.ui;

import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;
import com.alibaba.tangtang.a97shouyou.base.NetCallback;
import com.alibaba.tangtang.a97shouyou.common.constant.Constant;
import com.alibaba.tangtang.a97shouyou.common.net.HttpNet;
import com.alibaba.tangtang.a97shouyou.module.main.bean.LoginInfo;
import com.google.gson.Gson;
import com.se7en.utils.SystemUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends BaseActivity implements View.OnClickListener{

    private TextView goto_register;
    private Button user_login;
    private TextView lost_pas;
    private long oldTime=0;
    private EditText userName;
    private EditText userPas;
    private CheckBox login_check;

    @Override
    protected int setViewId(){
        return R.layout.activity_login;
    }

    @Override
    protected void findViews(){
        //登录界面的注册
        goto_register = (TextView) findViewById(R.id.goTo_register);
        //登录界面的登录btn
        user_login = (Button) findViewById(R.id.user_login);
        //登录界面的忘记密码
        lost_pas = (TextView) findViewById(R.id.lost_pas);
        userName = (EditText) findViewById(R.id.userName);
        userPas = (EditText) findViewById(R.id.userPas);
        login_check = (CheckBox) findViewById(R.id.login_check);
    }

    @Override
    protected void init(){

    }

    @Override
    protected void initEvent(){
        goto_register.setOnClickListener(this);
        user_login.setOnClickListener(this);
    }

    @Override
    protected void loadData(){
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.goTo_register:
                //跳转到注册页面
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,UserRegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.user_login:
                user_login.setClickable(false);
                user_login.setText("正在登录中，请稍后");

                String strUser = userName.getText().toString();
                String strPsw = userPas.getText().toString();
                //使用base64对密码进行加密
                strPsw = new String(Base64.encode(strPsw.getBytes(),Base64.DEFAULT));
                Log.e("onClick",strUser+"---"+strPsw);
                //点击登录
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",strUser);
                params.put("password",strPsw);


                HttpNet.doHttpRequest("POST",
                        Constant.LOGIN_URL,
                        params,
                        new NetCallback() {
                            @Override
                            public void success(String strResult) {
                                Log.d("qf","网络访问成功："+strResult);
                                user_login.setClickable(true);
                                user_login.setText("登录");

                                doLoginInfo(strResult);
                            }

                            @Override
                            public void fail(String strMsg) {
                                Log.d("qf","网络访问失败失败：" + strMsg);
                                user_login.setClickable(true);
                                user_login.setText("登录");
                            }
                        });
                break;
            case R.id.lost_pas:
                //找回密码

                break;
        }
    }

    private void doLoginInfo(String loginInfo){
        Gson gson = new Gson();
        LoginInfo loginInfos = gson.fromJson(loginInfo, LoginInfo.class);
        String state = loginInfos.getState();
        if("success".equals(state)){
            Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
            Log.e("doLoginInfo","登录成功");
            //如果登录成功，则记录这种状态

            SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,true);

            //登录成功后，显示主页面，并销毁当前页面
            Intent intent = new Intent();
            intent.setClass(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            SystemUtil.setSharedBoolean(Constant.LOGIN_FLAG,false);
            Log.e("doLoginInfo","登录失败");
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    //第一种处理back回退键的方法
    /*@Override
    public void onBackPressed(){
        long currentTime = System.currentTimeMillis();
        //判断两秒时间
        if(currentTime-oldTime>2000){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            oldTime = currentTime;
        }else {
            finish();//直接退出
        }
        //super.onBackPressed();
    }*/
    //第二种处理回退键的方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode==KeyEvent.KEYCODE_BACK){
            long currentTime = System.currentTimeMillis();
            //判断两秒时间
            if(currentTime-oldTime>2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                oldTime = currentTime;
            }else {
                finish();//直接退出
            }

            return true;//已经处理完Back的点击事件
        }
        return super.onKeyDown(keyCode, event);
    }
}
