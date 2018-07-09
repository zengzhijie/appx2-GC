package com.dreawer.category.persistence;

import com.dreawer.category.domain.CategorySystemPropertyName;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * 类目系统属性名DAO
 */

@Repository
public class CategorySystemPropertyNameDao extends MyBatisBaseDao<CategorySystemPropertyName>{

    /**
     * 通过类目ID和模糊查询关键字查询系统属性名列表
     * @param categoryId 类目ID
     * @param keyword    模糊查询关键字
     * @return 系统属性名列表：成功返回列表，失败或未查询到结果则返回NULL。
     */
    public List<CategorySystemPropertyName> findPropertyNames(String categoryId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return selectList("findPropertyNames", params);
    }

    /**
     * 通过类目ID和模糊查询关键字查询系统属性名总数
     * @param categoryId 类目ID
     * @param keyword    模糊查询关键字
     * @return 系统属性名总数：成功返回总数，失败或未查询到结果则返回0。
     */
    public int getPropertyNameCount(String categoryId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return count("getPropertyNameCount", params);
    }


    /**
     * 通过类目ID和属性名称查询系统属性名信息
     * @param categoryId 类目ID
     * @param name 属性名称
     * @return 系统属性名信息：成功返回属性名信息，失败或未查询到结果则返回NULL。
     */
    public CategorySystemPropertyName findPropertyNameByName(String categoryId, String name){

        //封装请求参数
        Map<String,Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(NAME, name);

        //返回查询结果
        return selectOne("findPropertyNameByName",params);
    }
}
