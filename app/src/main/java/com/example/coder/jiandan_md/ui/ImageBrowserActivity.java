package com.example.coder.jiandan_md.ui;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Boring;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.util.DiskCacheUtils;
import com.example.coder.jiandan_md.util.DownLoadFinishCallBack;
import com.example.coder.jiandan_md.util.FileUtil;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.example.coder.jiandan_md.util.JDMDMediaScannerConnectionClient;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageBrowserActivity extends AppCompatActivity implements ConstantString ,DownLoadFinishCallBack {


    @BindView(R.id.imageBrowser_navigationBar)
    public RelativeLayout navigationBar;

    @BindView(R.id.imageBrowser_toolBar)
    public LinearLayout toolBar;

    @BindView(R.id.imageBrowser_webView)
    public WebView webView;

    @BindView(R.id.imageBrowser_photoView)
    public PhotoView photoView;

    @BindView(R.id.imageBrowser_progressBar)
    public ProgressBar progressBar;

    @BindView(R.id.imageBrowser_backImageView)
    public ImageView backImageView;

    public static final int ANIMATION_DURATION = 400;

    /**
     * 是否为gif图片
     */
    private boolean isGif;

    /**
     * 是否显示导航栏 菜单栏
     */
    private boolean isShowBar = true;

    /**
     * 图片是否加载成功
     */
    private boolean isImgHaveLoad = false;

    /**
     * 图片文件
     */
    private File imageCacheFile = null;

    /**
     * 通知文件更新链接
     */
    private MediaScannerConnection connection = null;

    private Boring.Comment comment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);

        setContentView(R.layout.activity_imagebrowser);


        ButterKnife.bind(this);

        initData();

        initView();
    }


    private void initData() {

        comment = (Boring.Comment) getIntent().getSerializableExtra(IMAGEBROWER_DATA);

        isGif = getIntent().getBooleanExtra(IMAGEBROWER_ISGIF,true);

        webView.setBackgroundResource(android.R.color.background_dark);

        photoView.setBackgroundResource(android.R.color.background_dark);

    }

    private void initView() {


        photoView.setVisibility(isGif ? View.GONE : View.VISIBLE);

        webView.setVisibility(isGif ? View.VISIBLE : View.GONE);

        if (isGif) {

            ImageLoadProxy.loadImageFromLocalCache(comment.getPics()[0],new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    if (loadedImage != null) {

                        imageCacheFile = DiskCacheUtils.findInCache(imageUri,ImageLoadProxy.getImageLoader().getDiskCache());

                        showImgInWebView("file://" + imageCacheFile.getAbsolutePath());

                    }

                    isImgHaveLoad = true;

                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(.this,LoadingError,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLoadingStarted(String imageUri, View view) {

                    progressBar.setVisibility(View.VISIBLE);

                }
            });

        } else {

            ImageLoadProxy.loadImageFromLocalCache(comment.getPics()[0],new SimpleImageLoadingListener() {


                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    photoView.setImageBitmap(loadedImage);

                    isImgHaveLoad = true;

                    progressBar.setVisibility(View.GONE);

                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(.this,LoadingError,Toast.LENGTH_LONG).show();
                }

                @Override
                public void onLoadingStarted(String imageUri, View view) {

                    progressBar.setVisibility(View.VISIBLE);

                }
            });
        }

        photoView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {

                toggleAnimation();
            }
        });

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    toggleAnimation();
                }

                return false;
            }
        });

    }

    private void showImgInWebView(final String s) {
        if (webView != null) {
            webView.loadDataWithBaseURL("", "<!doctype html> <html lang=\"en\"> <head> <meta charset=\"UTF-8\"> <title></title><style type=\"text/css\"> html,body{width:100%;height:100%;margin:0;padding:0;background-color:black;} *{ -webkit-tap-highlight-color: rgba(0, 0, 0, 0);}#box{ width:100%;height:100%; display:table; text-align:center; background-color:black;} body{-webkit-user-select: none;user-select: none;-khtml-user-select: none;}#box span{ display:table-cell; vertical-align:middle;} #box img{  width:100%;} </style> </head> <body> <div id=\"box\"><span><img src=\"img_url\" alt=\"\"></span></div> <script type=\"text/javascript\" >document.body.onclick=function(e){window.external.onClick();e.preventDefault(); };function load_img(){var url=document.getElementsByTagName(\"img\")[0];url=url.getAttribute(\"src\");var img=new Image();img.src=url;if(img.complete){\twindow.external.img_has_loaded();\treturn;};img.onload=function(){window.external.img_has_loaded();};img.onerror=function(){\twindow.external.img_loaded_error();};};load_img();</script></body> </html>".replace("img_url", s), "text/html", "utf-8", "");
        }
    }

    private void toggleAnimation() {

        if (isImgHaveLoad) {

            if (isShowBar) {

                isShowBar = false;

                ObjectAnimator.ofFloat(toolBar,"translationY",0,toolBar.getHeight())
                        .setDuration(ANIMATION_DURATION)
                        .start();

                ObjectAnimator.ofFloat(navigationBar,"translationY",0,-navigationBar.getHeight())
                        .setDuration(ANIMATION_DURATION)
                        .start();

            } else {

                isShowBar = true;

                ObjectAnimator.ofFloat(toolBar,"translationY",toolBar.getHeight(),0)
                        .setDuration(ANIMATION_DURATION)
                        .start();

                ObjectAnimator.ofFloat(navigationBar,"translationY",-navigationBar.getHeight(),0)
                        .setDuration(ANIMATION_DURATION)
                        .start();

            }

        }
    }

    @OnClick({R.id.imageBrowser_backImageView,R.id.imageBrowser_download,R.id.imageBrowser_like,R.id.imageBrowser_unlike,R.id.imageBrowser_comment,R.id.imageBrowser_shareImageView})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.imageBrowser_backImageView : {

                onBackPressed();
            }
            break;

            case R.id.imageBrowser_download : {

                FileUtil.savePicture(this,comment.getPics()[0],this);

            }
            break;

            default: {

                Toast.makeText(this,"暂未实现",Toast.LENGTH_LONG).show();
            }
            break;
        }
    }

    @Override
    public void onLoadingComplete(Object object) {

        Bundle bundle = (Bundle)object;

        File filePath = new File(bundle.getString(ConstantString.DATA_FILE_PATH));

        JDMDMediaScannerConnectionClient client = new JDMDMediaScannerConnectionClient(filePath);

        connection = new MediaScannerConnection(this,client);

        client.setMediaScannerConnection(connection);

        connection.connect();

        Toast.makeText(this,ConstantString.DOWNLOAD_SUCCESS,Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (connection != null && connection.isConnected()) {

            connection.disconnect();
        }

        if (photoView.getVisibility() != View.GONE) {

            ImageLoadProxy.getImageLoader().cancelDisplayTask(photoView);
        }
    }
}
