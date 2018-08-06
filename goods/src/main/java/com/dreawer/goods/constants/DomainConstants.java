package com.dreawer.goods.constants;

/**
 * <CODE>DAOConstants</CODE> 领域模型常量。
 * 该类用于定义“对象属性”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 * @author kael
 */
public abstract class DomainConstants {

    // --------------------------------------------------------------------------------
    // 对象
    // --------------------------------------------------------------------------------
    
	/** 运费模板 */
    public static final String FREIGHT = "freight";
	
    /** 运费模板列表 */
    public static final String FREIGHTS = "freights";
	
	/** 分组 */
    public static final String GROUP = "group";
	
	/** 分组、商品信息列表 */
    public static final String GROUP_GOODSES = "groupGoodses";
    
	/** 商品 */
    public static final String GOODS = "goods";
    
	/** 商品列表 */
    public static final String GOODSES = "goodses";
    
    /** 属性名 */
    public static final String PROPERTY_NAME = "propertyName";

    /** 属性值 */
    public static final String PROPERTY_VALUE = "propertyValue";
    
	/** SKU */
    public static final String SKU = "sku";
    
	/** SKU列表 */
    public static final String SKUS = "skus";
    
	/** 计价方式方式 */
    public static final String PRICING_METHOD = "pricingMethod";
    
	/** 物流方式 */
    public static final String LOGISTICS_METHOD = "logisticsMethod";
    
	/** 物流方式列表 */
    public static final String LOGISTICS_METHODS = "logisticsMethods";
    
	/** 城市信息 */
    public static final String CITY = "city";
    
	/** 城市信息列表 */
    public static final String CITIES = "cities";
    
    /** 商品属性名列表 */
    public static final String GOODS_PROPERTY_NAMES = "goodsPropertyNames";
    
    /** 商品属性值列表 */
    public static final String GOODS_PROPERTY_VALUES = "goodsPropertyValues";
    
    /** 分组列表 */
    public static final String GROUPS = "groups";
    
	/** 小程序应用信息 */
    public static final String APP = "app";
    
    /** 库存处理明细列表 */
    public static final String INVENTORY_OPERATION_DETAILS = "inventoryOperationDetails";
    
    
    // --------------------------------------------------------------------------------
    // 属性
    // --------------------------------------------------------------------------------
    
    /** 店铺ID */
    public static final String STORE_ID = "storeId";
    
    /** 商品ID */
    public static final String GOODS_ID = "goodsId";
    
    /** 商品ID列表 */
    public static final String GOODS_IDS = "goodsIds";
    
    /** 描述 */
    public static final String DESCRIPTION = "description";
    
    /** 父ID */
    public static final String PARENT_ID = "parentId";

    /** ID号 */
    public static final String ID = "id";

    /** ID列表 */
    public static final String IDS = "ids";

    /** 类目ID */
    public static final String CATEGORY_ID = "categoryId";

    /** 分组ID */
    public static final String GROUP_ID = "groupId";

    /** 分组名称 */
    public static final String GROUP_NAME = "groupName";
    
    /** 类型 */
    public static final String TYPE = "type";
    
    /** 状态 */
    public static final String STATUS = "status";
    
    /** 推荐状态 */
    public static final String IS_RECOMMEND = "isRecommend";
    
    /** 属性名ID */
    public static final String PROPERTY_NAME_ID = "propertyNameId";

    /** 属性名类型 */
    public static final String PROPERTY_NAME_TYPE = "propertyNameType";
    
    /** 属性名排列序号 */
    public static final String PROPERTY_NAME_SQUENCE = "propertyNameSquence";
    
    /** 属性值ID */
    public static final String PROPERTY_VALUE_ID = "propertyValueId";
    
    /** 创建者ID */
    public static final String CREATER_ID = "createrId";
    
    /** 创建时间 */
    public static final String CREATE_TIME = "createTime";
    
    /** 是否父分组 */
    public static final String IS_PARENT = "isParent";
    
    /** SKU描述 */
    public static final String SKU_DESCRIPTION = "skuDescription";
    
    /** 锁定库存数 */
    public static final String LOCKED_INVENTORY = "lockedInventory";
    
    /** SKUID */
    public static final String SKU_ID = "skuId";
    
    /** 订单ID */
    public static final String ORDER_ID = "orderId";
    
	/** 物流方式类型 */
    public static final String LOGISTICS_METHOD_TYPE = "logisticsMethodType";
    
	/** 物流方式名称 */
    public static final String LOGISTICS_METHOD_NAME = "logisticsMethodName";
    
	/** 物流方式“是否默认”属性 */
    public static final String LOGISTICS_METHOD_IS_DEFAULT = "logisticsMethodIsDefault";
    
