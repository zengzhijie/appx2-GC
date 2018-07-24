package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

import java.util.List;

/**
 * 购买信息列表表单
 */
public class PurchaseInfosForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<PurchaseInfoForm> purchaseInfoForms = null;

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public List<PurchaseInfoForm> getPurchaseInfoForms() {
		return purchaseInfoForms;
	}

	public void setPurchaseInfoForms(List<PurchaseInfoForm> purchaseInfoForms) {
		this.purchaseInfoForms = purchaseInfoForms;
	}
	
}
