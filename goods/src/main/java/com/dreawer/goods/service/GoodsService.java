package com.dreawer.goods.service;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dreawer.goods.domain.Freight;
import com.dreawer.goods.domain.FreightParam;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.goods.domain.GoodsPropertyValue;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.domain.Sku;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.GoodsType;
import com.dreawer.goods.lang.InventoryType;
import com.dreawer.goods.lang.PricingMethod;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.persistence.AppDao;
import com.dreawer.goods.persistence.FreightDao;
import com.dreawer.goods.persistence.FreightParamDao;
import com.dreawer.goods.persistence.GoodsDao;
import com.dreawer.goods.persistence.GoodsPropertyNameDao;
import com.dreawer.goods.persistence.GoodsPropertyValueDao;
import com.dreawer.goods.persistence.GroupDao;
import com.dreawer.goods.persistence.SkuDao;
import com.dreawer.goods.view.ViewCartDetail;
import com.dreawer.goods.view.ViewGoods;
import com.dreawer.goods.view.ViewPurchaseDetail;
import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.GCRuleError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.RuleError;
import com.dreawer.responsecode.rcdt.StatusError;
import com.dreawer.responsecode.rcdt.Success;

/**
 * <CODE>GoodsService</CODE> 商品Service。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
@Service
public class GoodsService extends BaseService{

    @Autowired
    private GoodsDao goodsDao; // 商品信息DAO
    
    @Autowired
    private GroupDao groupDao; // 分组信息DAO
    
    @Autowired
    private SkuDao skuDao; // SKU信息DAO
    
    @Autowired
    private GoodsPropertyNameDao goodsPropertyNameDao; // 商品属性名信息DAO
    
    @Autowired
    private GoodsPropertyValueDao goodsPropertyValueDao; // 商品属性值信息DAO
    
    @Autowired
    private FreightDao freightDao; // 运费模板信息DAO
    
    @Autowired
    private FreightParamDao freightParamDao; // 运费参数信息DAO
    
    @Autowired
    private AppDao appDao; // 小程序应用信息DAO
    
    /**
     * 添加商品信息。
     * @param goods 待添加的商品信息（商品信息、SKU列表、商品属性名信息、商品属性值信息、分组信息）。
     * @return 成功返回商品ID、失败返回相应的错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode save(Goods goods){
    	
    	//添加商品信息
    	goodsDao.save(goods);
    	
    	//添加分组列表
    	if(goods.getGroups() != null && goods.getGroups().size()>0){
        	
    		//获取分组列表
        	List<Group> groups = goods.getGroups();
        	
        	//创建集合封装分组、商品信息列表
        	List<Map<String, Object>> groupGoodses = new ArrayList<>();
        	
        	//循环分组列表
        	for (Group group : groups) {
        		
        		//判断该分组是否存在
        		Group findGroup = groupDao.findGroup(group.getId());
        		if(findGroup == null){
        			return RuleError.NON_EXISTENT(GROUP_ID+":"+group.getId());
        		}
        		
    			//创建集合封装分组、商品信息
    			Map<String, Object> groupGoods = new HashMap<>();
    			groupGoods.put(GROUP_ID, group.getId());
    			groupGoods.put(GOODS_ID, goods.getId());
    			groupGoods.put(CREATER_ID, goods.getCreaterId());
    			groupGoods.put(CREATE_TIME, goods.getCreateTime());
        		
    			//添加到List集合中
    			groupGoodses.add(groupGoods);
    			
        	}
        	
        	//执行添加
        	groupDao.saveBatchGroupGoods(groupGoodses);
        	
        	//增加分组中商品数（商品数加一）
        	groupDao.increaseGoodsQuantity(goods.getId());
    	}
    	
    	//添加SKU列表
    	if(goods.getSkus() != null && goods.getSkus().size()>0){
    		
    		//获取SKU列表
    		List<Sku> skus = goods.getSkus();
    		
    		//批量添加SKU
    		skuDao.saveBatch(skus);
    		
    	}
    	
    	//添加商品属性名列表
    	if(goods.getPropertyNames() != null && goods.getPropertyNames().size() > 0){
    		
    		//获取属性名列表
    		List<GoodsPropertyName> propertyNames = goods.getPropertyNames();
    		
    		//创建List集合用来接收属性值列表
    		List<GoodsPropertyValue> propertyValues = new ArrayList<>(); 
    		
    		//批量添加属性名信息
    		goodsPropertyNameDao.saveBatch(propertyNames);
    		
    		//循环属性名列表
    		for (GoodsPropertyName goodsPropertyName : propertyNames) {
    			if(goodsPropertyName.getPropertyValues() != null && goodsPropertyName.getPropertyValues().size() >0){
    				
    				//将属性值列表添加到List集合中
    				propertyValues.addAll(goodsPropertyName.getPropertyValues());
    			}
			}
    		
    		//批量添加属性值列表
    		goodsPropertyValueDao.saveBatch(propertyValues);
    	}
    	
    	//获取物流参数信息
    	FreightParam freightParam = goods.getFreightParam();
    	
    	if(freightParam != null){
    		//获取商品ID
    		String goodsId = freightParam.getGoodsId();
    		
    		//获取体积或重量
    		String amount = freightParam.getAmount();
    		
    		//查询运费模板信息
    		if(StringUtils.isNotEmpty(freightParam.getFreightId())){
    			Freight freight = freightDao.findFreightById(freightParam.getFreightId());
        		
        		if(freight == null){
        			return RuleError.NON_EXISTENT(FREIGHT_ID+":"+freightParam.getFreightId());
        		}
        		
        		//判断运费模板计价方式
        		if(!freight.getPricingMethod().equals(PricingMethod.NUMBER) && amount == null){
        			return EntryError.EMPTY(AMOUNT);
        		}
    		}
    		
    		//删除运费参数信息
    		freightParamDao.delete(goodsId);
    		
    		//添加运费参数信息
    		freightParamDao.save(freightParam);
    	}
		
		//判断小程序应用信息是否为空
		if(goods.getApp() != null){
			
			//删除APP信息
			appDao.delete(goods.getId());
			
			//执行添加
			appDao.save(goods.getApp());
		}
		
    	//返回添加结果
    	return Success.SUCCESS(goods.getId());
    }
    
    /**
     * 批量删除商品信息。
     * @param ids 待删除的商品ID列表。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode deleteBatch(List<String> ids){

    	//循环ID列表
    	for (String id : ids) {
			
    		//查询商品信息
    		Goods goods = goodsDao.findGoodsDetailById(id, null);
    		
    		//判断商品是否为REMOVED状态或下架状态
    		if(!goods.getStatus().equals(GoodsStatus.REMOVED)){
    			return GCRuleError.NOTALLOW_DELETE_NON_RECOVERY(GOODS_ID+":"+id); // 未进入回收站的商品不允许删除
    		}
    		
    		//获取SKU列表
    		List<Sku> skus = goods.getSkus();
    		
    		//循环SKU列表
    		for (Sku sku : skus) {
    			
    			//判断sku库存状态
    			if(sku.getLockedInventory() != 0){
    				return GCRuleError.NOTALLOW_DELETE_LOCKED(GOODS_ID+":"+id); // 有锁定库存的商品不允许删除
    			}
			}
		}
    	
    	//批量删除商品
    	goodsDao.deleteBatch(ids);
    	
    	//删除小程序应用模板信息
    	appDao.deleteBatch(ids);
    	
    	//返回删除结果
    	return Success.SUCCESS;
    	
    }
    
    /**
     * 批量上架商品。
     * @param goodses 待更新的商品列表（商品ID、商品上架状态-DEFAULT、更新者ID、更新时间）。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode shelveGoodses(List<Goods> goodses){
    	
    	//执行更新
    	goodsDao.updateBatchStatus(goodses);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 批量下架商品。
     * @param goodses 待更新的商品列表（商品ID、商品下架状态-APPLIED、更新者ID、更新时间）。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode applyGoodses(List<Goods> goodses){
    	
    	//执行更新
    	goodsDao.updateBatchStatus(goodses);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 批量移除商品。
     * @param goodses 待更新的商品列表（商品ID、商品移除状态-REMOVED、更新者ID、更新时间）。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode removeGoodses(List<Goods> goodses){
    	
    	//创建List集合封装商品ID
    	List<String> goodsIds = new ArrayList<>();
    	
    	//循环商品列表
    	for (Goods goods : goodses) {
			
    		//查询商品信息
    		Goods findGoods = goodsDao.findGoodsByIdForUpdate(goods.getId());
    		
    		//判断商品信息是否存在
    		if(findGoods == null){
    			return RuleError.NON_EXISTENT(GOODS_ID+":"+goods.getId());
    		}
    		
    		//判断商品是否为状态或下架状态
    		if(findGoods.getStatus().equals(GoodsStatus.DEFAULT)){
    			
    			return GCRuleError.NOTALLOW_RECOVERY_NON_DOWN(GOODS_ID+":"+goods.getId()); // 未下架的商品不允许移除
    		}else if(findGoods.getStatus().equals(GoodsStatus.APPLIED)){
    			
    			//封装商品ID
        		goodsIds.add(goods.getId());
    		}
    		
    		
		}
    	
    	//批量删除分组、商品关系
    	groupDao.deleteBatchGroupGoods(goodsIds);
    	
		//批量减少分组中的商品数量
		groupDao.reduceBatchGoodsQuantity(goodsIds);
    	
    	//执行更新
    	goodsDao.updateBatchStatus(goodses);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 批量恢复商品。
     * @param goodses 待更新的商品列表（商品ID、商品下架状态-APPLIED、更新者ID、更新时间）。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode recoverGoodses(List<Goods> goodses, String groupId){
    	
    	//判断分组是否存在
    	Group group = groupDao.findGroup(groupId);
    	if(group == null){
    		return RuleError.NON_EXISTENT(GROUP);
    	}
    	
    	//封装请求参数
    	List<Map<String, Object>> groupGoodses = new ArrayList<>();
    	for (Goods goods : goodses) {
			Map<String, Object> groupGoods = new HashMap<>();
			groupGoods.put(GROUP_ID, groupId);
			groupGoods.put(GOODS_ID, goods.getId());
			groupGoods.put(CREATER_ID, goods.getUpdaterId());
			groupGoods.put(CREATE_TIME, goods.getUpdateTime());
			groupGoodses.add(groupGoods);
		}
    	
    	//执行更新
    	goodsDao.updateBatchStatus(goodses);
    	groupDao.saveBatchGroupGoods(groupGoodses);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 批量更新商品推荐状态。
     * @param goodses 待更新的商品列表（商品ID、商品推荐状态、更新者ID、更新时间）。
     * @return 成功返回成功码，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode updateBatchRecommend(List<Goods> goodses){
    	
    	//执行更新
    	goodsDao.updateBatchRecommend(goodses);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 更新商品信息。
     * @param goods 待更新的商品信息（商品信息、SKU列表、商品属性名信息、商品属性值信息、分组信息）。
     * @return 成功返回商品ID、失败返回相应的错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode update(Goods goods){
    	
    	//判断该商品信息是否存在
    	Goods findGoods = goodsDao.findGoodsByIdForUpdate(goods.getId());
    	if(findGoods != null){
        	//更新商品信息
        	goodsDao.update(goods);
        	
        	//更新分组列表
        	if(goods.getGroups() != null && goods.getGroups().size()>0){
            	
        		//删除原分组、商品列表
        		List<String> goodsIds = new ArrayList<>();
        		goodsIds.add(goods.getId());
        		groupDao.deleteBatchGroupGoods(goodsIds);
        		
        		//减少原分组中的商品数
        		groupDao.reduceBatchGoodsQuantity(goodsIds);
        		
        		//获取分组列表
            	List<Group> groups = goods.getGroups();
            	
            	//创建集合封装分组、商品信息列表
            	List<Map<String, Object>> groupGoodses = new ArrayList<>();
            	
            	//循环分组列表
            	for (Group group : groups) {
            		
            		//判断该分组是否存在
            		Group findGroup = groupDao.findGroup(group.getId());
            		if(findGroup == null){
            			return RuleError.NON_EXISTENT(GROUP_ID+":"+group.getId());
            		}
            		
        			//创建集合封装分组、商品信息
        			Map<String, Object> groupGoods = new HashMap<>();
        			groupGoods.put(GROUP_ID, group.getId());
        			groupGoods.put(GOODS_ID, goods.getId());
        			groupGoods.put(CREATER_ID, goods.getUpdaterId());
        			groupGoods.put(CREATE_TIME, goods.getUpdateTime());
            		
        			groupGoodses.add(groupGoods);
        			
            	}
            	
            	//执行添加
            	groupDao.saveBatchGroupGoods(groupGoodses);
            	
            	//增加分组中商品数（商品数加一）
            	groupDao.increaseGoodsQuantity(goods.getId());
        	}
        	
        	//更新SKU列表
        	if(goods.getSkus() != null && goods.getSkus().size() > 0){
        		
        		//获取原SKU列表
        		List<Sku> orginalSkus = skuDao.findSkus(goods.getId());
        		
        		//获取SKU列表
        		List<Sku> skus = goods.getSkus();
        		
        		//创建Set集合，接收原SKU列表和当前SKU列表中相同ID的SKU信息
        		Set<Sku> CommonSkuSet = new HashSet<>();
        		
        		//循环原SKU列表
        		for (Sku orginalSku : orginalSkus) {
					
        			//循环当前SKU列表
        			for (Sku sku : skus) {
						
        				//比对两集合中的相同元素
        				if(orginalSku.getId().equals(sku.getId())){
        					
        					//将锁定库存数添加到当前SKU实体类中
        					sku.setLockedInventory(orginalSku.getLockedInventory());
        					
        					//比对两集合中SKU的库存(当前SKU库存小于原SKU库存，并且小于锁定库存数)
        					if(sku.getInventory() < orginalSku.getLockedInventory()){
        						
        						return GCRuleError.LOWER_LOCKING_NUMBER(SKU_ID+":"+sku.getId()); // 修改后的库存小于锁定库存数，不允许修改
        					}
        					
        					CommonSkuSet.add(orginalSku);
        				}
					}
				}
        		
        		
        		//比对共同SKU集合和原SKU集合的长度
        		List<Sku> CommonSkus = new ArrayList<>();
        		for (Sku sku : CommonSkuSet) {
        			CommonSkus.add(sku);
				}
        		
        		if(CommonSkus.size() <= orginalSkus.size()){
        			orginalSkus.removeAll(CommonSkus); // 获取被删除的SKU列表
        		}
        		
        		//循环被删除的SKU列表
        		for (Sku orginalSku : orginalSkus) {
					
        			//判断被删除的SKU是否还有锁定库存
        			if(orginalSku.getLockedInventory() != 0){
        				
						return GCRuleError.NOTALLOW_DELETE_LOCKED(SKU_ID+":"+orginalSku.getId()); // 有锁定库存，不允许删除
        			}
				}
        		
        		//删除原SKU列表
        		skuDao.deleteByGoodsId(goods.getId());
        		
        		//批量添加SKU
        		skuDao.saveBatch(skus);
        		
        	}
        	
        	//添加商品属性名列表
        	if(goods.getPropertyNames() != null && goods.getPropertyNames().size() >0){
        		
        		//删除原商品属性名和属性值列表
        		goodsPropertyNameDao.deleteByGoodsId(goods.getId());
        		
        		//获取属性名列表
        		List<GoodsPropertyName> propertyNames = goods.getPropertyNames();
        		
        		//创建List集合用来接收属性值列表
        		List<GoodsPropertyValue> propertyValues = new ArrayList<>(); 
        		
        		//批量添加属性名信息
        		goodsPropertyNameDao.saveBatch(propertyNames);
        		
        		//循环属性名列表
        		for (GoodsPropertyName goodsPropertyName : propertyNames) {
        			if(goodsPropertyName.getPropertyValues() != null && goodsPropertyName.getPropertyValues().size() >0){
        				
        				//将属性值列表添加到List集合中
        				propertyValues.addAll(goodsPropertyName.getPropertyValues());
        			}
    			}
        		
        		//批量添加属性值列表
        		goodsPropertyValueDao.saveBatch(propertyValues);
        	}
    	}
    	
    	//获取物流参数信息
    	FreightParam freightParam = goods.getFreightParam();
    	
    	if(freightParam != null){
    		//获取商品ID
    		String goodsId = freightParam.getGoodsId();
    		
    		//获取体积或重量
    		String amount = freightParam.getAmount();
    		
    		//查询运费模板信息
    		if(StringUtils.isNotEmpty(freightParam.getFreightId())){
    			Freight freight = freightDao.findFreightById(freightParam.getFreightId());
        		
        		if(freight == null){
        			return RuleError.NON_EXISTENT(FREIGHT_ID+":"+freightParam.getFreightId());
        		}
        		
        		//判断运费模板计价方式
        		if(!freight.getPricingMethod().equals(PricingMethod.NUMBER) && amount == null){
        			return EntryError.EMPTY(AMOUNT);
        		}
    		}
    		
    		//删除运费参数信息
    		freightParamDao.delete(goodsId);
    		
    		//添加运费参数信息
    		freightParamDao.save(freightParam);
    	}
    	
		//判断小程序应用信息是否为空
		if(goods.getApp() != null){
			
			
			appDao.delete(goods.getId());
			
			//执行添加
			appDao.save(goods.getApp());
		}
		
    	//返回更新结果
    	return Success.SUCCESS(goods.getId());
    }
    
    /**
     * 根据店铺ID、分组ID、商品状态、推荐状态、类目ID、模糊查询关键字分页查询商品列表(根据创建时间倒叙)
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param status	      商品状态。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @param isSoldOut   是否售罄。
     * @param start		      分页起始（0为第一条记录）。
     * @param pageSize    每页显示记录数。
     * @return 查询到结果返回商品列表，未查询到结果返回空，失败则返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findGoodses(String storeId, String groupId, GoodsType type, GoodsStatus status,
    							   Boolean isRecommend, String categoryId, String keyword,
    							   Boolean isSoldOut, Integer start, Integer pageSize) {
    	
    	//查询商品列表
    	List<Goods> goodses = goodsDao.findGoodses(storeId, groupId, type, status, isRecommend, categoryId, keyword, isSoldOut, start, pageSize);
    	
    	//转换查询结果为视图列表
    	List<ViewGoods> viewGoodses = convertGoodsViews(goodses);
    	
    	//查询商品总数
    	int totalSize = goodsDao.getGoodsCount(storeId, groupId, type, status, isRecommend, categoryId, keyword, isSoldOut);
    	
		//计算出总页数
		int totalPage = totalSize%pageSize==0?totalSize/pageSize:(totalSize/pageSize + 1);
		
		
		//创建Map集合封装分页参数
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put(TOTAL_SIZE, totalSize);
		pageParam.put(TOTAL_PAGE, totalPage);
		
		//创建Map集合封装查询结果
		Map<String, Object> result = new HashMap<>();
		result.put(GOODSES, viewGoodses);
		result.put(PAGE_PARAM, pageParam);
    	
    	//返回查询结果
        return Success.SUCCESS(result);
    }
    
    /**
     * 根据店铺ID、分组ID、推荐状态、类目ID、模糊查询关键字分页查询正在销售的商品列表(根据创建时间倒叙，正在销售即商品库存、状态均为正常)。
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @param start		      分页起始（0为第一条记录）。
     * @param pageSize    每页显示记录数。
     * @return 查询到结果返回商品列表，未查询到结果返回空，失败则返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findSellingGoodses(String storeId, String groupId,GoodsType type, Boolean isRecommend, 
    		                              String categoryId, String keyword, Integer start, Integer pageSize){
    	
    	//查询商品列表
    	List<Goods> goodses = goodsDao.findSellingGoodses(storeId, groupId, type, isRecommend, categoryId, keyword, start, pageSize);
    	
    	//转换查询结果为视图列表
    	List<ViewGoods> viewGoodses = convertGoodsViews(goodses);
    	
    	//查询商品总数
    	int totalSize = goodsDao.getSellingGoodsCount(storeId, groupId, type, isRecommend, categoryId, keyword);
    	
		//计算出总页数
		int totalPage = totalSize%pageSize==0?totalSize/pageSize:(totalSize/pageSize + 1);
		
		
		//创建Map集合封装分页参数
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put(TOTAL_SIZE, totalSize);
		pageParam.put(TOTAL_PAGE, totalPage);
		
		//创建Map集合封装查询结果
		Map<String, Object> result = new HashMap<>();
		result.put(GOODSES, viewGoodses);
		result.put(PAGE_PARAM, pageParam);
    	
    	//返回查询结果
        return Success.SUCCESS(result);
    }
    
    /**
     * 根据商品ID查询商品详细信息（根据属性名排列序号正序、属性值排列序号正序）。
     * @param id 商品ID。
     * @return 查询到结果返回商品详情，未查询到结果返回空，失败则返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findGoodsDetail(String id, GoodsType type) {
    	
    	//查询商品详情
    	Goods goods = goodsDao.findGoodsDetailById(id, type);
    	
    	//转换查询结果为视图对象
    	ViewGoods viewGoods = convertGoodsView(goods);
    	
    	
    	//返回查询结果
        return Success.SUCCESS(viewGoods);
    }
    
    /**
     * 根据购买信息列表查询购物车详情列表。
     * @param purchaseInfos 购买信息列表。
     * @return 成功返回购物车商品信息列表，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode getPurchaseDetails(List<PurchaseInfo> purchaseInfos) {
    	
    	//创建购买详情列表
    	List<ViewPurchaseDetail> purchaseDetails = new ArrayList<>();
    	
    	//循环购买信息列表
    	for (PurchaseInfo purchaseInfo : purchaseInfos) {
			
    		//查询SKU信息
    		Sku sku = skuDao.findSkuById(purchaseInfo.getSkuId());
    		
    		//判断SKU信息是否存在
			if(sku == null){
				return StatusError.DELETED(SKU_ID+":"+purchaseInfo.getSkuId()); // SKU已删除
			}
    		
    		//查询商品信息
    		Goods goods = goodsDao.findGoodsById(sku.getGoodsId());
    		
    		//判断商品是否存在
    		if(goods == null){
    			return RuleError.NON_EXISTENT(GOODS_ID+":"+sku.getGoodsId()); //商品不存在或已删除
    		}
    				
    		//判断商品是否下架
			if(goods.getStatus().equals(GoodsStatus.APPLIED)){
				return StatusError.APPLIED(GOODS_ID+":"+sku.getGoodsId());
			}
    		
			//判断商品是否被删除
			if(goods.getStatus().equals(GoodsStatus.REMOVED)){
				return StatusError.DELETED(GOODS_ID+":"+sku.getGoodsId());
			}
			
			//判断是否达到起售量
			if(purchaseInfo.getQuantity() < sku.getSalesVolume()){
				
				return GCRuleError.BELOW_MOQ(SKU_ID+":"+purchaseInfo.getSkuId()); // 未达到起售量
			}
    				
			//判断sku库存是否充足
			if(goods.getInventoryType().equals(InventoryType.LIMITED)){
				if((sku.getInventory() - sku.getLockedInventory()) >= purchaseInfo.getQuantity()){
					
					return GCRuleError.SHORT_INVENTORY(SKU_ID+":"+purchaseInfo.getSkuId()); // 库存不足
				}	
			}
			
			//创建购买详情视图对象，封装购买信息
			ViewPurchaseDetail purchaseDetail = new ViewPurchaseDetail();
			purchaseDetail.setSpuId(goods.getId());
			purchaseDetail.setSkuId(sku.getId());
			purchaseDetail.setName(goods.getName());
			
			//获取商品图片
			String[] photos = goods.getMainDiagram().split(";");
			purchaseDetail.setPhoto(photos[0]);
			purchaseDetail.setPrice(sku.getPrice());
			purchaseDetail.setQuantity(purchaseInfo.getQuantity());
			
			
			//获取描述
			String description = sku.getDescription();
			StringBuffer descriptionStringBuffer = new StringBuffer();
			
			//获取属性名属性值键值对列表
			if(!StringUtils.isEmpty(description) && description.contains(";")){
				String[] descriptions = description.split(";");
				for (String string : descriptions) {
					int index = string.indexOf(":");
					
					//获取属性名ID
					String propertyNameId = string.substring(0, index);
					
					//获取属性值ID
					String propertyValueId = string.substring(index+1);
					
					//查询属性名信息
					List<GoodsPropertyName> goodsPropertyNames = goodsPropertyNameDao.findGoodsPropertyNames(goods.getId(), propertyNameId);
					List<GoodsPropertyValue> goodsPropertyValues = goodsPropertyValueDao.findGoodsPropertyValues(goods.getId(), goodsPropertyNames.get(0).getId(), propertyValueId);
					
					if(goodsPropertyNames != null && goodsPropertyNames.size()>0){
						if(goodsPropertyValues != null && goodsPropertyValues.size()>0){
							descriptionStringBuffer = descriptionStringBuffer.append(goodsPropertyNames.get(0).getName()).append(":").append(goodsPropertyValues.get(0).getName()).append(";");
						}
					}
				}
				
		    	//设置描述信息
				if(descriptionStringBuffer.length() >0 && descriptionStringBuffer.toString().contains(";")){
					purchaseDetail.setDescription(descriptionStringBuffer.substring(0, descriptionStringBuffer.lastIndexOf(";")).toString());
				}
			
			}

	    	
	    	//添加购买详情到详情列表中
	    	purchaseDetails.add(purchaseDetail);
    	}	
    	
    	//返回处理结果
    	return Success.SUCCESS(purchaseDetails);
    }
    
    /**
     * 根据购买信息列表查询购买详情列表。
     * @param purchaseInfos 购买信息列表。
     * @return 成功返回购买信息列表，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode getCartDetails(List<PurchaseInfo> purchaseInfos) {
    	
    	//创建购买详情列表
    	List<ViewCartDetail> cartDetails = new ArrayList<>();
    	
    	//循环购买信息列表
    	for (PurchaseInfo purchaseInfo : purchaseInfos) {
			
    		//查询SKU信息
    		Sku sku = skuDao.findSkuById(purchaseInfo.getSkuId());
    		
    		//判断SKU信息是否存在
			if(sku != null){
	    		
	    		//查询商品信息
	    		Goods goods = goodsDao.findGoodsById(sku.getGoodsId());
	    		
	    		//判断商品是否存在
	    		if(goods != null){
	    			
					//创建购买详情视图对象
					ViewCartDetail cartDetail = new ViewCartDetail();
	    			
					//封装购买信息
					cartDetail.setSpuId(goods.getId());
					cartDetail.setSkuId(sku.getId());
					cartDetail.setName(goods.getName());
					cartDetail.setStatus(goods.getStatus());
					cartDetail.setQuantity(purchaseInfo.getQuantity());
					cartDetail.setSalesVolume(sku.getSalesVolume());
					cartDetail.setInventoryType(sku.getInventoryType());
					cartDetail.setInventory(sku.getInventory()-sku.getLockedInventory());
					
					
					//获取商品图片
					String[] photos = goods.getMainDiagram().split(";");
					cartDetail.setPhoto(photos[0]);
					cartDetail.setPrice(sku.getPrice());
					cartDetail.setQuantity(purchaseInfo.getQuantity());
					
					
					//获取描述
					String description = sku.getDescription();

					StringBuffer descriptionStringBuffer = new StringBuffer();
					
					//获取属性名属性值键值对列表
					if(!StringUtils.isEmpty(description) && description.contains(";")){
						String[] descriptions = description.split(";");
						for (String string : descriptions) {
							int index = string.indexOf(":");
							
							//获取属性名ID
							String propertyNameId = string.substring(0, index);
							
							//获取属性值ID
							String propertyValueId = string.substring(index+1);
							
							//查询属性名信息
							List<GoodsPropertyName> goodsPropertyNames = goodsPropertyNameDao.findGoodsPropertyNames(goods.getId(), propertyNameId);
							List<GoodsPropertyValue> goodsPropertyValues = goodsPropertyValueDao.findGoodsPropertyValues(goods.getId(), goodsPropertyNames.get(0).getId(), propertyValueId);
							
							if(goodsPropertyNames != null && goodsPropertyNames.size()>0){
								if(goodsPropertyValues != null && goodsPropertyValues.size()>0){
									descriptionStringBuffer = descriptionStringBuffer.append(goodsPropertyNames.get(0).getName()).append(":").append(goodsPropertyValues.get(0).getName()).append(";");
								}
							}
						}
						
				    	//设置描述信息
						if(descriptionStringBuffer.length() >0 && descriptionStringBuffer.toString().contains(";")){
							cartDetail.setDescription(descriptionStringBuffer.substring(0, descriptionStringBuffer.lastIndexOf(";")).toString());
						}
						
						
					}
					
			    	//添加购买详情到详情列表中
					cartDetails.add(cartDetail);
	    		}
			}
    	}	
    	
    	//返回处理结果
    	return Success.SUCCESS(cartDetails);
    }
    
}