	/** 物流方式起始量 */
    public static final String LOGISTICS_METHOD_START_QUANTITY = "logisticsMethodStartQuantity";
    
	/** 物流方式起始价格 */
    public static final String LOGISTICS_METHOD_START_PRICE = "logisticsMethodStartPrice";
    
	/** 物流方式增量 */
    public static final String LOGISTICS_METHOD_INCREMENT_QUANTITY = "logisticsMethodIncrementQuantity";
    
	/** 物流方式则增加价格 */
    public static final String LOGISTICS_METHOD_INCREMENT_PRICE = "logisticsMethodIncrementPrice";
    
	/** 城市ID */
    public static final String CITY_ID = "cityId";
    
	/** 城市名称 */
    public static final String CITY_NAME = "cityName";
    
    /** 名称 */
    public static final String NAME = "name";

    /** 价格 */
    public static final String PRICE = "price";
    
	/** 运费模板ID */
    public static final String FREIGHT_ID = "freightId";
    
	/** 量 */
    public static final String AMOUNT = "amount";
    
    /** 数量 */
    public static final String QUANTITY = "quantity";
    
    /** 跳转地址 */
    public static final String URL = "url";
    
    /** 当前分组ID */
    public static final String CURRENT_GROUP_ID = "currentGroupId";
    
    /** 原分组ID */
    public static final String ORIGINAL_GROUP_ID = "originalGroupId";
    
    /** 属性值名称 */
    public static final String PROPERTY_VALUE_NAME = "propertyValueName";

    /** 属性值备注 */
    public static final String PROPERTY_VALUE_REMARK = "propertyValueRemark";
    
	/** 销售属性 */
    public static final String SALES_PROPERTY = "salesProperty";
    
	/** 库存 */
    public static final String INVENTORY = "inventory";
    
	/** 原价 */
    public static final String ORIGINAL_PRICE = "originalPrice";
    
    /** 备注 */
    public static final String REMARK = "remark";
    
    /** 条码 */
    public static final String BARCODE = "barcode";
    
    /** 编码 */
    public static final String CODE = "code";
    
    /** 起售量 */
    public static final String SALES_VOLUME = "salesVolumes";
    
    /** 详情 */
    public static final String DETAIL = "detail";
    
    /** 服务 */
    public static final String SERVICE = "service";
    
    /** 是否搜索 */
    public static final String IS_SEARCH = "isSearch";
    
    /** 是否单选框 */
    public static final String IS_RADIO = "isRadio";
    
    /** 是否复选框 */
    public static final String IS_CHECKBOX = "isCheckbox";
    
    /** 是否输入框 */
    public static final String IS_INPUT = "isInput";
    
    /** 是否下拉框 */
    public static final String IS_SELECT = "isSelect";
    
    /** 是否文本编辑器 */
    public static final String IS_VISUAL_EDITOR = "isVisualEditor";
    
    /** 是否颜色属性 */
    public static final String IS_COLOR = "isColor";
    
    /** 是否必须属性 */
    public static final String IS_REQUIRED = "isRequired";
    
    /** 是否销售属性 */
    public static final String IS_SALES = "isSales";
    
    /** 是否基础属性 */
    public static final String IS_BASIC = "isBasic";
    
    /** 是否关键属性 */
    public static final String IS_KEY = "isKey";
    
    /** 是否图片属性 */
    public static final String IS_IMAGE = "isImage";
    
    /** 属性值类型 */
    public static final String PROPERTY_VALUE_TYPE = "propertyValueType";
    
    /** 属性值排列序号 */
    public static final String PROPERTY_VALUE_SQUENCE = "propertyValueSquence";
    
	/** 用户ID */
    public static final String USER_ID = "userId";
    
    
    
    
    
	/** 模板ID */
    public static final String TEMPLET_ID = "templetId";
    
	/** 小程序码 */
    public static final String APP_CODE = "appCode";
    
	/** 图片 */
    public static final String IMAGE = "image";
    
    // --------------------------------------------------------------------------------
    // 分页属性
    // --------------------------------------------------------------------------------
    
    /** 分页参数 */
    public static final String PAGE_PARAM = "pageParam";
    
    /** 分页起始 */
    public static final String START = "start";
    
    /** 页面记录数 */
    public static final String PAGE_SIZE = "pageSize";
    
    /** 总记录数 */
    public static final String TOTAL_SIZE = "totalSize";
    
    /** 总页数 */
    public static final String TOTAL_PAGE = "totalPage";
    
    /** 页码数 */
    public static final String PAGE_NO = "pageNo";
    
    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------

    /** 关键字 */
    public static final String KEYWORD = "keyword";

    /** 错误 */
    public static final String ERROR = "error";
    
}
