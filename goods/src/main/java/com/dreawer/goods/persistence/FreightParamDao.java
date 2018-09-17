package com.dreawer.goods.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.FreightParam;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>FreightParamDao</CODE> 运费参数信息 DAO 类，负责对运费参数数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class FreightParamDao extends MyBatisBaseDao<FreightParam> {

	/**
	 * 添加运费参数信息。
	 * @param freightParam 待添加的运费参数信息。
	 * @return 添加记录数：成功返回1，失败返回0。
	 */
	public int save(FreightParam freightParam){
		
		//返回添加结果
		return insert("save", freightParam);
	}
	
	/**
	 * 删除运费参数信息。
	 * @param goodsId 商品ID。
	 * @return 删除记录数：成功返回1，失败返回0。
	 */
	public int delete(String goodsId){
		return delete("delete", goodsId);
	}
	
	/**
	 * 更新运费参数信息。
	 * @param freightParam 待更新的运费参数信息。
	 * @return 更新记录数：成功返回1，失败返回0。
	 */
	public int update(FreightParam freightParam){
		return update("update", freightParam);
	}
	
	/**
	 * 通过商品ID查询运费参数信息。
	 * @param goodsId 商品ID
	 * @return 查询结果：查询到结果返回运费参数信息，未查询到结果返回NULL。
	 */
	public FreightParam findFreightParam(String goodsId){
		return selectOne("findFreightParam", goodsId);
	}
	
	/**
	 * 通过商品ID查询运费参数信息列表。
	 * @param goodsId 商品ID
	 * @return 查询结果：查询到结果返回运费参数信息列表，未查询到结果返回NULL。
	 */
	public List<FreightParam> findFreightParams(String freightId){
		return selectList("findFreightParams", freightId);
	}
}
