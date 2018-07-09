package com.dreawer.category.form;

import com.dreawer.category.lang.PropertyNameType;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import static com.dreawer.category.constants.MessageConstants.*;

/**
 * 添加客户属性值表单
 */
public class AddCustomerPropertyValueForm {

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String categoryId = null; // 类目ID

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String storeId = null; // 店铺ID

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    @Length(min=1, max=20000, message=ENTRY_ERROR_OVERRANGE)
    private String name = null; // 名称

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String propertyNameType = null; // 属性名类型

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String propertyNameId = null; // 属性名ID（系统属性或用户自定义属性）

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String userId = null; // 用户ID

    @Length(min=1, max=2000, message=ENTRY_ERROR_OVERRANGE)
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
        return PropertyNameType.get(propertyNameType);
    }

    public void setPropertyNameType(String propertyNameType) {
        this.propertyNameType = propertyNameType;
    }

    public String getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(String propertyNameId) {
        this.propertyNameId = propertyNameId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
