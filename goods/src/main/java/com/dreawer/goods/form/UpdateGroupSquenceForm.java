package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更新分组排列序号表单
 */
public class UpdateGroupSquenceForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // 分组ID
	
	@NotNull(message=ENTRY_ERROR_EMPTY)
	private Integer squence = null; // 排列序号
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSquence() {
		return squence;
	}

	public void setSquence(Integer squence) {
		this.squence = squence;
	}

}
