package com.dreawer.goods.lang;

public enum GoodsStatus {
	
	/** 默认或已上架或已发布 */
	DEFAULT,
	
	/** 未发布 */
	UNPUBLISHED,
	
	/** 已下架 */
	APPLIED,
	
    /** 已移除 **/
	REMOVED;
	
    /**
     * 获取商品状态。
     * @param name 状态名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static GoodsStatus get(String name) {
        for (GoodsStatus status : GoodsStatus.values()) {
            if (status.toString().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
