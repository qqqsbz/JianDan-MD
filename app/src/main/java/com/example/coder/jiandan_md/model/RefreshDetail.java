package com.example.coder.jiandan_md.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by coder on 16/9/14.
 */
public class RefreshDetail implements Serializable {

    @SerializedName("status")
    private String status;

    @SerializedName("previous_url")
    private String previousUrl;

    @SerializedName("next_url")
    private String nextUrl;

    @SerializedName("post")
    private Post post;

    public String getNextUrl() {
        return nextUrl;
    }

    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }

    public void setPreviousUrl(String previousUrl) {
        this.previousUrl = previousUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public class Post {

        private int id;

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
