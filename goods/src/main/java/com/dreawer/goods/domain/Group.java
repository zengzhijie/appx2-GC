package com.dreawer.goods.domain;

import java.sql.Timestamp;
import java.util.List;

import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.GroupStatus;
/**
 * <CODE>Group</CODE> 分组实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class Group extends BaseDomain {
    
	private static final long serialVersionUID = 2489442656007445610L;

	private String storeId = null; // 店铺ID
	
	private String name = null; // 名称
	
	private String parentId = null; // 父分组ID
	
	private Boolean isParent = null; // 是否父分组
	
	private Integer squence = null; // 排列序号
	
	private String logo = null; // LOGO
	
	private String intro = null; // 简介
	
	private GroupStatus status = null; // 状态
	
	private String url = null; // 跳转地址（APPX专用）
	
	private Integer goodsQuantity = null; // 商品数量
	
	private String createrId = null; // 创建者ID
    
    private Timestamp createTime = null; // 创建时间
    
    private String updaterId = null; // 更新者ID

    private Timestamp updateTime = null; // 更新时间
    
    private String remark = null; // 备注
    
    private List<Goods> goodses = null; // 商品列表
    
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public Group() {
        super();
    }

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

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
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

	public void setStatus(GroupStatus status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getGoodsQuantity() {
		return goodsQuantity;
	}

	public void setGoodsQuantity(Integer goodsQuantity) {
		this.goodsQuantity = goodsQuantity;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Goods> getGoodses() {
		return goodses;
	}

	public void setGoodses(List<Goods> goodses) {
		this.goodses = goodses;
	}
	
}
