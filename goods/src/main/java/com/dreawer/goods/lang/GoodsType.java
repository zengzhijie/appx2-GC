package com.dreawer.goods.lang;

/**
 * 运费类型
 */
public enum GoodsType {

    /**
     * 默认或单独销售
     **/
	DEFAULT,

    /**
     * 绑定销售或仅支持绑定销售
     **/
	BUNDLED;


    /**
     * 获取运费类型
     * @param name
     * @return 枚举对象
     */
    public static GoodsType get(String name) {
        for (GoodsType type : GoodsType.values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }

}
