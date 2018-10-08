package application.controller;

import application.pojo.Spit;
import application.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @program: tensquare_parent
 * @description: 吐槽控制类
 * @author: chenglong
 * @create:2018-10-07
 */

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Description: 查询全部
     * @Param: []
     * @return: entity.Result
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK,
                "查询成功", spitService.findAll());
    }

    /**
     * @Description: 根据id查询单个Spit
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findSpit(@PathVariable String id) {
        return new Result(true, StatusCode.OK,
                "查询成功", spitService.findById(id));
    }

    /**
     * @Description: 添加Spit
     * @Param: [spit]
     * @return: entity.Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit) {
        spitService.add(spit);
        return new Result(true, StatusCode.OK,
                "添加成功");
    }

    /**
     * @Description: 修改Spit
     * @Param: [spit, id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String id) {
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Description: 删除Spit
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,
                "删除成功");
    }

    /**
     * @Description: 根据上级id查询吐槽分页数据
     * @Param: [id, pageNum, pageSize]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String id, @PathVariable int pageNum,
                                 @PathVariable int pageSize) {
        Page<Spit> pageList = spitService.findByParentid(id, pageNum, pageSize);
        return new Result(true, StatusCode.OK,
                "查询成功", new PageResult<Spit>(pageList.getTotalElements(),
                pageList.getContent()));

    }

    /**
     * @Description: 点赞、防止重复点赞
     * @Param: [id]
     * @return: entity.Result
     */
    //用redis防止重复点赞，是否合适，比如是否存在缓存过多，缓存失效等问题
    @RequestMapping(value = "thumbUp/{id}", method = RequestMethod.PUT)
    public Result thumbUp(@PathVariable String id) {

        String userId = "123";
        if (redisTemplate.opsForValue().get("thumbup_" + userId + "+" + id) != null)
            return new Result(false, StatusCode.REPERROR,
                    "你已经点过赞了");
        spitService.thumbUp(id);
        redisTemplate.opsForValue().set("thumbup_" + userId + "+" + id, "1");
        return new Result(true, StatusCode.OK,
                "点赞成功");
    }

    /**
     * @Description: 增加浏览量
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "visitsAdd/{id}", method = RequestMethod.PUT)
    public Result visitsAdd(@PathVariable String id) {
        spitService.visitsAdd(id);
        return new Result(true, StatusCode.OK,
                "浏览量增加");
    }

    /**
     * @Description: 增加分享数
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "shareAdd/{id}", method = RequestMethod.PUT)
    public Result shareAdd(@PathVariable String id) {
        spitService.shareAdd(id);
        return new Result(true, StatusCode.OK,
                "分享数增加");
    }
}
