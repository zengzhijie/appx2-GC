package com.dreawer.goods.view;

import java.util.List;
import com.dreawer.goods.lang.PropertyNameType;

/**
 * 商品属性名视图类。
 */
public class ViewGoodsPropertyName {
    
	private String id = null; // 商品属性名ID
	
	private String propertyNameId = null; // 属性名ID
	
	private String name = null; // 名称
	
	private String categoryId = null; // 类目ID（子类目）
	
	private PropertyNameType type = null; // 属性名类型（SYSTEM-系统、CUSTOMER-客户）
	
    private Integer squence = null; // 排列序号

    private Boolean isSearch = null; // 是否搜索框

    private Boolean isRadio = null; // 是否单选框

    private Boolean isCheckbox = null; // 是否复选框

    private Boolean isInput = null; // 是否输入框

    private Boolean isSelect = null; // 是否下拉框

    private Boolean isVisualEditor = null; // 是否富文本编辑器

    private Boolean isColor = null; // 是否颜色属性

    private Boolean isRequired = null; // 是否必须属性

    private Boolean isSales = null; // 是否销售属性

    private Boolean isBasic = null; // 是否基础属性

    private Boolean isKey = null; // 是否关键属性
	
	private Boolean isImage = null; // 是否图片属性
	
	private List<ViewGoodsPropertyValue> propertyValues = null; // 商品属性值列表
    
    // --------------------------------------------------------------------------------
    // 构造器
    // --------------------------------------------------------------------------------

    /**
     * 默认构造器。
     */
    public ViewGoodsPropertyName() {
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

	public String getPropertyNameId() {
		return propertyNameId;
	}

	public void setPropertyNameId(String propertyNameId) {
		this.propertyNameId = propertyNameId;
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

	public PropertyNameType getType() {
		return type;
	}

	public void setType(PropertyNameType type) {
		this.type = type;
	}

	public Integer getSquence() {
		return squence;
	}

	public void setSquence(Integer squence) {
		this.squence = squence;
	}

	public Boolean getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}

	public Boolean getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(Boolean isRadio) {
		this.isRadio = isRadio;
	}

	public Boolean getIsCheckbox() {
		return isCheckbox;
	}

	public void setIsCheckbox(Boolean isCheckbox) {
		this.isCheckbox = isCheckbox;
	}

	public Boolean getIsInput() {
		return isInput;
	}

	public void setIsInput(Boolean isInput) {
		this.isInput = isInput;
	}

	public Boolean getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(Boolean isSelect) {
		this.isSelect = isSelect;
	}

	public Boolean getIsVisualEditor() {
		return isVisualEditor;
	}

	public void setIsVisualEditor(Boolean isVisualEditor) {
		this.isVisualEditor = isVisualEditor;
	}

	public Boolean getIsColor() {
		return isColor;
	}

	public void setIsColor(Boolean isColor) {
		this.isColor = isColor;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}

	public Boolean getIsSales() {
		return isSales;
	}

	public void setIsSales(Boolean isSales) {
		this.isSales = isSales;
	}

	public Boolean getIsBasic() {
		return isBasic;
	}

	public void setIsBasic(Boolean isBasic) {
		this.isBasic = isBasic;
	}

	public Boolean getIsKey() {
		return isKey;
	}

	public void setIsKey(Boolean isKey) {
		this.isKey = isKey;
	}

	public Boolean getIsImage() {
		return isImage;
	}

	public void setIsImage(Boolean isImage) {
		this.isImage = isImage;
	}

	public List<ViewGoodsPropertyValue> getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(List<ViewGoodsPropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}
	
}
