package com.dreawer.goods.lang;

/**
 * <CODE>PurchaseInfo</CODE> 购买信息实体。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class PurchaseInfo{

	private String goodsId = null; // 商品ID
	
	private String skuId = null; // SKUID
	
	private Integer quantity = null; // 购买数量 

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
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
