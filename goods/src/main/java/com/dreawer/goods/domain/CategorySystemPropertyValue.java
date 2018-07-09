package com.dreawer.category.domain;

import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;

/**
 * <CODE>CategorySystemPropertyValue</CODE>
 * 类目系统属性值信息类
 */

public class CategorySystemPropertyValue extends BaseDomain {

    private String categoryId = null; // 类目ID（子类目ID）

    private SystemPropertyName propertyName = null; // 属性名

    private SystemPropertyValue propertyValue = null; // 属性值

    private String createrId = null; // 创建者

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

    public SystemPropertyName getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(SystemPropertyName propertyName) {
        this.propertyName = propertyName;
    }

    public SystemPropertyValue getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(SystemPropertyValue propertyValue) {
        this.propertyValue = propertyValue;
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
