package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 编辑城市信息表单
 */
public class EditCityForm extends AddCityForm{
    
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
