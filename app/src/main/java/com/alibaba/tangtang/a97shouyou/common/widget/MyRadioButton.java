package com.alibaba.tangtang.a97shouyou.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by zhaoaiqiu on 2016/6/30.
 */
public class MyRadioButton extends RadioButton{

    private Paint paint;

    public MyRadioButton(Context context){
        super(context);
        initPaint(context,null);
    }

    public MyRadioButton(Context context, AttributeSet attrs){
        super(context, attrs);
        initPaint(context,attrs);
    }

    private void initPaint(Context context, AttributeSet attrs){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        canvas.drawCircle(10,10,20,paint);

    }
}
