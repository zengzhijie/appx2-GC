package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

/**
 * SKU信息表单
 */
public class SkuForm {
    
	private String id = null; // SKUID
	
	@NotNull(message=ENTRY_ERROR_EMPTY)
	private Integer inventory = null; // 库存
	
	@NotNull(message=ENTRY_ERROR_EMPTY)
	private Integer salesVolume = null; //起售量
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal originalPrice = null; // 原价
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private BigDecimal price = null; // 售价
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String description = null; // 描述
	
	private String code = null; // 编码

	private String barcode = null; // 条码
	
	@Length(min=1, max=200, message=ENTRY_ERROR_OVERRANGE)
    private String remark = null; // 备注

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public Integer getInventory() {
		return inventory;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
