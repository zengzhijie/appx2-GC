package com.dreawer.category.persistence;

import com.dreawer.category.domain.Category;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.KEYWORD;
import static com.dreawer.category.constants.DomainConstants.PARENT_ID;

/**
 * 类目DAO
 */

@Repository
public class CategoryDao extends MyBatisBaseDao<Category>{

    /**
     * 新增类目信息
     * @param category 待增加类目信息
     * @return 增加记录数：成功返回1，失败则返回0。
     */
    public int insert(Category category){

        //返回查询结果
        return insert("insert",category);
    }

    /**
     * 更新类目基本信息（名称、排列序号、父ID）
     * @param category 待更新的类目信息
     * @return 更新记录数：成功返回1，失败则返回0。
     */
    public int updateBasicInfo(Category category){

        //返回查询结果
        return update("updateBasicInfo",category);
    }

    /**
     * 通过父类目ID和模糊查询关键字查询子类目列表
     * @param parentId 父类目ID
     * @param keyword 模糊查询关键字
     * @return 类目列表：成功返回列表，失败或未查询到结果则返回NULL。
     */
    public List<Category> findCategories(String parentId, String keyword){

        System.out.println("成功");

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(PARENT_ID, parentId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return selectList("findCategories", params);
    }

    /**
     * 通过父类目ID和模糊查询关键字查询子类目总数
     * @param parentId 父类目ID
     * @param keyword 模糊查询关键字
     * @return 类目总数：成功返回总数，失败或未查询到结果则返回0。
     */
    public int getCategoryCount(String parentId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(PARENT_ID, parentId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return count("getCategoryCount", params);
    }

    /**
     * 根据类目ID查询类目详细信息
     * @param id 类目ID
     * @return 类目详细信息：成功返回类目信息，失败或未查询到结果则返回NULL。
     */
    public Category findCategoryById(String id){

        //返回查询结果
        return selectOne("findCategoryById", id);
    }
}
