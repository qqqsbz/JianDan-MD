package com.example.coder.jiandan_md.ui.fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Comment;
import com.example.coder.jiandan_md.model.Stain;
import com.example.coder.jiandan_md.net.ApiService;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;

import org.w3c.dom.Text;

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
public class StainItemAdapter extends RecyclerView.Adapter<StainItemAdapter.ViewHolder> implements ConstantString {


    /**
     * 上下文
     */
    private Context context;

    /**
     * 回调
     */
    private CallBackService callBackService;

    /**
     * 数据
     */
    private List<Comment> comments = new ArrayList<Comment>();

    /**
     * 页数
     */
    private int page = 1;

    /**
     * 最后执行动画的位置
     */
    private int lastPosition = -1;

    public StainItemAdapter(Context context,CallBackService callBackService) {

        this.context = context;

        this.callBackService = callBackService;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.stain_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Comment comment = comments.get(position);

        holder.titleTextView.setText(comment.getCommentAuthor());

        holder.timeTextView.setText(comment.getCommentDate());

        holder.contentTextView.setText(comment.getCommentContent());

        holder.likeTextView.setText("赞:" + comment.getVotePositive());

        holder.unlikeTextView.setText("踩:" + comment.getVoteNegative());

        holder.tucaoTextView.setText("吐槽:" + comment.getCommentReplyID().split(",").length);

        setAnimation(holder.cardView,position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    /**
     * 防止快速滑动时 造成CardView悬浮在RecycleView不消失
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {

        holder.cardView.clearAnimation();
    }

    private void setAnimation(View view, int position) {

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,R.anim.item_animation);

            view.startAnimation(animation);

            lastPosition = position;
        }
    }

    public void onRefresh() {

        page = 1;

        reloadData();
    }

    public void onRefreshMore() {

        page ++;

        reloadData();
    }

    public void clear() {

        page = 1;

        comments.clear();

        notifyDataSetChanged();
    }

    private void reloadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(STAIN_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Stain> call = apiService.getStainWithPage(page);

        call.enqueue(new Callback<Stain>() {
            @Override
            public void onResponse(Call<Stain> call, Response<Stain> response) {

                if (page == 1) {

                    comments.clear();
                }

                comments.addAll(response.body().getComments());

                notifyDataSetChanged();

                callBackService.onSuccess(page,comments);

            }

            @Override
            public void onFailure(Call<Stain> call, Throwable t) {

                callBackService.onError(page,LOADINGERROR);

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.stain_title)
        public TextView titleTextView;

        @BindView(R.id.stain_time)
        public TextView timeTextView;

        @BindView(R.id.stain_content)
        public TextView contentTextView;

        @BindView(R.id.stain_like)
        public TextView likeTextView;

        @BindView(R.id.stain_unlike)
        public TextView unlikeTextView;

        @BindView(R.id.stain_tucao)
        public TextView tucaoTextView;

        @BindView(R.id.stain_share)
        public ImageView shareImageView;

        @BindView(R.id.stain_cardView)
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
