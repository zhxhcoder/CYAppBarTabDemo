package com.android.cy.appbardemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.android.cy.appbardemo.adapter.MyRecyclerViewAdapter;
import com.android.cy.appbardemo.R;
import com.android.cy.appbardemo.listener.MyItemClickListener;
import com.android.cy.appbardemo.view.LoadMoreRecyclerView;

public class FragmentHS extends Fragment {
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static FragmentHS instance=null;
    public static FragmentHS newInstance() {
        if(instance==null){
            instance= new FragmentHS();
        }
        return instance;
    }
    public FragmentHS(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_layout, container, false);
        final LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]mImgList = new int[]{R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg};
        String [] mTag = getActivity().getResources().getStringArray(R.array.test);
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getActivity(),mImgList,mTag);
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(),"test"+postion,Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAutoLoadMoreEnable(true);
        mRecyclerView.setLoadMoreListener(new LoadMoreRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"加载更多",Toast.LENGTH_SHORT).show();
                        //TODO 下面注释掉的代码中的false是当数据加载完成以后为false的，然后加载更多的条目就会隐藏。
//                        mRecyclerView.notifyMoreFinish(false);
                    }
                }, 1000);
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(),"刷新",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
