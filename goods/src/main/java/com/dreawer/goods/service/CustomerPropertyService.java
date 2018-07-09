package com.dreawer.category.service;

import com.dreawer.category.domain.CategorySystemPropertyName;
import com.dreawer.category.domain.CategorySystemPropertyValue;
import com.dreawer.category.domain.CustomerPropertyName;
import com.dreawer.category.domain.CustomerPropertyValue;
import com.dreawer.category.lang.PropertyNameType;
import com.dreawer.category.persistence.*;
import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.RuleError;
import com.dreawer.responsecode.rcdt.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.dreawer.category.constants.DomainConstants.*;

/**
 * <CODE>CustomerPropertyService</CODE>
 * 客户属性信息service
 */

@Service
public class CustomerPropertyService extends BaseService{

    @Autowired
    private CustomerPropertyNameDao customerPropertyNameDao;

    @Autowired
    private CustomerPropertyValueDao customerPropertyValueDao;

    @Autowired
    private CategorySystemPropertyNameDao categorySystemPropertyNameDao;

    @Autowired
    private CategorySystemPropertyValueDao categorySystemPropertyValueDao;

    /**
     * 添加信息（属性名和属性值）
     * @param customerPropertyName 客户属性名信息
     * @return 添加结果
     */
    public ResponseCode add(CustomerPropertyName customerPropertyName) {

        //判断属性名信息是否存在
        String name = customerPropertyName.getName();//获取属性名的名称
        String categoryId = customerPropertyName.getCategoryId();//获取类目ID
        String storeId = customerPropertyName.getStoreId();//获取店铺ID

        //查询系统属性名信息
        CategorySystemPropertyName querySystemPropertyName = categorySystemPropertyNameDao.findPropertyNameByName(categoryId, name);

        //判断系统属性名中是否存在该属性名信息
        if(querySystemPropertyName != null){
            return RuleError.EXISTED(PROPERTY_NAME);//属性名名称已存在
        }

        //查询客户属性名信息
        CustomerPropertyName queryCustomerPropertyName = customerPropertyNameDao.findPropertyValueByName(categoryId, storeId, name);

        if(queryCustomerPropertyName!=null){
            return RuleError.EXISTED(PROPERTY_NAME);//属性名名称已存在
        }

        //添加属性名
        customerPropertyNameDao.insert(customerPropertyName);

        //判断属性值列表是否为空
        List<CustomerPropertyValue> propertyValues = customerPropertyName.getPropertyValues();//获取属性值信息列表
        if(propertyValues != null && propertyValues.size() > 0){
            //判断属性值中是否存在名称重复
            Set<String> valueSet = new HashSet<>();//创建set集合接收属性值名称
            for (CustomerPropertyValue propertyValue : propertyValues) {

                //绑定属性名ID
                propertyValue.setPropertyNameId(customerPropertyName.getId());

                valueSet.add(propertyValue.getName());
            }

            //判断两个集合的长度是否一致
            if(valueSet.size() != propertyValues.size()){
                return EntryError.DUPLICATE(PROPERTY_VALUE_NAME);//属性值名称重复
            }

            //执行添加

            customerPropertyValueDao.insertBatch(propertyValues);//批量添加属性值
        }

        //返回处理结果
        return Success.SUCCESS(customerPropertyName.getId());
    }

