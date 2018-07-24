package com.dreawer.goods.domain;

import java.util.Date;

import com.dreawer.domain.BaseDomain;

/**
 * <CODE>City</CODE> 城市信息实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class City extends BaseDomain{

	private static final long serialVersionUID = 8977646339482803087L;

	private String logisticsMethodId = null; // 物流方式ID
	
	private String name = null; // 名称 
	
	private String parentId = null; // 父级ID
	
	private String cityId = null; // 城市ID
	
	private String createrId = null; // 创建者ID
	
	private Date createTime = null; // 创建时间
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public City() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getLogisticsMethodId() {
		return logisticsMethodId;
	}

	public void setLogisticsMethodId(String logisticsMethodId) {
		this.logisticsMethodId = logisticsMethodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
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

}
