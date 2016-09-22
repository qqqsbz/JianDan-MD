package com.example.coder.jiandan_md.net;

import com.example.coder.jiandan_md.model.Movie;
import com.example.coder.jiandan_md.model.Picture;
import com.example.coder.jiandan_md.model.Refresh;
import com.example.coder.jiandan_md.model.RefreshDetail;
import com.example.coder.jiandan_md.model.Stain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coder on 16/9/12.
 */
public interface ApiService {

    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1")
    Call<Refresh> getRefreshWithPage(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=get_post&include=content")
    Call<RefreshDetail> getRefreshWithId(@Query("id") int id);

    @GET("?oxwlxojflwblxbsapi=jandan.get_pic_comments")
    Call<Picture> getBoringWithPage(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=jandan.get_ooxx_comments")
    Call<Picture> getGirlWithPage(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=jandan.get_duan_comments")
    Call<Stain> getStainWithPage(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=jandan.get_video_comments")
    Call<Movie> getMovieWithPage(@Query("page") int page);
}
