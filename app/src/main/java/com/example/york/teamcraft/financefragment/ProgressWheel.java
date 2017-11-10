package com.example.york.teamcraft.financefragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by York on 2017/9/18.
 */

public class ProgressWheel extends View {
    private int sweepAngle; // 進度條移動的角度
    private int startAngle; // 進度條開始的角度
    private int totalTimerTime;

    // 圓環所在的矩形區塊
    private RectF rectF;
    // 圓環的直徑
    private int diameter;
    // 實際進度條
    private Paint paintProgress;
    private int progressWidth;  // Paint繪圖的寬度
    // 背景圓環
    private Paint paintBackground;
    private int backgroundWidth;    // Paint繪圖的寬度


    int percentNum;
    // 圓環中間的文字
    private Paint textPaint;
    String text;
    float x;    // 繪製文字的x軸位置
    float y;    // 繪製文字的y軸位置

    private Handler handler = new Handler();

    // 一定要實做多了AttributeSet的Constructor才能夠在xml中使用這個View
    public ProgressWheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sweepAngle = 0; // 進度條移動的角度
        startAngle = 270;   // 進度條開始的角度，0度位於右邊的水平線，故270度位於垂直線的上邊
        totalTimerTime = 8;
//        rectF = new RectF(canvas.getWidth() * 1/4, canvas.getHeight() / 8, canvas.getWidth() * 3/4, canvas.getHeight() * 1/8 + diameter );    // 圓環所在的矩形區塊
        progressWidth = 30; // 進度條的寬度
        backgroundWidth = 40;   // 背景的寬度

        setPaintProgress();
        setProgressText();
        setPaintBackground();
//        new Thread(this).start();   // 在Background Threads啟動動畫
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        text = "10%"; // 加上"0%"這兩個字元之後包圍Text的Rect才能符合預計的寬度
        diameter = w / 2;   // 設定圓環直徑為View的一半寬度
        rectF = new RectF(w * 1/4, h / 8, w * 3/4, h * 1/8 + diameter );    // 圓環所在的矩形區塊

        Rect rect = new Rect(w * 1/4, h / 8, w * 3/4, h * 1/8 + diameter );
        this.textPaint.getTextBounds(text, 0, text.length(), rect);    // 得到Text所在的矩形區塊的高和寬，並設在rect的height, width
//         - rect.width() / 2
//          + rect.height() / 2
        x = rectF.centerX() - rect.width() / 2;
        y = rectF.centerY() + rect.height() / 2;
    }

    // 設定進度條
    public void setPaintProgress() {
        paintProgress = new Paint();    // 進度條的畫筆
        paintProgress.setColor(Color.RED);
        paintProgress.setAntiAlias(true);   // 設定是否反鋸齒
        paintProgress.setStyle(Paint.Style.STROKE); // 畫筆的樣式, STROKE為空心
        paintProgress.setStrokeWidth(progressWidth);    // 當畫筆樣式為STROKE時, 設置畫筆的寬度
        paintProgress.setStrokeCap(Paint.Cap.ROUND);    // 畫筆使末端的樣式, ROUND為圓弧狀
    }

    // 設定圓環背景
    public void setPaintBackground() {
        paintBackground = new Paint();  // 背景的畫筆
        paintBackground.setColor(Color.GRAY);   // 抗鋸齒, 能夠讓圖的邊界變得模糊
        paintBackground.setAntiAlias(true);
        paintBackground.setStyle(Paint.Style.STROKE);   // Paint.Style.STROKE: 像是用畫筆畫過的效果, 呈現出只有外框的樣式
        paintBackground.setStrokeWidth(backgroundWidth);
        paintBackground.setStrokeCap(Paint.Cap.ROUND);  // 當畫筆樣式為STROKE或FILL_OR_STROKE時，設置筆刷的圖形樣式，如圓形樣式，從軌跡的頭跟尾可看出差異
    }

    // 設定圓環中間的文字
    public void setProgressText() {
        // ProgressWheel中間的文字
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(200);
    }

    // 負責更新View(重繪)的方法
    public void update() {
        // 每次重繪都增加1度
        sweepAngle = sweepAngle + 1;
//        percentNum = percentNum + 1;
//        text = Integer.toString(percentNum);
        invalidate();   // 此方法會通知Android系統去呼叫onDraw()
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
//        if(diameter == 0){
//            diameter = canvas.getWidth() / 2;   // ProgressWheel的直徑
//        }

        // 背景
        canvas.drawArc(rectF, 0, 360, false, paintBackground);
        // 實際進度
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paintProgress);
        // 進度百分比
        canvas.drawText(text, x, y, textPaint);
    }

//    @Override
//    public void run() {
//
//            final Timer timer= new Timer();
//            timer.schedule(new TimerTask() {
//                @Override
//                public void run() {
//                    Log.d("Animation", "run()" );
//                    // Background Thread 不能更改UI，因此須呼叫Handler.post()將更改UI的程序放到UI Thread來做
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            update();
//                            if(sweepAngle == 270) {
//                                Log.d("sweepAngle", Integer.toString(sweepAngle));
//                                timer.cancel();
//                                timer.purge();
//                            }
//                        }
//                    });
//
//                }
//            }, 0, 5);   // 暫時設定每5毫秒執行handler.post()
//
//    }

}
