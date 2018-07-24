package com.dreawer.goods.lang;

/**
 * 物流方式类型
 */
public enum LogisticsMethodType {
	
	/** 快递 */
	DELIVERY;
	
    /**
     * 获取物流类型类型。
     * @param name 类型名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static LogisticsMethodType get(String name) {
        for (LogisticsMethodType type : LogisticsMethodType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
