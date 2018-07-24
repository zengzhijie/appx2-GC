package com.dreawer.goods.form;

import com.dreawer.goods.lang.FreightType;
import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

import java.math.BigDecimal;

/**
 * 设置物流参数表单
 */
public class SetFreightParamForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String goodsId = null; // 商品ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private FreightType type = null; // 运费类型
	
	private BigDecimal price = null; // 价格

	private String freightId = null; // 运费模板ID
	
	private String amount = null; // 重量或体积

	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String userId = null; // 用户ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public FreightType getType() {
		return type;
	}

	public void setType(String type) {
		this.type = FreightType.get(type);
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFreightId() {
		return freightId;
	}

	public void setFreightId(String freightId) {
		this.freightId = freightId;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
