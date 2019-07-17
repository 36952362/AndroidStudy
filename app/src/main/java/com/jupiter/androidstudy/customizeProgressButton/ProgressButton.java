package com.jupiter.androidstudy.customizeProgressButton;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;

import lombok.Data;

@Data
public class ProgressButton extends AppCompatButton {

    private boolean progressable = false;

    private Drawable progressDrawable;

    private int currentProgress;

    private float maxValue = 0;


    public ProgressButton(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public ProgressButton(Context context){
        super(context);
    }

    public  void setCurrentProgress(int currentProgress){
        this.currentProgress = currentProgress;
        // 请求重新绘制，也就是再次调用onDraw方法
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas){
        if(progressable){
            if( 0 == maxValue){
                maxValue = 100;
            }

            //设置画图的上下左右
            int left = this.getLeft();
            int top = this.getTop();
            int bottom = this.getMeasuredHeight(); //高度就是当前按钮的高度

            //根据传入的进度而改变
            int right = (int)(this.currentProgress * this.getMeasuredWidth() * 1f/ maxValue + 0.5f);

            progressDrawable.setBounds(left, top, right, bottom);
            progressDrawable.draw(canvas);
        }
        super.onDraw(canvas);
    }

}
