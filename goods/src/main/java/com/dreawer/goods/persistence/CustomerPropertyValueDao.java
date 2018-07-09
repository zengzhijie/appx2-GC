package com.dreawer.category.persistence;

import com.dreawer.category.domain.CustomerPropertyValue;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * 客户系统属性值DAO
 */

@Repository
public class CustomerPropertyValueDao extends MyBatisBaseDao<CustomerPropertyValue>{

    /**
     * 添加客户属性值
     * @param customerPropertyValue 客户属性值信息
     * @return 成功添加记录数：成功返回1，失败则返回0。
     */
    public int insert(CustomerPropertyValue customerPropertyValue){

        //返回添加结果
        return insert("insert", customerPropertyValue);
    }

    /**
     * 批量添加客户属性值
     * @param customerPropertyValues 客户属性值信息列表
     * @return 成功添加记录数：成功返回添加数目，失败则返回0。
     */
    public int insertBatch(List<CustomerPropertyValue> customerPropertyValues){

        //返回添加结果
        return insertBatch("insertBatch", customerPropertyValues);
    }

    /**
     * 删除客户属性值
     * @param id 待删除的客户属性值ID
     * @return 成功删除记录数：成功返回1，失败则返回0。
     */
    public int delete(String id){

        //返回删除结果
        return delete("delete",id);
    }

    /**
     * 根据属性名ID删除客户属性值
     * @param propertyNameId 属性名ID
     * @return 成功删除记录数：成功删除数目，失败则返回0。
     */
    public int deleteByPropertyNameId(String propertyNameId){

        //返回删除结果
        return delete("deleteByPropertyNameId",propertyNameId);
    }

    /**
     * 批量删除客户属性值
     * @param ids 待删除的客户属性值ID列表
     * @return 成功删除记录数：成功删除数目，失败则返回0。
     */
    public int deleteBatch(List<String> ids){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(IDS, ids);

        //返回删除结果
        return deleteBatch("deleteBatch", params);
    }

    /**
     * 更新客户属性值信息
     * @param customerPropertyValue 待更新的客户属性值信息
     * @return 成功更新记录数：成功返回1，失败则返回0。
     */
    public int update(CustomerPropertyValue customerPropertyValue){

        //返回更新结果
        return update("update", customerPropertyValue);
    }

    /**
     * 根据类目ID、店铺ID、属性名ID和模糊查询关键字查询客户属性值信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param propertyNameId 属性名ID
     * @param keyword 模糊查询关键字
     * @return 客户属性值列表：成功返回列表，失败或未查询到结果则返回NULL。
     */
    public List<CustomerPropertyValue> findPropertyValues(String categoryId, String storeId, String propertyNameId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return selectList("findPropertyValues", params);
    }

    /**
     * 根据类目ID、店铺ID、属性名ID和模糊查询关键字查询客户属性值信息总数
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param propertyNameId 属性名ID
     * @param keyword 模糊查询关键字
     * @return 客户属性值总数：成功返回总数，失败或未查询到结果则返回NULL。
     */
    public int getPropertyValueCount(String categoryId, String storeId, String propertyNameId, String keyword){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(KEYWORD, keyword);

        //返回查询结果
        return count("getPropertyValueCount", params);
    }

    /**
     * 根据类目ID、店铺ID、属性名ID和属性值名称查询属性值信息
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param propertyNameId 属性名ID
     * @param name 名称
     * @return 客户属性值信息：成功返回属性值信息，失败或未查询到结果则返回NULL。
     */
    public CustomerPropertyValue findPropertyNameByName(String categoryId, String storeId, String propertyNameId, String name){

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(NAME, name);

        //返回查询结果
        return selectOne("findPropertyNameByName", params);
    }

    /**
     * 根据ID查询客户属性值信息
     * @param  id 属性值ID
     * @return 客户属性值信息：成功返回属性值信息，失败或未查询到结果则返回NULL。
     */
    public CustomerPropertyValue findPropertyNameById(String id){

        //返回查询结果
        return selectOne("findPropertyNameById", id);
    }

}
