package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 库存操作表单
 */
public class InventoryOperationForm extends PurchaseInfosForm{
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String orderNo = null; // 订单号

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
