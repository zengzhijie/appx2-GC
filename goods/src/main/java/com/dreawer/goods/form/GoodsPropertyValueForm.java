package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.dreawer.goods.lang.PropertyValueType;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 添加商品属性值信息表单
 */
public class GoodsPropertyValueForm {
    
	private String id = null; // 商品属性值ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String propertyValueId = null; // 属性值ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
    @Length(min=1, max=40, message=ENTRY_ERROR_OVERRANGE)
	private String name = null; // 名称
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private PropertyValueType type = null; // 属性值类型（SYSTEM-系统、CUSTOMER-客户）
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
    private Integer squence = null; // 排列序号

	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private Boolean isDefaultImage = null; // 是否默认图片

	private String imageUrl = null; // 图片地址

    @Length(min=1, max=255, message=ENTRY_ERROR_OVERRANGE)
	private String remark = null; // 备注
    
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
