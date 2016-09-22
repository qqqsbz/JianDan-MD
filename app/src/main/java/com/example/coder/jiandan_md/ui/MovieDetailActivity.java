package com.example.coder.jiandan_md.ui;

import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Comment;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.widget.ProgressWebView;
import com.victor.loading.rotate.RotateLoading;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {


    @BindView(R.id.movie_detail_webView)
    public ProgressWebView webView;

    @BindView(R.id.navigation_toolbar)
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.movie_detail);

        ButterKnife.bind(this);


        initData();
    }

    private void initData() {

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Comment comment = (Comment) getIntent().getSerializableExtra(ConstantString.MOVIEDETAIL_DATA);

        getSupportActionBar().setTitle(comment.getVideos().get(0).getTitle().toString());

        webView.loadUrl(comment.getVideos().get(0).getLink().replace("http://v.youku.com/v_show/","http://m.youku.com/video/"));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
