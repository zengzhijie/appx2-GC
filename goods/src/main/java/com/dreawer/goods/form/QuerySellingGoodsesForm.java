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
	
	private Boolean isRecommend = null; // 是否推荐
	
	private String categoryId = null; // 类目ID
	
	private String keyword = null; // 模糊查询关键字
	
	private Integer pageNo = null; // 当前页码
	
	private Integer pageSize = null; // 每页显示记录数

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

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
