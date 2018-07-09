package com.dreawer.category.lang;

/**
 * 属性值类型
 */
public enum PropertyValueType {

    /**
     * 系统
     **/
    SYSTEM,

    /**
     * 用户
     **/
    CUSTOMER;


    /**
     * 获取属性值类型
     *
     * @param name
     * @return 枚举对象
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
