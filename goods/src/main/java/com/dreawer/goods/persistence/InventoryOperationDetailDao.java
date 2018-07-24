package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.InventoryOperationDetail;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>InventoryOperationDetailDao</CODE> 库存处理明细 DAO 类，负责对库存处理明细数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class InventoryOperationDetailDao extends MyBatisBaseDao<InventoryOperationDetail>{

    /**
     * 批量添加锁定库存信息。
     * @param inventoryOperationDetails 待添加的库存处理明细列表。
     * @return 添加成功记录数:成功返回添加记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int saveBatch(List<InventoryOperationDetail> inventoryOperationDetails){
    	
    	//返回添加结果
    	return insertBatch("saveBatch", inventoryOperationDetails);
    }
    
    /**
     * 批量修改库存状态。
     * @param inventoryOperationDetails 待更新的库存处理明细列表。
     * @return 更新成功记录数:成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchStatus(List<InventoryOperationDetail> inventoryOperationDetails){
    	
    	//返回更新结果
    	return updateBatch("updateBatchStatus", inventoryOperationDetails);
    }
    
    /**
     * 根据订单ID和SKUID查询库存操作明细。
     * @param orderId 订单ID
     * @param skuId SKUID。
     * @return 查询到结果返回库存操作明细，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public InventoryOperationDetail findInventoryOperationDetail(String orderId, String skuId){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(ORDER_ID, orderId);
    	params.put(SKU_ID, skuId);
    	
    	//返回查询结果
    	return selectOne("findInventoryOperationDetail", params);
    }
    
}
