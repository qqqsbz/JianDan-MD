package com.example.coder.jiandan_md.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coder.jiandan_md.R;
import com.example.coder.jiandan_md.model.Comment;
import com.example.coder.jiandan_md.model.Movie;
import com.example.coder.jiandan_md.model.Picture;
import com.example.coder.jiandan_md.model.Video;
import com.example.coder.jiandan_md.net.ApiService;
import com.example.coder.jiandan_md.util.CallBackService;
import com.example.coder.jiandan_md.util.ConstantString;
import com.example.coder.jiandan_md.util.ImageLoadProxy;
import com.example.coder.jiandan_md.util.NetWorkUtil;

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
public class MovieItemAdapter extends BaseAdapter implements ConstantString {


    /**
     * 回调
     */
    private CallBackService callBackService;

    /**
     * 当前页数
     */
    private int page = 1;

    /**
     * 最后位置
     */
    private int lastPosition = -1;

    /**
     * 上下文
     */
    private Context context;

    /**
     * 数据
     */
    private List<Comment> comments = new ArrayList<Comment>();

    public MovieItemAdapter(Context context , CallBackService callBackService) {

        this.context = context;

        this.callBackService = callBackService;
    }


    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item,parent,false);

            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        boolean isWifi = NetWorkUtil.isWifiConnected(parent.getContext());

        Video video = comments.get(position).getVideos().get(0);

        Comment comment = comments.get(position);

        ImageLoadProxy.displayImageWithLoadingPicture(isWifi ? video.getThumbnail() : video.getThumbnailV2() , viewHolder.coverImageView,R.drawable.ic_loading_small);

        viewHolder.titleTextView.setText(video.getTitle());

        viewHolder.likeTextView.setText("赞:" + comment.getVotePositive());

        viewHolder.unlikeTextView.setText("踩:" + comment.getVoteNegative());

        setAnimation(convertView,position);

        return convertView;
    }




    private void setAnimation(View view, int position) {

        if (position > lastPosition) {

            Animation animation = AnimationUtils.loadAnimation(context,R.anim.item_animation);

            view.setAnimation(animation);

            lastPosition = position;

        }

    }

    public Comment getComment(int position) {

        return comments.get(position);
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

        comments.clear();

        notifyDataSetChanged();
    }

    private void reloadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Movie> call = apiService.getMovieWithPage(page);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {

                if (page == 1) {

                    comments.clear();
                }

                comments.addAll(response.body().getComments());

                List<Comment> commentList = new ArrayList<Comment>();

                for (Comment comment : comments) {

                    if (comment.getVideos().size() <= 0) {

                        commentList.add(comment);
                    }
                }

                comments.removeAll(commentList);

                callBackService.onSuccess(page,comments);

                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

                callBackService.onError(page,LOADINGERROR);

            }
        });

    }

    public class ViewHolder {


        @BindView(R.id.movie_cover)
        public ImageView coverImageView;

        @BindView(R.id.movie_title)
        public TextView titleTextView;

        @BindView(R.id.movie_like)
        public TextView likeTextView;

        @BindView(R.id.movie_unlike)
        public TextView unlikeTextView;

        @BindView(R.id.movie_tucao)
        public TextView tucaoTextView;

        @BindView(R.id.movie_share)
        public ImageView shareImageView;

        public ViewHolder(View view) {

            ButterKnife.bind(this,view);

        }


    }
}
