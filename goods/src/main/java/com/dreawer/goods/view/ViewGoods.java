package com.dreawer.goods.view;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.InventoryType;

/**
 * 商品视图类。
 */
public class ViewGoods {
    
	private String id = null; // 商品ID
	
	private String storeId = null; // 店铺ID
	
	private String name = null; // 名称
	
	private String categoryId = null; // 类目ID（子类目）
	
	private BigDecimal minPrice = null; // 最低售价
	
	private BigDecimal minOriginalPrice = null; // 最低原价
	
	private InventoryType inventoryType = null; // 库存类型
	
	private Integer totalInventory = null; // 总库存
	
	private Integer totalLockedInventory = null; // 锁定总库存
	
	private Integer totalPurchasableInventory = null; // 可下单总库存
	
	private String mainDiagram = null; // 主图
	
	private String detail = null; // 详情
	
	private String service = null; // 售后服务
	
	private GoodsStatus status = null; // 状态
	
	private Boolean isRecommend = null; //是否推荐
	
	private String createrId = null; // 创建者ID
    
	private Timestamp createTime = null; // 创建时间
    
	private String remark = null; // 备注
	
	private List<ViewGroup> groups = null; // 分组列表
	
	private List<ViewSku> skus = null; // SKU列表
	
	private List<ViewGoodsPropertyName> propertyNames = null; //商品属性名列表
    
	private ViewApp viewApp = null; // 小程序应用信息视图
	
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewGoods() {
        super();
    }

    // --------------------------------------------------------------------------------
    // getter 和 setter 方法
    // --------------------------------------------------------------------------------

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Integer getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Integer totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Integer getTotalLockedInventory() {
		return totalLockedInventory;
	}

	public void setTotalLockedInventory(Integer totalLockedInventory) {
		this.totalLockedInventory = totalLockedInventory;
	}

	public Integer getTotalPurchasableInventory() {
		return totalPurchasableInventory;
	}

	public void setTotalPurchasableInventory(Integer totalPurchasableInventory) {
		this.totalPurchasableInventory = totalPurchasableInventory;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public List<ViewGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<ViewGroup> groups) {
		this.groups = groups;
	}

	public List<ViewSku> getSkus() {
		return skus;
	}

	public void setSkus(List<ViewSku> skus) {
		this.skus = skus;
	}

	public List<ViewGoodsPropertyName> getPropertyNames() {
		return propertyNames;
	}

	public void setPropertyNames(List<ViewGoodsPropertyName> propertyNames) {
		this.propertyNames = propertyNames;
	}

	public ViewApp getViewApp() {
		return viewApp;
	}

	public void setViewApp(ViewApp viewApp) {
		this.viewApp = viewApp;
	}
    
}
