package com.example.coder.jiandan_md.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.coder.jiandan_md.PictureItemAdapter;
import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends Fragment implements CallBackService , ConstantString {

    @BindView(R.id.picture_recyclerView)
    public RecyclerView recyclerView;

    @BindView(R.id.picture_materialRefreshLayout)
    public MaterialRefreshLayout materialRefreshLayout;

    @BindView(R.id.loading)
    public RotateLoading loading;

    /**
     * 数据适配器
     */
    private PictureItemAdapter pictureItemAdapter;

    protected PictureType pictureType;

    public enum PictureType {
        PictureTypeBoring,PictureTypeGirl;
    }

    public PictureFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_picture,container,false);

        setHasOptionsMenu(true);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        pictureItemAdapter = new PictureItemAdapter(getActivity(),this,pictureType);

        recyclerView.setAdapter(pictureItemAdapter);

        loading.start();

        pictureItemAdapter.onRefresh();

        materialRefreshLayout.setLoadMore(true);

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {

            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                pictureItemAdapter.onRefresh();

            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                pictureItemAdapter.onRefreshMore();
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

        Toast.makeText(getActivity(),errorMsg,Toast.LENGTH_LONG).show();
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

            pictureItemAdapter.clear();

            pictureItemAdapter.onRefresh();

            loading.start();

            materialRefreshLayout.finishRefreshLoadMore();

            materialRefreshLayout.finishRefresh();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
