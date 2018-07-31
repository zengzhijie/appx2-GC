package com.dreawer.goods.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import static com.dreawer.goods.constants.DomainConstants.*;
import com.dreawer.goods.domain.City;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>CityDao</CODE> 城市信息 DAO 类，负责对城市信息数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */

@Repository
public class CityDao extends MyBatisBaseDao<City> {

	/**
	 * 批量添加城市信息。
	 * @param cities 待添加的城市信息列表。
	 * @return 添加记录数：成功返回添加记录数，失败返回0。
	 */
	public int saveBatch(List<City> cities){
		
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(CITIES, cities);
		
		//返回添加结果
		return insertBatch("saveBatch", params);
	}
	
}
