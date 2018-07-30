package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.dreawer.goods.lang.GroupStatus;
import com.dreawer.goods.lang.SourceType;
import static com.dreawer.goods.constants.MessageConstants.*;

/**
 * 添加分组信息表单
 */
public class AddGroupForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String storeId = null; // 店铺ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	@Length(min=1, max=40, message=ENTRY_ERROR_OVERRANGE)
	private String name = null; // 名称
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String parentId = null; // 父分组ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private Integer squence = null; // 排列序号
	
	@Length(min=1, max=255, message=ENTRY_ERROR_OVERRANGE)
	private String logo = null; // LOGO
	
	@Length(min=1, max=200, message=ENTRY_ERROR_OVERRANGE)
	private String intro = null; // 简介
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private GroupStatus status = null; // 状态
	
	private String url = null; // 跳转地址（APPX专用）
    
	@Length(min=1, max=200, message=ENTRY_ERROR_OVERRANGE)
    private String remark = null; // 备注

	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private SourceType source = null; // 来源类型

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------
	
	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSquence() {
		return squence;
	}

	public void setSquence(Integer squence) {
		this.squence = squence;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public GroupStatus getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = GroupStatus.get(status);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public SourceType getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = SourceType.get(source);
	}
	
}
