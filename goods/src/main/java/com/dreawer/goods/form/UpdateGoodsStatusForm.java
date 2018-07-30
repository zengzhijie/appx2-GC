package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 更新商品状态表单
 */
public class UpdateGoodsStatusForm {
    
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