    /**
     * 添加属性值
     * @param customerPropertyValue 客户属性值信息
     * @return 添加结果
     */
    public ResponseCode addPropertyValue(CustomerPropertyValue customerPropertyValue) {

        String name = customerPropertyValue.getName();//获取属性值名称
        String storeId = customerPropertyValue.getStoreId();//获取店铺ID
        String categoryId = customerPropertyValue.getCategoryId();//获取类目ID
        PropertyNameType propertyNameType = customerPropertyValue.getPropertyNameType();//获取属性名类型
        String propertyNameId = customerPropertyValue.getPropertyNameId();//获取属性名ID

        //判断属性名类型
        if(propertyNameType.equals(PropertyNameType.CUSTOMER)){

            //查询客户属性中是否存在名称相同的属性值
            CustomerPropertyValue propertyValue = customerPropertyValueDao.findPropertyNameByName(categoryId, storeId, propertyNameId, name);

            //属性值已存在
            if(propertyValue != null){
                return RuleError.EXISTED(PROPERTY_VALUE);
            }
        }else{
            //查询系统属性中是否存在名称相同的属性值
            CategorySystemPropertyValue propertyValue = categorySystemPropertyValueDao.findPropertyValueByName(categoryId, propertyNameId, name);

            //属性值已存在
            if(propertyValue != null){
                return RuleError.EXISTED(PROPERTY_VALUE);
            }

        }

        //执行添加
        customerPropertyValueDao.insert(customerPropertyValue);

        //返回处理结果
        return Success.SUCCESS(customerPropertyValue.getId());
    }

    /**
     * 编辑属性信息(属性名和属性值)
     * @param customerPropertyName 客户属性名信息
     * @return 编辑结果
     */
    public ResponseCode edit(CustomerPropertyName customerPropertyName) {

        //判断属性名信息是否存在
        String name = customerPropertyName.getName();//获取属性名的名称
        String categoryId = customerPropertyName.getCategoryId();//获取类目ID
        String storeId = customerPropertyName.getStoreId();//获取店铺ID


        //查询系统属性名信息

        CategorySystemPropertyName querySystemPropertyName = categorySystemPropertyNameDao.findPropertyNameByName(categoryId, name );

        //判断系统属性名中是否存在该属性名信息
        if(querySystemPropertyName != null){
            return RuleError.EXISTED(PROPERTY_NAME);//属性名名称已存在
        }

        //查询客户属性名信息
        CustomerPropertyName queryCustomerPropertyName = customerPropertyNameDao.findPropertyValueByName(categoryId, storeId, name);

        if(queryCustomerPropertyName!=null){
            return RuleError.EXISTED(PROPERTY_NAME);//属性名名称已存在
        }

        //更新属性名信息
        customerPropertyNameDao.update(customerPropertyName);

        //将该属性名下原属性值列表删除
        customerPropertyValueDao.deleteByPropertyNameId(customerPropertyName.getId());


        //判断属性值列表是否为空
        List<CustomerPropertyValue> propertyValues = customerPropertyName.getPropertyValues();//获取属性值信息列表

        if(propertyValues != null && propertyValues.size() > 0){
            //判断属性值中是否存在名称重复
            List<CustomerPropertyValue> addPropertyValues = new ArrayList<>();//创建List集合接收待添加属性值

            Set<String> valueSet = new HashSet<>();//创建set集合接收属性值名称

            for (CustomerPropertyValue propertyValue : propertyValues) {
                valueSet.add(propertyValue.getName());

                //判断属性值ID是否存在（无ID则为新增的属性值）
                if(propertyValue.getId()==null){
                    propertyValue.setId(UUID.randomUUID().toString().replace("-",""));
                }

                //封装到待添加属性值列表中
                addPropertyValues.add(propertyValue);
            }

            //判断两个集合的长度是否一致
            if(valueSet.size() != propertyValues.size()){
                return EntryError.DUPLICATE(PROPERTY_VALUE_NAME);//属性值名称重复
            }


            //批量新增属性值
            customerPropertyValueDao.insertBatch(addPropertyValues);
        }

        //返回处理结果
        return Success.SUCCESS(customerPropertyName.getId());
    }

