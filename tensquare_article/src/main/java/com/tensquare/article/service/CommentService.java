package com.tensquare.article.service;

import com.tensquare.article.dao.CommentDao;
import com.tensquare.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import utils.IdWorker;

import java.util.Date;

/**
 * @program: tensquare_parent
 * @description: 评论服务类
 * @author: chenglong
 * @create: 2018-10-08
 */

public class CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private IdWorker idWorker;

    /**
    * @Description: 根据id查询评论
    * @Param: [id]
    * @return: com.tensquare.article.pojo.Comment
    */
    public Comment findById(String id){
        return commentDao.findById(id).get();
    }

    /**
    * @Description: 添加评论
    * @Param: [comment]
    * @return: void
    */
    public void add(Comment comment){
        comment.set_id(idWorker.nextId() + "");
        comment.setPublishDate(new Date());
        commentDao.save(comment);
    }

    /**
    * @Description: 更新评论
    * @Param: [comment]
    * @return: void
    */
    public void update(Comment comment){
        commentDao.save(comment);
    }

    /**
    * @Description: 删除评论
    * @Param: [id]
    * @return: void
    */
    public void delete(String id){
        commentDao.deleteById(id);
    }

    /**
    * @Description: 根据上级ID查询评论
    * @Param: [id, pageNum, pageSize]
    * @return: org.springframework.data.domain.Page<com.tensquare.article.pojo.Comment>
    */
    public Page<Comment> findByParentId(String id,int pageNum,int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return commentDao.findByParentId(id,pageRequest);
    }

    /**
    * @Description: 根据用户id查询评论
    * @Param: [id, pageNum, pageSize]
    * @return: org.springframework.data.domain.Page<com.tensquare.article.pojo.Comment>
    */
    public Page<Comment> findByUserId(String id,int pageNum,int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return commentDao.findByUserId(id,pageRequest);
    }

    /**
    * @Description: 根据文章id查询评论
    * @Param: [id, pageNum, pageSize]
    * @return: org.springframework.data.domain.Page<com.tensquare.article.pojo.Comment>
    */
    public Page<Comment> findByArticleId(String id,int pageNum,int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return commentDao.findByArticleId(id,pageRequest);
    }
}
