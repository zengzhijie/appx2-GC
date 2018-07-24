package com.dreawer.goods.lang;

/**
 * 运费类型
 */
public enum FreightType {

    /**
     * 固定
     **/
	FIXED,

    /**
     * 不固定
     **/
	NOFIXED;


    /**
     * 获取运费类型
     * @param name
     * @return 枚举对象
     */
    public static FreightType get(String name) {
        for (FreightType type : FreightType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}
