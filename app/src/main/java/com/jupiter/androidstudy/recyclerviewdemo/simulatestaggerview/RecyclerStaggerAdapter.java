package com.jupiter.androidstudy.recyclerviewdemo.simulatestaggerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jupiter.androidstudy.R;

public class RecyclerStaggerAdapter extends RecyclerView.Adapter<RecyclerStaggerAdapter.ItemHolder> {
    private final Context context;
    private final int[] pics;

    public RecyclerStaggerAdapter(Context context, int[] pics) {
        this.context = context;
        this.pics = pics;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.simulate_stagger_item, null, false);
        return  new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerStaggerAdapter.ItemHolder holder, int position) {

        int data = pics[position];
        holder.setData(data);

    }

    @Override
    public int getItemCount() {
        return pics == null ? 0 : pics.length;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.stageImageView);
        }

        public void setData(int data) {

            //通过设置采样率来降低图片大小

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2 ;

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), data, options);

            imageView.setImageBitmap(bitmap);
        }
    }
}
