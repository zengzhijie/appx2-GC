package com.dreawer.goods.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.LogisticsMethodType;

/**
 * <CODE>LogisticsMethod</CODE> 物流方式信息实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class LogisticsMethod extends BaseDomain{

	private static final long serialVersionUID = -4512807120121464839L;

	private String freightId = null; // 运费模板ID
	
	private Boolean isDefault = null; // 是否默认
	
	private LogisticsMethodType type = null; // 类型
	
	private BigDecimal startQuantity = null; // 起始量 
	
	private BigDecimal startPrice = null; // 起始价格

	private BigDecimal incrementQuantity = null; // 增量
	
	private BigDecimal incrementPrice = null; // 增加价格
	
	private String createrId = null; // 创建者ID
	
	private Date createTime = null; // 创建时间
	
	private List<City> cities= null; // 城市信息列表

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public LogisticsMethod() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
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

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

}
