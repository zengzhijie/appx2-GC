package com.dreawer.goods.service;

import static com.dreawer.goods.constants.MessageConstants.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.dreawer.goods.domain.App;
import com.dreawer.goods.domain.City;
import com.dreawer.goods.domain.Freight;
import com.dreawer.goods.domain.FreightParam;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.goods.domain.GoodsPropertyValue;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.domain.LogisticsMethod;
import com.dreawer.goods.domain.Sku;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.persistence.GoodsDao;
import com.dreawer.goods.persistence.GroupDao;
import com.dreawer.goods.view.ViewApp;
import com.dreawer.goods.view.ViewCity;
import com.dreawer.goods.view.ViewFreight;
import com.dreawer.goods.view.ViewFreightParam;
import com.dreawer.goods.view.ViewGoods;
import com.dreawer.goods.view.ViewGoodsPropertyName;
import com.dreawer.goods.view.ViewGoodsPropertyValue;
import com.dreawer.goods.view.ViewGroup;
import com.dreawer.goods.view.ViewLogisticsMethod;
import com.dreawer.goods.view.ViewSku;

/**
 * 基础服务类
 */
public class BaseService {

	@Autowired 
	private GroupDao groupDao; //分组DAO
	
    @Autowired
    private GoodsDao goodsDao; // 商品信息DAO
	
	/**
	 * 商品视图列表排序（根据创建时间倒叙）
	 * @param viewGoodses 待排序的商品视图列表。
	 * @return viewGoodses 排序后的商品视图列表。
	 */
	protected List<ViewGoods> sortViewGoodsByCreateTimeDesc(List<ViewGoods> viewGoodses){
		
		Collections.sort(viewGoodses, new Comparator<ViewGoods>(){
			@Override
			public int compare(ViewGoods viewGoods1, ViewGoods viewGoods2) {
				return viewGoods2.getCreateTime().compareTo(viewGoods1.getCreateTime());
			}
		});
		
		return viewGoodses;
	}
    
	/**
	 * 购买信息排序（根据SKUID排序）
	 * @param purchaseInfos 待排序的购买信息列表。
	 * @return purchaseInfos 排序后的购买信息列表。
	 */
	protected List<PurchaseInfo> sortPurchaseInfoBySkuId(List<PurchaseInfo> purchaseInfos){
		
		Collections.sort(purchaseInfos, new Comparator<PurchaseInfo>(){
			@Override
			public int compare(PurchaseInfo purchaseInfo1, PurchaseInfo purchaseInfo2) {
				return purchaseInfo1.getSkuId().compareTo(purchaseInfo2.getSkuId());
			}
		});
		
		return purchaseInfos;
	}
    
	/**
	 * 物流方式列表排序（根据排列序号正序）
	 * @param viewLogisticsMethods 待排序的物流方式视图列表。
	 * @return viewGoodses 排序后的物流方式视图列表。
	 */
	protected List<ViewLogisticsMethod> sortViewLogisticsMethodsBySquenceAsc(List<ViewLogisticsMethod> viewLogisticsMethods){
		
		Collections.sort(viewLogisticsMethods, new Comparator<ViewLogisticsMethod>(){
			@Override
			public int compare(ViewLogisticsMethod viewLogisticsMethod1, ViewLogisticsMethod viewLogisticsMethod2) {
				return viewLogisticsMethod1.getSquence().compareTo(viewLogisticsMethod2.getSquence());
			}
		});
		
		return viewLogisticsMethods;
	}
	
