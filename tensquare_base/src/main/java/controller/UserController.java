package controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
public class UserController {

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
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }


}