    /**
     * 编辑属性值信息
     * @param customerPropertyValue 客户属性值信息
     * @return 编辑结果
     */
    public ResponseCode editPropertyValue(CustomerPropertyValue customerPropertyValue) {


        String id = customerPropertyValue.getId();//获取属性值ID
        String name = customerPropertyValue.getName();//获取属性值名称
        String storeId = customerPropertyValue.getStoreId();//获取店铺ID

        //获取客户编辑的属性值信息
        CustomerPropertyValue originalPropertyValue = customerPropertyValueDao.findPropertyNameById(id);
        String originalName = originalPropertyValue.getName();

        //判断名称是否修改
        if(name.equals(originalName)){
            return Success.SUCCESS(id);//名称未修改则直接返回成功
        }

        String categoryId = originalPropertyValue.getCategoryId();//获取类目ID
        PropertyNameType propertyNameType = originalPropertyValue.getPropertyNameType();//获取属性名类型
        String propertyNameId = originalPropertyValue.getPropertyNameId();//获取属性名ID

        //判断属性名类型
        if(propertyNameType.equals(PropertyNameType.CUSTOMER)){

            //查询客户属性中是否存在名称相同的属性值
            CustomerPropertyValue propertyValue = customerPropertyValueDao.findPropertyNameByName(categoryId, storeId, propertyNameId, name);

            //属性值已存在
            if(propertyValue != null){
                return RuleError.EXISTED(PROPERTY_VALUE);
            }
        }else{
            //查询系统属性中是否存在名称相同的属性值
            CategorySystemPropertyValue propertyValue = categorySystemPropertyValueDao.findPropertyValueByName(categoryId, propertyNameId, name);

            //属性值已存在
            if(propertyValue != null){
                return RuleError.EXISTED(PROPERTY_VALUE);
            }

        }

        //执行更新
        customerPropertyValueDao.update(customerPropertyValue);

        //返回处理结果
        return Success.SUCCESS(id);
    }

    /**
     * 删除属性信息（属性名和属性值）
     * @param propertyNameId 客户属性名ID
     * @return 删除结果
     */
    public ResponseCode delete(String propertyNameId) {

        //执行删除
        customerPropertyNameDao.delete(propertyNameId);

        //返回处理结果
        return Success.SUCCESS;
    }

    /**
     * 删除属性值信息
     * @param propertyValueId 客户属性值ID
     * @return 删除结果
     */
    public ResponseCode deletePropertyValue(String propertyValueId) {

        //执行删除
        customerPropertyValueDao.delete(propertyValueId);

        //返回处理结果
        return Success.SUCCESS;
    }

    /**
     * 根据类目ID、店铺ID查询客户属性名信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @return 客户属性名列表
     */
    public ResponseCode findPropertyNames(String categoryId, String storeId) {

        //执行查询
        List<CustomerPropertyName> propertyNames = customerPropertyNameDao.findPropertyNames(categoryId, storeId,null);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 根据类目ID、店铺ID和模糊查询关键字查询客户属性名信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param keyword 模糊查询关键字
     * @return 客户属性名列表
     */
    public ResponseCode suggestPropertyNames(String categoryId, String storeId, String keyword) {

        //执行查询
        List<CustomerPropertyName> propertyNames = customerPropertyNameDao.findPropertyNames(categoryId, storeId, keyword);

        //返回处理结果
        return Success.SUCCESS(propertyNames);
    }

    /**
     * 根据类目ID、店铺ID、属性名ID查询客户属性值信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param propertyNameId 属性名ID
     * @return 客户属性值列表
     */
    public ResponseCode findPropertyValues(String categoryId, String storeId, String propertyNameId) {

        //执行查询
        List<CustomerPropertyValue> propertyValues = customerPropertyValueDao.findPropertyValues(categoryId, storeId, propertyNameId, null);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

    /**
     * 根据类目ID、店铺ID、属性名ID和模糊查询关键字查询客户属性值信息列表
     * @param categoryId 类目ID
     * @param storeId 店铺ID
     * @param propertyNameId 属性名ID
     * @param keyword 模糊查询关键字
     * @return 客户属性值列表
     */
    public ResponseCode suggestPropertyValues(String categoryId, String storeId, String propertyNameId, String keyword) {

        //执行查询
        List<CustomerPropertyValue> propertyValues = customerPropertyValueDao.findPropertyValues(categoryId, storeId, propertyNameId, keyword);

        //返回处理结果
        return Success.SUCCESS(propertyValues);
    }

}
