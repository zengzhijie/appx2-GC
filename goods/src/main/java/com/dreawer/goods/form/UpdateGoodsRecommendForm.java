package com.dreawer.goods.form;

import static com.dreawer.goods.constants.MessageConstants.ENTRY_ERROR_EMPTY;
import javax.validation.constraints.NotEmpty;

/**
 * 更新商品推荐状态表单
 */
public class UpdateGoodsRecommendForm extends UpdateGoodsStatusForm{
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private Boolean isRecommend = null; // 是否推荐

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	
}
