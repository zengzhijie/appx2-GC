package com.dreawer.goods.lang;

/**
 * 属性名类型
 */
public enum PropertyNameType {
	
	/** 系统 */
	SYSTEM,
	
	/** 客户 */
	CUSTOMER;
	
    /**
     * 获取属性名类型。
     * @param name 类型名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static PropertyNameType get(String name) {
        for (PropertyNameType type : PropertyNameType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
