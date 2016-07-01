package com.alibaba.tangtang.a97shouyou.module.main.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;
import com.alibaba.tangtang.a97shouyou.common.widget.ButtonMenu;
import com.alibaba.tangtang.a97shouyou.module.gess.ui.GessFragment;
import com.alibaba.tangtang.a97shouyou.module.home.ui.HomeFragment;
import com.alibaba.tangtang.a97shouyou.module.mine.ui.MineFragment;
import com.alibaba.tangtang.a97shouyou.module.money.ui.MoneyFragment;
import com.alibaba.tangtang.a97shouyou.module.shop.ui.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{

    private ButtonMenu lastMenu;

    private HomeFragment homeFragment;
    private MoneyFragment moneyFragment;
    private GessFragment gessFragment;
    private ShopFragment shopFragment;
    private MineFragment mineFragment;

    private Fragment lastFragment;
    private List<Fragment> fragmentList;

    private long oldTime=0;
    //点击按钮做相应处理
    public void choose(View view){
        ButtonMenu buttonMenu = (ButtonMenu) view;
        buttonMenu.onselect();
        if(!lastMenu.equals(buttonMenu)){
            lastMenu.unselect();
        }
        lastMenu = buttonMenu;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){

            case R.id.buttonMenu_home:
                if(!(lastFragment instanceof HomeFragment)){
                    transaction.hide(lastFragment);

                }
                lastFragment = homeFragment;
                transaction.show(homeFragment);
                break;
            case R.id.buttonMenu_money:
                if(!(lastFragment instanceof MoneyFragment)){
                    transaction.hide(lastFragment);

                }
                lastFragment = moneyFragment;
                transaction.show(moneyFragment);
                moneyFragment.showLoadDialog();

                break;
            case R.id.buttonMenu_gess:
                if(!(lastFragment instanceof GessFragment)){
                    transaction.hide(lastFragment);
                    lastFragment = gessFragment;
                    transaction.show(gessFragment);
                }

                break;
            case R.id.buttonMenu_shop:
                if(!(lastFragment instanceof ShopFragment)){
                    transaction.hide(lastFragment);
                    lastFragment = shopFragment;
                }
                transaction.show(shopFragment);
                break;
            case R.id.buttonMenu_mine:
                if(!(lastFragment instanceof MineFragment)){
                    transaction.hide(lastFragment);
                    lastFragment = mineFragment;
                }
                transaction.show(mineFragment);
                break;

        }
        transaction.commit();
    }

    @Override
    protected int setViewId(){
        return R.layout.activity_main;
    }

    @Override
    protected void findViews(){
        lastMenu = (ButtonMenu)findViewById(R.id.buttonMenu_home);
    }


    @Override
    protected void init(){
        lastMenu.onselect();
        fragmentList = new ArrayList<>();

        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        gessFragment = new GessFragment();
        moneyFragment = new MoneyFragment();
        shopFragment = new ShopFragment();

        lastFragment = homeFragment;
        //把Fragment放入到集合中
        /*fragmentList.add(homeFragment);
        fragmentList.add(moneyFragment);
        fragmentList.add(gessFragment);
        fragmentList.add(shopFragment);
        fragmentList.add(mineFragment);*/


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        /*for(int i = 0; i < fragmentList.size(); i++){
            transaction.add(R.id.fragment_container,fragmentList.get(i));
        }*/

        transaction.add(R.id.fragment_container,moneyFragment);
        transaction.hide(moneyFragment);

        transaction.add(R.id.fragment_container,gessFragment);
        transaction.hide(gessFragment);

        transaction.add(R.id.fragment_container,shopFragment);
        transaction.hide(shopFragment);

        transaction.add(R.id.fragment_container,mineFragment);
        transaction.hide(mineFragment);

        transaction.add(R.id.fragment_container,homeFragment);

        transaction.commit();
    }

    @Override
    protected void initEvent(){
    }

    @Override
    protected void loadData(){
    }
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
