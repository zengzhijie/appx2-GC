package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.ENTRY_ERROR_EMPTY;

import javax.validation.constraints.NotEmpty;

/**
 * 更新分组状态表单
 */
public class EditGroupForm extends AddGroupForm{
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // ID序列号

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
