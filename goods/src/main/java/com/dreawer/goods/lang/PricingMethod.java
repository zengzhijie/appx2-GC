package com.dreawer.goods.lang;


/**
 * 计价方式
 */
public enum PricingMethod {
	
	/** 件数 */
	NUMBER,
	
	/** 重量 */
	WEIGHT,
	
	/** 体积 */
	VOLUME;
	
    /**
     * 获取计价方式。
     * @param name 名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static PricingMethod get(String name) {
        for (PricingMethod pricingMethod : PricingMethod.values()) {
            if (pricingMethod.toString().equalsIgnoreCase(name)) {
                return pricingMethod;
            }
        }
        return null;
    }
}
