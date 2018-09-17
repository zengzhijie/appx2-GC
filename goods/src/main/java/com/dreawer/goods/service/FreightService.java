package com.dreawer.goods.service;

import static com.dreawer.goods.constants.DomainConstants.*;
import static com.dreawer.goods.constants.ControllerConstants.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dreawer.goods.domain.City;
import com.dreawer.goods.domain.Freight;
import com.dreawer.goods.domain.FreightParam;
import com.dreawer.goods.domain.LogisticsMethod;
import com.dreawer.goods.lang.FreightType;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.persistence.CityDao;
import com.dreawer.goods.persistence.FreightDao;
import com.dreawer.goods.persistence.FreightParamDao;
import com.dreawer.goods.persistence.LogisticsMethodDao;
import com.dreawer.goods.view.ViewFreight;
import com.dreawer.goods.view.ViewFreightParam;
import com.dreawer.goods.lang.Payer;
import com.dreawer.goods.lang.PricingMethod;
import com.dreawer.responsecode.rcdt.PermissionsError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.RuleError;
import com.dreawer.responsecode.rcdt.Success;

/**
 * <CODE>FreightService</CODE> 运费信息Service。
 * @author kael
 * @since Dreawer 2.0
 * @version 1.0
 */
@Service
public class FreightService extends BaseService{

    @Autowired
    private FreightDao freightDao; // 运费模板信息DAO
    
    @Autowired
    private LogisticsMethodDao logisticsMethodDao; // 物流方式DAO
    
    @Autowired
    private CityDao cityDao; // 城市信息DAO
    
    @Autowired
    private FreightParamDao freightParamDao; // 运费参数信息DAO
    
	/**
	 * 添加运费模板信息。
	 * @param freight 待添加的运费模板信息。
	 * @return 成功返回运费模板ID，失败则返回相应错误码。
	 */
    @Transactional
	public ResponseCode save(Freight freight){
		
		//查询是否存在名称相同的运费模板
		Freight findFreight = freightDao.findFreightByNameForUpdate(freight.getName(), freight.getStoreId());
		if(findFreight != null){
			return RuleError.EXISTED(FREIGHT_+FREIGHT_NAME+":"+freight.getName()); // 运费模板名称已存在
		}
		
		//添加运费模板信息
		freightDao.save(freight);
		
		//获取配送方式列表
		List<LogisticsMethod> logisticsMethods = freight.getLogisticsMethods();
		
		//判断配送方式列表是否为空
		if(logisticsMethods != null && logisticsMethods.size()>0){
			
			//添加配送方式列表
			logisticsMethodDao.saveBatch(logisticsMethods);
			
			//创建List集合接收城市列表
			List<City> cities = new ArrayList<>();
			
			for (LogisticsMethod logisticsMethod : logisticsMethods) {
				
				//判断运费方式指定的城市列表是否为空
				if(logisticsMethod.getCities() != null && logisticsMethod.getCities().size()>0){
					
					//添加城市列表到集合中
					cities.addAll(logisticsMethod.getCities());
				}
			}
			
			//判断城市列表集合是否为空
			if(cities.size() > 0){
				cityDao.saveBatch(cities);
			}
		}
		
		//返回添加结果
		return Success.SUCCESS(freight.getId());
	}
	
