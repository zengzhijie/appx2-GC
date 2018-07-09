package com.dreawer.category.service;

import com.dreawer.category.domain.CategorySystemPropertyName;
import com.dreawer.category.domain.CategorySystemPropertyValue;
import com.dreawer.category.persistence.CategorySystemPropertyNameDao;
import com.dreawer.category.persistence.CategorySystemPropertyValueDao;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <CODE>CategorySystemPropertyNameService</CODE>
 * 类目系统属性信息服务
 */

@Service
public class CategorySystemPropertyService extends BaseService{

    @Autowired
    private CategorySystemPropertyNameDao categorySystemPropertyNameDao;

    @Autowired
    private CategorySystemPropertyValueDao categorySystemPropertyValueDao;

    /**
     * 通过类目ID查询系统属性名列表
     * @param categoryId 类目ID
     * @return 系统属性名列表
     */
    public ResponseCode findPropertyNames(String categoryId) {

        //执行查询
        List<CategorySystemPropertyName> propertyNames = categorySystemPropertyNameDao.findPropertyNames(categoryId, null);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 通过类目ID和模糊查询关键字查询系统属性名列表
     * @param categoryId 类目ID
     * @param keyword    模糊查询关键字
     * @return 系统属性名列表
     */
    public ResponseCode suggestPropertyNames(String categoryId, String keyword) {

        //执行查询
        List<CategorySystemPropertyName> propertyNames = categorySystemPropertyNameDao.findPropertyNames(categoryId, keyword);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 通过类目ID和系统属性名ID查询系统属性值列表
     * @param categoryId     类目ID
     * @param propertyNameId 系统属性名ID
     * @return 系统属性值列表
     */
    public ResponseCode findPropertyValues(String categoryId, String propertyNameId) {

        //执行查询
        List<CategorySystemPropertyValue> propertyValues = categorySystemPropertyValueDao.findPropertyValues(categoryId, propertyNameId, null);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

    /**
     * 通过类目ID、系统属性名ID和模糊查询关键字查询系统属性值列表
     * @param categoryId     类目ID
     * @param propertyNameId 系统属性名ID
     * @param keyword        模糊查询关键字
     * @return 系统属性值列表
     */
    public ResponseCode suggestPropertyValues(String categoryId, String propertyNameId, String keyword) {



        //执行查询
        List<CategorySystemPropertyValue> propertyValues = categorySystemPropertyValueDao.findPropertyValues(categoryId, propertyNameId, keyword);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

}
