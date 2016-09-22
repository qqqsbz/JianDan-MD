package com.example.coder.jiandan_md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
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
import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.util.CallBackService;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class StainFragment extends Fragment implements CallBackService {


    @BindView(R.id.stain_recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.loading)
    public RotateLoading loading;

    @BindView(R.id.stain_materialRefreshLayout)
    public MaterialRefreshLayout materialRefreshLayout;

    private StainItemAdapter stainItemAdapter;

    public StainFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stain,null);

        ButterKnife.bind(this,view);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setHasFixedSize(true);

        stainItemAdapter = new StainItemAdapter(getActivity(),this);

        recyclerView.setAdapter(stainItemAdapter);

        stainItemAdapter.onRefresh();

        loading.start();

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                stainItemAdapter.onRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                stainItemAdapter.onRefreshMore();
            }
        });
    }

    @Override
    public void onError(int page, String errorMsg) {

        loading.stop();

        if (page == 1) {

            materialRefreshLayout.finishRefresh();

        } else {

            materialRefreshLayout.finishRefreshLoadMore();
        }

        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(int page, Object object) {

        loading.stop();

        if (page == 1) {

            materialRefreshLayout.finishRefresh();

        } else {

            materialRefreshLayout.finishRefreshLoadMore();
        }


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_refresh) {

            loading.start();

            stainItemAdapter.clear();
        }

        return super.onOptionsItemSelected(item);
    }
}

