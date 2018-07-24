package com.dreawer.goods.form;

import javax.validation.constraints.NotEmpty;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 添加商品信息表单
 */
public class QuerySellingGoodsesForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String storeId = null; // 店铺ID
	
	private String groupId = null; // 分组ID
	
	private Boolean isRecommend = null; // 库存类型
	
	private String categoryId = null; // 主图
	
	private String keyword = null; // 详情
	
	private Integer start = null; // 状态
	
	private Integer pageSize = null; //是否推荐

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
