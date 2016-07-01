package com.alibaba.tangtang.a97shouyou.common.utils;

import java.text.DecimalFormat;

/**
 * 输入一个int类型整数，得到一个字符串
 * eg:1000   1,000
 *
 * Created by zhaoaiqiu on 2016/6/30.
 */
public class CountFormation{
    public static String getStringFomat(int count){

        if(count<1000){
            return count+"";
        }else {
            DecimalFormat df = new DecimalFormat("#,###");
            String format = df.format(count);
            return format;
        }
    }
}
