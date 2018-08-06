package com.dreawer.goods.controller;

import com.dreawer.goods.domain.City;
import com.dreawer.goods.domain.Freight;
import com.dreawer.goods.domain.LogisticsMethod;
import com.dreawer.goods.form.AddCityForm;
import com.dreawer.goods.form.AddFreightForm;
import com.dreawer.goods.form.AddLogisticsMethodForm;
import com.dreawer.goods.form.CalculateFreightForm;
import com.dreawer.goods.form.DeleteFreightForm;
import com.dreawer.goods.form.EditCityForm;
import com.dreawer.goods.form.EditFreightForm;
import com.dreawer.goods.form.EditLogisticsMethodForm;
import com.dreawer.goods.lang.LogisticsMethodType;
import com.dreawer.goods.lang.Payer;
import com.dreawer.goods.lang.PricingMethod;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.service.FreightService;
import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.ResponseCodeRepository;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.dreawer.goods.constants.ControllerConstants.*;
import static com.dreawer.goods.constants.DomainConstants.*;
import static com.dreawer.responsecode.rcdt.Error.APPSERVER;
import static com.dreawer.responsecode.rcdt.Error.ENTRY;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <CODE>FreightController</CODE>
 * 运费模板控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_FREIGHT)
public class FreightController extends BaseController{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private FreightService freightService; //运费模板service

