package com.dreawer.goods.lang;

/**
 * 支付方
 */
public enum Payer {
	
	/** 买家 */
	BUYER,
	
	/** 卖家 */
	SELLER;
	
    /**
     * 获取支付方。
     * @param name 名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static Payer get(String name) {
        for (Payer payer : Payer.values()) {
            if (payer.toString().equalsIgnoreCase(name)) {
                return payer;
            }
        }
        return null;
    }
}
