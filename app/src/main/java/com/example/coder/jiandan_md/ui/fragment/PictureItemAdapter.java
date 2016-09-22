package com.example.coder.jiandan_md.ui.fragment;

/**
 * Created by coder on 16/9/21.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Comment;
import com.example.coder.jiandan_md.model.Picture;
import com.example.coder.jiandan_md.net.ApiService;
import com.example.coder.jiandan_md.ui.ImageBrowserActivity;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.example.coder.jiandan_md.util.NetWorkUtil;
import com.example.coder.jiandan_md.widget.ShowMaxImageView;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coder on 16/9/21.
 */
public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemAdapter.ViewHolder> implements ConstantString {


    /**
     * 回调
     */
    private CallBackService callBackService;

    /**
     * 上下文
     */
    private Context context;

    /**
     * 数据
     */
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * 当前页数
     */
    private int page = 1;

    /**
     * 动画执行的最后位置
     */
    private int lastPosition = -1;

    /**
     * 网络连接是否为wifi
     */
    private boolean isWifi;

    /**
     * 数据类型
     */
    private PictureFragment.PictureType pictureType;

    /**
     * 构造函数
     * @param context 上下文
     * @param callBackService 回调
     * @param pictureType   要加载的数据类型
     */

    public PictureItemAdapter(Context context , CallBackService callBackService , PictureFragment.PictureType pictureType) {

        this.context = context;

        this.callBackService = callBackService;

        this.pictureType = pictureType;

        isWifi = NetWorkUtil.isNetWorkConnected(context);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public PictureItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.picture_item,parent,false);

        return new ViewHolder(view);
    }

    /**
     * 当ViewHolder从窗口消失 清除动画 避免快速滑动时ViewHolde重叠
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {

        holder.cardView.clearAnimation();
    }

    @Override
    public void onBindViewHolder(final PictureItemAdapter.ViewHolder holder, int position) {

        final Comment comment = comments.get(position);

        holder.titleTextView.setText(comment.getCommentAuthor());

        holder.timeTextView.setText(comment.getCommentDate());

        holder.contentTextView.setText(comment.getTextContent().trim());

        holder.likeTextView.setText("顶:" + comment.getVotePositive());

        holder.unlikeTextView.setText("踩:" + comment.getVoteNegative());

        holder.tucaoTextView.setText("吐槽:"+ comment.getCommentReplyID().split(",").length);

        holder.contentTextView.setVisibility(comment.getTextContent().trim().length() <= 0 ? View.GONE : View.VISIBLE);

        if (comment.getPics().length <= 0) {

            holder.container.setVisibility(View.GONE);

        } else  {

            String imageUrl = comment.getPics()[0];

            if (!isWifi) {

                imageUrl = imageUrl.replace("mw600", "small")
                        .replace("mw690","small")
                        .replace("mw1024","small")
                        .replace("mw1200", "small")
                        .replace("large", "small");
            }


            ImageLoadProxy.displayImageList(imageUrl, holder.coverImageView, R.drawable.ic_loading_large, new SimpleImageLoadingListener() {

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    holder.progressBar.setVisibility(View.GONE);
                }
            }, new ImageLoadingProgressListener() {
                @Override
                public void onProgressUpdate(String imageUri, View view, int current, int total) {

                    holder.progressBar.setProgress((current / total) * 100);
                }
            });



            holder.container.setVisibility(View.VISIBLE);

            holder.gifImageView.setVisibility(imageUrl.endsWith(".gif") ? View.VISIBLE : View.GONE);

        }

        holder.coverImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ImageBrowserActivity.class);

                intent.putExtra(IMAGEBROWER_ISGIF,comment.getPics()[0].endsWith(".gif"));

                intent.putExtra(IMAGEBROWER_DATA,comment);

                context.startActivity(intent);
            }
        });

        setAnimation(holder.cardView,position);

    }

    /**
     * 设置动画
     */
    private void setAnimation(View view , int position) {

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(view.getContext(),R.anim.item_animation);

            view.setAnimation(animation);

            lastPosition = position;
        }

    }


    /**
     * 加载第一页
     */
    public void onRefresh() {

        page = 1;

        reloadData();

    }

    /**
     * 加载更多
     */
    public void onRefreshMore() {

        page ++;

        reloadData();

    }

    /**
     * 清除数据
     */
    public void clear() {

        comments.clear();

        notifyDataSetChanged();
    }

    private void reloadData() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BORING_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Picture> call = null;

        if (pictureType == PictureFragment.PictureType.PictureTypeGirl) {

            call = apiService.getGirlWithPage(page);

        } else if (pictureType == PictureFragment.PictureType.PictureTypeBoring) {

            call = apiService.getBoringWithPage(page);
        }

        call.enqueue(new Callback<Picture>() {
            @Override
            public void onResponse(Call<Picture> call, Response<Picture> response) {

                if (page == 1) {

                    comments.clear();
                }

                comments.addAll(response.body().getComments());

                notifyDataSetChanged();

                callBackService.onSuccess(page,comments);
            }

            @Override
            public void onFailure(Call<Picture> call, Throwable t) {

                callBackService.onError(page,LOADINGERROR);

            }
        });


    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.picture_title)
        public TextView titleTextView;

        @BindView(R.id.picture_time)
        public TextView timeTextView;

        @BindView(R.id.picture_progress)
        public ProgressBar progressBar;

        @BindView(R.id.picture_content)
        public TextView contentTextView;

        @BindView(R.id.picture_gif)
        public ImageView gifImageView;

        @BindView(R.id.picture_cover)
        public ShowMaxImageView coverImageView;

        @BindView(R.id.picture_like)
        public TextView likeTextView;

        @BindView(R.id.picture_unlike)
        public TextView unlikeTextView;

        @BindView(R.id.picture_tucao)
        public TextView tucaoTextView;

        @BindView(R.id.picture_share)
        public ImageView shareImageView;

        @BindView(R.id.picture_cardView)
        public CardView cardView;

        @BindView(R.id.picture_container)
        public RelativeLayout container;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
