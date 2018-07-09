package com.dreawer.category.domain;

import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;

/**
 * <CODE>CategorySystemPropertyName</CODE>
 * 类目系统属性名信息类
 */

public class CategorySystemPropertyName extends BaseDomain {

    private String categoryId = null; // 类目ID（子类目ID）

    private SystemPropertyName propertyName = null; // 属性名

    private Integer squence = null; // 排列序号

    private Boolean isRequired = null; // 是否必须属性

    private Boolean isSales = null; // 是否销售属性

    private Boolean isBasic = null; // 是否基础属性

    private Boolean isKey = null; // 是否关键属性

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

    public Integer getSquence() {
        return squence;
    }

    public void setSquence(Integer squence) {
        this.squence = squence;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Boolean getIsSales() {
        return isSales;
    }

    public void setIsSales(Boolean sales) {
        this.isSales = isSales;
    }

    public Boolean getIsBasic() {
        return isBasic;
    }

    public void setIsBasic(Boolean isBasic) {
        this.isBasic = isBasic;
    }

    public Boolean getIsKey() {
        return isKey;
    }

    public void setIsKey(Boolean isKey) {
        this.isKey = isKey;
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
