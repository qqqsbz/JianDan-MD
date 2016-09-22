package com.example.coder.jiandan_md.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by coder on 16/9/22.
 */
public class Video implements Serializable {

    private String id;

    private String title;

    private String description;

    private String thumbnail;

    @SerializedName("thumbnail_v2")
    private String thumbnailV2;

    private String category;

    private String[] streamtypes;

    private String published;

    private String duration;

    @SerializedName("view_count")
    private int viewCount;

    @SerializedName("comment_count")
    private int commentCount;

    @SerializedName("favorite_count")
    private int favoriteCount;

    @SerializedName("up_count")
    private int upCount;

    @SerializedName("down_count")
    private int downCount;

    @SerializedName("is_panorama")
    private int isPanorama;

    @SerializedName("public_type")
    private String publicType;

    private String state;

    private String tags;

    @SerializedName("copyright_type")
    private String copyrightType;

    private int paid;

    private String link;

    private String player;

    @SerializedName("operation_limit")
    private String[] operationLimit;

    @SerializedName("download_status")
    private String[] downloadStatus;

    @SerializedName("video_source")
    private String videoSource;

    private User user;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCopyrightType() {
        return copyrightType;
    }

    public void setCopyrightType(String copyrightType) {
        this.copyrightType = copyrightType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDownCount() {
        return downCount;
    }

    public void setDownCount(int downCount) {
        this.downCount = downCount;
    }

    public String[] getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(String[] downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsPanorama() {
        return isPanorama;
    }

    public void setIsPanorama(int isPanorama) {
        this.isPanorama = isPanorama;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String[] getOperationLimit() {
        return operationLimit;
    }

    public void setOperationLimit(String[] operationLimit) {
        this.operationLimit = operationLimit;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPublicType() {
        return publicType;
    }

    public void setPublicType(String publicType) {
        this.publicType = publicType;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String[] getStreamtypes() {
        return streamtypes;
    }

    public void setStreamtypes(String[] streamtypes) {
        this.streamtypes = streamtypes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailV2() {
        return thumbnailV2;
    }

    public void setThumbnailV2(String thumbnailV2) {
        this.thumbnailV2 = thumbnailV2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUpCount() {
        return upCount;
    }

    public void setUpCount(int upCount) {
        this.upCount = upCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    private class User implements Serializable {

        private int id;

        private String name;

        private String link;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
