package com.example.coder.jiandan_md.refresh;

import java.util.Date;
import java.util.List;

/**
 * Created by coder on 16/9/12.
 */
public class Refresh {

    private String status;

    private int count;

    private int count_total;

    private int pages;

    private List<RefreshPost> posts;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountTotal() {
        return count_total;
    }

    public void setCountTotal(int count_total) {
        this.count_total = count_total;
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
