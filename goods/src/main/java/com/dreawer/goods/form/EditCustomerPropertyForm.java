package com.dreawer.category.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_EMPTY;
import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_OVERRANGE;

/**
 * 编辑客户属性表单
 */
public class EditCustomerPropertyForm extends AddCustomerPropertyForm{

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String id = null; // ID序列号

    private List<EditCustomerPropertyValueForm>  editPropertyValues = null; // 编辑客户属性值列表

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<EditCustomerPropertyValueForm> getEditPropertyValues() {
        return editPropertyValues;
    }

    public void setEditPropertyValues(List<EditCustomerPropertyValueForm> editPropertyValues) {
        this.editPropertyValues = editPropertyValues;
    }
}
