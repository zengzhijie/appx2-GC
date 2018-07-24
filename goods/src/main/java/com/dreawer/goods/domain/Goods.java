package com.dreawer.goods.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.dreawer.domain.BaseDomain;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.GoodsType;
import com.dreawer.goods.lang.InventoryType;

/**
 * <CODE>Goods</CODE> 商品实体类。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
public class Goods extends BaseDomain {

	private static final long serialVersionUID = 8302038744388373288L;

	private String storeId = null; // 店铺ID
	
	private String name = null; // 名称
	
	private String categoryId = null; // 类目ID（子类目）
	
	private BigDecimal minPrice = null; // 最低售价
	
	private BigDecimal minOriginalPrice = null; // 最低原价
	
	private InventoryType inventoryType = null; // 库存类型
	
	private String mainDiagram = null; // 主图
	
	private String detail = null; // 详情
	
	private String service = null; // 售后服务
	
	private GoodsType type = null; // 商品类型
	
	private GoodsStatus status = null; // 状态
	
	private Boolean isRecommend = null; //是否推荐
	
	private String createrId = null; // 创建者ID
    
	private Timestamp createTime = null; // 创建时间
    
    private String updaterId = null; // 更新者ID

    private Timestamp updateTime = null; // 更新时间
    
    private String remark = null; // 备注
    
	private List<Group> groups = null; // 分组列表
	
	private List<Sku> skus = null; // SKU列表
	
	private List<GoodsPropertyName> propertyNames = null; //商品属性名列表
    
	private FreightParam freightParam = null; // 运费参数
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public Goods() {
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(BigDecimal minPrice) {
		this.minPrice = minPrice;
	}

	public BigDecimal getMinOriginalPrice() {
		return minOriginalPrice;
	}

	public void setMinOriginalPrice(BigDecimal minOriginalPrice) {
		this.minOriginalPrice = minOriginalPrice;
	}

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public String getMainDiagram() {
		return mainDiagram;
	}

	public void setMainDiagram(String mainDiagram) {
		this.mainDiagram = mainDiagram;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public GoodsType getType() {
		return type;
	}

	public void setType(GoodsType type) {
		this.type = type;
	}

	public GoodsStatus getStatus() {
		return status;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
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
	
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

	public List<GoodsPropertyName> getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(List<GoodsPropertyName> propertyNames) {
		this.propertyNames = propertyNames;
	}

	public FreightParam getFreightParam() {
		return freightParam;
	}

	public void setFreightParam(FreightParam freightParam) {
		this.freightParam = freightParam;
	}
	
}
