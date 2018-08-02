package com.dreawer.goods.view;

import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.InventoryType;

/**
 * 购买信息详情视图类。
 */
public class ViewCartDetail extends ViewPurchaseDetail{
    
	private GoodsStatus status = null; // 商品状态
	
	private Integer Inventory = null; // 库存
	
	private Integer salesVolume = null; // 起售量
	
	private InventoryType inventoryType = null; // 库存状态
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewCartDetail() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public Integer getInventory() {
		return Inventory;
	}

	public void setInventory(Integer inventory) {
		Inventory = inventory;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}
	
}
