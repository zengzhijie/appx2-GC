package com.dreawer.goods.domain;

import com.dreawer.domain.BaseDomain;

/**
 * <CODE>App</CODE> 小程序应用信息实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class App extends BaseDomain{

	private static final long serialVersionUID = -677501746031597509L;

	private String goodsId = null; // 商品ID
	
	private String templetId = null; // 模板ID 
	
	private String appCode = null; // 小程序码
	
	private String image = null; // 首页配图
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public App() {
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

	public String getTempletId() {
		return templetId;
	}

	public void setTempletId(String templetId) {
		this.templetId = templetId;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
    
}
