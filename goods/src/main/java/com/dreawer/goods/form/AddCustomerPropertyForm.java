package com.dreawer.category.form;

import javafx.beans.DefaultProperty;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.dreawer.category.constants.MessageConstants.*;

/**
 * 添加客户属性表单
 */
public class AddCustomerPropertyForm {

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String storeId = null; // 店铺ID

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String categoryId = null; // 类目ID（子类目）

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    @Length(min=1, max=40, message=ENTRY_ERROR_OVERRANGE)
    private String name = null; // 名称

    @NotEmpty(message="EntryError.EMPTY")
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

    private List<AddCustomerPropertyValueForm> addPropertyValues = null; // 添加客户属性值列表

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String userId = null; // 用户ID

    @Length(min=1, max=2000, message=ENTRY_ERROR_OVERRANGE)
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
        if(isSearch == null){
            return true;
        }
        return isSearch;
    }

    public void setIsSearch(Boolean isSearch) {
        this.isSearch = isSearch;
    }

    public Boolean getIsRadio() {
        if(isRadio == null){
            return false;
        }
        return isRadio;
    }

    public void setIsRadio(Boolean isRadio) {
        this.isRadio = isRadio;
    }

    public Boolean getIsCheckbox() {
        if(isCheckbox == null){
            return false;
        }
        return isCheckbox;
    }

    public void setIsCheckbox(Boolean isCheckbox) {
        this.isCheckbox = isCheckbox;
    }

    public Boolean getIsInput() {
        if(isInput == null){
            return true;
        }
        return isInput;
    }

    public void setIsInput(Boolean isInput) {
        this.isInput = isInput;
    }

    public Boolean getIsSelect() {
        if(isSelect == null){
            return false;
        }
        return isSelect;
    }

    public void setIsSelect(Boolean isSelect) {
        this.isSelect = isSelect;
    }

    public Boolean getIsVisualEditor() {
        if(isVisualEditor == null){
            return false;
        }
        return isVisualEditor;
    }

    public void setIsVisualEditor(Boolean isVisualEditor) {
        this.isVisualEditor = isVisualEditor;
    }

    public Boolean getIsColor() {
        if(isColor == null){
            return false;
        }
        return isColor;
    }

    public void setIsColor(Boolean isColor) {
        this.isColor = isColor;
    }

    public Boolean getIsRequired() {
        if(isRequired == null){
            return false;
        }
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Boolean getIsSales() {
        if(isSales == null){
            return false;
        }
        return isSales;
    }

    public void setISales(Boolean sales) {
        this.isSales = isSales;
    }

    public Boolean getIsBasic() {
        if(isBasic == null){
            return true;
        }
        return isBasic;
    }

    public void setIsBasic(Boolean isBasic) {
        this.isBasic = isBasic;
    }

    public Boolean getIsKey() {
        if(isKey == null){
            return false;
        }
        return isKey;
    }

    public void setIsKey(Boolean isKey) {
        this.isKey = isKey;
    }

    public List<AddCustomerPropertyValueForm> getAddPropertyValues() {
        return addPropertyValues;
    }

    public void setAddPropertyValues(List<AddCustomerPropertyValueForm> addPropertyValues) {
        this.addPropertyValues = addPropertyValues;
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
