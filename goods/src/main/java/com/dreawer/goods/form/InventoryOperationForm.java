package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 库存操作表单
 */
public class InventoryOperationForm extends PurchaseInfosForm{
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String orderId = null; // 订单ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
