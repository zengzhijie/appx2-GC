package com.dreawer.goods.view;

import com.dreawer.goods.lang.PropertyNameType;
import com.dreawer.goods.lang.PropertyValueType;

/**
 * 商品属性值视图类。
 */
public class ViewGoodsPropertyValue {
    
	private String id = null; // 商品属性值ID
	
	private String propertyValueId = null; // 属性值ID
	
	private String propertyNameId = null; // 属性名ID
	
	private String goodsId = null; // 商品ID
	
	private PropertyNameType propertyNameType = null; // 属性名类型（SYSTEM-系统、CUSTOMER-客户）
	
	private PropertyValueType type = null; // 属性值类型（SYSTEM-系统、CUSTOMER-客户）
	
    private Integer squence = null; // 排列序号
	
	private String name = null; // 名称
	
	private Boolean isDefaultImage = null; // 是否默认图片
	
	private String imageUrl = null; // 图片地址
	
	private String remark = null; // 备注
    
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewGoodsPropertyValue() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