	/**
	 * 添加运费模板信息。
     * @param req 用户请求。
     * @param form 添加运费模板表单。
	 * @return 成功返回运费模板ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_ADD, method=RequestMethod.POST)
    public @ResponseBody ResponseCode add(HttpServletRequest req, @RequestBody @Valid AddFreightForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//获取用户ID
    		String userId = req.getHeader(USER_ID);
    		
    		//判断用户ID是否为空
    		if(StringUtils.isEmpty(userId)){
    			return EntryError.EMPTY(USER_ID);
    		}
    		
    		//校验并封装请求参数
    		Freight freight = new Freight();
    		
    		//创建物流方式列表
    		List<LogisticsMethod> logisticsMethods = new ArrayList<>();
    		
    		//封装运费模板数据
    		freight.setId(generateUUID());
    		freight.setStoreId(form.getStoreId());
    		freight.setName(form.getName());
    		freight.setDeliveryAddress(form.getDeliveryAddress());
    		freight.setDeliveryTime(form.getDeliveryTime());
    		freight.setPayer(form.getPayer());
    		freight.setPricingMethod(form.getPricingMethod());
    		freight.setCreaterId(userId);
    		freight.setCreateTime(getNow());
    		freight.setLogisticsMethods(logisticsMethods);
    		
       		//判断是否买家支付运费
    		if(form.getPayer().equals(Payer.BUYER)){
    			
    			//获取计价方式
    			PricingMethod pricingMethod = form.getPricingMethod();
    			
    			//判断计价方式是否为空
    			if(pricingMethod == null){
    				return EntryError.EMPTY(PRICING_METHOD);
    			}
    			
    			//获取运送方式表单列表
    			List<AddLogisticsMethodForm> addLogisticsMethods = form.getAddLogisticsMethods();
    			
    			//判断运送方式表单列表是否为空
    			if(addLogisticsMethods == null || addLogisticsMethods.size() <= 0){
    				return EntryError.EMPTY(LOGISTICS_METHODS);
    			}
    			
    			//创建Map集合接收城市信息
    			Map<LogisticsMethodType, StringBuffer> cityMap = new HashMap<>();
    			
    			//创建循环数，判断是否存在一条以上的默认运送方式
    			int i = 0;
    			
    			//循环运送方式表单列表
    			for (AddLogisticsMethodForm logisticsMethodForm : addLogisticsMethods) {
					
    				//获取运送方式类型
    				LogisticsMethodType type = logisticsMethodForm.getType();
    				
    				//判断运送方式类型是否为空
    				if(type == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_TYPE);
    				}
    				
    				//验证非0正整数，取值范围1-9999
    				Pattern pattern = Pattern.compile("^[1-9]([0-9]{0,3})$");
    				
    				//获取起始量
    				BigDecimal startQuantity = logisticsMethodForm.getStartQuantity();
    				
    				//判断起始量是否为空
    				if(startQuantity == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_START_QUANTITY); // 起始量为空 
    				}
    				
    				//判断起始量格式是否正确
    				if(!pattern.matcher(startQuantity.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_START_QUANTITY); // 起始量格式不正确
    				}
    				
    				//获取起始金额
    				BigDecimal startPrice = logisticsMethodForm.getStartPrice();
    				
    				//判断起始价格是否为空
    				if(startPrice == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_START_PRICE); // 起始价为空
    				}
    				
    				//判断起始价格格式是否正确
    				if(!pattern.matcher(startPrice.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_START_PRICE); // 起始价格格式不正确
    				}
    				
    				//获取增量
    				BigDecimal incrementQuantity = logisticsMethodForm.getIncrementQuantity();
    				
    				//判断增量是否为空
    				if(incrementQuantity == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_INCREMENT_QUANTITY); // 增量为空
    				}
    				
    				//判断增量格式是否正确
    				if(!pattern.matcher(incrementQuantity.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_INCREMENT_QUANTITY); // 增量格式不正确
    				}
    				
    				//获取增加价格
    				BigDecimal incrementPrice = logisticsMethodForm.getIncrementPrice();
    				
    				//判断增加价格是否为空
    				if(incrementPrice == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_INCREMENT_PRICE); // 增加价格为空
    				}
    				
    				//判断郑家价格格式是否正确
    				if(!pattern.matcher(incrementPrice.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_INCREMENT_PRICE); // 增量格式不正确
    				}

    				//获取物流方式“是否默认”属性
    				Boolean isDefault = logisticsMethodForm.getIsDefault();
    				
    				//判断运“是否默认”属性是否为空
    				if(isDefault == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_IS_DEFAULT);
    				}
    				
    				//List集合接收城市信息
    				List<City> cities = new ArrayList<>();
    				
    				//创建物流参数实体类，封装物流参数信息
    				LogisticsMethod logisticsMethod = new LogisticsMethod();
    				logisticsMethod.setId(generateUUID());
    				logisticsMethod.setFreightId(freight.getId());
    				logisticsMethod.setIsDefault(isDefault);
    				logisticsMethod.setStartQuantity(startQuantity);
    				logisticsMethod.setStartPrice(startPrice);
    				logisticsMethod.setIncrementQuantity(incrementQuantity);
    				logisticsMethod.setIncrementPrice(incrementPrice);
    				logisticsMethod.setType(type);
    				logisticsMethod.setCreaterId(freight.getCreaterId());
    				logisticsMethod.setCreateTime(getNow());
    				logisticsMethod.setCities(cities);
    				
    				//添加物流参数到集合中
    				logisticsMethods.add(logisticsMethod);
    				
    				//判断是否默认运费
    				if(isDefault){
    					//判断循环数大小
    					if(i>=1){
    						return EntryError.DUPLICATE(LOGISTICS_METHOD_IS_DEFAULT);
    					}
    					
    					//循环数加一
    					i++;
    				}else{
    					
        				//获取添加城市表单列表
        				List<AddCityForm> addCities = logisticsMethodForm.getAddCities();
        				
        				//添加城市表单列表为空
        				if(addCities == null || addCities.size() <= 0){
        					
        					return EntryError.EMPTY(CITIES);
        				}
        				
        				//循环城市列表
        				for (AddCityForm addCityForm : addCities) {
							
        					//获取城市ID
        					String cityId = addCityForm.getCityId();
        					
        					//判断城市ID是否为空
        					if(cityId == null){
        						return EntryError.EMPTY(CITY_ID); // 城市ID为空
        					}
        					
        					//获取城市名称
        					String name = addCityForm.getName();
        					
        					//判断城市名称是否为空
        					if(name == null){
        						return EntryError.EMPTY(CITY_NAME); // 城市名称为空
        					}
        					
        					//判断城市名称长度
        					if(name.length() <= 0){
        						return EntryError.TOO_SHORT(CITY_NAME); // 城市名称长度过短
        					}
        					
        					if(name.length() > 40){
        						return EntryError.TOO_LONG(CITY_NAME); // 城市名称长度过长
        					}
        					
        					//获取城市父ID
        					String parentId = addCityForm.getParentId();
        					
        					//保存城市ID到Map集合中
        					StringBuffer cityIds = cityMap.get(logisticsMethod.getType());
        					if(cityIds == null || cityIds.length()<=0){
        						cityIds.append(cityId);
        					}else{
        						
        						//判断城市ID是否存在
        						if(cityIds.toString().contains(cityId)){
        							return EntryError.DUPLICATE(CITY);
        						}
        						
        						//添加城市ID到Map集合中
        						cityIds.append(";").append(cityId);
        					}
        					
        					//创建城市信息实体类封装城市信息
        					City city = new City();
        					city.setId(generateUUID());
        					city.setLogisticsMethodId(logisticsMethod.getId());
        					city.setName(name);
        					city.setCityId(cityId);
        					city.setParentId(parentId);
        					city.setCreaterId(freight.getCreaterId());
        					city.setCreateTime(getNow());
        					
        					//添加城市信息到List集合中
        					cities.add(city);
						}
    				}
				}
    		}
    		
    		//执行添加
    		ResponseCode responseCode = freightService.save(freight);
        	
            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }

	/**
	 * 编辑运费模板信息。
     * @param req 用户请求。
     * @param form 编辑运费模板表单。
	 * @return 成功返回运费模板ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_EDIT, method=RequestMethod.POST)
    public @ResponseBody ResponseCode edit(HttpServletRequest req, @RequestBody @Valid EditFreightForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//获取用户ID
    		String userId = req.getHeader(USER_ID);
    		
    		//判断用户ID是否为空
    		if(StringUtils.isEmpty(userId)){
    			return EntryError.EMPTY(USER_ID);
    		}
    		
    		//校验并封装请求参数
    		Freight freight = new Freight();
    		
    		//创建物流方式列表
    		List<LogisticsMethod> logisticsMethods = new ArrayList<>();
    		
    		//封装运费模板数据
    		freight.setId(generateUUID());
    		freight.setStoreId(form.getStoreId());
    		freight.setName(form.getName());
    		freight.setDeliveryAddress(form.getDeliveryAddress());
    		freight.setDeliveryTime(form.getDeliveryTime());
    		freight.setPayer(form.getPayer());
    		freight.setPricingMethod(form.getPricingMethod());
    		freight.setUpdaterId(userId);
    		freight.setUpdateTime(getNow());
    		freight.setLogisticsMethods(logisticsMethods);
    		
       		//判断是否买家支付运费
    		if(form.getPayer().equals(Payer.BUYER)){
    			
    			//获取计价方式
    			PricingMethod pricingMethod = form.getPricingMethod();
    			
    			//判断计价方式是否为空
    			if(pricingMethod == null){
    				return EntryError.EMPTY(PRICING_METHOD);
    			}
    			
    			//获取运送方式表单列表
    			List<EditLogisticsMethodForm> editLogisticsMethods = form.getEditLogisticsMethods();
    			
    			//判断运送方式表单列表是否为空
    			if(editLogisticsMethods == null || editLogisticsMethods.size() <= 0){
    				return EntryError.EMPTY(LOGISTICS_METHODS);
    			}
    			
    			//创建Map集合接收城市信息
    			Map<LogisticsMethodType, StringBuffer> cityMap = new HashMap<>();
    			
    			//创建循环数，判断是否存在一条以上的默认运送方式
    			int i = 0;
    			
    			//循环运送方式表单列表
    			for (EditLogisticsMethodForm logisticsMethodForm : editLogisticsMethods) {
					
    				//获取运送方式类型
    				LogisticsMethodType type = logisticsMethodForm.getType();
    				
    				//判断运送方式类型是否为空
    				if(type == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_TYPE);
    				}
    				
    				//验证非0正整数，取值范围1-9999
    				Pattern pattern = Pattern.compile("^[1-9]([0-9]{0,3})$");
    				
    				//获取起始量
    				BigDecimal startQuantity = logisticsMethodForm.getStartQuantity();
    				
    				//判断起始量是否为空
    				if(startQuantity == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_START_QUANTITY); // 起始量为空 
    				}
    				
    				//判断起始量格式是否正确
    				if(!pattern.matcher(startQuantity.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_START_QUANTITY); // 起始量格式不正确
    				}
    				
    				//获取起始金额
    				BigDecimal startPrice = logisticsMethodForm.getStartPrice();
    				
    				//判断起始价格是否为空
    				if(startPrice == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_START_PRICE); // 起始价为空
    				}
    				
    				//判断起始价格格式是否正确
    				if(!pattern.matcher(startPrice.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_START_PRICE); // 起始价格格式不正确
    				}
    				
    				//获取增量
    				BigDecimal incrementQuantity = logisticsMethodForm.getIncrementQuantity();
    				
    				//判断增量是否为空
    				if(incrementQuantity == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_INCREMENT_QUANTITY); // 增量为空
    				}
    				
    				//判断增量格式是否正确
    				if(!pattern.matcher(incrementQuantity.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_INCREMENT_QUANTITY); // 增量格式不正确
    				}
    				
    				//获取增加价格
    				BigDecimal incrementPrice = logisticsMethodForm.getIncrementPrice();
    				
    				//判断增加价格是否为空
    				if(incrementPrice == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_INCREMENT_PRICE); // 增加价格为空
    				}
    				
    				//判断郑家价格格式是否正确
    				if(!pattern.matcher(incrementPrice.toString()).matches()){
    					return EntryError.FORMAT(LOGISTICS_METHOD_INCREMENT_PRICE); // 增量格式不正确
    				}

    				//获取物流方式“是否默认”属性
    				Boolean isDefault = logisticsMethodForm.getIsDefault();
    				
    				//判断运“是否默认”属性是否为空
    				if(isDefault == null){
    					return EntryError.EMPTY(LOGISTICS_METHOD_IS_DEFAULT);
    				}
    				
    				//List集合接收城市信息
    				List<City> cities = new ArrayList<>();
    				
    				//创建物流参数实体类，封装物流参数信息
    				LogisticsMethod logisticsMethod = new LogisticsMethod();
    				
    				
    				//判断运送方式ID是否为空
    				if(StringUtils.isEmpty(logisticsMethodForm.getId())){
    					logisticsMethod.setId(generateUUID());
    				}else{
    					logisticsMethod.setId(form.getId());
    				}
    				
    				logisticsMethod.setFreightId(freight.getId());
    				logisticsMethod.setIsDefault(isDefault);
    				logisticsMethod.setStartQuantity(startQuantity);
    				logisticsMethod.setStartPrice(startPrice);
    				logisticsMethod.setIncrementQuantity(incrementQuantity);
    				logisticsMethod.setIncrementPrice(incrementPrice);
    				logisticsMethod.setType(type);
    				logisticsMethod.setCreaterId(freight.getUpdaterId());
    				logisticsMethod.setCreateTime(getNow());
    				logisticsMethod.setCities(cities);
    				
    				//添加物流参数到集合中
    				logisticsMethods.add(logisticsMethod);
    				
    				//判断是否默认运费
    				if(isDefault){
    					//判断循环数大小
    					if(i>=1){
    						return EntryError.DUPLICATE(LOGISTICS_METHOD_IS_DEFAULT);
    					}
    					
    					//循环数加一
    					i++;
    				}else{
    					
        				//获取城市表单列表
        				List<EditCityForm> editCities = logisticsMethodForm.getEditCities();
        				
        				//城市表单列表为空
        				if(editCities == null || editCities.size() <= 0){
        					
        					return EntryError.EMPTY(CITIES);
        				}
        				
        				//循环城市列表
        				for (EditCityForm cityForm : editCities) {
							
        					//获取城市ID
        					String cityId = cityForm.getCityId();
        					
        					//判断城市ID是否为空
        					if(cityId == null){
        						return EntryError.EMPTY(CITY_ID); // 城市ID为空
        					}
        					
        					//获取城市名称
        					String name = cityForm.getName();
        					
        					//判断城市名称是否为空
        					if(name == null){
        						return EntryError.EMPTY(CITY_NAME); // 城市名称为空
        					}
        					
        					//判断城市名称长度
        					if(name.length() <= 0){
        						return EntryError.TOO_SHORT(CITY_NAME); // 城市名称长度过短
        					}
        					
        					if(name.length() > 40){
        						return EntryError.TOO_LONG(CITY_NAME); // 城市名称长度过长
        					}
        					
        					//获取城市父ID
        					String parentId = cityForm.getParentId();
        					
        					//保存城市ID到Map集合中
        					StringBuffer cityIds = cityMap.get(logisticsMethod.getType());
        					if(cityIds == null || cityIds.length()<=0){
        						cityIds.append(cityId);
        					}else{
        						
        						//判断城市ID是否存在
        						if(cityIds.toString().contains(cityId)){
        							return EntryError.DUPLICATE(CITY);
        						}
        						
        						//添加城市ID到Map集合中
        						cityIds.append(";").append(cityId);
        					}
        					
        					//创建城市信息实体类封装城市信息
        					City city = new City();
        					
        					
        					//判断ID序列号是否存在
        					if(StringUtils.isEmpty(cityForm.getId())){
        						city.setId(generateUUID());
        					}else{
        						city.setId(cityForm.getId());
        					}
        					
        					city.setLogisticsMethodId(logisticsMethod.getId());
        					city.setName(name);
        					city.setCityId(cityId);
        					city.setParentId(parentId);
        					city.setCreaterId(freight.getUpdaterId());
        					city.setCreateTime(getNow());
        					
        					//添加城市信息到List集合中
        					cities.add(city);
						}
    				}
				}
    		}
    		
    		//执行更新
    		ResponseCode responseCode = freightService.update(freight);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 批量删除运费模板信息。
     * @param req 用户请求。
     * @param form 删除运费模板表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_DELETE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode delete(HttpServletRequest req, @RequestBody @Valid DeleteFreightForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//获取用户ID
    		String userId = req.getHeader(USER_ID);
    		
    		//判断用户ID是否为空
    		if(StringUtils.isEmpty(userId)){
    			return EntryError.EMPTY(USER_ID);
    		}
    		
    		//执行删除
    		ResponseCode responseCode = freightService.deleteBatch(form.getFreightIds());
        	
            //返回删除结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 根据店铺ID分页查询运费模板详情列表(按照创建时间倒叙)。
     * @param req 用户请求。
	 * @param storeId 	  店铺ID。
	 * @param pageNo     当前页码。
	 * @param pageSize   每页显示记录数。
	 * @return 查询到结果返回运费模板详情列表，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_DETAILS, method=RequestMethod.GET)
    public @ResponseBody ResponseCode details(HttpServletRequest req, @RequestParam(STORE_ID)String storeId, @RequestParam(name=PAGE_NO,defaultValue="1")Integer pageNo,
    		                                  @RequestParam(name=PAGE_SIZE,defaultValue="5")Integer pageSize) {
    	try {
    		
    		//计算分页起始
    		int start = (pageNo-1)*(pageSize+1);
    		
    		//执行查询
    		ResponseCode responseCode = freightService.findFreightDetails(storeId, start, pageSize);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 根据店铺ID查询运费模板列表(按照创建时间倒叙)。
     * @param req 用户请求。
	 * @param storeId 店铺ID
	 * @return 查询到结果返回运费模板列表，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_LIST, method=RequestMethod.GET)
    public @ResponseBody ResponseCode list(HttpServletRequest req, @RequestParam(STORE_ID)String storeId) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = freightService.findFreights(storeId);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 根据ID查询运费模板详细信息。
     * @param req 用户请求。
	 * @param id 运费模板ID。
	 * @return 查询到结果返回运费详情，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_DETAIL, method=RequestMethod.GET)
    public @ResponseBody ResponseCode detail(HttpServletRequest req, @RequestParam(ID)String id) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = freightService.findFreightById(id);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 设置运费参数信息（无参数信息则执行添加，有参数信息则执行更新）。
     * @param req 用户请求。
	 * @param form 设置运费参数表单。
	 * @return 成功则返回商品ID，失败则返回相应错误码。
	 */
/*    @RequestMapping(value=REQ_SET_PARAM, method=RequestMethod.POST)
    public @ResponseBody ResponseCode setParam(HttpServletRequest req, @RequestBody @Valid SetFreightParamForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//创建运费参数实体类封装运费参数信息
    		FreightParam freightParam = new FreightParam();
    		freightParam.setGoodsId(form.getGoodsId());
    		freightParam.setCreaterId(form.getUserId());
    		freightParam.setCreateTime(getNow());
    		freightParam.setUpdaterId(form.getUserId());
    		freightParam.setUpdateTime(getNow());
    		
    		//获取运费类型
    		FreightType type = form.getType();
    		freightParam.setType(type);
    		
    		//判断运费类型为固定运费或不固定运费
    		if(type.equals(FreightType.FIXED)){
    			
    			//判断运费价格是否为空
    			if(form.getPrice() == null){
    				return EntryError.EMPTY(PRICE);
    			}
    			
    			//设置运费价格
    			freightParam.setPrice(form.getPrice());
    		}else{
    			
    			//判断运费模板ID是否为空
    			if(StringUtils.isEmpty(form.getFreightId())){
    				return EntryError.EMPTY(FREIGHT_ID);
    			}
    			
    			//设置运费模板ID
    			freightParam.setFreightId(form.getFreightId());
    			
    			//判断重量或体积是否符合格式规范
    			String amount = form.getAmount();
    			if(amount != null){
    				
    				//创建匹配正则（0.01-99.99）
    				Pattern pattern = Pattern.compile("[^0]\\d?$|[^0]\\d?.\\d{1,2}$|0.0?[^0]$"); 
    				if(!pattern.matcher(amount.toString()).matches()){
    					return EntryError.FORMAT(AMOUNT);
    				}
    				
        			//设置重量或体积
        			freightParam.setAmount(amount);
    			}
    		}
    		
    		//执行设置
    		ResponseCode responseCode = freightService.setFreightParam(freightParam);
        	
            //返回结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }*/
    
