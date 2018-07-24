package com.dreawer.goods.lang;

/**
 * 属性值类型
 */
public enum PropertyValueType {
	
	/** 系统 */
	SYSTEM,
	
	/** 客户 */
	CUSTOMER;
	
    /**
     * 获取属性值类型。
     * @param name 类型名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static PropertyValueType get(String name) {
        for (PropertyValueType type : PropertyValueType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
}
