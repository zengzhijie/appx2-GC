package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加分组、商品信息表单
 */
public class AddGroupGoodsForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String groupId = null; // 分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String goodsId = null; // 商品ID
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}
