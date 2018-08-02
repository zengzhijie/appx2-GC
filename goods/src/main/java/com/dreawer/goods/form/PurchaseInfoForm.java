package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 购买信息表单
 */
public class PurchaseInfoForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String spuId = null; // 商品ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String skuId = null; // SKUID
	
	@NotNull(message=ENTRY_ERROR_EMPTY)
	private Integer quantity = null; // 购买数量 

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getSpuId() {
		return spuId;
	}

	public void setSpuId(String spuId) {
		this.spuId = spuId;
	}
	
	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
