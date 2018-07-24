package com.dreawer.goods.view;

/**
 * 运费参数信息视图对象
 */
public class ViewCity {

	private String id = null; // ID序列号
	
	private String name = null; // 名称 
	
	private String parentId = null; // 父级ID
	
	private String cityId = null; // 城市ID

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

}