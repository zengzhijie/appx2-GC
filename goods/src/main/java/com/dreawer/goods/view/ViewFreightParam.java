package com.dreawer.goods.view;

import java.math.BigDecimal;

import com.dreawer.goods.lang.FreightType;

/**
 * 运费参数信息视图对象
 */
public class ViewFreightParam {

	private String goodsId = null; // 商品ID
	
	private FreightType type = null; // 运费类型
	
	private BigDecimal price = null; // 价格(固定运费需要填写)
	
	private String freightId = null; // 运费ID
	
	private String amount = null; // 量（重量或体积）

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public FreightType getType() {
		return type;
	}

	public void setType(FreightType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}