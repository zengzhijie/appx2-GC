package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

import java.util.List;

/**
 * 添加商品信息表单
 */
public class DeleteGoodsForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<String> ids = null; // 商品ID列表

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
