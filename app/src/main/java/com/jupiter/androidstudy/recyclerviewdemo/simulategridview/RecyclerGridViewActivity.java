package com.jupiter.androidstudy.recyclerviewdemo.simulategridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jupiter.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerGridViewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid_view);
        recyclerView = findViewById(R.id.recyclergrid);

        initDatas();
    }

    private void initDatas() {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);

        List<String> datas = new ArrayList<>();

        for(int i = 0 ; i < 100 ; i ++){
            datas.add("" + i);
        }

        RecyclerView.Adapter adapter = new RecyclerGridViewAdapter(this, datas);

        recyclerView.setAdapter(adapter);


        RecyclerView.ItemDecoration itemDecoration = new RecyclerGridItemDecoration(this);

        recyclerView.addItemDecoration(itemDecoration);
    }
}
