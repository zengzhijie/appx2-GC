package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.lang.GroupStatus;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>GroupDao</CODE> 分组信息 DAO 类，负责对分组数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class GroupDao extends MyBatisBaseDao<Group> {

    /**
     * 添加分组信息。
     * @param group 分组。
     * @return 添加成功记录数：成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int save(Group group){
    	
    	//返回添加结果
    	return insert("save", group);
    }
    
    /**
     * 添加分组、商品关系。
     * @param groupGoodses 待添加的分组、商品信息（分组ID、商品ID等）。
     * @return 添加成功记录数：成功返回添加记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int saveBatchGroupGoods(List<Map<String, Object>> groupGoodses){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
		params.put(GROUP_GOODSES, groupGoodses);
		
    	//返回添加结果
    	return insertBatch("saveBatchGroupGoods", params);
    }
    
    /**
     * 根据分组ID删除分组信息、商品和分组关系。
     * @param id 分组ID。
     * @return 删除成功记录数:成功返回删除记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int delete(String id){
    	
    	//返回删除结果
    	return delete("delete", id);
    }
    
    /**
     * 根据商品ID列表批量删除分组和商品关系。
     * @param goodsIds 商品ID列表。
     * @return 删除成功记录数:成功返回删除记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteBatchGroupGoods(List<String> goodsIds){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_IDS, goodsIds);
    	
    	//返回删除结果
    	return deleteBatch("deleteBatchGroupGoods", params);
    }
    
    /**
     * 根据商品ID和分组ID删除分组和商品关系。
     * @param goodsId 商品ID。
     * @param groupId 分组ID。
     * @return 删除成功记录数:成功返回删除记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteGroupGoods(String goodsId, String groupId){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
		params.put(GOODS_ID, goodsId);
		params.put(GROUP_ID, groupId);
    	
    	//返回删除结果
    	return delete("deleteGroupGoodsByGoodsIdAndGroupId", params);
    }
    
    /**
     * 更新isParent属性。
     * @param group 待更新的分组信息（分组ID、是否父类目属性等）。
     * @return 更新成功记录数:成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateIsParent(Group group){
    	
    	//返回更新结果
    	return update("updateIsParent", group);
    }
    
    /**
     * 更新排列顺序。
     * @param group 待更新的分组信息（分组ID、排列序号、更新者ID、更新时间等）。
     * @return 更新成功记录数:成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateSquence(Group group){
    	
    	//返回更新结果
    	return update("updateSquence", group);
    }
    
    /**
     * 更新分组状态。
     * @param groups 待更新的分组列表。
     * @return 更新成功记录数:成功返回更新记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateBatchStatus(List<Group> groups){
    	
    	//返回更新结果
    	return updateBatch("updateBatchStatus", groups);
    }
    
    /**
     * 增加分组中商品数量。
     * @param goodsQuantity 待增加的商品数量。
     * @param id            分组ID。
     * @return 更新成功记录数:成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int increaseGoodsQuantity(Integer goodsQuantity, String id){
    	
    	//封装请求参数
    	Group group = new Group();
    	group.setGoodsQuantity(goodsQuantity);
    	group.setId(id);
    	
    	//返回更新结果
    	return update("increaseGoodsQuantity", group);
    }
    
    /**
     * 减少分组中商品数量。
     * @param goodsQuantity 待减少的商品数量。
     * @param id            分组ID。
     * @return 更新成功记录数:成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int reduceGoodsQuantity(Integer goodsQuantity, String id){
    	
    	//封装请求参数
    	Group group = new Group();
    	group.setGoodsQuantity(goodsQuantity);
    	group.setId(id);
    	
    	//返回更新结果
    	return update("reduceGoodsQuantity", group);
    }
    
    /**
     * 通过商品ID更新分组中的商品数（商品数加一）。
     * @param goodsId 商品ID。
     * @return 更新成功记录数:成功返回更新记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int increaseGoodsQuantity(String goodsId){
    	
    	//返回更新结果
    	return update("increaseGoodsQuantityByGoodsId", goodsId);
    }
    
    /**
     * 通过商品ID列表批量更新分组中的商品数（商品数减一）。
     * @param goodsIds 商品ID列表。
     * @return 更新成功记录数:成功返回更新记录数，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int reduceBatchGoodsQuantity(List<String> goodsIds){
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_IDS, goodsIds);
    	
    	//返回更新结果
    	return updateBatch("reduceBatchGoodsQuantityByGoodsId", params);
    }
    
    /**
     * 更新分组信息（名称、是否父分组属性、排列序号、LOGO、简介、状态、URL、商品数）。
     * @param group 待更新分组信息。
     * @return 更新成功记录数:成功返回1，失败返回0。
     * @author kael
     * @since 1.0
     */
    public int update(Group group){
    	
    	//返回更新结果
    	return update("update", group);
    }
    
    /**
     * 根据父ID、店铺ID、分组状态查询分组列表(根据排列序号正序)。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态。
     * @return 查询到结果返回分组列表，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Group> findChildGroups(String parentId, String storeId, GroupStatus status) {
		
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
		params.put(STORE_ID, storeId);
		params.put(PARENT_ID, parentId);
		params.put(STATUS, status);
		
		//返回查询结果
        return selectList("findChildGroups", params);
    }
    
    /**
     * 根据父ID、店铺ID、分组状态获取分组总数。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态。
     * @return 查询到结果返回分组总数，未查询到结果返回0。
     * @author kael
     * @since 1.0
     */
    public int getChildGroupsCount(String parentId, String storeId, GroupStatus status) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
		params.put(STORE_ID, storeId);
		params.put(PARENT_ID, parentId);
		params.put(STATUS, status);
		
		//返回查询结果
        return count("getChildGroupCount", params);
    }
    
    /**
     * 根据分组ID查询分组信息。
     * @param id 分组ID。
     * @return 查询到结果返回分组信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Group findGroup(String id) {
    	
    	//返回查询结果
        return selectOne("findGroupById", id);
    }
    
    /**
     * 根据父分组ID、店铺ID和分组名称查询分组信息。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param name     名称。
     * @return 查询到结果返回分组信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Group findGroup(String parentId, String storeId, String name) {
		
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
		params.put(STORE_ID, storeId);
		params.put(PARENT_ID, parentId);
		params.put(NAME, name);
		
		//返回查询结果
        return selectOne("findGroupByParentIdAndName",params);
    }
    
    /**
     * 根据商品ID查询分组列表(分组状态为正常)。
     * @param goodsId 商品ID。
     * @return 查询到结果返回分组列表，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<Group> findGoodsGroups(String goodsId) {
    	
    	//返回查询结果
        return selectList("findGroupsByGoodsId",goodsId);
    }
    
    /**
     * 根据分组ID、商品ID查询分组信息。
     * @param id 分组ID。
     * @param goodsId 商品ID。
     * @return 查询到结果返回分组信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public Group findGroup(String id, String goodsId) {
    	
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(ID, id);
    	params.put(GOODS_ID, goodsId);
    	
    	//返回查询结果
        return selectOne("findGroupByIdAndGoodsId",params);
    }
    
}
