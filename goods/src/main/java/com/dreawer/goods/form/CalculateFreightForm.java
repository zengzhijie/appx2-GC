package com.dreawer.goods.form;

import com.dreawer.goods.lang.PurchaseInfo;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 计算运费表单
 */
public class CalculateFreightForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<PurchaseInfo> purchaseInfos = null; // 购买信息
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String cityId = null; // 城市ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public List<PurchaseInfo> getPurchaseInfos() {
		return purchaseInfos;
	}

	public void setPurchaseInfos(List<PurchaseInfo> purchaseInfos) {
		this.purchaseInfos = purchaseInfos;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
}
