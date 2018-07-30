package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 删除分组信息表单
 */
public class DeleteGroupForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // 分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String storeId = null; // 店铺ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	
}
