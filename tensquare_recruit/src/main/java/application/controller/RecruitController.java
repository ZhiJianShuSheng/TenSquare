package application.controller;

import application.pojo.Recruit;
import application.service.RecruitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @program: tensquare_parent
 * @description: 招聘信息控制层
 * @author: chenglong
 * @create:2018-09-25
 */

@RestController
@RequestMapping("/recruit")
@CrossOrigin
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * @Description: 查询全部招聘信息
     * @Param: []
     * @return: entity.Result<java.util.List>
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    /**
     * @Description: 根据id查询招聘信息
     * @Param: [id]
     * @return: entity.Result<application.pojo.Recruit>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Recruit> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }

    /**
     * @Description: 添加招聘信息
     * @Param: [label]
     * @return: entity.Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Recruit label) {
        recruitService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Description: 修改招聘信息
     * @Param: [label, id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Recruit label, @PathVariable String id) {
        label.setId(id);
        recruitService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Description: 删除招聘信息
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        recruitService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * @Description: 条件查询
     * @Param: [searchMap]
     * @return: entity.Result<java.util.List>
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap) {
        return new Result<>(true, StatusCode.OK, "查询成功", recruitService.findSearch(searchMap));
    }

    /**
    * @Description: 分页查询
    * @Param: [searchMap, page, size]
    * @return: entity.Result<java.util.List>
    */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page pageList = recruitService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
    * @Description: 查询最新职位列表（按创建日期降序）
    * @Param: []
    * @return: entity.Result
    */
    @RequestMapping(value = "/search/newlist",method = RequestMethod.GET)
    public Result newlist(){
        return new Result(true,StatusCode.OK,"查询成功",recruitService.newlist());
    }
}
