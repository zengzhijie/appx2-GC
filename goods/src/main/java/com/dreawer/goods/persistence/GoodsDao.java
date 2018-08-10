package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.GoodsType;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>GoodsDao</CODE> 商品信息 DAO 类，负责对商品数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class GoodsDao extends MyBatisBaseDao<Goods> {

    /**
     * 添加商品信息。
     * @param goods 待添加的商品信息
     * @return 添加成功记录数：成功返回1,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int save(Goods goods){
    	
    	//返回添加结果
    	return insert("save", goods);
    }
    
    /**
     * 批量删除商品。
     * @param ids 待删除的商品ID列表。
     * @return 删除成功记录数：成功返回删除记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteBatch(List<String> ids){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(IDS, ids);
    	
    	//返回删除结果
    	return deleteBatch("deleteBatch", params);
    }
    
    /**
     * 批量更新商品状态。
     * @param goodses 待更新的商品列表（商品ID、商品状态、更新者ID、更新时间）。
     * @return 更新成功记录数：成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchStatus(List<Goods> goodses){
    	
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(GOODSES, goodses);
    	
    	//返回更新结果
    	return updateBatch("updateBatchStatus", params);
    }
    
    /**
     * 批量更新商品推荐状态。
     * @param goodses 待更新的商品列表（商品ID、商品推荐状态、更新者ID、更新时间）。
     * @return 更新成功记录数：成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchRecommend(List<Goods> goodses){
    	
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(GOODSES, goodses);
    	
    	//返回更新结果
    	return updateBatch("updateBatchRecommend", params);
    }
    
    /**
     * 更新商品基本信息（名称、最低售价、最低原价、库存类型、总库存、主图、详情、售后服务、销量、推荐状态、备注等）。
     * @param goods 待更新的商品信息。
     * @return 更新成功记录数：成功返回1,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int update(Goods goods){
    	return update("update", goods);
    }
    
    /**
     * 根据店铺ID、分组ID、商品类型、商品状态、推荐状态、类目ID、模糊查询关键字分页查询商品列表(根据创建时间倒叙)
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param type	                 商品类型。
     * @param status	      商品状态。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @param isSoldOut   是否售罄。
     * @param start		      分页起始（0为第一条记录）。
     * @param pageSize    每页显示记录数。
     * @return 查询到结果返回商品列表，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Goods> findGoodses(String storeId, String groupId, GoodsType type, GoodsStatus status,
    							   Boolean isRecommend, String categoryId, String keyword,
    							   Boolean isSoldOut, Integer start, Integer pageSize) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(STORE_ID, storeId);
    	params.put(GROUP_ID, groupId);
    	params.put(TYPE, type);
    	params.put(STATUS, status);
    	params.put(IS_RECOMMEND, isRecommend);
    	params.put(CATEGORY_ID, categoryId);
    	params.put(KEYWORD, keyword);
    	params.put(IS_SOLD_OUT, isSoldOut);
    	params.put(START, start);
    	params.put(PAGE_SIZE, pageSize);
    	
    	//返回查询结果
        return selectList("findGoodses", params);
    }
    
    /**
     * 根据店铺ID、分组ID、商品类型、商品状态、推荐状态、类目ID、模糊查询关键字分页获取商品总数。
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param type	                 商品类型。
     * @param status	      商品状态。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @param isSoldOut   是否售罄。
     * @return 查询到结果返回商品总数，未查询到结果返回0。
     * @author kael
     * @since 1.0
     */
    public int getGoodsCount(String storeId, String groupId, GoodsType type, GoodsStatus status,
    						 Boolean isRecommend, String categoryId, String keyword, Boolean isSoldOut) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(STORE_ID, storeId);
    	params.put(GROUP_ID, groupId);
    	params.put(TYPE, type);
    	params.put(STATUS, status);
    	params.put(IS_RECOMMEND, isRecommend);
    	params.put(CATEGORY_ID, categoryId);
    	params.put(KEYWORD, keyword);
    	params.put(IS_SOLD_OUT, isSoldOut);
    	
    	//返回查询结果
    	return count("getGoodsCount", params);
    }
    
    /**
     * 根据店铺ID、分组ID、 商品类型、推荐状态、类目ID、模糊查询关键字分页查询正在销售的商品列表(根据创建时间倒叙，正在销售即商品库存、状态均为正常)。
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param type	                 商品类型。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @param start		      分页起始（0为第一条记录）。
     * @param pageSize    每页显示记录数。
     * @return 查询到结果返回商品列表，未查询到结果则返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Goods> findSellingGoodses(String storeId, String groupId, GoodsType type, Boolean isRecommend, 
    		                              String categoryId, String keyword, Integer start, Integer pageSize){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(STORE_ID, storeId);
    	params.put(GROUP_ID, groupId);
    	params.put(TYPE, type);
    	params.put(IS_RECOMMEND, isRecommend);
    	params.put(CATEGORY_ID, categoryId);
    	params.put(KEYWORD, keyword);
    	params.put(START, start);
    	params.put(PAGE_SIZE, pageSize);
    	
    	//返回查询结果
        return selectList("findSellingGoodses", params);
    }
    
    /**
     * 根据店铺ID、分组ID、 商品类型、推荐状态、类目ID、模糊查询关键字分页获取正在销售的商品总数(正在销售即商品库存、状态均为正常)。
     * @param storeId     店铺ID。
     * @param groupId	      分组ID。
     * @param type	                 商品类型。
     * @param isRecommend 推荐状态。
     * @param categoryId  类目ID。 
     * @param keyword     模糊查询关键字。
     * @return 查询到结果返回商品总数，未查询到结果返回0。
     * @author kael
     * @since 1.0
     */
    public int getSellingGoodsCount(String storeId, String groupId, GoodsType type, Boolean isRecommend, String categoryId, String keyword) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(STORE_ID, storeId);
    	params.put(GROUP_ID, groupId);
    	params.put(TYPE, type);
    	params.put(IS_RECOMMEND, isRecommend);
    	params.put(CATEGORY_ID, categoryId);
    	params.put(KEYWORD, keyword);
    	
    	//返回查询结果
        return count("getSellingGoodsCount", params);
    }
    
    /**
     * 根据商品ID查询商品信息。
     * @param id   商品ID。
     * @return 查询到结果返回商品信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Goods findGoodsById(String id) {
    	
    	//返回查询结果
        return selectOne("findGoodsById", id);
    }
    
    /**
     * 根据商品ID查询商品信息。
     * @param id   商品ID。
     * @return 查询到结果返回商品信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Goods findGoodsByIdForUpdate(String id) {
    	
    	//返回查询结果
        return selectOne("findGoodsByIdForUpdate", id);
    }
    
    /**
     * 根据商品ID、商品类型查询商品详细信息（根据属性名排列序号正序、属性值排列序号正序）。
     * @param id   商品ID。
     * @param type 商品类型。
     * @return 查询到结果返回商品详细信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Goods findGoodsDetailById(String id, GoodsType type) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(ID, id);
    	params.put(TYPE, type);
    	
    	//返回查询结果
        return selectOne("findGoodsDetailById",params);
    }
    
}
