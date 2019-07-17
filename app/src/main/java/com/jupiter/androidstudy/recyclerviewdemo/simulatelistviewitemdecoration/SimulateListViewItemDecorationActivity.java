package com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewitemdecoration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.jupiter.androidstudy.R;

import java.util.ArrayList;
import java.util.List;


/**
 *使用RecycleView来实现ListView的效果,并使用ItemDecoration实现分割线的效果
 * 1. 创建一个线性布局管理器并赋值给RecyclerView控件
 * 2. 创建一个Adapater并赋值给RecyclerView控件
 *    2.1 在Adapater中创建布局View
 *    2.2 在View中绑定数据并显示
 * 3. 使用ItemDecoration设置分割线
 *    3.1 在styles.xml文件中定义一个android:listDivider风格
 *    3.2 listDivider的值设置成一个drawable对象，在drawable对象中对颜色和分割线的大小进行设置
 */

public class SimulateListViewItemDecorationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulate_list_view_item_decoration);
        recyclerView = findViewById(R.id.recyclerListViewItemDecoration);

        initDatas();
    }

    private void initDatas() {

        //设置布局管理器
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SimulateListViewItemDecorationActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        List<String> datas = new ArrayList<>();
        for(int i = 0 ; i < 100; i++){
            datas.add("" + i );
        }

        //设置适配器并绑定数据
        RecyclerView.Adapter adapter = new RecyclerViewItemDecorationAdapter(SimulateListViewItemDecorationActivity.this, datas);
        recyclerView.setAdapter(adapter);

        //设置分割线
        RecyclerViewItemDecoration recyclerViewItemDecoration = new RecyclerViewItemDecoration(SimulateListViewItemDecorationActivity.this);
        recyclerView.addItemDecoration(recyclerViewItemDecoration);
    }
}
