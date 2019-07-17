package com.jupiter.androidstudy.recyclerviewdemo.simulategridview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerGridItemDecoration extends RecyclerView.ItemDecoration {
    private final Context context;

    private  static  final  int ATTR [] = new int[]{android.R.attr.listDivider};
    private final Drawable drawable;

    public RecyclerGridItemDecoration(Context context) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(ATTR);
        drawable = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        onHorizontalDraw(c, parent);

        onVerticalDraw(c, parent);
    }

    private void onVerticalDraw(Canvas c, RecyclerView parent) {



        int childCount = parent.getChildCount();
        for(int i = 0 ; i < childCount; i++){
            View child = parent.getChildAt(i);
            int top = child.getTop();
            int bottom = child.getBottom();
            int left = child.getRight();
            int right = left + drawable.getIntrinsicWidth();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }

    }

    private void onHorizontalDraw(@NonNull Canvas c, @NonNull RecyclerView parent) {
        int left = parent.getLeft();
        int right = parent.getRight();

        int childCount = parent.getChildCount();
        for(int i = 0 ; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom();
            int bottom = top + drawable.getIntrinsicHeight();
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }
}
