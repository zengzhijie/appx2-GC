package com.dreawer.category.domain;

import com.dreawer.category.lang.PropertyNameType;
import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;

/**
 * <CODE>CustomerPropertyValue</CODE>
 * 客户属性值信息实体类
 */

public class CustomerPropertyValue extends BaseDomain {

    private String categoryId = null; // 类目ID

    private String storeId = null; // 店铺ID

    private String name = null; // 名称

    private PropertyNameType propertyNameType = null; // 属性名类型

    private String propertyNameId = null; // 属性名ID（系统属性或用户自定义属性）

    private String createrId = null ; // 创建者

    private Timestamp createTime = null; // 创建时间

    private String updaterId = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    private String remark = null; // 备注

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyNameType getPropertyNameType() {
        return propertyNameType;
    }

    public void setPropertyNameType(PropertyNameType propertyNameType) {
        this.propertyNameType = propertyNameType;
    }

    public String getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(String propertyNameId) {
        this.propertyNameId = propertyNameId;
    }

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
