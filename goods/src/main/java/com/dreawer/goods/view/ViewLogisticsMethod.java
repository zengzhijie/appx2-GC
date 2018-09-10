package com.dreawer.goods.view;

import java.math.BigDecimal;
import java.util.List;
import com.dreawer.goods.lang.LogisticsMethodType;

/**
 * 物流方式信息视图对象
 */
public class ViewLogisticsMethod {

	private String id = null; // ID序列号
	
	private Boolean isDefault = null; // 是否默认
	
	private LogisticsMethodType type = null; // 类型
	
	private BigDecimal startQuantity = null; // 起始量 
	
	private BigDecimal startPrice = null; // 起始价格

	private BigDecimal incrementQuantity = null; // 增量
	
	private BigDecimal incrementPrice = null; // 增加价格

	private Integer squence = null; // 排列序号
	
	private List<ViewCity> cities= null; // 城市信息列表

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public LogisticsMethodType getType() {
		return type;
	}

	public void setType(LogisticsMethodType type) {
		this.type = type;
	}

	public BigDecimal getStartQuantity() {
		return startQuantity;
	}

	public void setStartQuantity(BigDecimal startQuantity) {
		this.startQuantity = startQuantity;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		this.startPrice = startPrice;
	}

	public BigDecimal getIncrementQuantity() {
		return incrementQuantity;
	}

	public void setIncrementQuantity(BigDecimal incrementQuantity) {
		this.incrementQuantity = incrementQuantity;
	}

	public BigDecimal getIncrementPrice() {
		return incrementPrice;
	}

	public void setIncrementPrice(BigDecimal incrementPrice) {
		this.incrementPrice = incrementPrice;
	}

	public Integer getSquence() {
		return squence;
	}

	public void setSquence(Integer squence) {
		this.squence = squence;
	}

	public List<ViewCity> getCities() {
		return cities;
	}

	public void setCities(List<ViewCity> cities) {
		this.cities = cities;
	}

}