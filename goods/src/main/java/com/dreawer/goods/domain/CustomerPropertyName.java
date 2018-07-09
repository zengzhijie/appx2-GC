package com.dreawer.category.domain;

import com.dreawer.domain.BaseDomain;

import java.sql.Timestamp;
import java.util.List;

/**
 * <CODE>CustomerPropertyName</CODE>
 * 客户属性名信息实体类
 */
public class CustomerPropertyName extends BaseDomain {

    private String storeId = null; // 店铺ID

    private String categoryId = null; // 类目ID（子类目）

    private String name = null; // 名称

    private Integer squence = null; // 排列序号

    private Boolean isSearch = null; // 是否搜索框

    private Boolean isRadio = null; // 是否单选框

    private Boolean isCheckbox = null; // 是否复选框

    private Boolean isInput = null; // 是否输入框

    private Boolean isSelect = null; // 是否下拉框

    private Boolean isVisualEditor = null; // 是否富文本编辑器

    private Boolean isColor = null; // 是否颜色属性

    private Boolean isRequired = null; // 是否必须属性

    private Boolean isSales = null; // 是否销售属性

    private Boolean isBasic = null; // 是否基础属性

    private Boolean isKey = null; // 是否关键属性

    private String createrId = null ; // 创建者

    private Timestamp createTime = null; // 创建时间

    private String updaterId = null; // 更新者

    private Timestamp updateTime = null; // 更新时间

    private List<CustomerPropertyValue> propertyValues = null;

    private String remark = null; // 备注

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

    public Integer getSquence() {
        return squence;
    }

    public void setSquence(Integer squence) {
        this.squence = squence;
    }

    public Boolean getIsSearch() {
        return isSearch;
    }

    public void setIsSearch(Boolean isSearch) {
        this.isSearch = isSearch;
    }

    public Boolean getIsRadio() {
        return isRadio;
    }

    public void setIsRadio(Boolean isRadio) {
        this.isRadio = isRadio;
    }

    public Boolean getIsCheckbox() {
        return isCheckbox;
    }

    public void setIsCheckbox(Boolean isCheckbox) {
        this.isCheckbox = isCheckbox;
    }

    public Boolean getIsInput() {
        return isInput;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }

    public Boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Boolean getIsVisualEditor() {
        return isVisualEditor;
    }

    public void setIsVisualEditor(Boolean isVisualEditor) {
        this.isVisualEditor = isVisualEditor;
    }

    public Boolean getIsColor() {
        return isColor;
    }

    public void setIsColor(Boolean isColor) {
        this.isColor = isColor;
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

    public List<CustomerPropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<CustomerPropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
