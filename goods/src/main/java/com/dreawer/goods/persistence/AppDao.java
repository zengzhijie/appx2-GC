package com.dreawer.goods.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.dreawer.goods.constants.DomainConstants.*;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.App;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>AppDao</CODE> 小程序应用信息 DAO 类，负责对城市信息数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */

@Repository
public class AppDao extends MyBatisBaseDao<App> {

	/**
	 * 添加小程序应用信息。
	 * @param app 待添加的小程序应用信息。
	 * @return 添加记录数：成功返回添加记录数，失败返回0。
	 */
	public int save(App app){
		
		//返回添加结果
		return insert("save", app);
	}
	
	/**
	 * 删除小程序应用信息。
	 * @param goodsId 商品ID。
	 * @return 删除成功记录数：成功返回删除记录数，失败返回0。
	 */
	public int delete(String goodsId){
		
		//返回添加结果
		return delete("delete", goodsId);
	}
	
	/**
	 * 批量删除小程序应用信息。
	 * @param goodsIds 商品ID列表。
	 * @return 删除成功记录数：成功返回删除记录数，失败返回0。
	 */
	public int deleteBatch(List<String> goodsIds){
		
		//封装请求参数
		Map<String, Object> params = new HashMap<>();
		params.put(IDS, goodsIds);
		
		//返回添加结果
		return deleteBatch("deleteBatch", params);
	}
	
	/**
	 * 查询小程序应用信息。
	 * @param goodsId 商品ID。
	 * @return 查询到结果返回小程序应用信息，未查询到结果返回NULL。
	 */
	public App findApp(String goodsId){
		
		//返回添加结果
		return selectOne("findAppByGoodsId", goodsId);
	}
	
}