	/**
	 * 递归删除分组信息
	 * @param id 分组ID。
	 * @param storeId 店铺ID。
	 */
	protected void recursiveDeleteGroup(String id, String storeId) throws Exception{
		
    	//判断分组中是否存在商品
    	List<Goods> goodses = goodsDao.findGoodses(storeId, id, null, null, null, null, null, null, null, null);
    	if(goodses != null && goodses.size()>0){
    		throw new Exception(PERMISSIONS_ERROR_DATA_NOT_ALLOW); // 分组中存在商品不允许删除
    	}
		
		//判断该分组是否为父分组
		Group group = groupDao.findGroup(id);
		if(!group.getIsParent()){
			//不是父分组，则执行删除
			groupDao.delete(id);
		}else{
			
			//执行删除
			groupDao.delete(id);
			
			//获取子分组列表
			List<Group> childGroups = groupDao.findChildGroups(id, storeId, null);
			
			for (Group childGroup : childGroups) {
				recursiveDeleteGroup(childGroup.getId(), storeId);
			}
		}
	}
	
	
    /**
     * 转换运费参数视图。
     * @param freightParam 运费参数信息。
     * @return 运费参数视图。
     */
    protected ViewFreightParam convertFreightParamView(FreightParam freightParam){

    	//创建视图对象
    	ViewFreightParam viewFreightParam = new ViewFreightParam();
    	
    	//判断运费参数信息是否为NULL
    	if(freightParam != null){
    		//封装运费参数信息到视图对象
        	viewFreightParam.setFreightId(freightParam.getFreightId());
        	viewFreightParam.setGoodsId(freightParam.getGoodsId());
        	viewFreightParam.setPrice(freightParam.getPrice());
        	viewFreightParam.setType(freightParam.getType());
        	viewFreightParam.setAmount(freightParam.getAmount());
    	}

        //返回结果
        return viewFreightParam;
    }
    
    /**
     * 转换运费模板视图。
     * @param freight 运费模板信息。
     * @return 运费模板视图。
     */
    protected ViewFreight convertFreightView(Freight freight){
    	
    	//创建运费模板视图对象
    	ViewFreight viewFreight = new ViewFreight();
    	
    	//创建物流方式视图列表
    	List<ViewLogisticsMethod> viewLogisticsMethods = new ArrayList<>(); // 
    	
    	//判断运费模板信息是否为NULL
    	if(freight != null){
    		
        	//封装运费模板视图
        	viewFreight.setId(freight.getId());
        	viewFreight.setName(freight.getName());
        	viewFreight.setDeliveryAddress(freight.getDeliveryAddress());
        	viewFreight.setDeliveryTime(freight.getDeliveryTime());
        	viewFreight.setPayer(freight.getPayer());
        	viewFreight.setPricingMethod(freight.getPricingMethod());
        	viewFreight.setCreaterId(freight.getCreaterId());
        	viewFreight.setCreateTime(freight.getCreateTime());
        	
        	
        	//判断物流方式信息列表是否为空
        	if(freight.getLogisticsMethods() != null && freight.getLogisticsMethods().size() > 0){
            	
            	//封装物流方式视图列表
            	for (LogisticsMethod logisticsMethod : freight.getLogisticsMethods()) {
        			
            		//创建物流方式视图对象
            		ViewLogisticsMethod viewLogisticsMethod = new ViewLogisticsMethod();
            		
            		//创建城市信息视图对象列表
            		List<ViewCity> viewCities = new ArrayList<>(); 
            		
            		//封装物流方式视图
            		viewLogisticsMethod.setId(logisticsMethod.getId());
            		viewLogisticsMethod.setIsDefault(logisticsMethod.getIsDefault());
            		viewLogisticsMethod.setType(logisticsMethod.getType());
            		viewLogisticsMethod.setStartPrice(logisticsMethod.getStartPrice());
            		viewLogisticsMethod.setStartQuantity(logisticsMethod.getStartQuantity());
            		viewLogisticsMethod.setIncrementPrice(logisticsMethod.getIncrementPrice());
            		viewLogisticsMethod.setIncrementQuantity(logisticsMethod.getIncrementQuantity());
            		
            		
            		//添加到视图列表
            		viewLogisticsMethods.add(viewLogisticsMethod);
            		
            		//判断城市信息列表是否未空
            		if(logisticsMethod.getCities() != null && logisticsMethod.getCities().size() > 0){
                		
                		//封装城市信息视图
                		for (City city : logisticsMethod.getCities()) {
            				
                			//创建城市信息视图对象
                			ViewCity viewCity = new ViewCity();
                			
                			//封装城市信息视图
                			viewCity.setId(city.getId());
                			viewCity.setName(city.getName());
                			viewCity.setParentId(city.getParentId());
                			viewCity.setCityId(city.getCityId());
                			
                			//添加到视图列表
                			viewCities.add(viewCity);
            			}
                		viewLogisticsMethod.setCities(viewCities);
            		}
        		}
            	viewLogisticsMethods = sortViewLogisticsMethodsBySquenceAsc(viewLogisticsMethods);
            	viewFreight.setLogisticsMethods(viewLogisticsMethods);
        	}
    	}
    	
        //返回结果
        return viewFreight;
    }
    
