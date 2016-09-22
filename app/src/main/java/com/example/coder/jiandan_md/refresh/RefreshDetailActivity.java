package com.example.coder.jiandan_md.refresh;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.HTMLElement;
import com.example.coder.jiandan_md.model.RefreshDetail;
import com.example.coder.jiandan_md.model.RefreshPost;
import com.example.coder.jiandan_md.net.ApiService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.util.HTMLParser;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.victor.loading.rotate.RotateLoading;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RefreshDetailActivity extends AppCompatActivity implements ConstantString {

    @BindView(R.id.refresh_detail_toolbar)
    public Toolbar toolbar;

    @BindView(R.id.refresh_detail_collapsingToobar)
    public CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.refresh_detail_cover)
    public ImageView coverImageView;

    @BindView(R.id.loading)
    public RotateLoading loading;

    @BindView(R.id.refresh_detail_content)
    public LinearLayout contentLayout;

    private RefreshPost refreshPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_detail);

        ButterKnife.bind(this);

        refreshPost = (RefreshPost) getIntent().getSerializableExtra(REFRESH_ITEM_KEY);

        initView();

        initData();
    }

    private void initView() {

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout.setExpandedTitleMarginStart(25);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });

    }

    private void initData() {

        collapsingToolbarLayout.setTitle(refreshPost.getTitle());

        ImageLoadProxy.displayImageWithLoadingPicture(refreshPost.getCustomFields().getThumbC().get(0),coverImageView,R.drawable.ic_loading_large);

        loadDetail();
    }

    private void loadDetail() {

        loading.start();

        Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(REFRESH_DETAIL_BASEURL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<RefreshDetail> call = apiService.getRefreshWithId(refreshPost.getId());

        call.enqueue(new Callback<RefreshDetail>() {
            @Override
            public void onResponse(Call<RefreshDetail> call, Response<RefreshDetail> response) {

                loading.stop();

                List<HTMLElement> elements = HTMLParser.parseContent(response.body().getPost().getContent());

                RefreshDetailContent.addInView(contentLayout,elements);
            }

            @Override
            public void onFailure(Call<RefreshDetail> call, Throwable t) {

                loading.stop();

                Toast.makeText(RefreshDetailActivity.this,LOADINGERROR,Toast.LENGTH_LONG).show();
            }
        });

    }


}
