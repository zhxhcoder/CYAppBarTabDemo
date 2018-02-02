package com.android.cy.appbardemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.cy.appbardemo.R;
import com.android.cy.appbardemo.adapter.MyRecyclerViewAdapter;
import com.android.cy.appbardemo.view.LoadMoreRecyclerView;


public class FragmentUS extends Fragment {

    private static FragmentUS instance=null;
    public static FragmentUS newInstance() {
        if(instance==null){
            instance= new FragmentUS();
        }
        return instance;
    }
    public FragmentUS(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        LoadMoreRecyclerView mRecyclerView = (LoadMoreRecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        int[]mImgList = new int[]{R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg,R.drawable.ic_bg};
        String [] mTag = getActivity().getResources().getStringArray(R.array.test);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(getActivity(), mImgList,mTag));
        return view;
    }


}