    /**
     * 转换运费模板视图列表。
     * @param freights 运费模板列表。
     * @return 运费模板视图列表。
     */
    protected List<ViewFreight> convertFreightViews(List<Freight> freights){

    	//创建运费模板视图列表
    	List<ViewFreight> viewFreights = new ArrayList<>();
    	
    	//判断运费模板信息列表是否为空
    	if(freights != null && freights.size()>0){
    		
    		for (Freight freight : freights) {
        		
            	//创建运费模板视图对象
            	ViewFreight viewFreight = new ViewFreight();
            	
            	//创建物流方式视图列表
            	List<ViewLogisticsMethod> viewLogisticsMethods = new ArrayList<>(); // 
        		
            	//封装运费模板视图
            	viewFreight.setId(freight.getId());
            	viewFreight.setName(freight.getName());
            	viewFreight.setDeliveryAddress(freight.getDeliveryAddress());
            	viewFreight.setDeliveryTime(freight.getDeliveryTime());
            	viewFreight.setPayer(freight.getPayer());
            	viewFreight.setPricingMethod(freight.getPricingMethod());
            	viewFreight.setCreaterId(freight.getCreaterId());
            	viewFreight.setCreateTime(freight.getCreateTime());
            	
            	//添加到视图列表
            	viewFreights.add(viewFreight);
            	
            	//判断物流方式信息列表是否为空
            	if(freight.getLogisticsMethods() != null && freight.getLogisticsMethods().size() > 0){
                	
                	//封装物流方式视图列表
                	for (LogisticsMethod logisticsMethod : freight.getLogisticsMethods()) {
            			
                		//创建物流方式视图对象
                		ViewLogisticsMethod viewLogisticsMethod = new ViewLogisticsMethod();
                		
                		//创建城市信息视图对象列表
                		List<ViewCity> viewCities = new ArrayList<>(); 
                		
                		//封装物流方式视图
                		viewLogisticsMethod.setId(logisticsMethod.getId());
                		viewLogisticsMethod.setIsDefault(logisticsMethod.getIsDefault());
                		viewLogisticsMethod.setType(logisticsMethod.getType());
                		viewLogisticsMethod.setStartPrice(logisticsMethod.getStartPrice());
                		viewLogisticsMethod.setStartQuantity(logisticsMethod.getStartQuantity());
                		viewLogisticsMethod.setIncrementPrice(logisticsMethod.getIncrementPrice());
                		viewLogisticsMethod.setIncrementQuantity(logisticsMethod.getIncrementQuantity());
                		
                		//添加到视图列表
                		viewLogisticsMethods.add(viewLogisticsMethod);
                		
                		//判断城市信息列表是否未空
                		if(logisticsMethod.getCities() != null && logisticsMethod.getCities().size() > 0){
                    		
                    		//封装城市信息视图
                    		for (City city : logisticsMethod.getCities()) {
                				
                    			//创建城市信息视图对象
                    			ViewCity viewCity = new ViewCity();
                    			
                    			//封装城市信息视图
                    			viewCity.setId(city.getId());
                    			viewCity.setName(city.getName());
                    			viewCity.setParentId(city.getParentId());
                    			viewCity.setCityId(city.getCityId());
                    			
                    			//添加到视图列表
                    			viewCities.add(viewCity);
                			}
                    		viewLogisticsMethod.setCities(viewCities);
                		}
            		}
                	viewLogisticsMethods = sortViewLogisticsMethodsBySquenceAsc(viewLogisticsMethods);
                	viewFreight.setLogisticsMethods(viewLogisticsMethods);
            	}
			}
    	}
    	
    	//返回结果
    	return viewFreights;
    }
    
