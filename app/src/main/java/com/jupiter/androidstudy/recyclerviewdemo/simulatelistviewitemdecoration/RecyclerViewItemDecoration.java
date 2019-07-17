package com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewitemdecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {

    private static  final  int [] ATTR = new int[]{android.R.attr.listDivider};
    private final Drawable drawable;

    public  RecyclerViewItemDecoration(Context context){
        TypedArray typedArray = context.obtainStyledAttributes(ATTR);

        drawable = typedArray.getDrawable(0);

        typedArray.recycle();
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int left = parent.getLeft();
        int right = parent.getRight();

        int childCount = parent.getChildCount();
        for(int i = 0 ; i < childCount ; i++){
            int top = parent.getChildAt(i).getBottom();
            int bottom = top + drawable.getIntrinsicHeight();

            drawable.setBounds(left,top, right,bottom);
            drawable.draw(c);
        }

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
    }
}
