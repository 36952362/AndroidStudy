package com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewsimpledivider;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jupiter.androidstudy.R;

import java.util.List;

public class RecyclerViewSimpleDividerAdapter extends RecyclerView.Adapter<RecyclerViewSimpleDividerAdapter.ItemHolder> {

    private final Context context;
    private final List<String> datas;
    private OnItemClickListener onItemClickListener;

    public RecyclerViewSimpleDividerAdapter(Context context, List<String> datas){
        this.datas = datas;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.simulate_list_simple_item, null, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        Log.e("TAG","onBindViewHolder, position:" + position);
        String data = datas.get(position);
        holder.setData(data, position);

    }

    @Override
    public int getItemCount() {
        int size = (datas == null ? 0: datas.size());
        Log.e("TAG", "size=" + size);
        if(datas != null){
            return  datas.size();
        }
        return 0;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private int position;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.listText);

            initEvent(itemView);

        }

        private void initEvent(View itemView) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view, position);
                }
            });
        }

        public void setData(String data, int position) {
                textView.setText(data);
                this.position = position;
        }
    }

    public void setItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }


    public interface OnItemClickListener{

        void onItemClick(View view, int position);
    }
}
