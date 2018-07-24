package com.dreawer.goods.constants;

/**
 * <CODE>ControllerConstants</CODE> 控制器层常量类。
 * 该类用于定义“请求链接”的代码规范性常量，以统一工程中有关于对象、属性名称的代码规范。
 * @author kael
 */
public final class ControllerConstants {
    
    /**
     * 私有构造器。
     */
    private ControllerConstants() {
    }
    
    // --------------------------------------------------------------------------------
    // 请求地址
    // --------------------------------------------------------------------------------
  
    /** 请求“运费” */
    public static final String REQ_FREIGHT = "/freight";
    
    /** 请求“分组” */
    public static final String REQ_GROUP = "/group";
    
    /** 请求“添加” */
    public static final String REQ_ADD = "/add";
    
    /** 请求“编辑” */
    public static final String REQ_EDIT = "/edit";
    
    /** 请求“删除” */
    public static final String REQ_DELETE = "/delete";

    /** 请求“列表” */
    public static final String REQ_LIST = "/list";
    
    /** 请求“批量删除” */
    public static final String REQ_DELETE_BATCH = "/deleteBatch";
    
    /** 请求“详情列表” */
    public static final String REQ_DETAILS = "/details";
    
    /** 请求“详情” */
    public static final String REQ_DETAIL = "/detail";
    
    /** 请求“设置参数” */
    public static final String REQ_SET_PARAM = "/setParam";

    /** 请求“参数” */
    public static final String REQ_PARAM = "/param";

    /** 请求“计算” */
    public static final String REQ_CACULATE = "/calculate";
    
    /** 请求“模糊查询列表” */
    public static final String REQ_SUGGEST = "/suggest";

    /** 请求“属性” */
    public static final String REQ_PROPERTY = "/property";
    
    /** 请求“添加商品列表” */
    public static final String REQ_ADD_GOODSES = "/addGoodses";
    
    /** 请求“更新商品列表” */
    public static final String REQ_UPDATE_GOODSES = "/updateGoodses";
    
    /** 请求“更新排序” */
    public static final String REQ_UPDATE_SQUENCE = "/updateSquence";
    
    /** 请求“更新状态” */
    public static final String REQ_UPDATE_STATUS = "/updateStatus";
    
    /** 请求“数量” */
    public static final String REQ_COUNT = "/count";
    
    /** 请求“商品分组列表” */
    public static final String REQ_GOODS_GROUPS = "/goodsGroups";
    
    /** 请求“商品” */
    public static final String REQ_GOODS = "/goods";
    
    /** 请求“绑定的商品（不单独销售）” */
    public static final String REQ_BUNDLE_GOODS = "/bundleGoods";
    
    /** 请求“上架” */
    public static final String REQ_SHELVE = "/shelve";
    
    /** 请求“下架” */
    public static final String REQ_APPLY = "/apply";
    
    /** 请求“移除” */
    public static final String REQ_REMOVE = "/remove";

    /** 请求“更新推荐状态” */
    public static final String REQ_UPDATE_RECOMMEND = "/updateRecommend";
    
    /** 请求“正销售商品列表” */
    public static final String REQ_SELLING_LIST = "/SellingList";

    /** 请求“购买信息详情” */
    public static final String REQ_PURCHASE_DETAIL = "/purchaseDetail";
    
    /** 请求“扣减库存” */
    public static final String REQ_LOCK_INVENTORY = "/lockInventory";
    
    /** 请求“锁定库存” */
    public static final String REQ_RELEASE_INVENTORY = "/releaseInventory";
    
    /** 请求“释放库存” */
    public static final String REQ_DEDUCTION_INVENTORY = "/deductionBatchInventory";
    

    
    
    /** 请求“包装” */
    public static final String REQ_PACKING = "/packing";
    

    
    /** 请求“查询能否生成订单” */
    public static final String ORDER_QUERY = "/order/query";
    
    /** 请求“查询sku” */
    public static final String SKU_QUERY = "/sku/query";
    
    /** 请求“查询sku详情” */
    public static final String SKU_DETAIL = "/sku/detail";
    
    /** 请求“查询sku列表” */
    public static final String SKU_LIST = "/sku/list";
    
    /** 请求“查询sku排序” */
    public static final String SKU_SORT = "/sku/sort";
    
    /** 请求“查询sku排序” */
    public static final String PROPERTY_SCREENING = "/property/screening";
    

    
    /** 请求“添加值” */
    public static final String ADD_VALUE = "/addValue";
    
    /** 请求“更新店铺分类、商品关系” */
    public static final String UPDATE_GROUP = "/updateGroup";
    

    
    /** 请求“上架” */
    public static final String SHELF = "/shelf";
    
    /** 请求“下架” */
    public static final String APPLIED = "/applied";
    
    /** 请求“移除” */
    public static final String REMOVE = "/remove";
    

    
    /** 请求“客户” */
    public static final String CUSTOMER = "/customer";
    
    /** 请求“列表” */
    public static final String NAMES = "/names";
    
    /** 请求“已删除列表” */
    public static final String REMOVED_LIST = "/removedList";
    
    /** 请求“值列表” */
    public static final String VALUE_LIST = "/valueList";
    
    /** 请求“模糊查询值列表” */
    public static final String VALUE_SUGGEST = "/valueSuggest";
    
    /** 请求“名称” */
    public static final String REQ_NAME = "/name";
    
    /** 请求“详情” */
    public static final String REQ_QUERY = "/query";
    
    /** 请求“删除校验” */
    public static final String REQ_DELETE_VALID = "/deleteValid";
    
    /** 请求“删除校验” */
    public static final String REQ_CUSTOM_LIST = "/custom/list";
    // --------------------------------------------------------------------------------
    // 其他常量
    // --------------------------------------------------------------------------------
    
    /** 用户请求 URL */
    public static final String REQ_URL = "requestUrl";
    
    /** 错误信息 */
    public static final String ERRORS = "errors";
    
    /** 错误信息 */
    public static final String ERROR = "error";
    
}
