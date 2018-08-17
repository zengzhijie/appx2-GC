package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更新商品状态表单
 */
public class GoodsRecoverForm extends UpdateGoodsStatusForm{
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String groupId = null; // 分组ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
