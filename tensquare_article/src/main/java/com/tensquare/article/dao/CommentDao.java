package com.tensquare.article.dao;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDao extends MongoRepository<Comment,String> {

    public Page<Comment> findByParentId(String parentId, Pageable pageable);

    public Page<Comment> findByUserId(String userId, Pageable pageable);

    public Page<Comment> findByArticleId(String articleId, Pageable pageable);
}
