package com.dreawer.goods.lang;

/**
 * 库存类型
 */
public enum InventoryType {
	
	/** 无限制 */
	UNLIMITED,
	
	/** 有限制 */
	LIMITED;
	
    /**
     * 获取库存类型。
     * @param name 类型名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static InventoryType get(String name) {
        for (InventoryType type : InventoryType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
