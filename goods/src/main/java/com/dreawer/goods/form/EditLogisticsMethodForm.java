package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑物流方式表单
 */
public class EditLogisticsMethodForm extends AddLogisticsMethodForm{
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // ID序列号
	
	private List<EditCityForm> editCities= null; // 添加城市信息表单列表

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<EditCityForm> getEditCities() {
		return editCities;
	}

	public void setEditCities(List<EditCityForm> editCities) {
		this.editCities = editCities;
	}
}