    /**
     * 转换分组视图。
     * @param group 分组信息。
     * @return 分组视图。
     */
    protected ViewGroup convertGroupView(Group group){

    	//创建分组视图
    	ViewGroup viewGroup = new ViewGroup();
    	
    	//判断分组是否为空
    	if(group != null){
			//封装分组视图
			viewGroup.setId(group.getId());
			viewGroup.setStoreId(group.getStoreId());
			viewGroup.setName(group.getName());
			viewGroup.setParentId(group.getParentId());
			viewGroup.setIsParent(group.getIsParent());
			viewGroup.setSquence(group.getSquence());
			viewGroup.setLogo(group.getLogo());
			viewGroup.setIntro(group.getIntro());
			viewGroup.setStatus(group.getStatus());
			viewGroup.setUrl(group.getUrl());
			viewGroup.setGoodsQuantity(group.getGoodsQuantity());
			viewGroup.setCreaterId(group.getCreaterId());
			viewGroup.setCreateTime(group.getCreateTime());
			viewGroup.setRemark(group.getRemark());
    	}
    	
    	//返回处理结果
    	return viewGroup;
    }
    
    /**
     * 转换分组视图列表。
     * @param groups 分组列表。
     * @return 分组视图列表。
     */
    protected List<ViewGroup> convertGroupViews(List<Group> groups){

    	//创建分组视图列表
    	List<ViewGroup> viewGroups = new ArrayList<>();
    	
    	//判断分组信息列表是否为空
    	if(groups != null && groups.size()>0){
    		for (Group group : groups) {
				
    			//创建分组视图对象
    			ViewGroup viewGroup = new ViewGroup();
    			
    			//封装分组视图
    			viewGroup.setId(group.getId());
    			viewGroup.setStoreId(group.getStoreId());
    			viewGroup.setName(group.getName());
    			viewGroup.setParentId(group.getParentId());
    			viewGroup.setIsParent(group.getIsParent());
    			viewGroup.setSquence(group.getSquence());
    			viewGroup.setLogo(group.getLogo());
    			viewGroup.setIntro(group.getIntro());
    			viewGroup.setStatus(group.getStatus());
    			viewGroup.setUrl(group.getUrl());
    			viewGroup.setGoodsQuantity(group.getGoodsQuantity());
    			viewGroup.setCreaterId(group.getCreaterId());
    			viewGroup.setCreateTime(group.getCreateTime());
    			viewGroup.setRemark(group.getRemark());
    			
    			//添加到视图列表
    			viewGroups.add(viewGroup);
			}
    	}
    	
    	//返回处理结果
    	return viewGroups;
    }
    
    /**
     * 转换SKU视图列表。
     * @param skus SKU列表。
     * @return SKU视图列表。
     */
    protected List<ViewSku> convertSkuViews(List<Sku> skus){

    	//创建SKU视图列表
    	List<ViewSku> viewSkus = new ArrayList<>();
    	
    	//判断SKU信息列表是否为空
    	if(skus != null && skus.size()>0){
    		for (Sku sku : skus) {
    			
    			//创建SKU视图对象
    			ViewSku viewSku = new ViewSku();
    			
    			//封装SKU视图
    			viewSku.setId(sku.getId());
    			viewSku.setInventoryType(sku.getInventoryType());
    			viewSku.setInventory(sku.getInventory());
    			viewSku.setLockedInventory(sku.getLockedInventory());
    			viewSku.setPurchasableInventory(sku.getInventory() - sku.getLockedInventory());
    			viewSku.setSalesVolume(sku.getSalesVolume());
    			viewSku.setOriginalPrice(sku.getOriginalPrice());
    			viewSku.setPrice(sku.getPrice());
    			viewSku.setDescription(sku.getDescription());
    			viewSku.setCode(sku.getCode());
    			viewSku.setBarcode(sku.getBarcode());
    			viewSku.setRemark(sku.getRemark());
    			
    			//添加到视图列表
    			viewSkus.add(viewSku);
			}
    	}
    	
    	//返回处理结果
    	return viewSkus;
    }
    
