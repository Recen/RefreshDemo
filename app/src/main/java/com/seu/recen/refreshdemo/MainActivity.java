package com.seu.recen.refreshdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.seu.recen.refreshdemo.refresh.PtrClassicDefaultHeader;
import com.seu.recen.refreshdemo.refresh.PtrClassicFrameLayout;
import com.seu.recen.refreshdemo.refresh.PtrDefaultHandler;
import com.seu.recen.refreshdemo.refresh.PtrDefaultHandlerLoadMore;
import com.seu.recen.refreshdemo.refresh.PtrFrameLayout;
import com.seu.recen.refreshdemo.refresh.PtrHandlerLoadMore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RefreshAdapter adapter;
    private PtrClassicFrameLayout ptrFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        ptrFrameLayout = (PtrClassicFrameLayout)findViewById(R.id.ptr_refresh);
        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(manager);

        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("This is item " + i);
        }
        adapter = new RefreshAdapter(datas,getBaseContext());
        recyclerView.setAdapter(adapter);
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
//        ptrFrameLayout.setPtrHandler(new PtrDefaultHandlerLoadMore() {
//            @Override
//            public void onLoadMoreBegin(PtrFrameLayout frame) {
//
//            }
//
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//
//            }
//        });
        ptrFrameLayout.setPtrHandler(new PtrHandlerLoadMore() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandlerLoadMore.checkContentCanBePulledUp(frame, content, footer);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                System.out.println("上拉刷新刷新hahahha");
                ptrFrameLayout.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                // here check $mListView instead of $content
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, recyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("刷新刷新哈哈哈");
                ptrFrameLayout.refreshComplete();
            }
        });

        ptrFrameLayout.setPullToRefresh(true);
    }
}
