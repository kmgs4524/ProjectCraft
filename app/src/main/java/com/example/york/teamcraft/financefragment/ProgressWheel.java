package com.example.york.teamcraft.financefragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by York on 2017/9/18.
 */

public class ProgressWheel extends View {
    private int sweepAngle; // 進度條移動的角度
    private int startAngle; // 進度條開始的角度

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

    // 一定要實做多了AttributeSet的Constructor才能夠在xml中使用這個View
    public ProgressWheel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sweepAngle = 50; // 進度條移動的角度
        startAngle = 270;   // 進度條開始的角度，0度位於右邊的水平線，故270度位於垂直線的上邊

        progressWidth = 30; // 進度條的寬度
        backgroundWidth = 40;   // 背景的寬度
        setView();
        setPaintProgress();
        setPaintBackground();
        setTextPaint();

    }

    public void setRectF (int height, int width) {
        diameter = width / 2;   // 設定圓環直徑為View的一半寬度
        // 圓環所在的矩形區塊
        // RectF由四個邊(left, top, right, bottom)的float型態座標來代表，width = right - left, height = bottom - top
        rectF = new RectF(width * 1/4, height / 8, width * 3/4, height * 1/8 + diameter );    // 左邊起點為距離整個View的最左邊到整個寬度的1/4的地方
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTextRect() {
        // 圓環中間文字的矩形區塊
        // Rect同RectF, 但座標型態為int
        Rect rect = new Rect(0, 0, 0, 0);
        textPaint.getTextBounds(text, 0, text.length(), rect);    // 得到Text所在的矩形區塊的高和寬，並設在rect的height, width

        // 繪製文字的原點座標
        // 設定文字的起始位置，讓文字對齊圓環中心
        x = rectF.centerX() - rect.width() / 2; // x = 圓環的矩形區塊中心的x座標 - 文字的矩形區塊的1/2寬度
        y = rectF.centerY() + rect.height() / 2;    // y = 圓環的矩形區塊中心的y座標 - 文字的矩形區塊的1/2高度
    }

    public void setView() {
        this.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                setRectF(getHeight(), getWidth());
                setTextRect();

                return true;
            }
        });
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

    // 設定圓環中間的文字樣式
    public void setTextPaint() {
        // ProgressWheel中間的文字
        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
        textPaint.setTextSize(200);
    }

    // invalidate()會觸發此方法
    // View顯示在螢幕上的最後一個步驟
    @Override
    public void onDraw(@NonNull Canvas canvas) {
        // 繪製背景條背景
        canvas.drawArc(rectF, 0, 360, false, paintBackground);
        // 繪製進度條
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paintProgress);
        // 繪製進度條中間的文字
        canvas.drawText(text, x, y, textPaint);
    }

    // 負責更新View(重繪)的方法
//    public void update() {
//        // 每次重繪都增加1度
//        sweepAngle = sweepAngle + 1;
//
//        invalidate();   // 此方法會通知Android系統去呼叫onDraw()
//    }

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
