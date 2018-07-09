package com.dreawer.category.view;

import com.dreawer.category.lang.PropertyNameType;
import com.dreawer.category.lang.PropertyValueType;

/**
 * 类目属性值视图类
 */
public class ViewCategoryPropertyValue {

    private String id = null; // ID序列号

    private String storeId = null; // 店铺ID

    private String categoryId = null; // 类目ID（子类目）

    private String name = null; // 名称

    private PropertyValueType type = null; // 是否搜索框

    private String remark = null; // 备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PropertyValueType getType() {
        return type;
    }

    public void setType(PropertyValueType type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}