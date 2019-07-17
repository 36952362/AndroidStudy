package com.jupiter.androidstudy.recyclerviewdemo.simulatestaggerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.jupiter.androidstudy.R;

public class RecyclerStaggerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_stagger);

        recyclerView = findViewById(R.id.recyclerstagger);

        initDatas();
    }

    private void initDatas() {

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, RecyclerView.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new RecyclerStaggerAdapter(this, Pic.pics);

        recyclerView.setAdapter(adapter);

        //这里同样可以通过ItemDecoration对边界进行设置
        //TODO：
    }

}
