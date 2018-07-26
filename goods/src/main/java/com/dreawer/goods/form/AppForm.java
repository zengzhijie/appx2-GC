package com.dreawer.goods.form;

/**
 * 小程序应用信息信息表单
 */
public class AppForm {
    
	private String templetId = null; // 模板ID 
	
	private String appCode = null; // 小程序码
	
	private String image = null; // 首页配图

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
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
