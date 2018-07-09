package com.dreawer.category.domain;

import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;

/**
 * <CODE>SystemPropertyValue</CODE>
 * 系统属性值信息实体类
 */

public class SystemPropertyValue extends BaseDomain {

    private String name = null; // 名称

    private SystemPropertyName propertyName = null; // 属性名

    private String createrId = null ; // 创建者

    private Timestamp createTime = null; // 创建时间

    private String updaterId = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    private String remark = null; // 备注

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SystemPropertyName getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(SystemPropertyName propertyName) {
        this.propertyName = propertyName;
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
