package com.example.coder.jiandan_md.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by coder on 16/9/12.
 */
public class Refresh {

    private String status;

    private int count;

    @SerializedName("count_total")
    private int countTotal;

    private int pages;

    private List<RefreshPost> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(int countTotal) {
        this.countTotal = countTotal;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<RefreshPost> getPosts() {
        return posts;
    }

    public void setPosts(List<RefreshPost> posts) {
        this.posts = posts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
