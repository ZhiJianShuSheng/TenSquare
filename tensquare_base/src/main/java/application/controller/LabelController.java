package application.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import application.pojo.Label;
import application.service.LabelService;

import java.util.List;
import java.util.Map;

/**
 * @program: tensquare_parent
 * @description: 标签控制层
 * @author: chenglong
 * @create:2018-09-25
 */

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * @Description: 查询全部标签
     * @Param: []
     * @return: entity.Result<java.util.List>
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * @Description: 根据id查询标签
     * @Param: [id]
     * @return: entity.Result<application.pojo.Label>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * @Description: 添加标签
     * @Param: [label]
     * @return: entity.Result
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * @Description: 修改标签
     * @Param: [label, id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * @Description: 删除标签
     * @Param: [id]
     * @return: entity.Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * @Description: 条件查询
     * @Param: [searchMap]
     * @return: entity.Result<java.util.List>
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findSearch(searchMap));
    }

    /**
    * @Description: 分页查询
    * @Param: [searchMap, page, size]
    * @return: entity.Result<java.util.List>
    */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page pageList = labelService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功",
                new PageResult<>(pageList.getTotalElements(), pageList.getContent()));
    }

}
