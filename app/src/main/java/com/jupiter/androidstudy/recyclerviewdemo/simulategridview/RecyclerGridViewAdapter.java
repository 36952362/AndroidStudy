package com.jupiter.androidstudy.recyclerviewdemo.simulategridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jupiter.androidstudy.R;

import java.util.List;

public class RecyclerGridViewAdapter extends RecyclerView.Adapter<RecyclerGridViewAdapter.ItemHolder> {
    private final Context context;
    private final List<String> datas;

    public RecyclerGridViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;

    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.simulate_grid_item, null, false);

        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {

        String data = datas.get(position);
        itemHolder.setData(data);

    }

    @Override
    public int getItemCount() {
        return  datas == null? 0 : datas.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private String data;
        private TextView textView;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvGridView);
        }

        public void setData(String data) {
            this.data = data;
            textView.setText(data);
        }
    }
}