	/**
	 * 更新运费模板信息。
	 * @param freight 待更新的运费模板信息。
	 * @return 成功返回运费模板ID，失败则返回相应错误码
	 */
    @Transactional
	public ResponseCode update(Freight freight){
		
		//判断运费模板信息是否存在
		Freight oldFreight = freightDao.findFreightById(freight.getId());
		if(oldFreight == null){
			return RuleError.NON_EXISTENT(FREIGHT_+FREIGHT_ID+":"+freight.getId()); // 运费模板信息不存在
		}
		
		//查询是否存在名称相同的运费模板
		Freight findFreight = freightDao.findFreightByNameForUpdate(freight.getName(), freight.getStoreId());
		if(findFreight != null && !findFreight.getId().equals(freight.getId())){
			return RuleError.EXISTED(FREIGHT_+FREIGHT_NAME+":"+freight.getName()); // 运费模板名称已存在
		}
		
		//更新运费模板信息
		freightDao.update(freight);
		
		//删除配送方式信息及城市列表信息
		logisticsMethodDao.delete(freight.getId());
		
		//获取配送方式列表
		List<LogisticsMethod> logisticsMethods = freight.getLogisticsMethods();
		
		//判断配送方式列表是否为空
		if(logisticsMethods != null && logisticsMethods.size()>0){
			
			//添加配送方式列表
			logisticsMethodDao.saveBatch(logisticsMethods);
			
			//创建List集合接收城市列表
			List<City> cities = new ArrayList<>();
			
			for (LogisticsMethod logisticsMethod : logisticsMethods) {
				
				//判断运费方式指定的城市列表是否为空
				if(logisticsMethod.getCities() != null && logisticsMethod.getCities().size()>0){
					
					//添加城市列表到集合中
					cities.addAll(logisticsMethod.getCities());
				}
			}
			
			//判断城市列表集合是否为空
			if(cities.size() > 0){
				cityDao.saveBatch(cities);
			}
		}
		
		//返回更新结果
		return Success.SUCCESS(freight.getId());
	}
	
	/**
	 * 批量删除运费模板信息。
	 * @param ids 运费信息ID列表。
	 * @return 成功返回成功响应码，失败则返回相应错误码
	 */
    @Transactional
	public ResponseCode deleteBatch(List<String> ids){
		
    	for (String id : ids) {
    		List<FreightParam> freightParams = freightParamDao.findFreightParams(id);
    		if(freightParams != null && freightParams.size()>0){
    			return PermissionsError.DATA_NO_ALLOW(FREIGHT_+FREIGHT_HAS_BEING_USED+";"+FREIGHT_ID+":"+id); //运费模板正在被商品使用，不允许删除
    		}
		}
    	
		//执行删除
		freightDao.deleteBatch(ids);
		
		//返回删除结果
		return Success.SUCCESS;
	}
	
	/**
	 * 根据店铺ID分页查询运费模板详情列表(按照创建时间倒叙)。
	 * @param storeId 	  店铺ID。
	 * @param start      分页起始。
	 * @param pageSize   每页记录数。
	 * @return 查询到结果返回运费模板详情列表，未查询到结果返回空，失败返回相应错误码。
	 */
	public ResponseCode findFreightDetails(String storeId, Integer start, Integer pageSize){
		
		//查询运费模板详情列表
		List<Freight> freights = freightDao.findFreightDetails(storeId, start, pageSize);
		
		//转换查询结果为视图列表
		List<ViewFreight> viewFreights = convertFreightViews(freights);
		
		//查询运费模板总数
		int totalSize = freightDao.getFreightCount(storeId);
		
		//计算出总页数
		int totalPage = totalSize%pageSize==0?totalSize/pageSize:(totalSize/pageSize + 1);
		
		
		//创建Map集合封装分页参数
		Map<String, Object> pageParam = new HashMap<>();
		pageParam.put(TOTAL_SIZE, totalSize);
		pageParam.put(TOTAL_PAGE, totalPage);
		
		//创建Map集合封装查询结果
		Map<String, Object> result = new HashMap<>();
		result.put(FREIGHTS, viewFreights);
		result.put(PAGE_PARAM, pageParam);
		
		//返回结果
		return Success.SUCCESS(result);
	}
	
	/**
	 * 根据店铺ID查询运费模板列表(按照创建时间倒叙)。
	 * @param storeId 店铺ID
	 * @return 查询到结果返回运费模板列表，未查询到结果返回空，失败返回相应错误码。
	 */
	public ResponseCode findFreights(String storeId){
		
		//查询运费模板列表(基本信息)
		List<Freight> freights = freightDao.findFreights(storeId);
		
		//转换查询结果为视图列表
		List<ViewFreight> viewFreights = convertFreightViews(freights);
		
		//返回结果
		return Success.SUCCESS(viewFreights);
	}
	
