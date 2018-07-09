package com.dreawer.category.persistence;

import com.dreawer.category.domain.CategorySystemPropertyValue;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * 类目系统属性值DAO
 */

@Repository
public class CategorySystemPropertyValueDao extends MyBatisBaseDao<CategorySystemPropertyValue>{

    /**
     * 通过类目ID、系统属性名ID和模糊查询关键字查询系统属性值列表
     * @param categoryId     类目ID
     * @param propertyNameId 系统属性名ID
     * @param keyword        模糊查询关键字
     * @return 系统属性值列表：成功返回列表，失败或未查询到结果则返回NULL。
     */
    public List<CategorySystemPropertyValue> findPropertyValues(String categoryId, String propertyNameId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return selectList("findPropertyValues", params);
    }

    /**
     * 通过类目ID、属性名ID和模糊查询关键字查询系统属性值信息总数
     * @param categoryId     类目ID
     * @param propertyNameId 系统属性名ID
     * @param keyword        模糊查询关键字
     * @return 系统属性值总数：成功返回总数，失败或未查询到结果则返回0。
     */
    public int getPropertyValueCount(String categoryId, String propertyNameId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return count("getPropertyValueCount", params);
    }

    /**
     * 通过类目ID、属性名ID和属性值名称查询系统属性值信息
     * @param categoryId     类目ID
     * @param propertyNameId 系统属性名ID
     * @param name        属性值名称
     * @return 系统属性值信息：成功返回属性值信息，失败或未查询到结果则返回NULL。
     */
    public CategorySystemPropertyValue findPropertyValueByName(String categoryId, String propertyNameId, String name){

        //封装请求参数
        Map<String,Object> params = new HashMap<>();
        params.put(NAME,name);
        params.put(CATEGORY_ID,categoryId);
        params.put(PROPERTY_NAME_ID,propertyNameId);

        //返回查询结果
        return selectOne("findPropertyValueByName", params);
    }

}
