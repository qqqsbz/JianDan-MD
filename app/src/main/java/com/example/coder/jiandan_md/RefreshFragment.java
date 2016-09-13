package com.example.coder.jiandan_md;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.coder.jiandan_md.refresh.RefreshItemAdapter;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RefreshFragment extends Fragment implements CallBackService,ConstantString {


    @BindView(R.id.loading)
    public RotateLoading loading;

    @BindView(R.id.refresh_recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.refresh_materialRefreshLayout)
    public MaterialRefreshLayout materialRefreshLayout;

    private RefreshItemAdapter refreshItemAdapter;

    public RefreshFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_refresh,null);

        ButterKnife.bind(this,view);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loading.start();

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshItemAdapter = new RefreshItemAdapter(getActivity(),this,false);

        recyclerView.setAdapter(refreshItemAdapter);

        materialRefreshLayout.setIsOverLay(true);

        materialRefreshLayout.setWaveShow(false);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                refreshItemAdapter.onRefresh();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                refreshItemAdapter.onRefreshMore();

            }
        });

        refreshItemAdapter.onRefresh();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.refresh_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.refresh) {

            return true;
        }

        return false;
    }

    @Override
    public void onSuccess(int page, Object object) {

        loading.stop();

        materialRefreshLayout.finishRefresh();

        materialRefreshLayout.finishRefreshLoadMore();
    }

    @Override
    public void onError(int page, String errorMsg) {

        loading.stop();

        materialRefreshLayout.finishRefresh();

        materialRefreshLayout.finishRefreshLoadMore();

        Toast.makeText(getActivity(),LoadingError,Toast.LENGTH_SHORT).show();
    }
}
