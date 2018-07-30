package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加分组、商品列表表单
 */
public class AddGroupGoodsesForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String groupId = null; // 分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<String> goodsIds = null; // 商品ID列表
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public List<String> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<String> goodsIds) {
		this.goodsIds = goodsIds;
	}
	
}
