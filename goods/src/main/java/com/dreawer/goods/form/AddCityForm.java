package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 添加城市信息表单
 */
public class AddCityForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	@Length(min=1, max=40, message=ENTRY_ERROR_OVERRANGE)
	private String name = null; // 名称
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String parentId = null; // 父级ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String cityId = null; // 城市ID

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
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
