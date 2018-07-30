package com.dreawer.goods.persistence;

import static com.dreawer.goods.constants.DomainConstants.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dreawer.goods.domain.GoodsPropertyValue;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>GoodsPropertyValueDao</CODE> 商品属性值信息 DAO 类，负责对商品属性数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class GoodsPropertyValueDao extends MyBatisBaseDao<GoodsPropertyValue> {

    /**
     * 批量添加商品属性值信息。
     * @param merchandiseProperty 商品属性
     * @return 添加成功记录数：成功返回添加记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int saveBatch(List<GoodsPropertyValue> goodsPropertyValues){
    	
    	//返回添加结果
    	return insertBatch("saveBatch", goodsPropertyValues);
    }
    
    /**
     * 根据属性值ID删除商品属性值。
     * @param propertyValueId 属性值ID
     * @return 删除成功记录数：成功返回删除记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteByPropertyValueId(String propertyValueId){
    	
    	//返回删除结果
    	return delete("deleteByPropertyValueId", propertyValueId);
    }
    
    /**
     * 根据属性值ID更新商品属性值基本信息。
     * @param goodsPropertyValue 待更新的商品属性值。
     * @return 更新成功记录数：成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateByPropertyValueId(GoodsPropertyValue goodsPropertyValue){
    	
    	//返回更新结果
    	return update("updateByPropertyValueId", goodsPropertyValue);
    }
    
    /**
     * 根据商品ID、商品属性名ID、属性值ID查询商品属性值列表(根据排列序号正序)。
     * @param goodsId 商品ID。
     * @param propertyNameId 属性名ID。
     * @param propertyNameId 属性值ID。
     * @return 查询到结果返回商品属性值列表，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<GoodsPropertyValue> findGoodsPropertyValues(String goodsId, String propertyNameId, String propertyValueId) {
        
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_ID, goodsId);
    	params.put(PROPERTY_NAME_ID, propertyNameId);
    	params.put(PROPERTY_VALUE_ID, propertyValueId);
    	
    	//返回查询结果
    	return selectList("findGoodsPropertyValues", params);
    }
    
    /**
     * 根据商品ID、商品属性名ID、属性值ID查询商品属性值。
     * @param goodsId 商品ID。
     * @param propertyNameId 属性名ID。
     * @param propertyNameId 属性值ID。
     * @return 查询到结果返回商品属性值信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public GoodsPropertyValue findGoodsPropertyValueForUpdate(String goodsId, String propertyNameId, String propertyValueId) {
        
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_ID, goodsId);
    	params.put(PROPERTY_NAME_ID, propertyNameId);
    	params.put(PROPERTY_VALUE_ID, propertyValueId);
    	
    	//返回查询结果
    	return selectOne("findGoodsPropertyValueForUpdate", params);
    }
    
}
