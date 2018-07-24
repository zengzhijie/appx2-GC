package com.dreawer.goods.lang;

/**
 * 来源类型
 */
public enum SourceType {

    /**
     * APPX
     **/
	APPX,

    /**
     * 零售
     **/
	RETAIL;


    /**
     * 获取来源类型
     * @param name
     * @return 枚举对象
     */
    public static SourceType get(String name) {
        for (SourceType type : SourceType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}
