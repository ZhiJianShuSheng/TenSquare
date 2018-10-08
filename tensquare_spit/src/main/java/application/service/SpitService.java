package application.service;

import application.dao.SpitDao;
import application.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import utils.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @program: tensquare_parent
 * @description: 吐槽业务逻辑类
 * @author: chenglong
 * @create:2018-10-07
 */

@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @Description: 查询全部记录
     * @Param: []
     * @return: java.util.List<application.pojo.Spit>
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * @Description: 根据主键查询实体
     * @Param: [id]
     * @return: application.pojo.Spit
     */
    public Spit findById(String id) {
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * @Description: 增加、初始化数据
     * @Param: [spit]
     * @return: void
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.nextId() + "");
        spit.setPublishTime(new Date());
        spit.setVisits(0);
        spit.setComment(0);//回复数
        spit.setShare(0);
        spit.setThumbUp(0);
        spit.setState("1");//状态

        //如果该吐槽有父节点，则更新父节点
        if (spit.getParentId() != null && !"".equals(spit.getParentId())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentId()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    /**
     * @Description: 修改
     * @Param: [spit]
     * @return: void
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * @Description: 删除
     * @Param: [id]
     * @return: void
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    /**
     * @Description: 根据上级ID查询吐槽列表
     * @Param: [id, pageNum, pageSize]
     * @return: org.springframework.data.domain.Page<application.pojo.Spit>
     */
    public Page<Spit> findByParentid(String id, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        return spitDao.findByParentid(id, pageRequest);
    }

    /**
     * @Description: 点赞
     * @Param: [id]
     * @return: void
     */
    public void thumbUp(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * @Description: 增加浏览量
     * @Param: [id]
     * @return: void
     */
    public void visitsAdd(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("visits", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }

    /**
     * @Description: 增加分享数
     * @Param: [id]
     * @return: void
     */
    public void shareAdd(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("share", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
