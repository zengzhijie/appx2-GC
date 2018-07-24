package com.dreawer.goods.lang;

public enum GroupStatus {
	
	/** 默认或已正常 */
	DEFAULT,
	
	/** 已禁用 */
	DISABLED;
	
    /**
     * 获取分组状态。
     * @param name 状态名称。
     * @return 枚举对象。
     * @author kael
     * @since 1.0
     */
    public static GroupStatus get(String name) {
        for (GroupStatus status : GroupStatus.values()) {
            if (status.toString().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }
}
