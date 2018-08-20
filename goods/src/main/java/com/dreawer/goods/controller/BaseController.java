package com.dreawer.goods.controller;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.goods.domain.Sku;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 * @author kael
 */
public class BaseController{

    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------
    
    /**
     * 获取当前系统时间。
     * @return 当前系统时间。
     * @author kael
     */
    protected Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }
	
    /**
     * 生成UUID。
     * @return 32位UUID。
     * @author kael
     */
    protected String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

	/**
	 * 对SKU进行排序（售价由低到高）。
	 * @param skus 待排序的SKU列表。
	 * @return 排序后的SKU列表。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<Sku> sortSkuByPriceAsc(List<Sku> skus){
		Collections.sort(skus, new Comparator<Sku>(){
			@Override
			public int compare(Sku sku1, Sku sku2) {
				return sku1.getPrice().compareTo(sku2.getPrice());
			}
		});
		return skus;
	}
	
	/**
	 * 对商品SKU进行排序（原价由低到高）。
	 * @param skus 待排序的SKU对象集合。
	 * @return 排序后的SKU对象集合。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<Sku> sortSkuByOriginalPriceAsc(List<Sku> skus){
		Collections.sort(skus, new Comparator<Sku>(){
			@Override
			public int compare(Sku sku1, Sku sku2) {
				return sku1.getOriginalPrice().compareTo(sku2.getOriginalPrice());
			}
		});
		return skus;
	}
    
	/**
	 * 对SKU描述进行排序（排列序号由低到高低到高）。
	 * @param skuDescription 待排序的SKU描述。
	 * @return 排序后的SKU描述。
	 * @author Kael
	 * @since 2.0
	 */
	protected List<String> sortSkuDescription(List<String> skuDescription, List<GoodsPropertyName> goodsPropertyNames){
		Collections.sort(skuDescription, new Comparator<String>(){
			@Override
			public int compare(String desc1, String desc2) {
				
				Integer desc1Squence = 0;
				Integer desc2Squence = 0;
				
				for (GoodsPropertyName goodsPropertyName : goodsPropertyNames) {
					if(desc1.contains(goodsPropertyName.getPropertyNameId())){
						desc1Squence = goodsPropertyName.getSquence();
					}
					if(desc2.contains(goodsPropertyName.getPropertyNameId())){
						desc2Squence = goodsPropertyName.getSquence();
					}
				}
				
				return desc1Squence.compareTo(desc2Squence);
			}
		});
		return skuDescription;
	}
}
