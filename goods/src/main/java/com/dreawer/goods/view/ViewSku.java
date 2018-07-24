package com.dreawer.goods.view;

import java.math.BigDecimal;
import com.dreawer.goods.lang.InventoryType;

/**
 * SKU视图类。
 */
/**
 * @author lenovo
 *
 */
public class ViewSku {
    
	private String id = null; // SKUID
	
	private InventoryType inventoryType = null; // 库存类型
	
	private Integer inventory = null; // 库存
	
	private Integer lockedInventory = null; // 锁定库存数
	
	private Integer purchasableInventory = null; // 可下单库存数
	
	private Integer salesVolume = null; //起售量
	
	private BigDecimal originalPrice = null; // 原价
	
	private BigDecimal price = null; // 售价
	
	private String description = null; // 描述
	
	private String code = null; // 编码

	private String barcode = null; // 条码
	
    private String remark = null; // 备注
    
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewSku() {
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

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Integer getLockedInventory() {
		return lockedInventory;
	}

	public void setLockedInventory(Integer lockedInventory) {
		this.lockedInventory = lockedInventory;
	}

	public Integer getPurchasableInventory() {
		return purchasableInventory;
	}

	public void setPurchasableInventory(Integer purchasableInventory) {
		this.purchasableInventory = purchasableInventory;
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
