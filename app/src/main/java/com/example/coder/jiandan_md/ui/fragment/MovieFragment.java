package com.example.coder.jiandan_md.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.ui.MovieDetailActivity;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements CallBackService {


    @BindView(R.id.movie_materialRefreshLayout)
    public MaterialRefreshLayout materialRefreshLayout;

    @BindView(R.id.movie_gridView)
    public GridView gridView;

    @BindView(R.id.loading)
    public RotateLoading loading;

    private MovieItemAdapter movieItemAdapter;

    public MovieFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie,null);

        ButterKnife.bind(this,view);

        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        movieItemAdapter = new MovieItemAdapter(getActivity(),this);

        gridView.setAdapter(movieItemAdapter);

        movieItemAdapter.onRefresh();

        loading.start();

        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {

                movieItemAdapter.onRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                movieItemAdapter.onRefreshMore();
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(),MovieDetailActivity.class);

                intent.putExtra(ConstantString.MOVIEDETAIL_DATA, movieItemAdapter.getComment(position));


                startActivity(intent);

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.refresh_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_refresh) {

            loading.start();

            movieItemAdapter.clear();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onError(int page, String errorMsg) {

        loading.stop();

        if (page == 1) {

            materialRefreshLayout.finishRefresh();

        } else {

            materialRefreshLayout.finishRefreshLoadMore();
        }
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
}
