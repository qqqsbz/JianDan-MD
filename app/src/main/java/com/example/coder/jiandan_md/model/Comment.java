package com.example.coder.jiandan_md.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by coder on 16/9/22.
 */
public class Comment implements Serializable {

    @SerializedName("comment_ID")
    private String commentID;

    @SerializedName("comment_post_ID")
    private String  commentPostID;

    @SerializedName("comment_author")
    private String commentAuthor;

    @SerializedName("comment_author_email")
    private String commentAuthorEmail;

    @SerializedName("comment_author_url")
    private String commentAuthorUrl;

    @SerializedName("comment_author_IP")
    private String commentAuthorIP;

    @SerializedName("comment_date")
    private String commentDate;

    @SerializedName("comment_date_gmt")
    private String commentDateGmt;

    @SerializedName("comment_content")
    private String commentContent;

    @SerializedName("comment_karma")
    private String commentKarma;

    @SerializedName("comment_approved")
    private String commentApproved;

    @SerializedName("comment_agent")
    private String commentAgent;

    @SerializedName("comment_type")
    private String commentType;

    @SerializedName("comment_parent")
    private String commentParent;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("comment_subscribe")
    private String commentSubscribe;

    @SerializedName("comment_reply_ID")
    private String commentReplyID;

    @SerializedName("vote_positive")
    private String votePositive;

    @SerializedName("vote_negative")
    private String voteNegative;

    @SerializedName("vote_ip_pool")
    private String voteIpPool;

    @SerializedName("text_content")
    private String textContent;

    @SerializedName("pics")
    private String[] pics;

    @SerializedName("videos")
    private List<Video> videos;

    public String getCommentAgent() {
        return commentAgent;
    }

    public void setCommentAgent(String commentAgent) {
        this.commentAgent = commentAgent;
    }

    public String getCommentApproved() {
        return commentApproved;
    }

    public void setCommentApproved(String commentApproved) {
        this.commentApproved = commentApproved;
    }

    public String getCommentAuthor() {
        return commentAuthor;
    }

    public void setCommentAuthor(String commentAuthor) {
        this.commentAuthor = commentAuthor;
    }

    public String getCommentAuthorEmail() {
        return commentAuthorEmail;
    }

    public void setCommentAuthorEmail(String commentAuthorEmail) {
        this.commentAuthorEmail = commentAuthorEmail;
    }

    public String getCommentAuthorIP() {
        return commentAuthorIP;
    }

    public void setCommentAuthorIP(String commentAuthorIP) {
        this.commentAuthorIP = commentAuthorIP;
    }

    public String getCommentAuthorUrl() {
        return commentAuthorUrl;
    }

    public void setCommentAuthorUrl(String commentAuthorUrl) {
        this.commentAuthorUrl = commentAuthorUrl;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentDateGmt() {
        return commentDateGmt;
    }

    public void setCommentDateGmt(String commentDateGmt) {
        this.commentDateGmt = commentDateGmt;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentKarma() {
        return commentKarma;
    }

    public void setCommentKarma(String commentKarma) {
        this.commentKarma = commentKarma;
    }

    public String getCommentParent() {
        return commentParent;
    }

    public void setCommentParent(String commentParent) {
        this.commentParent = commentParent;
    }

    public String getCommentPostID() {
        return commentPostID;
    }

    public void setCommentPostID(String commentPostID) {
        this.commentPostID = commentPostID;
    }

    public String getCommentReplyID() {
        return commentReplyID;
    }

    public void setCommentReplyID(String commentReplyID) {
        this.commentReplyID = commentReplyID;
    }

    public String getCommentSubscribe() {
        return commentSubscribe;
    }

    public void setCommentSubscribe(String commentSubscribe) {
        this.commentSubscribe = commentSubscribe;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public String getVoteIpPool() {
        return voteIpPool;
    }

    public void setVoteIpPool(String voteIpPool) {
        this.voteIpPool = voteIpPool;
    }

    public String getVoteNegative() {
        return voteNegative;
    }

    public void setVoteNegative(String voteNegative) {
        this.voteNegative = voteNegative;
    }

    public String getVotePositive() {
        return votePositive;
    }

    public void setVotePositive(String votePositive) {
        this.votePositive = votePositive;
    }
}