    /**
     * 转换SKU视图。
     * @param skus SKU信息。
     * @return SKU视图。
     */
    protected ViewSku convertSkuView(Sku sku){
    	
		//创建SKU视图对象
		ViewSku viewSku = new ViewSku();
    	
    	//判断SKU信息列表是否为空
    	if(sku != null){
    		
			//封装SKU视图
			viewSku.setId(sku.getId());
			viewSku.setInventoryType(sku.getInventoryType());
			viewSku.setInventory(sku.getInventory());
			viewSku.setLockedInventory(sku.getLockedInventory());
			viewSku.setPurchasableInventory(sku.getInventory() - sku.getLockedInventory());
			viewSku.setSalesVolume(sku.getSalesVolume());
			viewSku.setOriginalPrice(sku.getOriginalPrice());
			viewSku.setPrice(sku.getPrice());
			viewSku.setDescription(sku.getDescription());
			viewSku.setCode(sku.getCode());
			viewSku.setBarcode(sku.getBarcode());
			viewSku.setRemark(sku.getRemark());
    		
    	}
    	
    	//返回处理结果
    	return viewSku;
    }
    
    /**
     * 转换商品属性值视图列表。
     * @param propertyValues 商品属性值列表。
     * @return 商品属性值视图列表。
     */
    protected List<ViewGoodsPropertyValue> convertGoodsPropertyValueViews(List<GoodsPropertyValue> propertyValues){

    	//创建商品属性值视图列表
    	List<ViewGoodsPropertyValue> viewGoodsPropertyValues = new ArrayList<>();
    	
    	//判断商品属性值信息列表是否为空
    	if(propertyValues != null && propertyValues.size()>0){
    		for (GoodsPropertyValue goodsPropertyValue : propertyValues) {
    			
    			//创建商品属性值信息视图对象
    			ViewGoodsPropertyValue viewGoodsPropertyValue = new ViewGoodsPropertyValue();
    			
    			//封装商品属性值信息视图
    			viewGoodsPropertyValue.setId(goodsPropertyValue.getId());
    			viewGoodsPropertyValue.setPropertyValueId(goodsPropertyValue.getPropertyValueId());
    			viewGoodsPropertyValue.setPropertyNameId(goodsPropertyValue.getPropertyNameId());
    			viewGoodsPropertyValue.setGoodsId(goodsPropertyValue.getGoodsId());
    			viewGoodsPropertyValue.setPropertyNameType(goodsPropertyValue.getPropertyNameType());
    			viewGoodsPropertyValue.setType(goodsPropertyValue.getType());
    			viewGoodsPropertyValue.setSquence(goodsPropertyValue.getSquence());
    			viewGoodsPropertyValue.setName(goodsPropertyValue.getName());
    			viewGoodsPropertyValue.setIsDefaultImage(goodsPropertyValue.getIsDefaultImage());
    			viewGoodsPropertyValue.setImageUrl(goodsPropertyValue.getImageUrl());
    			viewGoodsPropertyValue.setRemark(goodsPropertyValue.getRemark());
    			
    			//添加到视图列表
    			viewGoodsPropertyValues.add(viewGoodsPropertyValue);
			}
    	}
    	
    	//返回处理结果
    	return viewGoodsPropertyValues;
    }
    
