package controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pojo.Label;
import service.LabelService;

import java.util.List;

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
     * @return: entity.Result<pojo.Label>
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


}
