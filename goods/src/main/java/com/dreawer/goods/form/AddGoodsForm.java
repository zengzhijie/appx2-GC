package com.dreawer.goods.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.InventoryType;
import com.dreawer.goods.lang.SourceType;
import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.List;

/**
 * 添加商品信息表单
 */
public class AddGoodsForm {
    
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String storeId = null; // 店铺ID
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	@Length(min=1, max=40, message=ENTRY_ERROR_OVERRANGE)
	private String name = null; // 名称
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private String categoryId = null; // 类目ID（子类目）
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private InventoryType inventoryType = null; // 库存类型
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	@Length(min=1, max=1020, message=ENTRY_ERROR_OVERRANGE)
	private String mainDiagram = null; // 主图
	
	@Length(min=1, max=50000, message=ENTRY_ERROR_OVERRANGE)
	private String detail = null; // 详情
	
	@Length(min=1, max=20000, message=ENTRY_ERROR_OVERRANGE)
	private String service = null; // 售后服务
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private GoodsStatus status = null; // 状态
	
	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private Boolean isRecommend = null; //是否推荐
	
	@Length(min=1, max=200, message=ENTRY_ERROR_OVERRANGE)
    private String remark = null; // 备注

	@NotEmpty(message=ENTRY_ERROR_EMPTY)
	private SourceType source = null; // 来源类型
    
	private List<String> groupIds = null; // 分组ID列表
	
	private List<SkuForm> skus = null; // 添加SKU表单列表
	
	private List<GoodsPropertyNameForm> goodsPropertyNames = null; //添加商品属性名表单列表

	private SetFreightParamForm freightParam = null; // 设置运费参数表单
	
	private AppForm app = null; // 小程序应用信息表单
	
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

	public InventoryType getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = InventoryType.get(inventoryType);
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

	public void setStatus(String status) {
		this.status = GoodsStatus.get(status);
	}

	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
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

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public List<SkuForm> getSkus() {
		return skus;
	}

	public void setSkus(List<SkuForm> skus) {
		this.skus = skus;
	}

	public List<GoodsPropertyNameForm> getGoodsPropertyNames() {
		return goodsPropertyNames;
	}

	public void setGoodsPropertyNames(List<GoodsPropertyNameForm> goodsPropertyNames) {
		this.goodsPropertyNames = goodsPropertyNames;
	}

	public void setInventoryType(InventoryType inventoryType) {
		this.inventoryType = inventoryType;
	}

	public void setStatus(GoodsStatus status) {
		this.status = status;
	}

	public void setSource(SourceType source) {
		this.source = source;
	}

	public SetFreightParamForm getFreightParam() {
		return freightParam;
	}

	public void setFreightParam(SetFreightParamForm freightParam) {
		this.freightParam = freightParam;
	}

	public AppForm getApp() {
		return app;
	}

	public void setApp(AppForm app) {
		this.app = app;
	}
	
}
