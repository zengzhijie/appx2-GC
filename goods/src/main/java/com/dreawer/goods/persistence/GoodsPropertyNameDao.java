package com.dreawer.goods.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.dreawer.goods.constants.DomainConstants.*;
import org.springframework.stereotype.Repository;
import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.persistence.mybatis.MyBatisBaseDao;

/**
 * <CODE>GoodsPropertyNameDao</CODE> 商品属性名信息 DAO 类，负责对商品属性数据进行访问和操作。
 * @since Dreawer 2.0
 * @version 1.0
 */
@Repository
public class GoodsPropertyNameDao extends MyBatisBaseDao<GoodsPropertyName> {

    /**
     * 批量添加商品属性名信息。
     * @param goodsPropertyNames 商品属性名列表。
     * @return 添加成功记录数：成功返回添加记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int saveBatch(List<GoodsPropertyName> goodsPropertyNames){
    	
    	//返回查询结果
    	return insertBatch("saveBatch", goodsPropertyNames);
    }
    
    /**
     * 根据属性名ID删除商品属性名和属性值。
     * @param propertyNameId 属性名ID。
     * @return 删除成功记录数：成功返回删除记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteByPropertyNameId(String propertyNameId){
    	
    	//返回查询结果
    	return delete("deleteByPropertyNameId", propertyNameId);
    }
    
    /**
     * 根据商品ID删除商品属性名和属性值。
     * @param goodsId 商品ID
     * @return 删除成功记录数：成功返回删除记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int deleteByGoodsId(String goodsId){
    	
    	//返回查询结果
    	return delete("deleteByGoodsId", goodsId);
    }
    
    /**
     * 通过属性名ID更新商品属性名信息。
     * @param goodsPropertyName 待更新的商品属性名信息。
     * @return 更新成功记录数：成功返回更新记录数,失败返回0。
     * @author kael
     * @since 1.0
     */
    public int updateByPropertyNameId(GoodsPropertyName goodsPropertyName){
    	
    	//返回查询结果
    	return update("updateByPropertyNameId", goodsPropertyName);
    }
    
    /**
     * 根据商品ID、属性名ID查询商品属性名列表(根据排列序号正序)。
     * @param goodsId 商品ID。
     * @param propertyNameId 属性名ID。
     * @return 查询到结果返回商品属性名列表，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public List<GoodsPropertyName> findGoodsPropertyNames(String goodsId, String propertyNameId) {
        
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_ID, goodsId);
    	params.put(PROPERTY_NAME_ID, propertyNameId);
    	
    	//返回查询结果
    	return selectList("findGoodsPropertyNames", params);
    }
    
    /**
     * 根据商品ID、属性名ID查询商品属性名。
     * @param goodsId 商品ID。
     * @param propertyNameId 属性名ID。
     * @return 查询到结果返回商品属性名信息，未查询到结果返回NULL。
     * @author kael
     * @since 1.0
     */
    public GoodsPropertyName findGoodsPropertyNameForUpdate(String goodsId, String propertyNameId) {
        
    	//封装请求参数
    	Map<String, Object> params = new HashMap<>();
    	params.put(GOODS_ID, goodsId);
    	params.put(PROPERTY_NAME_ID, propertyNameId);
    	
    	//返回查询结果
    	return selectOne("findGoodsPropertyNameForUpdate", params);
    }
    
}
