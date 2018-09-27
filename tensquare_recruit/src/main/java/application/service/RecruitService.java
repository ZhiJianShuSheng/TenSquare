package application.service;

import application.dao.RecruitDao;
import application.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
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
 * 招聘信息业务逻辑类
 */
@Service
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * @Description: 查询全部招聘信息
     * @Param: []
     * @return: java.util.List<application.pojo.Recruit>
     */
    public List<Recruit> findAll() {
        return recruitDao.findAll();
    }

    /**
     * @Description: 分页查询全部招聘信息
     * @Param: [page, size]
     * @return: org.springframework.data.domain.Page<application.pojo.Recruit>
     */
    public Page<Recruit> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return recruitDao.findAll(pageRequest);
    }

    /**
     * @Description: 根据id查询招聘信息
     * @Param: [id]
     * @return: application.pojo.Recruit
     */
    public Recruit findById(String id) {
        return recruitDao.findById(id).get();
    }

    /**
     * @Description: 增加招聘信息
     * @Param: [label]
     * @return: void
     */
    public void add(Recruit label) {
        label.setId(idWorker.nextId() + "");
        recruitDao.save(label);
    }

    /**
     * @Description: 修改招聘信息
     * @Param: [label]
     * @return: void
     */
    public void update(Recruit label) {
        recruitDao.save(label);
    }

    /**
     * @Description: 删除id
     * @Param: [id]
     * @return: void
     */
    public void deleteById(String id) {
        recruitDao.deleteById(id);
    }

    /**
     * @Description: 条件查询
     * @Param: [searchMap]
     * @return: java.util.List<application.pojo.Recruit>
     */
    public List<Recruit> findSearch(Map searchMap) {
        Specification specification = createSpecification(searchMap);
        return recruitDao.findAll(specification);
    }

    /**
     * @Description: 分页条件查询
     * @Param: [searchMap, page, size]
     * @return: org.springframework.data.domain.Page<application.pojo.Recruit>
     */
    public Page<Recruit> findSearch(Map searchMap, int page, int size) {
        Specification specification = createSpecification(searchMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return recruitDao.findAll(specification, pageRequest);
    }

    /**
    * @Description: 查询最新职位列表（按创建日期降序）
    * @Param: []
    * @return: java.util.List<application.pojo.Recruit>
    */
    public List<Recruit> newlist(){
        return recruitDao.findTop4ByStateOrderByCreatetimeDesc("0");
    }

    /**
     * @Description: 构建查询条件
     * @Param: [searchMap]
     * @return: org.springframework.data.jpa.domain.Specification<application.pojo.Recruit>
     */
    private Specification<Recruit> createSpecification(Map searchMap) {
        return new Specification<Recruit>() {
            @Override
            public Predicate toPredicate(Root<Recruit> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                // ID
                if (searchMap.get("id") != null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%" + (String) searchMap.get("id") + "%"));
                }
                // 职位名称
                if (searchMap.get("jobname") != null && !"".equals(searchMap.get("jobname"))) {
                    predicateList.add(cb.like(root.get("jobname").as(String.class), "%" + (String) searchMap.get("jobname") + "%"));
                }
                // 薪资范围
                if (searchMap.get("salary") != null && !"".equals(searchMap.get("salary"))) {
                    predicateList.add(cb.like(root.get("salary").as(String.class), "%" + (String) searchMap.get("salary") + "%"));
                }
                // 经验要求
                if (searchMap.get("condition") != null && !"".equals(searchMap.get("condition"))) {
                    predicateList.add(cb.like(root.get("condition").as(String.class), "%" + (String) searchMap.get("condition") + "%"));
                }
                // 学历要求
                if (searchMap.get("education") != null && !"".equals(searchMap.get("education"))) {
                    predicateList.add(cb.like(root.get("education").as(String.class), "%" + (String) searchMap.get("education") + "%"));
                }
                // 任职方式
                if (searchMap.get("type") != null && !"".equals(searchMap.get("type"))) {
                    predicateList.add(cb.like(root.get("type").as(String.class), "%" + (String) searchMap.get("type") + "%"));
                }
                // 办公地址
                if (searchMap.get("address") != null && !"".equals(searchMap.get("address"))) {
                    predicateList.add(cb.like(root.get("address").as(String.class), "%" + (String) searchMap.get("address") + "%"));
                }
                // 企业ID
                if (searchMap.get("eid") != null && !"".equals(searchMap.get("eid"))) {
                    predicateList.add(cb.like(root.get("eid").as(String.class), "%" + (String) searchMap.get("eid") + "%"));
                }
                // 状态
                if (searchMap.get("state") != null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(cb.like(root.get("state").as(String.class), "%" + (String) searchMap.get("state") + "%"));
                }
                // 网址
                if (searchMap.get("url") != null && !"".equals(searchMap.get("url"))) {
                    predicateList.add(cb.like(root.get("url").as(String.class), "%" + (String) searchMap.get("url") + "%"));
                }
                // 标签
                if (searchMap.get("label") != null && !"".equals(searchMap.get("label"))) {
                    predicateList.add(cb.like(root.get("label").as(String.class), "%" + (String) searchMap.get("label") + "%"));
                }
                // 职位描述
                if (searchMap.get("content1") != null && !"".equals(searchMap.get("content1"))) {
                    predicateList.add(cb.like(root.get("content1").as(String.class), "%" + (String) searchMap.get("content1") + "%"));
                }
                // 职位要求
                if (searchMap.get("content2") != null && !"".equals(searchMap.get("content2"))) {
                    predicateList.add(cb.like(root.get("content2").as(String.class), "%" + (String) searchMap.get("content2") + "%"));
                }

                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
