package com.example.binguner.zhijiao.View;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.example.binguner.zhijiao.R;

import butterknife.BindView;

/**
 * Created by binguner on 2017/8/21.
 */

public class WaveView extends View {


    private Context context;
    private int A = 30;  //振幅    在 Y 轴上的差值
    private int ω = 4;  //角速度   单位角度内
    private float φ = 0 ;  //初相，图像的左右移动
    private int k = 0;  //偏距，为图像的上移或下移
    //private int waveColor;
    private Path path;
    private Paint paint;
    private ValueAnimator valueAnimator;
    private int waveColor /*= R.color.colorBlue*/;


    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews(context,attrs);
        initPaint();
        initAnimartion();

    }

    private void initViews(Context context, @Nullable AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.WaveView);
        waveColor = typedArray.getColor(R.styleable.WaveView_waveView_color,getResources().getColor(R.color.colorBlack));
        A = typedArray.getInteger(R.styleable.WaveView_waveView_zhengFu,30);
        ω = typedArray.getInteger(R.styleable.WaveView_waveView_jiaoSuDu,4);
        φ = typedArray.getFloat(R.styleable.WaveView_waveView_chuXiang,0);
        k = typedArray.getInteger(R.styleable.WaveView_waveView_pianJu,0);
        typedArray.recycle();

    }

    /*public WaveView(Context context, AttributeSet attrs) {
        super(context,attrs);
        initPaint();
        initAnimartion();
    }*/




    private void initAnimartion() {
        /*WindowManager windowManager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int width = displayMetrics.widthPixels;*/
        valueAnimator = ValueAnimator.ofInt(0,getWidth());
        valueAnimator.setDuration(4000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        //更新
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Log.d("TAGTAG","执行");
                invalidate();
            }
        });
        valueAnimator.start();
    }

    private void initPaint() {
        paint = new Paint();
        //去除锯齿
        paint.setAntiAlias(true);
        paint.setColor(waveColor);
        //paint.setColor(getResources().getColor(waveColor));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();

        try {
            φ -= 0.05;
            float y;

            path.reset();

            path.moveTo(0, getHeight()/2);

            for (float x = 0; x <= getWidth(); x += 10) {
                //A Math.sin(ω x + φ ) + K)
                    /* A—振幅越大，波形在y轴上最大与最小值的差值越大
                       ω—角速度， 控制正弦周期(单位角度内震动的次数)
                       φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
                       k—偏距，反映在坐标系上则为图像的上移或下移。*/
                //y = (float) (30 * Math.sin(4 * Math.PI * x / getWidth()+φ));
                y = (float) (A * Math.sin(ω * Math.PI * x / getWidth()+φ) + k);
                path.lineTo(x, getHeight()/2 - y);
            }
            path.lineTo(getWidth(),getHeight());
            path.lineTo(0,getHeight());
            path.close();
            //path.lineTo(getWidth(), 0);
            //path.lineTo(0, getHeight());
            canvas.drawPath(path, paint);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
