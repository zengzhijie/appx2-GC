package com.dreawer.goods.view;

import java.util.Date;
import java.util.List;
import com.dreawer.goods.lang.Payer;
import com.dreawer.goods.lang.PricingMethod;

/**
 * 运费模板信息视图对象
 */
public class ViewFreight {

	private String id = null; // ID序列号
	
	private String storeId = null; // 店铺ID
	
	private String name = null; // 名称
	
	private String deliveryAddress = null; // 发货地址

	private String deliveryTime = null; // 发货时间
	
	private Payer payer = null; // 支付方
	
	private PricingMethod pricingMethod = null; // 计价方式
	
	private String createrId = null; // 创建者
	
	private Date createTime = null; // 创建时间
	
	private List<ViewLogisticsMethod> logisticsMethods = null; // 物流方式信息列表

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public PricingMethod getPricingMethod() {
		return pricingMethod;
	}

	public void setPricingMethod(PricingMethod pricingMethod) {
		this.pricingMethod = pricingMethod;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<ViewLogisticsMethod> getLogisticsMethods() {
		return logisticsMethods;
	}

	public void setLogisticsMethods(List<ViewLogisticsMethod> logisticsMethods) {
		this.logisticsMethods = logisticsMethods;
	}

}