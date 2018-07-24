package com.dreawer.goods.lang;

/**
 * 库存状态
 */
public enum InventoryStatus {
	
	/** 已锁定 */
	LOCKED,
	
	/** 已释放 */
	RELEASED,
	
	/** 已扣减 */
	DEDUCTED;
	
    /**
     * 获取库存状态。
     * @param name 状态名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static InventoryStatus get(String name) {
        for (InventoryStatus status : InventoryStatus.values()) {
            if (status.toString().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
