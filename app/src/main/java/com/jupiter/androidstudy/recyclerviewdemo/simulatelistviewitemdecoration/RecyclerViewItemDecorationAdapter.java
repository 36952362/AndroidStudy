package com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewitemdecoration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jupiter.androidstudy.R;

import java.util.List;

public class RecyclerViewItemDecorationAdapter extends RecyclerView.Adapter<RecyclerViewItemDecorationAdapter.ItemHolder> {

    private final List<String> datas;
    private final Context context;

    public RecyclerViewItemDecorationAdapter(Context context, List<String> datas){
        this.context = context;
        this.datas = datas;
    }
    
    @NonNull
    @Override
    public RecyclerViewItemDecorationAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.simulate_list_decoration_item,null, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewItemDecorationAdapter.ItemHolder holder, int position) {
        String data = datas.get(position);
        holder.setData(data);

    }

    @Override
    public int getItemCount() {
        return  datas == null? 0 : datas.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        TextView textView ;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvItemDecoration);
        }

        public void setData(String data) {
            textView.setText(data);
        }
    }
}
