package com.alibaba.tangtang.a97shouyou.module.money.ui;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.alibaba.tangtang.a97shouyou.R;
import com.alibaba.tangtang.a97shouyou.base.BaseActivity;
import com.alibaba.tangtang.a97shouyou.common.adapter.MyPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchGameActivity extends BaseActivity{

    private ViewPager search_viewPage;
    List<Fragment> fragments;
    private RadioGroup search_title_container;

    @Override
    protected int setViewId(){
        return R.layout.activity_search_game;
    }

    @Override
    protected void findViews(){
        search_viewPage = (ViewPager) findViewById(R.id.search_viewPage);
        search_title_container = (RadioGroup) findViewById(R.id.search_title_container);

    }

    @Override
    protected void init(){
        fragments = new ArrayList<>();
        fragments.add(new AllGameFragment());
        fragments.add(new TryGameFragment());
        fragments.add(new HaveGameFragment());

        MyPageAdapter vpAdapter = new MyPageAdapter(getSupportFragmentManager(),fragments);
        search_viewPage.setAdapter(vpAdapter);
    }

    @Override
    protected void initEvent(){
        //给ViewPage设置监听器
        search_viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            }

            @Override
            public void onPageSelected(int position){
                switch(position){
                    case 0:search_title_container.check(R.id.search_title_allGame);
                        break;
                    case 1:search_title_container.check(R.id.search_title_tryGame);
                        break;
                    case 2:search_title_container.check(R.id.search_title_haveGame);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });
        //给RadioGrup设置监听器
        search_title_container.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch(group.getCheckedRadioButtonId()){
                    case R.id.search_title_allGame:
                        search_viewPage.setCurrentItem(0);

                        break;
                    case R.id.search_title_tryGame:
                        search_viewPage.setCurrentItem(1);
                        break;
                    case R.id.search_title_haveGame:
                        search_viewPage.setCurrentItem(2);
                        break;
                }
            }
        });
        /*new View.OnClickListener(){
            @Override
            public void onClick(View v){
                switch(v.getId()){
                    case R.id.search_title_allGame:
                        search_viewPage.setCurrentItem(0);
                        break;
                    case R.id.search_title_tryGame:
                        search_viewPage.setCurrentItem(1);
                        break;
                    case R.id.search_title_haveGame:
                        search_viewPage.setCurrentItem(2);
                        break;
                }
            }
        }*///);

    }

    @Override
    protected void loadData(){
    }
}
