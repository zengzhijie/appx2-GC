package com.dreawer.goods.utils;

import java.io.Serializable;
import java.util.List;

public class OrderPacket implements Serializable{

	private static final long serialVersionUID = 2754566770369863383L;

	private String orderId = null; // 订单id

	private String userId = null; // 用户id

	private String storeId = null; // 店铺id

	private String promotionId = null; // 活动id

	private String coupon = null; // 优惠券id

	private List<GoodsPacket> goods = null; // 商品信息

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public List<GoodsPacket> getGoods() {
		return goods;
	}

	public void setGoods(List<GoodsPacket> goods) {
		this.goods = goods;
	}
	
	
}
