package com.dreawer.category.lang;

/**
 * 属性名类型
 */
public enum PropertyNameType {

    /**
     * 系统
     **/
    SYSTEM,

    /**
     * 用户
     **/
    CUSTOMER;


    /**
     * 获取属性名类型
     *
     * @param name
     * @return 枚举对象
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
