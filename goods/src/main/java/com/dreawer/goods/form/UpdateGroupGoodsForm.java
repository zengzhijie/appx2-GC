package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 更新分组、商品信息表单
 */
public class UpdateGroupGoodsForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String currentGroupId = null; // 当前分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String originalGroupId = null; // 原分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String goodsId = null; // 商品ID
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getCurrentGroupId() {
		return currentGroupId;
	}

	public void setCurrentGroupId(String currentGroupId) {
		this.currentGroupId = currentGroupId;
	}

	public String getOriginalGroupId() {
		return originalGroupId;
	}

	public void setOriginalGroupId(String originalGroupId) {
		this.originalGroupId = originalGroupId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

}
