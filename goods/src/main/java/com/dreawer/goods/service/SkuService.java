package com.dreawer.goods.service;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dreawer.goods.persistence.SkuDao;
import com.dreawer.goods.view.ViewSku;
import com.dreawer.responsecode.rcdt.GCRuleError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.RuleError;
import com.dreawer.responsecode.rcdt.StatusError;
import com.dreawer.responsecode.rcdt.Success;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.InventoryOperationDetail;
import com.dreawer.goods.domain.Sku;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.InventoryStatus;
import com.dreawer.goods.lang.InventoryType;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.persistence.GoodsDao;
import com.dreawer.goods.persistence.InventoryOperationDetailDao;

/**
 * <CODE>SkuService</CODE> SKUService。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
@Service
public class SkuService extends BaseService{

    @Autowired
    private SkuDao skuDao; // SKU信息DAO

    @Autowired
    private GoodsDao goodsDao; // 商品信息DAO
    
    @Autowired
    private InventoryOperationDetailDao inventoryOperationDetailDao; // 库存操作明细DAO
    
    /**
     * 批量锁定SKU库存。
     * @param purchaseInfos 购买信息列表。
     * @param orderId 	           订单ID。
     * @param userId 	           用户ID。
     * @param time			操作时间。
     * @return  成功返回订单ID，失败返回相应的错误码
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode lockBatchInventory(List<PurchaseInfo> purchaseInfos, String orderId, String userId, Timestamp time){
    	
    	//对购买信息排序
    	purchaseInfos = sortPurchaseInfoBySkuId(purchaseInfos);
    	
    	//创建List集合接收库存操作明细列表
    	List<InventoryOperationDetail> inventoryOperationDetails = new ArrayList<>();
    	
    	//创建List集合接收SKU列表
    	List<Sku> updateSkus = new ArrayList<>();
    	
    	//循环该SKU列表
    	for (PurchaseInfo purchaseInfo : purchaseInfos) {
			
    		//查询SKU信息
    		Sku sku = skuDao.findSkuById(purchaseInfo.getSkuId());
    		
    		//判断SKU信息是否存在
			if(sku == null){
				return StatusError.DELETED(SKU_ID+":"+purchaseInfo.getSkuId()); // SKU已删除
			}
    		
    		//查询商品信息
    		Goods goods = goodsDao.findGoodsByIdForUpdate(sku.getGoodsId());
    		
    		//查询是否存在该库存操作明细信息
    		InventoryOperationDetail inventoryOperationDetail = inventoryOperationDetailDao.findInventoryOperationDetail(orderId, sku.getId());
    		if(inventoryOperationDetail == null){
    			
    			//判断商品是否存在
    			if(goods != null){
    				
    				//判断商品状态
    				if(goods.getStatus().equals(GoodsStatus.DEFAULT)){
    					
    					//判断是否达到起售量
    					if(purchaseInfo.getQuantity() < sku.getSalesVolume()){
    						//TODO
    						GCRuleError.BELOW_MOQ(SKU_ID+":"+purchaseInfo.getSkuId()); // 未达到起售量
    					}
    					
    					//判断sku库存是否充足
    					if(sku.getInventoryType().equals(InventoryType.LIMITED) && (sku.getInventory() - sku.getLockedInventory())  >= purchaseInfo.getQuantity()){
    						
    						//将待锁定的库存信息添加到List集合中
    						inventoryOperationDetail = new InventoryOperationDetail();
    						inventoryOperationDetail.setOrderId(orderId);
    						inventoryOperationDetail.setSkuId(purchaseInfo.getSkuId());
    						inventoryOperationDetail.setInventory(purchaseInfo.getQuantity());
    						inventoryOperationDetail.setStatus(InventoryStatus.LOCKED);
    						inventoryOperationDetail.setCreaterId(userId);
    						inventoryOperationDetail.setCreateTime(time);
    						inventoryOperationDetails.add(inventoryOperationDetail);
    						
    						//将待锁定库存的SKU信息添加到List集合中
    						sku.setLockedInventory(sku.getLockedInventory()+purchaseInfo.getQuantity());
    						updateSkus.add(sku);
    						
    					}else{
    						GCRuleError.SHORT_INVENTORY(SKU_ID+":"+purchaseInfo.getSkuId()); // 库存不足
    					}	
            		}else if(goods.getStatus().equals(GoodsStatus.APPLIED)){
            			return StatusError.APPLIED(GOODS_ID+":"+sku.getGoodsId()); // 商品已下架
            		}else{
            			return StatusError.DELETED(GOODS_ID+":"+sku.getGoodsId()); // 商品已移除
            		}
    			}else{
    				return RuleError.NON_EXISTENT(GOODS_ID+":"+sku.getGoodsId()); // 商品不存在或已删除
    			}
    		}
    	}	
    	
    	
    	if(updateSkus.size() >0 ){
    		
    		//执行锁定库存
    		skuDao.updateBatchLockedInventory(updateSkus);
    	}
    	
    	if(inventoryOperationDetails.size()>0){
    		
    		//添加锁定库存信息
    		inventoryOperationDetailDao.saveBatch(inventoryOperationDetails);
    	}
    	
    	//返回处理结果
    	return Success.SUCCESS(orderId);
    }
    
    /**
     * 批量释放SKU库存。
     * @param purchaseInfos 购买信息列表。
     * @param orderId 	           订单ID。
     * @param userId 	           用户ID。
     * @param time			操作时间。
     * @return  成功返回订单ID，失败返回相应的错误码。
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode releaseBatchInventory(List<PurchaseInfo> purchaseInfos, String orderId, String userId, Timestamp time){
    	
    	//对购买信息排序
    	purchaseInfos = sortPurchaseInfoBySkuId(purchaseInfos);
    	
    	//创建List集合接收库存操作明细列表
    	List<InventoryOperationDetail> inventoryOperationDetails = new ArrayList<>();
    	
    	//创建List集合接收SKU列表
    	List<Sku> updateSkus = new ArrayList<>();
    	
    	//循环购买信息列表
    	for (PurchaseInfo purchaseInfo : purchaseInfos) {

    		//查询是否存在该库存操作明细信息
    		InventoryOperationDetail inventoryOperationDetail = inventoryOperationDetailDao.findInventoryOperationDetail(orderId, purchaseInfo.getSkuId());
    		if(inventoryOperationDetail != null && inventoryOperationDetail.getStatus().equals(InventoryStatus.LOCKED)){
   				//将待释放的库存信息添加到List集合中
				inventoryOperationDetail.setStatus(InventoryStatus.RELEASED);
				inventoryOperationDetail.setUpdaterId(userId);
				inventoryOperationDetail.setUpdateTime(time);
				inventoryOperationDetails.add(inventoryOperationDetail);
				
	    		//查询SKU信息
	    		Sku sku = skuDao.findSkuById(purchaseInfo.getSkuId());
	    		
	    		//判断SKU信息是否存在
				if(sku != null){
					//将待锁定库存的sku信息添加到List集合中
					sku.setLockedInventory(sku.getLockedInventory()-inventoryOperationDetail.getInventory());
					updateSkus.add(sku);
				}	
    		}
    	}	
    	
    	if(updateSkus.size() > 0){
    		//执行释放库存
    		skuDao.updateBatchLockedInventory(updateSkus);
    	}
    	
    	if(inventoryOperationDetails.size() > 0){
    		
    		//更新库存操作明细
    		inventoryOperationDetailDao.updateBatchStatus(inventoryOperationDetails);
    	}
    	
    		
    	//返回处理结果
    	return Success.SUCCESS(orderId);
    }
    
    /**
     * 批量扣减SKU库存。
     * @param purchaseInfos 购买信息列表。
     * @param orderId 	           订单ID。
     * @param userId 	           用户ID。
     * @param time			操作时间。
     * @return  成功返回订单ID，失败返回相应的错误码
     * @author kael
     * @since 1.0
     */
    @Transactional
    public ResponseCode deductionBatchInventory(List<PurchaseInfo> purchaseInfos, String orderId, String userId, Timestamp time){
    	
    	//对购买信息排序
    	purchaseInfos = sortPurchaseInfoBySkuId(purchaseInfos);
    	
    	//创建List集合接收库存操作明细列表
    	List<InventoryOperationDetail> inventoryOperationDetails = new ArrayList<>();
    	
    	//创建List集合接收SKU列表
    	List<Sku> updateSkus = new ArrayList<>();
    	
    	//循环该SKU列表
    	for (PurchaseInfo purchaseInfo : purchaseInfos) {
			
    		//查询是否存在该库存操作明细信息
    		InventoryOperationDetail inventoryOperationDetail = inventoryOperationDetailDao.findInventoryOperationDetail(orderId, purchaseInfo.getSkuId());
    		if(inventoryOperationDetail != null && inventoryOperationDetail.getStatus().equals(InventoryStatus.LOCKED)){

    			//将待扣减的库存信息添加到List集合中
				inventoryOperationDetail.setStatus(InventoryStatus.DEDUCTED);
				inventoryOperationDetail.setUpdaterId(userId);
				inventoryOperationDetail.setUpdateTime(time);
				inventoryOperationDetails.add(inventoryOperationDetail);
        		
	    		//查询SKU信息
	    		Sku sku = skuDao.findSkuById(purchaseInfo.getSkuId());
	    		
	    		//判断SKU信息是否存在
				if(sku != null){
					//将待扣减锁定库存和扣减库存的sku信息添加到List集合中
					sku.setLockedInventory(sku.getLockedInventory()-inventoryOperationDetail.getInventory());
					sku.setInventory(sku.getInventory() - inventoryOperationDetail.getInventory());
					updateSkus.add(sku);
				}else{
					return RuleError.NON_EXISTENT(SKU_ID+":"+purchaseInfo.getSkuId()); // SKU不存在
				}
    		}
    	}	
    	
    	//执行扣减库存
    	if(updateSkus.size() > 0){
    		skuDao.updateBatchInventory(updateSkus);
        	skuDao.updateBatchLockedInventory(updateSkus);
    	}
    	
    	if(inventoryOperationDetails.size() > 0){
    		
    		//更新库存操作明细
    		inventoryOperationDetailDao.updateBatchStatus(inventoryOperationDetails);
    	}
    	
    	//返回处理结果
    	return Success.SUCCESS(orderId);
    }
    
    /**
     * 根据商品ID查询SKU列表(根据创建时间倒叙)。
     * @param goodsId 商品ID。
     * @return 查询到结果返回SKU列表，未查询到结果则返回空，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findSkus(String goodsId) {
    	
    	//执行查询
    	List<Sku> skus = skuDao.findSkus(goodsId);
    	
    	//转换查询结果为视图列表
    	List<ViewSku> viewSkus = convertSkuViews(skus);
    	
    	//返回查询结果
        return Success.SUCCESS(viewSkus);
    }
    
    /**
     * 根据SKUID查询SKU信息。
     * @param id SKUID。
     * @return 查询到结果返回SKU信息，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findSkuById(String id) {
    	
    	//执行查询
    	Sku sku = skuDao.findSkuById(id);
    	
    	//转换查询结果为视图对象
    	ViewSku viewSku = convertSkuView(sku);
    	
    	//返回查询结果
    	return Success.SUCCESS(viewSku);
    }
    
}