    /**
     * 转换商品属性名视图列表。
     * @param propertyNames 商品属性名列表。
     * @return 商品属性名视图列表。
     */
    protected List<ViewGoodsPropertyName> convertGoodsPropertyNameViews(List<GoodsPropertyName> propertyNames){

    	//创建商品属性名视图列表
    	List<ViewGoodsPropertyName> viewGoodsPropertyNames = new ArrayList<>();
    	
    	//判断商品属性名信息列表是否为空
    	if(propertyNames != null && propertyNames.size()>0){
    		for (GoodsPropertyName goodsPropertyName : propertyNames) {
    			
    			//获取商品属性值视图列表
    			List<ViewGoodsPropertyValue> viewGoodsPropertyValues = convertGoodsPropertyValueViews(goodsPropertyName.getPropertyValues());
    			
    			//创建商品属性名信息视图对象
    			ViewGoodsPropertyName viewGoodsPropertyName = new ViewGoodsPropertyName();
    			
    			//封装商品属性名信息视图
    			viewGoodsPropertyName.setId(goodsPropertyName.getId());
    			viewGoodsPropertyName.setPropertyNameId(goodsPropertyName.getPropertyNameId());
    			viewGoodsPropertyName.setName(goodsPropertyName.getName());
    			viewGoodsPropertyName.setCategoryId(goodsPropertyName.getCategoryId());
    			viewGoodsPropertyName.setType(goodsPropertyName.getType());
    			viewGoodsPropertyName.setSquence(goodsPropertyName.getSquence());
    			viewGoodsPropertyName.setIsSales(goodsPropertyName.getIsSales());
    			viewGoodsPropertyName.setIsBasic(goodsPropertyName.getIsBasic());
    			viewGoodsPropertyName.setIsCheckbox(goodsPropertyName.getIsCheckbox());
    			viewGoodsPropertyName.setIsColor(goodsPropertyName.getIsColor());
    			viewGoodsPropertyName.setIsInput(goodsPropertyName.getIsInput());
    			viewGoodsPropertyName.setIsKey(goodsPropertyName.getIsKey());
    			viewGoodsPropertyName.setIsRadio(goodsPropertyName.getIsRadio());
    			viewGoodsPropertyName.setIsRequired(goodsPropertyName.getIsRequired());
    			viewGoodsPropertyName.setIsSearch(goodsPropertyName.getIsSearch());
    			viewGoodsPropertyName.setIsSelect(goodsPropertyName.getIsSelect());
    			viewGoodsPropertyName.setIsVisualEditor(goodsPropertyName.getIsVisualEditor());
    			viewGoodsPropertyName.setIsImage(goodsPropertyName.getIsImage());
    			viewGoodsPropertyName.setPropertyValues(viewGoodsPropertyValues);

    			//添加到视图列表
    			viewGoodsPropertyNames.add(viewGoodsPropertyName);
    			
			}
    	}
    	
    	//返回处理结果
    	return viewGoodsPropertyNames;
    }
    
    /**
     * 转换小程序应用信息视图。
     * @param app 小程序应用信息。
     * @return 商品视图列表。
     */
    protected ViewApp convertAppView(App app){

    	//创建小程序视图对象
    	ViewApp viewApp = new ViewApp();
    	
    	//判断小程序应用信息是否为空
    	if(app != null){
    		
    		//封装小程序应用信息视图
    		viewApp.setTempletId(app.getTempletId());
    		viewApp.setAppCode(app.getAppCode());
    		viewApp.setImage(app.getImage());
    		
    	}
    	
    	//返回处理结果
    	return viewApp;
    }
    
    /**
     * 转换商品视图列表。
     * @param goodses 商品列表。
     * @return 商品视图列表。
     */
    protected List<ViewGoods> convertGoodsViews(List<Goods> goodses){

    	//创建商品视图列表
    	List<ViewGoods> viewGoodses = new ArrayList<>();
    	
    	//判断商品信息列表是否为空
    	if(goodses != null && goodses.size()>0){
    		for (Goods goods : goodses) {
    			
    			//获取运费参数视图
    			ViewFreightParam viewFreightParam = convertFreightParamView(goods.getFreightParam());
    			
    			//获取分组视图列表
    			List<ViewGroup> viewGroups = convertGroupViews(goods.getGroups());
    			
    			//获取SKU视图列表
    			List<ViewSku> viewSkus = convertSkuViews(goods.getSkus());
    			
    			//获取商品属性名视图列表
    			List<ViewGoodsPropertyName> viewGoodsPropertyNames = convertGoodsPropertyNameViews(goods.getPropertyNames());
    			
    			//获取小程序应用信息视图
    			ViewApp viewApp = convertAppView(goods.getApp());
    			
    			//创建商品视图对象
    			ViewGoods viewGoods = new ViewGoods();
    			
    			//封装商品视图
    			viewGoods.setId(goods.getId());
    			viewGoods.setStoreId(goods.getStoreId());
    			viewGoods.setName(goods.getName());
    			viewGoods.setCategoryId(goods.getCategoryId());
    			viewGoods.setMinPrice(goods.getMinPrice());
    			viewGoods.setMinOriginalPrice(goods.getMinOriginalPrice());
    			viewGoods.setInventoryType(goods.getInventoryType());
    			
    			//计算商品总库存
    			Integer totalInventory = 0;
    			Integer totalLockedInventory = 0;
    			Integer totalPurchasableInventory = 0;
    			for (ViewSku sku : viewSkus) {
    				totalInventory = totalInventory + sku.getInventory();
    				totalLockedInventory = totalLockedInventory + sku.getLockedInventory();
    				totalPurchasableInventory = totalPurchasableInventory + sku.getPurchasableInventory();
    			}
    			
    			viewGoods.setTotalInventory(totalInventory);
    			viewGoods.setTotalLockedInventory(totalLockedInventory);
    			viewGoods.setTotalPurchasableInventory(totalPurchasableInventory);
    			viewGoods.setMainDiagram(goods.getMainDiagram());
    			viewGoods.setDetail(goods.getDetail());
    			viewGoods.setService(goods.getService());
    			viewGoods.setStatus(goods.getStatus());
    			viewGoods.setIsRecommend(goods.getIsRecommend());
    			viewGoods.setCreaterId(goods.getCreaterId());
    			viewGoods.setCreateTime(goods.getCreateTime());
    			viewGoods.setRemark(goods.getRemark());
    			viewGoods.setFreightParam(viewFreightParam);
    			viewGoods.setGroups(viewGroups);
    			viewGoods.setSkus(viewSkus);
    			viewGoods.setPropertyNames(viewGoodsPropertyNames);
    			viewGoods.setViewApp(viewApp);
    			
    			//添加到视图列表
    			viewGoodses.add(viewGoods);
			}
    	}
    	
    	//返回处理结果
    	return viewGoodses;
    }
    
