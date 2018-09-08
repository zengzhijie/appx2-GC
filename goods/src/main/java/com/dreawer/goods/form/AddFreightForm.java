package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.dreawer.goods.lang.Payer;
import com.dreawer.goods.lang.PricingMethod;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 添加运费模板表单
 */
public class AddFreightForm {

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
    @Length(min=1, max=50, message=ENTRY_ERROR_OVERRANGE)
	private String name = null; // 名称
	
    @Length(min=1, max=255, message=ENTRY_ERROR_OVERRANGE)
	private String deliveryAddress = null; // 发货地址

    @Length(min=1, max=10, message=ENTRY_ERROR_OVERRANGE)
	private String deliveryTime = null; // 发货时间
	
    @NotNull(message=ENTRY_ERROR_EMPTY)
	private Payer payer = null; // 支付方
	
    @NotNull(message=ENTRY_ERROR_EMPTY)
	private PricingMethod pricingMethod = null; // 计价方式
	
	private List<AddLogisticsMethodForm> addLogisticsMethods = null; // 物流方式信息列表

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = Payer.get(payer);
	}

	public PricingMethod getPricingMethod() {
		return pricingMethod;
	}

	public void setPricingMethod(String pricingMethod) {
		this.pricingMethod = PricingMethod.get(pricingMethod);
	}

	public List<AddLogisticsMethodForm> getAddLogisticsMethods() {
		return addLogisticsMethods;
	}

	public void setAddLogisticsMethods(List<AddLogisticsMethodForm> addLogisticsMethods) {
		this.addLogisticsMethods = addLogisticsMethods;
	}
	
}
