package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.dreawer.goods.domain.Sku;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>SkuDao</CODE> SKU DAO 类，负责对SKU数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class SkuDao extends MyBatisBaseDao<Sku>{

    /**
     * 添加SKU。
     * @param sku 待添加的SKU信息
     * @return 添加成功记录数:成功返回添加记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int saveBatch(List<Sku> skus){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(SKUS, skus);
    	
    	//返回添加结果
    	return insertBatch("saveBatch", params);
    }
    
    /**
     * 根据商品ID删除SKU。
     * @param goodsId 商品ID
     * @return 删除成功记录数:成功返回删除记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteByGoodsId(String goodsId){
    	
    	//返回删除结果
    	return delete("deleteByGoodsId", goodsId);
    }
    
    /**
     * 批量更新SKU库存。
     * @param skus 待更新的SKU列表（SKUID、库存数）。
     * @return 更新成功记录数:成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchInventory(List<Sku> skus){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(SKUS, skus);
    	
    	//返回更新结果
    	return updateBatch("updateBatchInventory", params);
    }
    
    /**
     * 批量更新SKU锁定库存。
     * @param skus 待更新的SKU列表（SKUID、锁定库存数）。
     * @return 更新成功记录数:成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchLockedInventory(List<Sku> skus){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(SKUS, skus);
    	
    	//返回更新结果
    	return updateBatch("updateBatchLockedInventory", params);
    }
    
    /**
     * 根据商品ID查询SKU列表(根据创建时间倒叙)。
     * @param goodsId 商品ID。
     * @return 查询到结果返回SKU列表，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Sku> findSkus(String goodsId) {
    	
    	//返回查询结果
        return selectList("findSkusByGoodsId", goodsId);
    }
    
    /**
     * 根据商品ID查询SKU总数。
     * @param goodsId 商品ID。
     * @return 查询到结果返回商品SKU总数，未查询到结果则返回0。
     * @author kael
     * @since 1.0
     */
    public int getSkusCount(String goodsId) {
    	
    	//返回查询结果
        return count("getSkuCount", goodsId);
    }
    
    /**
     * 根据SKUID查询SKU信息。
     * @param id SKUID。
     * @return 查询到结果返回SKU信息，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public Sku findSkuById(String id) {
    	
    	//返回查询结果
    	return selectOne("findSkuById", id);
    }
    
    /**
     * 根据商品ID和模糊查询关键字（SKU描述关键字）查询SKU列表。
     * @param goodsId 商品ID。
     * @param keyword 模糊查询关键字。
     * @return 查询到结果返回SKU列表，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Sku> findSkusLikeDescription(String goodsId, String keyword) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_ID, goodsId);
    	params.put(KEYWORD, keyword);
    	
    	//返回查询结果
    	return selectList("findSkusLikeDescription", params);
    }
    
}