	/**
	 * 通过商品ID查询运费参数信息。
     * @param req 用户请求。
	 * @param goodsId 运费参数信息。
	 * @return 查询到结果返回运费参数信息，未查询到结果返回空对象，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_PARAM, method=RequestMethod.GET)
    public @ResponseBody ResponseCode param(HttpServletRequest req, @RequestParam(GOODS_ID)String goodsId) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = freightService.findFreightParam(goodsId);
    		
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
	/**
	 * 计算运费。
     * @param req 用户请求。
	 * @param form 计算运费表单。
	 * @return 成功返回商品运费，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_CACULATE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode calculate(HttpServletRequest req, @RequestBody @Valid CalculateFreightForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//获取用户ID
    		String userId = req.getHeader(USER_ID);
    		
    		//判断用户ID是否为空
    		if(StringUtils.isEmpty(userId)){
    			return EntryError.EMPTY(USER_ID);
    		}
    		
    		//获取购买信息列表
    		List<PurchaseInfo> purchaseInfos = form.getPurchaseInfos();
    		
    		//循环列表
    		for (PurchaseInfo purchaseInfo : purchaseInfos) {
				
    			//判断商品ID是否为空
    			if(purchaseInfo.getGoodsId() == null){
    				return EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//判断购买数量是否为空
    			if(purchaseInfo.getQuantity() == null){
    				return EntryError.EMPTY(QUANTITY);
    			}
			}
    		
    		//执行计算
    		ResponseCode responseCode = freightService.calculate(purchaseInfos, form.getCityId());
    		
            //返回计算结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            logger.error(ERROR, e);
            return APPSERVER;
        }
    }
    
}
