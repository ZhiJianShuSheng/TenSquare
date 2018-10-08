package application.dao;

import application.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @program: tensquare_parent
 * @description: 吐槽数据访问层
 * @author: chenglong
 * @create:2018-10-07
 */

public interface SpitDao extends MongoRepository<Spit,String> {

    /**
    * @Description: 根据上级ID查询吐槽列表
    * @Param: [parentId, pageable]
    * @return: org.springframework.data.domain.Page<application.pojo.Spit>
    */
    public Page<Spit> findByParentid(String parentId, Pageable pageable);

}
