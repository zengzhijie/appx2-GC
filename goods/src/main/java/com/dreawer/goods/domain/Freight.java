package com.dreawer.goods.domain;

import java.util.Date;
import java.util.List;
import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.Payer;
import com.dreawer.goods.lang.PricingMethod;


/**
 * <CODE>Freight</CODE> 运费模板信息实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class Freight extends BaseDomain{

	private static final long serialVersionUID = -5573587781945670398L;

	private String storeId = null; // 店铺ID
	
	private String name = null; // 名称
	
	private String deliveryAddress = null; // 发货地址

	private String deliveryTime = null; // 发货时间
	
	private Payer payer = null; // 支付方
	
	private PricingMethod pricingMethod = null; // 计价方式
	
	private String createrId = null; // 创建者
	
	private Date createTime = null; // 创建时间
	
	private String updaterId = null; // 更新者

	private Date updateTime = null; // 更新时间

	private List<LogisticsMethod> logisticsMethods = null; // 添加物流方式信息表单列表

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public Freight() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
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

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<LogisticsMethod> getLogisticsMethods() {
		return logisticsMethods;
	}

	public void setLogisticsMethods(List<LogisticsMethod> logisticsMethods) {
		this.logisticsMethods = logisticsMethods;
	}
	
}
