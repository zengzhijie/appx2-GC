package com.dreawer.category.form;

import javax.validation.constraints.NotEmpty;

import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_EMPTY;

/**
 * 删除客户属性值表单
 */
public class DeleteCustomerPropertyValueForm {

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String propertyValueId = null; // 属性值ID

    public String getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(String propertyValueId) {
        this.propertyValueId = propertyValueId;
    }
}
