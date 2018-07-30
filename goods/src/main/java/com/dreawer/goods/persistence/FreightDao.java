package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.Freight;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>FreightDao</CODE> 运费模板信息 DAO 类，负责对运费模板数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class FreightDao extends MyBatisBaseDao<Freight> {

	/**
	 * 添加运费模板信息。
	 * @param freight 待添加的运费模板信息。
	 * @return 添加记录数：成功返回1，失败返回0。
	 */
	public int save(Freight freight){
		
		//返回添加结果
		return insert("save", freight);
	}
	
	/**
	 * 批量删除运费模板信息。
	 * @param ids 待删除的运费模板ID列表。
	 * @return 删除记录数：成功返回删除数量，失败返回0。
	 */
	public int deleteBatch(List<String> ids){
		
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(IDS, ids);
		
		//返回删除结果
		return deleteBatch("deleteBatch", params);
	}
	
	/**
	 * 更新运费模板信息。
	 * @param freight 待更新的运费模板信息。
	 * @return 更新记录数：成功返回1，失败返回0。
	 */
	public int update(Freight freight){
		
		//返回更新结果
		return update("update", freight);
	}
	
	/**
	 * 根据店铺ID分页查询运费模板详情列表(按照创建时间倒叙)。
	 * @param storeId    店铺ID。
	 * @param start      分页起始。
	 * @param maxResults 最大结果数。
	 * @return 查询结果：查询到结果返回运费模板详情列表，未查询到结果返回NULL。
	 */
	public List<Freight> findFreightDetails(String storeId, Integer start, Integer pageSize){
		
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(STORE_ID, storeId);
		params.put(START, start);
		params.put(PAGE_SIZE, pageSize);
		
		//返回查询结果
		return selectList("findFreightDetails",params);
	}
	
	/**
	 * 根据店铺ID查询运费模板总数。
	 * @param storeId 店铺ID。
	 * @return 查询结果：查询到结果返回运费模板总数，未查到结果返回0。
	 */
	public int getFreightCount(String storeId){
		
		//返回查询结果
		return count("getFreightCount", storeId);
	}
	
	/**
	 * 根据ID查询运费模板信息。
	 * @param id 运费模板ID。
	 * @return 查询结果：查询到结果返回运费模板信息，未查询到结果返回NULL。
	 */
	public Freight findFreightById(String id){
		
		//返回查询结果
		return selectOne("findFreightById", id);
	}
	
	/**
	 * 根据店铺ID和运费模板名称查询运费模板信息。
	 * @param name	     运费模板名称。
	 * @param storeId 店铺ID。
	 * @return 查询结果：查询到结果返回运费模板信息，未查询到结果返回NULL。
	 */
	public Freight findFreightByNameForUpdate(String name, String storeId){
		
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(STORE_ID, storeId);
		
		params.put(NAME, name);
		
		//返回查询结果
		return selectOne("findFreightByNameForUpdate", params);
	}
	
	/**
	 * 根据店铺ID查询运费模板列表(按照创建时间倒叙)。
	 * @param storeId 店铺ID
	 * @return 查询结果：查询到结果返回运费模板详情列表，未查询到结果返回NULL。
	 */
	public List<Freight> findFreights(String storeId){
		
		//返回查询结果
		return selectList("findFreights", storeId);
	}
	
}
