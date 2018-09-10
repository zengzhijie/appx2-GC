package com.dreawer.goods.form;

import com.dreawer.goods.lang.LogisticsMethodType;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 添加物流方式表单
 */
public class AddLogisticsMethodForm {
	
	private Boolean isDefault = null; // 是否默认
	
	@NotNull(message=ENTRY_ERROR_EMPTY)
	private LogisticsMethodType type = null; // 类型
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal startQuantity = null; // 起始量 
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal startPrice = null; // 起始价格

	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal incrementQuantity = null; // 增量
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal incrementPrice = null; // 增加价格
	
	private Integer squence = null; // 排列序号
	
	private List<AddCityForm> addCities= null; // 添加城市信息表单列表
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public LogisticsMethodType getType() {
		return type;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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

	public List<AddCityForm> getAddCities() {
		return addCities;
	}

	public void setAddCities(List<AddCityForm> addCities) {
		this.addCities = addCities;
	}
	
}
