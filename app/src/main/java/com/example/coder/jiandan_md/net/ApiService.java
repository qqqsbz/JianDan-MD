package com.example.coder.jiandan_md.net;

import com.example.coder.jiandan_md.model.Refresh;
import com.example.coder.jiandan_md.model.RefreshDetail;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by coder on 16/9/12.
 */
public interface ApiService {

    @GET("?oxwlxojflwblxbsapi=get_recent_posts&include=url,date,tags,author,title,comment_count,custom_fields&custom_fields=thumb_c,views&dev=1")
    Call<Refresh> getRefreshsWithPage(@Query("page") int page);

    @GET("?oxwlxojflwblxbsapi=get_post&include=content")
    Call<RefreshDetail> getRefreshWithId(@Query("id") int id);
}
