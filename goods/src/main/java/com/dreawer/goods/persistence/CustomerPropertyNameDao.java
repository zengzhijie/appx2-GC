package com.dreawer.category.persistence;

import com.dreawer.category.domain.CustomerPropertyName;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * 客户系统属性名DAO
 */

@Repository
public class CustomerPropertyNameDao extends MyBatisBaseDao<CustomerPropertyName>{

    /**
     * 添加客户属性名
     * @param customerPropertyName 客户属性名信息
     * @return 成功添加记录数：成功返回1，失败则返回0。
     */
    public int insert(CustomerPropertyName customerPropertyName){

        //返回添加结果
        return insert("insert", customerPropertyName);
    }

    /**
     * 删除客户属性名以及属性值
     * @param id 待删除的客户属性名ID
     * @return 成功删除记录数：成功返回1，失败则返回0。
     */
    public int delete(String id){

        //返回删除结果
        return delete("delete", id);
    }

    /**
     * 更新客户属性名信息
     * @param customerPropertyName 待更新的客户属性名信息
     * @return 成功更新记录数：成功返回1，失败则返回0。
     */
    public int update(CustomerPropertyName customerPropertyName){

        //返回更新结果
        return update("update", customerPropertyName);
    }

    /**
     * 根据类目ID、店铺ID和模糊查询关键字查询客户属性名信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param keyword 模糊查询关键字
     * @return 客户属性名列表：成功返回列表，失败或未查询到结果则返回NULL。
     */
    public List<CustomerPropertyName> findPropertyNames(String categoryId, String storeId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return selectList("findPropertyNames", params);
    }

    /**
     * 根据类目ID、店铺ID和模糊查询关键字查询客户属性名信息总数
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param keyword 模糊查询关键字
     * @return 客户属性名总数：成功返回总数，失败或未查询到结果则返回0。
     */
    public int getPropertyNameCount(String categoryId, String storeId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return count("getPropertyNameCount", params);
    }

    /**
     * 根据类目ID、店铺ID和属性名称查询客户属性名信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param name 属性名称
     * @return 客户属性名信息：成功返回属性值值信息，失败或未查询到结果则返回NULL。
     */
    public CustomerPropertyName findPropertyValueByName(String categoryId, String storeId, String name){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(NAME, name);

        return selectOne("findPropertyValueByName", params);
    }

}
