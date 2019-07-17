package com.jupiter.androidstudy.circelprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jupiter.androidstudy.R;

import lombok.Data;

@Data
public class CircleProgress extends RelativeLayout {

    private ImageView imageView;
    private TextView textView;
    private RectF rectF;
    private int currentProgress;
    private int maxValue = 0;
    private boolean progressEnable = false;
    private Paint paint = new Paint();
    private int CIRCLE_LINE_COLOR = Color.parseColor("#ff0000");
    private int CIRCLE_LINE_SIZE = 1;

    public CircleProgress(Context context){
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载ImageView 和 TextView
        View view = View.inflate(context, R.layout.circle_layout, this);
        imageView = view.findViewById(R.id.iv_circle);
        textView = view.findViewById(R.id.tx_ratio);
        setPaintAttribute();
    }

    public void setText(String text){
        textView.setText(text);
    }

    public void setCurrentProgress(int progress){
        this.currentProgress = progress;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        if(progressEnable){
            if(rectF == null){
                // 画弧线的第一个参数：也就是在一个矩形里的画一个圆
                // 因为把这个圆画在ImageView里头，所以直接获取ImageView的各边位置即可，也就是在其内部画圆
                float left = imageView.getLeft() + CIRCLE_LINE_SIZE;
                float top = imageView.getTop() + CIRCLE_LINE_SIZE;
                float bottom = imageView.getBottom() - CIRCLE_LINE_SIZE;
                float right = imageView.getRight() - CIRCLE_LINE_SIZE;
                rectF = new RectF(left, top, right, bottom);
            }

            // 开始的角度，这里的角度，水平方向，也就是x轴方向为零度
            float startAngel = -90;

            if(0 == maxValue){
                maxValue = 100;
            }

            // 扫过的角度
            float sweepAngel = currentProgress * 360 * 1f / maxValue + 0.5f;
            //是否绕中心画
            boolean useCenter = false;
            canvas.drawArc(rectF, startAngel, sweepAngel, useCenter, paint);
        }
        super.onDraw(canvas);
    }

    private void setPaintAttribute() {
        //设置抵抗锯齿
        paint.setAntiAlias(true);
        // 设置样式
        paint.setStyle(Paint.Style.STROKE);
        // 设置大小
        paint.setStrokeWidth(CIRCLE_LINE_SIZE);
        // 设置弧线颜色
        paint.setColor(CIRCLE_LINE_COLOR);
    }
}
