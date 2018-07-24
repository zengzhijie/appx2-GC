package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;

/**
 * 删除运费模板表单
 */
public class DeleteFreightForm{
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<String> freightIds= null; // 运费模板ID列表

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public List<String> getFreightIds() {
		return freightIds;
	}

	public void setFreightIds(List<String> freightIds) {
		this.freightIds = freightIds;
	}
	
}