    /**
     * 转换商品视图。
     * @param goods 商品信息。
     * @return 商品视图。
     */
    protected ViewGoods convertGoodsView(Goods goods){

    	//创建商品视图
    	ViewGoods viewGoods = new ViewGoods();
    	
    	//判断商品信息是否为空
    	if(goods != null){
    		
			//获取运费参数视图
			ViewFreightParam viewFreightParam = convertFreightParamView(goods.getFreightParam());
    		
			//获取分组视图列表
			List<ViewGroup> viewGroups = convertGroupViews(goods.getGroups());
			
			//获取SKU视图列表
			List<ViewSku> viewSkus = convertSkuViews(goods.getSkus());
			
			//获取商品属性名视图列表
			List<ViewGoodsPropertyName> viewGoodsPropertyNames = convertGoodsPropertyNameViews(goods.getPropertyNames());
			
			//获取小程序应用信息视图
			ViewApp viewApp = convertAppView(goods.getApp());
			
			//封装商品视图
			viewGoods.setId(goods.getId());
			viewGoods.setStoreId(goods.getStoreId());
			viewGoods.setName(goods.getName());
			viewGoods.setCategoryId(goods.getCategoryId());
			viewGoods.setMinPrice(goods.getMinPrice());
			viewGoods.setMinOriginalPrice(goods.getMinOriginalPrice());
			viewGoods.setInventoryType(goods.getInventoryType());
			
			//计算商品总库存
			Integer totalInventory = 0;
			Integer totalLockedInventory = 0;
			Integer totalPurchasableInventory = 0;
			for (ViewSku sku : viewSkus) {
				totalInventory = totalInventory + sku.getInventory();
				totalLockedInventory = totalLockedInventory + sku.getLockedInventory();
				totalPurchasableInventory = totalPurchasableInventory + sku.getPurchasableInventory();
			}
			
			viewGoods.setTotalInventory(totalInventory);
			viewGoods.setTotalLockedInventory(totalLockedInventory);
			viewGoods.setTotalPurchasableInventory(totalPurchasableInventory);
			viewGoods.setMainDiagram(goods.getMainDiagram());
			viewGoods.setDetail(goods.getDetail());
			viewGoods.setService(goods.getService());
			viewGoods.setStatus(goods.getStatus());
			viewGoods.setIsRecommend(goods.getIsRecommend());
			viewGoods.setCreaterId(goods.getCreaterId());
			viewGoods.setCreateTime(goods.getCreateTime());
			viewGoods.setRemark(goods.getRemark());
			viewGoods.setFreightParam(viewFreightParam);
			viewGoods.setGroups(viewGroups);
			viewGoods.setSkus(viewSkus);
			viewGoods.setPropertyNames(viewGoodsPropertyNames);
			viewGoods.setViewApp(viewApp);
    	}
    	
    	//返回处理结果
    	return viewGoods;
    }
    
}
