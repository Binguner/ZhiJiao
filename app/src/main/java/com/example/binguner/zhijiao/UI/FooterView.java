package com.example.binguner.zhijiao.UI;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

import com.example.binguner.zhijiao.R;
import com.example.binguner.zhijiao.View.WaveView;

public class FooterView extends AppCompatActivity {

   //private Path path;
    //private double φ = 0;
    //private ValueAnimator valueAnimator;
    //private WaveView waveView;
    private WaveView waveView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //waveView = new WaveView(this);
        //setContentView(waveView);
        try{
            setContentView(R.layout.activity_footer_view);
        }catch (Exception e){
            e.printStackTrace();
        }
        initId();
        //initAnimation();
    }

    private void initId() {
        waveView = findViewById(R.id.myWaveView1);
    }
/*
    private void initAnimation() {
        WindowManager windowManager = this.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final int width = displayMetrics.widthPixels;
        valueAnimator = ValueAnimator.ofInt(0,width);
        valueAnimator.setDuration(400);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Log.d("TAGTAG","执行");
                waveView.invalidate();
            }
        });
        valueAnimator.start();
    }

    class WaveView extends View {
        Paint paint;
        public WaveView(Context context) {
            super(context);
            paint = new Paint();
            paint.setColor(getResources().getColor(R.color.colorBlue));
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Path path = new Path();

           try {
                φ -= 0.02;
                float y;

                path.reset();

                path.moveTo(0, getHeight()/2);

                for (float x = 0; x <= getWidth(); x += 10) {
                    //A Math.sin(ω x + φ ) + K)
                    *//* A—振幅越大，波形在y轴上最大与最小值的差值越大
                       ω—角速度， 控制正弦周期(单位角度内震动的次数)
                       φ—初相，反映在坐标系上则为图像的左右移动。这里通过不断改变φ,达到波浪移动效果
                       k—偏距，反映在坐标系上则为图像的上移或下移。*//*
                    y = (float) (30 * Math.sin(4 * Math.PI * x / getWidth()+φ));
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
    }*/
}
