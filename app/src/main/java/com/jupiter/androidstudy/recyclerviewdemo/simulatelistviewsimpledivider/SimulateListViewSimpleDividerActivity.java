package com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewsimpledivider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.jupiter.androidstudy.R;

import java.util.ArrayList;
import java.util.List;


/**
 *使用RecycleView来实现ListView的效果,并使用简单的View控件实现分割线的效果
 * 1. 创建一个线性布局管理器并赋值给RecyclerView控件
 * 2。创建一个Adapater并赋值给RecyclerView控件
 *    2.1 在Adapater中创建布局View
 *    2.2 在View中绑定数据并显示
 *    2.3 在布局文件中添加一个View控件来实现分割线的效果
 */

public class SimulateListViewSimpleDividerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list_view);
        recyclerView = findViewById(R.id.recyclerListView);
        
        initDatas();
    }

    private void initDatas() {

        //设置RecyclerView的布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SimulateListViewSimpleDividerActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        //设置内容的显示方向
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        //设置显示内容的顺序，如果true, 则从底向上显示
        //layoutManager.setReverseLayout(true);

        List<String> datas = new ArrayList<>();
        for(int i = 1 ; i < 100 ; i ++){
            datas.add("" + i );
        }

        //设置Adapter
        RecyclerViewSimpleDividerAdapter recyclerViewSimpleDividerAdapter = new RecyclerViewSimpleDividerAdapter(this, datas);

        recyclerViewSimpleDividerAdapter.setItemClickListener(new RecyclerViewSimpleDividerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(SimulateListViewSimpleDividerActivity.this, "被点击的条目是:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(recyclerViewSimpleDividerAdapter);

    }
}
