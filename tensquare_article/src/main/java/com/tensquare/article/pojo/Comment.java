package com.tensquare.article.pojo;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @program: tensquare_parent
 * @description: 文章评论
 * @author: chenglong
 * @create: 2018-10-08
 */

public class Comment {

    @Id
    private String _id;
    private String articleId;
    private String content;
    private String userId;
    private String parentId;
    private Date publishDate;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
