package com.dreawer.goods.domain;

import java.sql.Timestamp;
import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.InventoryStatus;

/**
 * <CODE>InventoryOperationDetail</CODE> 库存操作明细实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class InventoryOperationDetail extends BaseDomain {

	private static final long serialVersionUID = 2498633554049066213L;

	private String orderId = null; // 订单ID
	
	private String skuId = null; // SKUID
	
	private Integer inventory = null; // 操作库存数
	
	private InventoryStatus status = null; // 库存状态
	
	private String createrId = null; // 创建者ID
    
	private Timestamp createTime = null; // 创建时间
    
    private String updaterId = null; // 更新者ID

    private Timestamp updateTime = null; // 更新时间
    
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public InventoryOperationDetail() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
