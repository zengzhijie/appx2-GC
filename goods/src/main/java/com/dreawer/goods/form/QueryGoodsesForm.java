package com.dreawer.goods.form;

import com.dreawer.goods.lang.GoodsStatus;

/**
 * 添加商品信息表单
 */
public class QueryGoodsesForm extends QuerySellingGoodsesForm{
    
	private GoodsStatus status = null; // 商品状态
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = GoodsStatus.get(status);
	}

}
