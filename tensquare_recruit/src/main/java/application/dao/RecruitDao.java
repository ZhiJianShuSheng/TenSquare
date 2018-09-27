package application.dao;

import application.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by chenglong on 2018-09-25.
 * 标签数据访问接口
 */
public interface RecruitDao extends JpaRepository<Recruit,String>, JpaSpecificationExecutor<Recruit> {

    /**
    * @Description: 查询最新职位列表（按创建日期降序）
    * @Param: [state]
    * @return: java.util.List<application.pojo.Recruit>
    */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
}
