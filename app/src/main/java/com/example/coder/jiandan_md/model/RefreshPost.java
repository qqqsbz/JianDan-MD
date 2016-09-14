package com.example.coder.jiandan_md.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by coder on 16/9/12.
 */
public class RefreshPost implements Serializable {

    private int id;

    private String url;

    private String title;

    private String date;

    private int comment_count;

    private List<Tag> tags;

    private Author author;

    private CustomFields custom_fields;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getCommentCount() {
        return comment_count;
    }

    public void setCommentCount(int comment_count) {
        this.comment_count = comment_count;
    }


    public CustomFields getCustomFields() {
        return custom_fields;
    }

    public void setCustomFields(CustomFields custom_fields) {
        this.custom_fields = custom_fields;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public class Tag implements Serializable {

        private int id;

        private String slug;

        private String title;

        private String description;

        private int post_count;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPostCount() {
            return post_count;
        }

        public void setPostCount(int post_count) {
            this.post_count = post_count;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class Author implements Serializable {

        private int id;

        private String slug;

        private String name;

        private String first_name;

        private String last_name;

        private String nickname;

        private String url;

        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFirstName() {
            return first_name;
        }

        public void setFirstName(String first_name) {
            this.first_name = first_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLastName() {
            return last_name;
        }

        public void setLastName(String last_name) {
            this.last_name = last_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class CustomFields implements Serializable {

        private List<String> thumb_c;

        public List<String> getThumbC() {
            return thumb_c;
        }

        public void setThumbC(List<String> thumb_c) {
            this.thumb_c = thumb_c;
        }
    }
}
