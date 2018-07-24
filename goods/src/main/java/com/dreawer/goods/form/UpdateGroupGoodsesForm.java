package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

import java.util.List;

/**
 * 更新分组、商品信息列表表单
 */
public class UpdateGroupGoodsesForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private List<UpdateGroupGoodsForm> groupGoodses = null;
	
    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public List<UpdateGroupGoodsForm> getGroupGoodses() {
		return groupGoodses;
	}

	public void setGroupGoodses(List<UpdateGroupGoodsForm> groupGoodses) {
		this.groupGoodses = groupGoodses;
	}

}
