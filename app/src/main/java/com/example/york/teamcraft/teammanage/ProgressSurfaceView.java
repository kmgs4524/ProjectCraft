package com.example.york.teamcraft.teammanage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by York on 2017/9/19.
 */

public class ProgressSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder surfaceHolder;    // SurfaceHolder providing access and control over this SurfaceView's underlying surface

    // 繪製ProgressWheel的屬性
    private int sweepAngle;
    private int startAngle;
    private int totalTimerTime;

    private RectF rectF;
    private int diameter;

    private Paint paintProgress;
    private Paint paintBackground;
    private Paint textPaint;

    private int progressWidth;
    private int backgroundWidth;

    int percentNum; // 進度百分比
    String text;    // 進度百分比的字串

    public ProgressSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        surfaceHolder = getHolder();    // getHolder: 從系統取得SurfaceHolder物件
        this.setZOrderOnTop(true);
        surfaceHolder.setFormat(PixelFormat.TRANSLUCENT);
        surfaceHolder.addCallback(this);    //  設定SurfaceHolder物件的CallBack函式
        setProgressWheel(); // 設定繪製ProgressWheel的必要屬性

        percentNum = 0;

    }

    public void setProgressWheel() {
        sweepAngle = 0; // 實際進度的移動角度
        startAngle = 270; // 實際進度的開始角度

        progressWidth = 30; // 實際進度的寬度
        backgroundWidth = 40;   // 進度背景的寬度

        // 設定Background Paint的屬性
        paintBackground = new Paint();
        paintBackground.setColor(Color.GRAY);   // 抗鋸齒, 能夠讓圖的邊界變得模糊
        paintBackground.setAntiAlias(true);
        paintBackground.setStyle(Paint.Style.STROKE);   // Paint.Style.STROKE: 像是用畫筆畫過的效果, 呈現出只有外框的樣式
        paintBackground.setStrokeWidth(backgroundWidth);
        paintBackground.setStrokeCap(Paint.Cap.ROUND);  // 當畫筆樣式為STROKE或FILL_OR_STROKE時，設置筆刷的圖形樣式，如圓形樣式，從軌跡的頭跟尾可看出差異

        // 設定Progress Paint的屬性
        paintProgress = new Paint();
        paintProgress.setColor(Color.RED);
        paintProgress.setAntiAlias(true);
        paintProgress.setStyle(Paint.Style.STROKE);
        paintProgress.setStrokeWidth(progressWidth);
        paintProgress.setStrokeCap(Paint.Cap.ROUND);


    }

    @Override
    public void run() {
        Canvas canvas = null;

        for(int i = 0; i < 150; i++) {
            Log.d("Animation", "run() " + i);

            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {  // 鎖定surfaceHolder物件
                    diameter = canvas.getWidth() / 2;   // ProgressWheel的直徑
                    rectF = new RectF(canvas.getWidth() * 1/4, canvas.getHeight() / 8, canvas.getWidth() * 3/4, canvas.getHeight() * 1/8 + diameter );
                    sweepAngle = sweepAngle + 3;
                    text = "30%";
                    draw(canvas);
                }
            }
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas == null) {
            return ;
        }
        super.draw(canvas);

        Log.d("Animation", "draw()");

        // 繪製進度條的背景
        canvas.drawArc(rectF, 0, 360, false, paintBackground);
        // 繪製實際進度條
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paintProgress);

        Rect rect = new Rect(canvas.getWidth() * 1/4, canvas.getHeight() / 8, canvas.getWidth() * 3/4, canvas.getHeight() * 1/8 + diameter );
        this.textPaint.getTextBounds(this.text, 0, text.length(), rect);    // 得到Text所在的矩形區塊的高和寬，並設在rect的height, width

        // 設定文字的起始位置
        float x = rectF.centerX() - rect.width() / 2;
        float y = rectF.centerY() + rect.height() / 2;
//        float x = (getWidth() / 2) - rectF.centerX();
//        float y = (getHeight() / 2) - rectF.centerY();

        canvas.drawText("30%", x, y, textPaint);
    }

    /* SurfaceHolder會主動回來呼叫下列三個函式 */
    // 當畫面大小改變時執行

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        new Thread(this).start();   // 啟動繪圖的thread(開始執行run())
    }

    // 當畫面的Canvas被建立時執行
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    // 當畫面的Canvas被銷毀時執行
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

}
