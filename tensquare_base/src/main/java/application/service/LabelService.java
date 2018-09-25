package application.service;

import application.dao.LabelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import application.pojo.Label;
import utils.IdWorker;

import java.util.List;

/**
 * Created by chenglong on 2018-09-25.
 * 标签业务逻辑类
 */
@Service
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Description: 查询全部标签
     * @Param: []
     * @return: java.util.List<application.pojo.Label>
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * @Description: 根据id查询标签
     * @Param: [id]
     * @return: application.pojo.Label
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * @Description: 增加标签
     * @Param: [label]
     * @return: void
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * @Description: 修改标签
     * @Param: [label]
     * @return: void
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * @Description: 删除id
     * @Param: [id]
     * @return: void
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

}
