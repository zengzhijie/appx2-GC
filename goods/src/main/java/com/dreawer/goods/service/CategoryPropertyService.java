package com.dreawer.category.service;

import com.dreawer.category.domain.CategorySystemPropertyName;
import com.dreawer.category.domain.CategorySystemPropertyValue;
import com.dreawer.category.domain.CustomerPropertyName;
import com.dreawer.category.domain.CustomerPropertyValue;
import com.dreawer.category.persistence.CategorySystemPropertyNameDao;
import com.dreawer.category.persistence.CategorySystemPropertyValueDao;
import com.dreawer.category.persistence.CustomerPropertyNameDao;
import com.dreawer.category.persistence.CustomerPropertyValueDao;
import com.dreawer.category.view.ViewCategoryPropertyName;
import com.dreawer.category.view.ViewCategoryPropertyValue;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * <CODE>CategorySystemPropertyNameService</CODE>
 * 类目系统属性信息服务
 */

@Service
public class CategoryPropertyService extends BaseService{

    @Autowired
    private CategorySystemPropertyNameDao categorySystemPropertyNameDao;

    @Autowired
    private CategorySystemPropertyValueDao categorySystemPropertyValueDao;

    @Autowired
    private CustomerPropertyNameDao customerPropertyNameDao;

    @Autowired
    private CustomerPropertyValueDao customerPropertyValueDao;

    /**
     * 通过类目ID和店铺ID查询属性名列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @return 系统属性名列表
     */
    public ResponseCode findPropertyNames(String categoryId, String storeId) {



        //查询系统属性名列表
        List<CategorySystemPropertyName> systemPropertyNames = categorySystemPropertyNameDao.findPropertyNames(categoryId, null);

        //查询客户属性名列表
        List<CustomerPropertyName> customerPropertyNames = customerPropertyNameDao.findPropertyNames(categoryId, storeId, null);

        //转换视图
        List<ViewCategoryPropertyName> propertyNames = convertCategoryPropertyNameViews(systemPropertyNames, customerPropertyNames);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 通过类目ID、店铺ID和模糊查询关键字查询属性名列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param keyword    模糊查询关键字
     * @return 系统属性名列表
     */
    public ResponseCode suggestPropertyNames(String categoryId, String storeId, String keyword) {

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(KEYWORD, keyword);

        //查询系统属性名列表
        List<CategorySystemPropertyName> systemPropertyNames = categorySystemPropertyNameDao.findPropertyNames(categoryId, keyword);

        //查询客户属性名列表
        List<CustomerPropertyName> customerPropertyNames = customerPropertyNameDao.findPropertyNames(categoryId, storeId, keyword);

        //转换视图
        List<ViewCategoryPropertyName> propertyNames = convertCategoryPropertyNameViews(systemPropertyNames, customerPropertyNames);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 通过类目ID、店铺ID、属性名ID查询系统属性值列表
     * @param categoryId     类目ID
     * @param storeId     店铺ID
     * @param propertyNameId 属性名ID
     * @return 系统属性值列表
     */
    public ResponseCode findPropertyValues(String categoryId, String storeId, String propertyNameId) {

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(PROPERTY_NAME_ID, propertyNameId);

        //查询系统属性值列表
        List<CategorySystemPropertyValue> systemPropertyValues = categorySystemPropertyValueDao.findPropertyValues(categoryId, propertyNameId, null);

        //查询客户属性值列表
        List<CustomerPropertyValue> customerPropertyValues = customerPropertyValueDao.findPropertyValues(categoryId, storeId, propertyNameId, null);

        //转换视图
        List<ViewCategoryPropertyValue> propertyValues = convertCategoryPropertyValueViews(systemPropertyValues, customerPropertyValues);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

    /**
     * 通过类目ID、系统属性名ID和模糊查询关键字查询系统属性值列表
     * @param categoryId     类目ID
     * @param storeId     店铺ID
     * @param propertyNameId 系统属性名ID
     * @param keyword        模糊查询关键字
     * @return 系统属性值列表
     */
    public ResponseCode suggestPropertyValues(String categoryId, String storeId, String propertyNameId, String keyword) {

        //封装请求参数
        Map<String, Object> params = new HashMap<>();
        params.put(CATEGORY_ID, categoryId);
        params.put(STORE_ID, storeId);
        params.put(PROPERTY_NAME_ID, propertyNameId);
        params.put(KEYWORD, keyword);

        //查询系统属性值列表
        List<CategorySystemPropertyValue> systemPropertyValues = categorySystemPropertyValueDao.findPropertyValues(categoryId, propertyNameId, keyword);

        //查询客户属性值列表
        List<CustomerPropertyValue> customerPropertyValues = customerPropertyValueDao.findPropertyValues(categoryId, storeId, propertyNameId, keyword);

        //转换视图
        List<ViewCategoryPropertyValue> propertyValues = convertCategoryPropertyValueViews(systemPropertyValues, customerPropertyValues);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

}
