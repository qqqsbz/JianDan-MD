package com.example.coder.jiandan_md.refresh;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Refresh;
import com.example.coder.jiandan_md.model.RefreshPost;
import com.example.coder.jiandan_md.net.ApiService;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

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
 * Created by coder on 16/9/12.
 */
public class RefreshItemAdapter extends RecyclerView.Adapter<RefreshItemAdapter.ViewHolder> {

    /**
     * 上下文
     */
    private Context context;

    /**
     * 回调
     */
    private CallBackService callBackService;

    /**
     * 页数
     */
    private int page = 1;

    /**
     * 数据
     */
    private List<RefreshPost> refreshPosts = new ArrayList<RefreshPost>();

    /**
     * 是否为大图模式
     */
    private boolean isLargeModel;

    /**
     * 最后位置
     */
    private int lastPosition = -1;

    /**
     * 图片加载选项
     */
    private DisplayImageOptions options;

    /**
     * 构造函数
     * @param context   上下文
     * @param callBackService   网络请求成功或者失败调用的回调
     * @param isLargeModel  是否为大图模式
     */
    public RefreshItemAdapter(Context context,CallBackService callBackService,boolean isLargeModel) {

        this.context = context;

        this.callBackService = callBackService;

        this.isLargeModel = isLargeModel;

        options = ImageLoadProxy.getOptions4PictureList(isLargeModel ? R.drawable.ic_loading_large : R.drawable.ic_loading_small);
    }

    /**
     * 创建ViewHoloder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layout = isLargeModel ? R.layout.refresh_item_big : R.layout.refresh_item_small;

        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

        return new ViewHolder(view);
    }

    /**
     * 对ViewHolder进行数据绑定
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       RefreshPost refreshPost = refreshPosts.get(position);

        holder.titleTextView.setText(refreshPost.getTitle());

        holder.userNameTextView.setText(refreshPost.getAuthor().getNickname());

        holder.browseTextView.setText("浏览:"+refreshPost.getTags().get(0).getPostCount() + "次");

        ImageLoadProxy.displayImage(refreshPost.getCustomFields().getThumbC().get(0),holder.coverImageView,options);

        setAnimation(isLargeModel ? holder.cardView : holder.contentView,position);

    }


    @Override
    public int getItemCount() {

        return refreshPosts.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 当ViewHolder从窗口消失 清除动画 避免快速滑动时ViewHolde重叠
     * @param holder
     */
    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {

        if (isLargeModel) {

            holder.cardView.clearAnimation();

        } else {

            holder.contentView.clearAnimation();
        }
    }

    /**
     * 设置动画
     * @param viewHolder 要进行动画的View
     * @param position   下标值
     */
    private void setAnimation(View viewHolder, int position) {

        if (lastPosition < position) {

            Animation animation = AnimationUtils.loadAnimation(context,R.anim.item_animation);

            viewHolder.startAnimation(animation);

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
     * 加载数据
     */
    private void reloadData() {

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ConstantString.REFRESH_BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Refresh> call = apiService.getRefreshWithPage(page);

        call.enqueue(new Callback<Refresh>() {
            @Override
            public void onResponse(Call<Refresh> call, Response<Refresh> response) {

                if (page == 1) {

                    refreshPosts.clear();
                }

                refreshPosts.addAll(response.body().getPosts());

                callBackService.onSuccess(RefreshItemAdapter.this.page,refreshPosts);

                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Refresh> call, Throwable t) {

                RefreshItemAdapter.this.callBackService.onError(RefreshItemAdapter.this.page,t.getMessage());

            }
        });

    }

    /**
     * 根据下标值获取 新鲜事对象
     * @param position 下标值
     * @return  新鲜事对象
     */
    public RefreshPost getRefreshPost(int position) {

        return refreshPosts.get(position);
    }

    /**
     * 清楚数据并刷新
     */
    public void clear() {

        refreshPosts.clear();

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.refresh_cover)
        public ImageView coverImageView;

        @BindView(R.id.refresh_title)
        public TextView titleTextView;

        @BindView(R.id.refresh_username)
        public TextView userNameTextView;

        @BindView(R.id.refresh_browse)
        public TextView browseTextView;

        @Nullable
        @BindView(R.id.refresh_share)
        public TextView shareTextView;

        @Nullable
        @BindView(R.id.refresh_cardView)
        public CardView cardView;

        @Nullable
        @BindView(R.id.refresh_content)
        public LinearLayout contentView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
