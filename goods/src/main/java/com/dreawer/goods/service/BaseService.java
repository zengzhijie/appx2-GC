package com.dreawer.category.service;

import com.dreawer.category.domain.CategorySystemPropertyName;
import com.dreawer.category.domain.CategorySystemPropertyValue;
import com.dreawer.category.domain.CustomerPropertyName;
import com.dreawer.category.domain.CustomerPropertyValue;
import com.dreawer.category.lang.PropertyNameType;
import com.dreawer.category.lang.PropertyValueType;
import com.dreawer.category.view.ViewCategoryPropertyName;
import com.dreawer.category.view.ViewCategoryPropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础服务类
 */
public class BaseService {

    /**
     * 转换类目属性名视图列表
     * @param systemPropertyNames 系统属性名列表
     * @param customerPropertyNames 客户属性名列表
     * @return 类目属性名视图列表
     */
    protected List<ViewCategoryPropertyName> convertCategoryPropertyNameViews(List<CategorySystemPropertyName> systemPropertyNames, List<CustomerPropertyName> customerPropertyNames){
        //创建视图列表
        List<ViewCategoryPropertyName> propertyNames = new ArrayList<>();

        //封装系统属性名到视图列表
        if(systemPropertyNames != null && systemPropertyNames.size()>0){
            for (CategorySystemPropertyName systemPropertyName : systemPropertyNames) {

                //创建视图对象
                ViewCategoryPropertyName propertyName = new ViewCategoryPropertyName();
                propertyName.setId(systemPropertyName.getPropertyName().getId());
                propertyName.setCategoryId(systemPropertyName.getCategoryId());
                propertyName.setName(systemPropertyName.getPropertyName().getName());
                propertyName.setSquence(systemPropertyName.getSquence());
                propertyName.setType(PropertyNameType.SYSTEM);
                propertyName.setISales(systemPropertyName.getIsSales());
                propertyName.setIsBasic(systemPropertyName.getIsBasic());
                propertyName.setIsCheckbox(systemPropertyName.getPropertyName().getIsCheckbox());
                propertyName.setIsColor(systemPropertyName.getPropertyName().getIsColor());
                propertyName.setIsInput(systemPropertyName.getPropertyName().getIsInput());
                propertyName.setIsKey(systemPropertyName.getIsKey());
                propertyName.setIsRadio(systemPropertyName.getPropertyName().getIsRadio());
                propertyName.setIsRequired(systemPropertyName.getIsRequired());
                propertyName.setIsSearch(systemPropertyName.getPropertyName().getIsSearch());
                propertyName.setIsSelect(systemPropertyName.getPropertyName().getIsSelect());
                propertyName.setIsVisualEditor(systemPropertyName.getPropertyName().getIsVisualEditor());

                //封装到视图列表
                propertyNames.add(propertyName);
            }
        }

        //封装客户属性名到视图列表
        if(customerPropertyNames != null && customerPropertyNames.size()>0){
            for (CustomerPropertyName customerPropertyName : customerPropertyNames) {

                //创建视图对象
                ViewCategoryPropertyName propertyName = new ViewCategoryPropertyName();
                propertyName.setId(customerPropertyName.getId());
                propertyName.setCategoryId(customerPropertyName.getCategoryId());
                propertyName.setName(customerPropertyName.getName());
                propertyName.setSquence(customerPropertyName.getSquence());
                propertyName.setType(PropertyNameType.CUSTOMER);
                propertyName.setISales(customerPropertyName.getIsSales());
                propertyName.setIsBasic(customerPropertyName.getIsBasic());
                propertyName.setIsCheckbox(customerPropertyName.getIsCheckbox());
                propertyName.setIsColor(customerPropertyName.getIsColor());
                propertyName.setIsInput(customerPropertyName.getIsInput());
                propertyName.setIsKey(customerPropertyName.getIsKey());
                propertyName.setIsRadio(customerPropertyName.getIsRadio());
                propertyName.setIsRequired(customerPropertyName.getIsRequired());
                propertyName.setIsSearch(customerPropertyName.getIsSearch());
                propertyName.setIsSelect(customerPropertyName.getIsSelect());
                propertyName.setIsVisualEditor(customerPropertyName.getIsVisualEditor());

                //封装到视图列表
                propertyNames.add(propertyName);
            }
        }

        //返回结果
        return propertyNames;
    }

    /**
     * 转换类目属性名视图列表
     * @param systemPropertyValues 系统属性值列表
     * @param customerPropertyValues 客户属性值列表
     * @return 类目属性名视图列表
     */
    protected List<ViewCategoryPropertyValue> convertCategoryPropertyValueViews(List<CategorySystemPropertyValue> systemPropertyValues, List<CustomerPropertyValue> customerPropertyValues){
        //创建视图列表
        List<ViewCategoryPropertyValue> propertyValues = new ArrayList<>();

        //封装系统属性值到视图列表
        if(systemPropertyValues != null && systemPropertyValues.size()>0){
            for (CategorySystemPropertyValue systemPropertyValue : systemPropertyValues) {

                //创建视图对象
                ViewCategoryPropertyValue propertyValue = new ViewCategoryPropertyValue();
                propertyValue.setId(systemPropertyValue.getPropertyValue().getId());
                propertyValue.setCategoryId(systemPropertyValue.getCategoryId());
                propertyValue.setName(systemPropertyValue.getPropertyValue().getName());
                propertyValue.setType(PropertyValueType.SYSTEM);
                propertyValue.setRemark(systemPropertyValue.getPropertyValue().getRemark());

                //封装到视图列表
                propertyValues.add(propertyValue);
            }
        }

        //封装客户属性名到视图列表
        if(customerPropertyValues != null && customerPropertyValues.size()>0){
            for (CustomerPropertyValue customerPropertyValue : customerPropertyValues) {

                //创建视图对象
                ViewCategoryPropertyValue propertyValue = new ViewCategoryPropertyValue();
                propertyValue.setId(customerPropertyValue.getId());
                propertyValue.setStoreId(customerPropertyValue.getStoreId());
                propertyValue.setCategoryId(customerPropertyValue.getCategoryId());
                propertyValue.setName(customerPropertyValue.getName());
                propertyValue.setType(PropertyValueType.CUSTOMER);
                propertyValue.setRemark(customerPropertyValue.getRemark());

                //封装到视图列表
                propertyValues.add(propertyValue);
            }
        }

        //返回结果
        return propertyValues;
    }

}
