package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.LogisticsMethod;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>LogisticsMethodDao</CODE> 物流方式信息 DAO 类，负责对物流方式信息数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class LogisticsMethodDao extends MyBatisBaseDao<LogisticsMethod> {

	/**
	 * 批量添加物流方式信息。
	 * @param logisticsMethods 待添加的物流方式列表。
	 * @return 添加记录数：成功返回添加记录数，失败返回0。
	 */
	public int saveBatch(List<LogisticsMethod> logisticsMethods){
		
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(LOGISTICS_METHODS, logisticsMethods);
		
		//返回添加结果
		return insertBatch("saveBatch", params);
	}
	
	/**
	 * 根据运费模板ID删除物流方式信息。
	 * @param freightId 运费模板ID。
	 * @return 删除记录数：成功返回删除记录数，失败返回0。
	 */
	public int delete(String freightId){
		
		//返回删除结果
		return delete("deleteByFreightId", freightId);
	}
	
}
