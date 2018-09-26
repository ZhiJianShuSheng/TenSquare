package application.service;

import application.dao.LabelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import application.pojo.Label;
import utils.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    * @Description: 分页查询全部标签
    * @Param: [page, size]
    * @return: org.springframework.data.domain.Page<application.pojo.Label>
    */
    public Page<Label> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(pageRequest);
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

    /**
     * @Description: 条件查询
     * @Param: [searchMap]
     * @return: java.util.List<application.pojo.Label>
     */
    public List<Label> findSearch(Map searchMap) {
        Specification specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * @Description: 分页条件查询
     * @Param: [searchMap, page, size]
     * @return: org.springframework.data.domain.Page<application.pojo.Label>
     */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(specification, pageRequest);
    }

    /**
     * @Description: 构建查询条件
     * @Param: [searchMap]
     * @return: org.springframework.data.jpa.domain.Specification<application.pojo.Label>
     */
    private Specification<Label> createSpecification(Map searchMap) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelName") != null && !"".equals(searchMap.get("labelName"))) {
                    predicateList.add(criteriaBuilder.like(root.get("labelName").as(String.class),
                            "%" + searchMap.get("labelName") + "%"));
                }
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class),
                            (String) searchMap.get("state")));
                }
                if (searchMap.get("recommend") != null && !"".equals(searchMap.get("recommend"))) {
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),
                            (String) searchMap.get("recommend")));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
