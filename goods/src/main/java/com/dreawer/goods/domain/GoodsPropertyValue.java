package com.dreawer.goods.domain;

import java.util.Date;

import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.PropertyNameType;
import com.dreawer.goods.lang.PropertyValueType;

/**
 * <CODE>GoodsPropertyValue</CODE> 商品属性值实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class GoodsPropertyValue extends BaseDomain {

	private static final long serialVersionUID = -341318430799561466L;
	
	private String goodsPropertyNameId = null; // 商品属性名ID
	
	private String propertyValueId = null; // 属性值ID
	
	private String propertyNameId = null; // 属性名ID
	
	private String goodsId = null; // 商品ID
	
	private PropertyNameType propertyNameType = null; // 属性名类型（SYSTEM-系统、CUSTOMER-客户）
	
	private PropertyValueType type = null; // 属性值类型（SYSTEM-系统、CUSTOMER-客户）
	
    private Integer squence = null; // 排列序号
	
	private String name = null; // 名称
	
	private Boolean isDefaultImage = null; // 是否默认图片
	
	private String imageUrl = null; // 图片地址
	
	private String createrId = null; // 创建者ID
	
	private Date createTime = null; // 创建时间
	
	private String remark = null; // 备注
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public GoodsPropertyValue() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public String getGoodsPropertyNameId() {
		return goodsPropertyNameId;
	}

	public void setGoodsPropertyNameId(String goodsPropertyNameId) {
		this.goodsPropertyNameId = goodsPropertyNameId;
	}
    
	public String getPropertyValueId() {
		return propertyValueId;
	}

	public void setPropertyValueId(String propertyValueId) {
		this.propertyValueId = propertyValueId;
	}

	public String getPropertyNameId() {
		return propertyNameId;
	}

	public void setPropertyNameId(String propertyNameId) {
		this.propertyNameId = propertyNameId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public PropertyNameType getPropertyNameType() {
		return propertyNameType;
	}

	public void setPropertyNameType(PropertyNameType propertyNameType) {
		this.propertyNameType = propertyNameType;
	}

	public PropertyValueType getType() {
		return type;
	}

	public void setType(PropertyValueType type) {
		this.type = type;
	}

	public Integer getSquence() {
		return squence;
	}

	public void setSquence(Integer squence) {
		this.squence = squence;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsDefaultImage() {
		return isDefaultImage;
	}

	public void setIsDefaultImage(Boolean isDefaultImage) {
		this.isDefaultImage = isDefaultImage;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
