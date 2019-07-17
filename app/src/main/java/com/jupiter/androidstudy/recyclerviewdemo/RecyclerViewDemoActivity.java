package com.jupiter.androidstudy.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jupiter.androidstudy.R;
import com.jupiter.androidstudy.recyclerviewdemo.simulategridview.RecyclerGridViewActivity;
import com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewitemdecoration.SimulateListViewItemDecorationActivity;
import com.jupiter.androidstudy.recyclerviewdemo.simulatelistviewsimpledivider.SimulateListViewSimpleDividerActivity;
import com.jupiter.androidstudy.recyclerviewdemo.simulatestaggerview.RecyclerStaggerActivity;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    private Button btnReclyclerListSimpleDivider;
    private Intent intent;
    private Button btnReclyclerListItemDecoration;
    private Button btnReclyclerGrid;
    private Button btnRecyclerStagger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        initView();
    }

    private void initView() {
        btnReclyclerListSimpleDivider = findViewById(R.id.btnRecyclerListViewSimpleDivider);
        btnReclyclerListItemDecoration = findViewById(R.id.btnRecyclerListViewItemDecoration);
        btnReclyclerGrid = findViewById(R.id.btnRecyclerGridView);
        btnRecyclerStagger = findViewById(R.id.btnRecyclerStaggerView);
    }

    public void onRecyclerViewClick(View view) {
        if(view == btnReclyclerListSimpleDivider){
            intent = new Intent(this, SimulateListViewSimpleDividerActivity.class);
        }else if(view == btnReclyclerListItemDecoration){
            intent = new Intent(this, SimulateListViewItemDecorationActivity.class);
        }else if(view == btnReclyclerGrid){
            intent = new Intent(this, RecyclerGridViewActivity.class);
        }else if(view == btnRecyclerStagger){
            intent = new Intent(this, RecyclerStaggerActivity.class);
        }
        startActivity(intent);
    }
}
