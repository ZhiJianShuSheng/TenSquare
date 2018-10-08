package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @program: tensquare_parent
 * @description: 评论控制类
 * @author: chenglong
 * @create: 2018-10-08
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
    * @Description: 根据文章id获取评论
    * @Param: [artcleId, pageNum, pageSize]
    * @return: entity.Result
    */
    @RequestMapping(value = "/{articleId}/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result findByArtcleId(@PathVariable String artcleId, @PathVariable int pageNum,
                                 @PathVariable int pageSize) {
        Page<Comment> pageList = commentService.findByArticleId(artcleId, pageNum, pageSize);
        return new Result(true, StatusCode.OK,
                "查询成功",new PageResult<Comment>(pageList.getTotalElements(),
                pageList.getContent()));
    }

    /**
    * @Description: 添加评论
    * @Param: [comment]
    * @return: entity.Result
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,
                "添加成功");
    }

    /**
    * @Description: 删除评论
    * @Param: [commentId]
    * @return: entity.Result
    */
    @RequestMapping(value = "/{commentId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String commentId){
        commentService.delete(commentId);
        return new Result(true, StatusCode.OK,
                "删除成功");
    }

}
