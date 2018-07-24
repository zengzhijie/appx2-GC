package com.dreawer.goods.service;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.lang.GroupStatus;
import com.dreawer.goods.persistence.GoodsDao;
import com.dreawer.goods.persistence.GroupDao;
import com.dreawer.goods.view.ViewGroup;
import com.dreawer.responsecode.rcdt.PermissionsError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.RuleError;
import com.dreawer.responsecode.rcdt.Success;

/**
 * <CODE>GroupService</CODE> 分组Service。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
@Service
public class GroupService extends BaseService{

    @Autowired
    private GroupDao groupDao; // 分组信息DAO

    @Autowired
    private GoodsDao goodsDao; // 商品信息DAO
    
    /**
     * 添加分组信息。
     * @param group 待添加的分组信息
     * @return 成功返回分组ID、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode save(Group group){
    	
    	//查询是否存在名称相同的分组信息
    	Group findGroup = groupDao.findGroup(group.getParentId(), group.getStoreId(), group.getName());
    	if(findGroup != null){
    		return RuleError.EXISTED(GROUP); // 分组名称已存在
    	}
    	
    	//父分组ID不为0，即不是顶级分组
		if(!group.getParentId().equals("0")){
			
			//判断父分组的isParent属性
			Group parentGroup = groupDao.findGroup(group.getParentId());
			
			//若父分组的isParent属性为false，更新父分组的isParent属性
			if(!parentGroup.getIsParent()){
				parentGroup.setIsParent(true);
				groupDao.updateIsParent(parentGroup);
			}
		}
    	
    	//添加分组
    	groupDao.save(group);
    	
    	//返回添加结果
    	return Success.SUCCESS(group.getId());
    }
    
    /**
     * 添加分组、商品关系(分组关联多商品)。
     * @param group 待添加的分组信息（分组ID、商品列表）。
     * @return 成功返回成功码、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode saveBatchGroupGoods(Group group){
    	
    	//判断该分组是否存在
    	Group groupInfo = groupDao.findGroup(group.getId());
    	if(groupInfo == null){
    		return RuleError.NON_EXISTENT(GROUP); // 分组不存在
    	}
    	
    	//创建集合封装分组、商品信息列表
    	List<Map<String, Object>> groupGoodses = new ArrayList<>();
    	
    	//获取商品列表
    	List<Goods> goodses = group.getGoodses();
    	
    	//循环商品列表
    	for (Goods goods : goodses) {
    		
    		//判断该商品是否存在
    		Goods findGoods = goodsDao.findGoodsById(goods.getId(), null);
    		if(findGoods == null){
    			return RuleError.NON_EXISTENT(GOODS);
    		}
    		
    		//判断分组中是否存在该商品，存在则不添加
    		Group findGroup = groupDao.findGroup(group.getId(), goods.getId());
    		if(findGroup == null){
    			
    			//创建集合封装分组、商品信息
    			Map<String, Object> groupGoods = new HashMap<>();
    			groupGoods.put(GROUP_ID, group.getId());
    			groupGoods.put(GOODS_ID, goods.getId());
    			groupGoods.put(CREATER_ID, group.getCreaterId());
    			groupGoods.put(CREATE_TIME, group.getCreateTime());
    			
    			//添加到集合中
    			groupGoodses.add(groupGoods);
    		}
    	}
    	
    	//执行添加
    	groupDao.saveBatchGroupGoods(groupGoodses);
    	
    	//增加分组中商品数量
    	groupDao.increaseGoodsQuantity(groupGoodses.size(), group.getId());
    	
    	//返回添加结果
    	return Success.SUCCESS(group.getId());
    }
    
    /**
     * 根据分组ID删除分组信息、商品和分组关系。
     * @param id      分组ID。
     * @param storeId 店铺ID。
     * @return 成功返回成功码、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode delete(String id, String storeId){
    	try {
    		
    		//执行递归删除
			recursiveDeleteGroup(id, storeId);
			
	    	//返回删除结果
	    	return Success.SUCCESS;
			
		} catch (Exception e) {
			return PermissionsError.DATA_NO_ALLOW(GROUP);
		}

    }
    
    /**
     * 批量更新商品、分组关系。
     * @param groupGoodses 待更新的商品分组关系。
     * @param goodsId         商品ID。
     * @param updaterId       更新者ID。
     * @param updateTime      更新时间。
     * @return 成功返回成功码、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode updateBatchGroupGoodses(List<Map<String, Object>> groupGoodses, String updaterId, Timestamp updateTime){
    	
    	//循环集合
    	for (Map<String, Object> groupGoods : groupGoodses) {
			
    		//获取商品ID
    		String goodsId = groupGoods.get(GOODS_ID).toString();
    		
    		//获取原分组ID
    		String originalGroupId = groupGoods.get(ORIGINAL_GROUP_ID).toString();
    		
    		//获取当前分组ID
    		String currentGroupId = groupGoods.get(CURRENT_GROUP_ID).toString();
    		
        	//执行删除
        	groupDao.deleteGroupGoods(goodsId, originalGroupId);
        	
        	//判断当前分组是否存在
        	Group group = null;
        	group = groupDao.findGroup(currentGroupId);
        	if(group == null){
        		return RuleError.NON_EXISTENT(GROUP); // 当前分组不存在
        	}
        	
        	//若当前分组中已存在该商品则不执行添加
        	group = groupDao.findGroup(currentGroupId, goodsId);
        	if(group != null){
        		return Success.SUCCESS; // 分组中已存在该商品返回成功
        	}
        	
        	//执行添加
        	groupDao.saveBatchGroupGoods(groupGoodses);
    		
		}
    	
    	//返回删除结果
    	return Success.SUCCESS;
    }
    
    /**
     * 更新分组排序。
     * @param group 待更新的分组信息待更新的分组信息（分组ID、排列序号、更新者ID、更新时间等）。
     * @return 成功返回分组ID、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode updateSquence(Group group){
    	
    	//执行更新
    	groupDao.updateSquence(group);
    	
    	
    	//返回更新结果
    	return Success.SUCCESS(group.getId());
    }
    
    /**
     * 批量更新分组状态。
     * @param groups 待更新的分组列表。
     * @return 成功返回成功码、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode updateBatchStatus(List<Group> groups){
    	
    	//执行更新
    	groupDao.updateBatchStatus(groups);
    	
    	//返回更新结果
    	return Success.SUCCESS;
    }
    
    /**
     * 更新分组信息（名称、是否父分组属性、排列序号、LOGO、简介、状态、URL、商品数）。
     * @param group 待更新分组信息。
     * @return 成功返回分组ID、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode update(Group group){
    	
    	//查询分组名称是否已存在
    	Group findGroup = groupDao.findGroup(group.getParentId(), group.getStoreId(), group.getName());
    	if(findGroup != null && !findGroup.getId().equals(group.getId())){
    		return RuleError.EXISTED(GROUP_NAME); // 分组名称已存在
    	}
    	
    	
    	//执行更新
    	groupDao.update(group);
    	
    	//返回更新结果
    	return Success.SUCCESS(group.getId());
    }
    
    /**
     * 根据父ID、店铺ID、分组状态查询分组列表(根据排列序号正序)。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态（可为NULL）。
     * @return 查询到结果返回分组列表，未查询到结果返回空，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findGroups(String parentId, String storeId, GroupStatus status) {
    	
    	//执行查询
    	List<Group> groups = groupDao.findChildGroups(parentId, storeId, status);
    	
    	//转换查询结果为视图列表
    	List<ViewGroup> viewGroups = convertGroupViews(groups);
    	
    	//返回查询结果
        return Success.SUCCESS(viewGroups);
    }
    
    /**
     * 根据父ID、店铺ID、分组状态获取分组总数。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态。
     * @return 查询到结果返回分组总数，未查询到结果返回0，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode getGroupsCount(String parentId, String storeId, GroupStatus status) {
        
    	//执行查询
    	int count = groupDao.getChildGroupsCount(parentId, storeId, status);
    	
    	//返回查询结果
    	return Success.SUCCESS(count);
    }
    
    /**
     * 根据分组ID查询分组信息。
     * @param id 分组ID。
     * @return 查询到结果返回分组信息，未查询到结果返回空，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findGroup(String id) {
    	
    	//执行查询
    	Group group = groupDao.findGroup(id);
    	
    	//转换查询结果为视图对象
    	ViewGroup viewGroup = convertGroupView(group);
    	
    	//返回查询结果
        return Success.SUCCESS(viewGroup);
    }
    
    /**
     * 根据商品ID查询分组列表(分组状态为正常)。
     * @param goodsId 商品ID。
     * @return 查询到结果返回分组列表，未查询到结果返回空，失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    public ResponseCode findGoodsGroups(String goodsId) {
    	
    	//执行查询
    	List<Group> groups = groupDao.findGoodsGroups(goodsId);
    	
    	//转换查询结果为视图列表
    	List<ViewGroup> viewGroups = convertGroupViews(groups);
    	
    	//返回查询结果
        return Success.SUCCESS(viewGroups);
    }
    
}
