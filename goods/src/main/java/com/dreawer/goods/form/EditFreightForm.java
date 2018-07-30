package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 编辑运费模板表单
 */
public class EditFreightForm extends AddFreightForm{

    @NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String id = null; // ID序列号
	
	private List<EditLogisticsMethodForm> editLogisticsMethods = null; // 物流方式信息列表

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<EditLogisticsMethodForm> getEditLogisticsMethods() {
		return editLogisticsMethods;
	}

	public void setEditLogisticsMethods(List<EditLogisticsMethodForm> editLogisticsMethods) {
		this.editLogisticsMethods = editLogisticsMethods;
	}
	
}