	/**
	 * 根据ID查询运费模板详细信息。
	 * @param id 运费模板ID。
	 * @return 查询到结果返回运费详情，未查询到结果返回空，失败返回相应错误码。
	 */
	public ResponseCode findFreightById(String id){
		
		//查询运费模板详细信息
		Freight freight = freightDao.findFreightById(id);
		 
		//转换查询结果为视图对象
		ViewFreight viewFreight = convertFreightView(freight);
		 
		//返回结果
		return Success.SUCCESS(viewFreight);
	}
    
	/**
	 * 通过商品ID查询运费参数信息。
	 * @param goodsId 运费参数信息。
	 * @return 查询到结果返回运费参数信息，未查询到结果返回空对象，失败则返回相应错误码。
	 */
	public ResponseCode findFreightParam(String goodsId){
		
		//执行查询
		FreightParam freightParam = freightParamDao.findFreightParam(goodsId);
		
		//转换查询结果为视图对象
		ViewFreightParam viewFreightParam = convertFreightParamView(freightParam);
		
		//返回查询结果
		return Success.SUCCESS(viewFreightParam);
	}
	
	/**
	 * 计算商品运费。
	 * @param purchaseInfos 购买信息列表（商品ID、数量等）。
	 * @param cityId 城市ID。
	 * @return 成功返回商品运费，失败则返回相应错误码。
	 */
	public ResponseCode calculate(List<PurchaseInfo> purchaseInfos, String cityId){
		
		//创建BigDecimal对象存储商品运费(初始化运费为0)
		BigDecimal count = new BigDecimal("0");
		
		//创建Map集合，将运费模板相同的商品的运费参数统一存放
		Map<Freight, BigDecimal> freightParamMap = new HashMap<>();
		
		//购买信息列表
		for (PurchaseInfo purchaseInfo : purchaseInfos) {
			
			//获得商品ID
			String goodsId = purchaseInfo.getGoodsId();
		
			//获得商品购买数量
			Integer quantity = purchaseInfo.getQuantity();
			
			//查询商品运费参数信息
			FreightParam freightParam = freightParamDao.findFreightParam(goodsId);
			
			if(freightParam != null){
				//获取运费类型
				FreightType freightType = freightParam.getType();
				
				//固定运费
				if(freightType.equals(FreightType.FIXED)){
					
					//获取运费价格
					BigDecimal price = freightParam.getPrice();
					
					//将该商品运费添加到总运费中
					count = count.add(price);
				}else{
					//不固定运费，需查询运费模板
					
					//获取运费模板ID
					String freightId = freightParam.getFreightId();
					
					//查询运费模板详情
					Freight freight = freightDao.findFreightById(freightId);
					
					//判断运费模板信息是否存在
					if(freight == null){
						return RuleError.NON_EXISTENT(FREIGHT_+FREIGHT_ID+":"+freightId);
					}
					
					//获取运费支付方
					Payer payer = freight.getPayer();
					
					//买家支付运费
					if(payer.equals(Payer.BUYER)){
						
						//获取计价方式
						PricingMethod pricingMethod = freight.getPricingMethod();
						
						//按件数计费
						if (pricingMethod.equals(PricingMethod.NUMBER)) {
							
							//从Map集合中获取该运费模板相关的运费参数
							BigDecimal param = freightParamMap.get(freight);
							
							//判断运费参数是否存在
							if(param == null){
								//不存在该运费参数，则将运费参数添加到集合中
								freightParamMap.put(freight, new BigDecimal(quantity.toString()));
							}else{
								
								//存在该运费参数，则将两运费参数相加，保存到Map集合中
								param = param.add(new BigDecimal(quantity.toString()));
								
								//保存到Map集合
								freightParamMap.put(freight, param);
							}
							
						}
						
						//按件数计费
						if(pricingMethod.equals(PricingMethod.WEIGHT)){
							
							//从Map集合中获取该运费模板相关的运费参数
							BigDecimal param = freightParamMap.get(freight);
							
							//判断运费参数是否存在
							if(param == null){
								
								//不存在该运费参数，则将运费参数添加到集合中(重量和购买数量相乘)
								param = new BigDecimal(quantity.toString()).multiply(new BigDecimal(freightParam.getAmount()));
								freightParamMap.put(freight, param);
							}else{
								
								//存在该运费参数，则将两运费参数相加，保存到Map集合中(重量和购买数量相乘)
								param = param.add(new BigDecimal(quantity.toString()).multiply(new BigDecimal(freightParam.getAmount())));
								
								//保存到Map集合
								freightParamMap.put(freight, param);
							}
							
						}
						
						//按体积计费
						if(pricingMethod.equals(PricingMethod.VOLUME)){
							
							//从Map集合中获取该运费模板相关的运费参数
							BigDecimal param = freightParamMap.get(freight);
							
							//判断运费参数是否存在
							if(param == null){
								
								//不存在该运费参数，则将运费参数添加到集合中(重量和购买数量相乘)
								param = new BigDecimal(quantity.toString()).multiply(new BigDecimal(freightParam.getAmount()));
								freightParamMap.put(freight, param);
							}else{
								
								//存在该运费参数，则将两运费参数相加，保存到Map集合中(重量和购买数量相乘)
								param = param.add(new BigDecimal(quantity.toString()).multiply(new BigDecimal(freightParam.getAmount())));
								
								//保存到Map集合
								freightParamMap.put(freight, param);
							}
						}
					}
				}
			}
		}
		
		//循环运费参数Map集合
		for (Freight freight : freightParamMap.keySet()) {
			
			//获得商品运费参数
			BigDecimal param = freightParamMap.get(freight);
			
			//获得运送方式信息列表
			List<LogisticsMethod> logisticsMethods = freight.getLogisticsMethods();
			
			//创建BigDecimal对象用来存储运费计算的起始数量
			BigDecimal startQuantity = null;
			
			//创建BigDecimal对象用来存储运费计算的起始价格
			BigDecimal startPrice = null;
			
			//创建BigDecimal对象用来存储运费计算的增量
			BigDecimal incrementQuantity = null;
			
			//创建BigDecimal对象用来存储运费计算的增加价格
			BigDecimal incrementPrice = null;
			
			//创建默认运送方式对象
			LogisticsMethod defaultLogisticsMethod = new LogisticsMethod();
			
			//循环运送方式列表
			for (LogisticsMethod logisticsMethod : logisticsMethods) {
				
				//获得城市列表
				List<City> cities = logisticsMethod.getCities();
				
				//判断运送方式是否为默认运送方式
				if(logisticsMethod.getIsDefault()){
					defaultLogisticsMethod = logisticsMethod;
				}
				
				//循环城市列表
				for (City city : cities) {
					
					//判断该城市ID是否匹配城市列表中的元素
					if(cityId.equals(city.getCityId())){
						
						//绑定起始量
						startQuantity = logisticsMethod.getStartQuantity();
						
						//绑定起始价格
						startPrice = logisticsMethod.getStartPrice();
						
						//绑定增量
						incrementQuantity = logisticsMethod.getIncrementQuantity();
						
						//绑定增加价格
						incrementPrice = logisticsMethod.getIncrementPrice();
						
						//停止当前循环
						break;
					}
				}
				
				//判断起始量、起始价格、增量、增加价格是否全部绑定
				if(startQuantity != null && startPrice != null && incrementQuantity != null && incrementPrice != null){
					//停止当前循环
					break;
				}
			}
			
			//起始量、起始价格、增量、增加价格未全部绑定则使用默认运送方式
			if(startQuantity == null || startPrice == null || incrementQuantity == null || incrementPrice == null){
				startQuantity = defaultLogisticsMethod.getStartQuantity();
				startPrice = defaultLogisticsMethod.getStartPrice();
				incrementQuantity = defaultLogisticsMethod.getIncrementQuantity();
				incrementPrice = defaultLogisticsMethod.getIncrementPrice();
			}
			
			//运费参数小于起始量，则按照起始价格计算运费
			if(param.compareTo(startQuantity) <= 0){
				count = count.add(startPrice);
			}
			
			//运费参数大于起始量，则根据起始价格和增量来计算运费
			if(param.compareTo(startQuantity) > 0){
				count = count.add(param.subtract(startQuantity).divide(incrementQuantity, 0, BigDecimal.ROUND_UP).multiply(incrementPrice).add(startPrice));
			}
		}
		
		//返回计算结果
		return Success.SUCCESS(count);
	}
}
