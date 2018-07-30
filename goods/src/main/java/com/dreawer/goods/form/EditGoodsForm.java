package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加商品信息表单
 */
public class EditGoodsForm extends AddGoodsForm{
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // 商品ID

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
