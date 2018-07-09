package com.dreawer.category.form;

import com.dreawer.category.lang.PropertyNameType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_EMPTY;
import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_OVERRANGE;

/**
 * 编辑客户属性值表单
 */
public class EditCustomerPropertyValueForm extends AddCustomerPropertyValueForm{

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String id = null; // ID序列号

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
