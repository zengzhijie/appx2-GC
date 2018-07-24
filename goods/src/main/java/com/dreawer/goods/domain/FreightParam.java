package com.dreawer.goods.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.FreightType;


/**
 * <CODE>FreightParam</CODE> 运费参数信息实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class FreightParam extends BaseDomain{

	private static final long serialVersionUID = -2966777869743758740L;

	private String goodsId = null; // 商品ID
	
	private FreightType type = null; // 运费类型
	
	private BigDecimal price = null; // 价格(固定运费需要填写)
	
	private String freightId = null; // 运费ID
	
	private String amount = null; // 体积或重量
	
	private String createrId = null; // 创建者ID
	
	private Date createTime = null; // 创建时间
	
	private String updaterId = null; // 更新者ID

	private Date updateTime = null; // 更新时间

    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public FreightParam() {
        super();
    }

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

	public void setType(FreightType type) {
		this.type = type;
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

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
