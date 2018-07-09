package com.dreawer.category.form;

import javax.validation.constraints.NotEmpty;
import java.util.List;

import static com.dreawer.category.constants.MessageConstants.ENTRY_ERROR_EMPTY;

/**
 * 删除客户属性表单
 */
public class DeleteCustomerPropertyForm {

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    private String propertyNameId = null; // 属性名ID

    public String getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(String propertyNameId) {
        this.propertyNameId = propertyNameId;
    }
}
