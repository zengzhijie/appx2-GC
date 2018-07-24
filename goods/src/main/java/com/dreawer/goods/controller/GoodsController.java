package com.dreawer.goods.controller;

import com.dreawer.goods.domain.FreightParam;
import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.GoodsPropertyName;
import com.dreawer.goods.domain.GoodsPropertyValue;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.domain.Sku;
import com.dreawer.goods.form.AddGoodsForm;
import com.dreawer.goods.form.DeleteGoodsForm;
import com.dreawer.goods.form.EditGoodsForm;
import com.dreawer.goods.form.GoodsPropertyNameForm;
import com.dreawer.goods.form.GoodsPropertyValueForm;
import com.dreawer.goods.form.InventoryOperationForm;
import com.dreawer.goods.form.PurchaseInfoForm;
import com.dreawer.goods.form.PurchaseInfosForm;
import com.dreawer.goods.form.QueryGoodsesForm;
import com.dreawer.goods.form.QuerySellingGoodsesForm;
import com.dreawer.goods.form.SetFreightParamForm;
import com.dreawer.goods.form.SkuForm;
import com.dreawer.goods.form.UpdateGoodsRecommendForm;
import com.dreawer.goods.form.UpdateGoodsStatusForm;
import com.dreawer.goods.lang.FreightType;
import com.dreawer.goods.lang.GoodsStatus;
import com.dreawer.goods.lang.GoodsType;
import com.dreawer.goods.lang.InventoryType;
import com.dreawer.goods.lang.PropertyNameType;
import com.dreawer.goods.lang.PropertyValueType;
import com.dreawer.goods.lang.PurchaseInfo;
import com.dreawer.goods.lang.SourceType;
import com.dreawer.goods.service.GoodsService;
import com.dreawer.goods.service.SkuService;
import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.ResponseCodeRepository;
import org.apache.commons.lang.StringUtils;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * <CODE>GoodsController</CODE>
 * 商品信息控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_GOODS)
public class GoodsController extends BaseController{

    @Autowired
    private GoodsService goodsService; //商品service

    @Autowired
    private SkuService skuService; //SKUservice
    
	/**
	 * 添加商品信息。
     * @param req 用户请求。
     * @param form 添加商品表单。
	 * @return 成功返回商品ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_ADD, method=RequestMethod.POST)
    public @ResponseBody ResponseCode add(HttpServletRequest req, @RequestBody @Valid AddGoodsForm form, BindingResult result) {
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
    		
    		//创建商品实体类
    		Goods goods = new Goods();
    		
    		//创建分组列表
    		List<Group> groups = new ArrayList<>();
    		
    		//创建SKU列表
    		List<Sku> skus = new ArrayList<>();
    		
    		//创建商品属性名列表
    		List<GoodsPropertyName> goodsPropertyNames = new ArrayList<>();
    		
    		//获取商品ID
    		String goodsId = generateUUID();
    		
    		//获取分组ID列表
    		List<String> groupIds = form.getGroupIds();
    		
    		//将分组信息封装到分组列表中
    		for (String groupId : groupIds) {
				Group group = new Group();
				group.setId(groupId);
				groups.add(group);
			}
    		
    		//获取SKU表单列表
    		List<SkuForm> skuForms = form.getSkuForms();
    		
    		//判断SKU表单列表是否为空
    		if(skuForms == null || skuForms.size()<=0){
    			return EntryError.EMPTY(SKU);
    		}
    		
    		//创建Set集合接收SKU描述
    		Set<String> descSet = new HashSet<>();
    		
    		//循环SKU表单列表
    		for (SkuForm addSkuForm : skuForms) {
    			
    			//判断SKU描述长度是否合规
    			String description = addSkuForm.getDescription();
    			if(description.length() > 270){
    				return EntryError.TOO_LONG(SALES_PROPERTY); // 销售属性过多
    			}
    			
    			//将SKU描述保存到Set集合中
    			descSet.add(description);
    			
    			//判断SKU库存是否为空
    			Integer inventory = addSkuForm.getInventory();
    			if(inventory == null){
        			if(form.getInventoryType().equals(InventoryType.LIMITED)){
        				return EntryError.EMPTY(INVENTORY);
        			}
    			}else{
    				
    				//判断SKU库存取值范围
    				if(inventory > 9999 || inventory<0){
    					return EntryError.OVERRANGE(INVENTORY);
    				}
    			}
    			
    			//创建价格正则表达式
    			Pattern pricePattern = Pattern.compile("^\\d{1,5}\\.(\\d\\d)$");
    			
    			//判断SKU原价是否为空
    			BigDecimal originalPrice = addSkuForm.getOriginalPrice();
    			if(originalPrice == null){
    				return EntryError.EMPTY(ORIGINAL_PRICE);
    			}
    			
    			//判断SKU原价格式是否正确
    			if(!pricePattern.matcher(originalPrice.toString()).matches() || originalPrice.compareTo(new BigDecimal("0.00")) <= 0){
    				return EntryError.FORMAT(ORIGINAL_PRICE); // SKU原价格式不正确
    			}
    			
    			//判断SKU售价是否为空
    			BigDecimal price = addSkuForm.getPrice();
    			if(price == null){
    				return EntryError.EMPTY(PRICE);
    			}
    			
    			//判断SKU原价格式是否正确
    			if(!pricePattern.matcher(price.toString()).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
    				return EntryError.FORMAT(PRICE); // SKU售价格式不正确
    			}
    			
    			//判断备注长度是否合规
    			String remark = addSkuForm.getRemark();
    			if(!StringUtils.isEmpty(remark)){
    				if(remark.length()>255){
    					EntryError.TOO_LONG(REMARK);
    				}
    			}
    			
    			//创建编码正则表达式
    			Pattern codePattern = Pattern.compile("^[0-9a-zA-Z]+$");
    			
    			//判断条码格式、长度是否合规
    			String barcode = addSkuForm.getBarcode();
    			if(!StringUtils.isEmpty(barcode)){
        			if(!codePattern.matcher(barcode).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
        				return EntryError.FORMAT(BARCODE); // 商品条码格式不正确
        			}
        			
        			if(barcode.length() > 255){
        				return EntryError.TOO_LONG(BARCODE); // 商品条码过长
        			}
    			}

    			//判断编码格式、长度是否合规
    			String code = addSkuForm.getCode();
    			if(!StringUtils.isEmpty(code)){
        			if(!codePattern.matcher(code).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
        				return EntryError.FORMAT(CODE); // 商品条码格式不正确
        			}
        			
        			if(code.length() > 255){
        				return EntryError.TOO_LONG(CODE); // 商品条码过长
        			}
    			}
    			
    			//判断起售量长度是否合规
    			Integer salesVolume = addSkuForm.getSalesVolume();
    			if(salesVolume != null){
    				if(salesVolume > 9999 || salesVolume <=0){
    					return EntryError.OVERRANGE(SALES_VOLUME); // 起售量长度不合规
    				}
    			}else{
    				salesVolume = 1; // 默认起售量为1
    			}
    			
    			//创建SKU实体类封装SKU信息
    			Sku sku = new Sku();
    			sku.setId(generateUUID());
    			sku.setGoodsId(goodsId);
    			sku.setInventoryType(form.getInventoryType());
    			sku.setLockedInventory(0); // 设置锁定库存为零
    			sku.setSalesVolume(salesVolume);
    			sku.setOriginalPrice(originalPrice);
    			sku.setPrice(price);
    			sku.setDescription(description);
    			sku.setCode(code);
    			sku.setBarcode(barcode);
    			sku.setCreaterId(userId);
    			sku.setCreateTime(getNow());
    			sku.setRemark(remark);
    			
    			//添加SKU实体类到集合中
    			skus.add(sku);
			}
    		
    		//判断有无重复的SKU描述
    		if(descSet.size() != skus.size()){
    			return EntryError.DUPLICATE(SKU_DESCRIPTION);
    		}
    		
    		//对sku排序（按照原价正序），获取最低原价
    		List<Sku> sortPriceSkus = sortSkuByPriceAsc(skus);
    		BigDecimal minPrice = sortPriceSkus.get(0).getPrice();
    		
    		//对sku排序（按照售价正序），获取最低售价
    		List<Sku> sortOriginalPriceSkus = sortSkuByOriginalPriceAsc(skus);
    		BigDecimal minOriginalPrice = sortOriginalPriceSkus.get(0).getOriginalPrice();
    		
    		//封装商品信息
    		goods.setId(goodsId);
    		goods.setStoreId(form.getStoreId());
    		goods.setCategoryId(form.getCategoryId());
    		goods.setMinPrice(minPrice);
    		goods.setMinOriginalPrice(minOriginalPrice);
    		goods.setInventoryType(form.getInventoryType());
    		goods.setMainDiagram(form.getMainDiagram());
    		
    		//判断来源
    		if(!form.getSource().equals(SourceType.APPX)){
    			
    			//判断商品详情是否为空
    			if(StringUtils.isEmpty(form.getDetail())){
    				return EntryError.EMPTY(DETAIL);
    			}
    			
    			//判断商品售后服务是否为空
    			if(StringUtils.isEmpty(form.getService())){
    				return EntryError.EMPTY(SERVICE);
    			}
    		}
    		goods.setDetail(form.getDetail());
    		goods.setService(form.getService());
    		goods.setType(GoodsType.DEFAULT);
    		goods.setStatus(form.getStatus());
    		goods.setIsRecommend(form.getIsRecommend());
    		goods.setCreaterId(userId);
    		goods.setCreateTime(getNow());
    		goods.setRemark(form.getRemark());
    		goods.setGroups(groups);
    		goods.setSkus(sortPriceSkus);
    		goods.setPropertyNames(goodsPropertyNames);
    		
    		//获取属性名表单列表
    		List<GoodsPropertyNameForm> goodsPropertyNameForms = form.getGoodsPropertyNameForms();
    		
    		//判断属性名表单列表是否为空
    		if(goodsPropertyNameForms != null && goodsPropertyNameForms.size()>0){
        		
    			//循环属性名表单列表
        		for (GoodsPropertyNameForm goodsPropertyNameForm : goodsPropertyNameForms) {
        			
        			//判断属性名ID是否为空
        			String propertyNameId = goodsPropertyNameForm.getPropertyNameId();
        			if(StringUtils.isBlank(propertyNameId)){
        				return EntryError.EMPTY(PROPERTY_NAME_ID);
        			}
        			
        			//判断属性名称是否为空
        			String name = goodsPropertyNameForm.getName();
        			if(StringUtils.isBlank(name)){
        				return EntryError.EMPTY(PROPERTY_NAME);
        			}
        			
        			//判断属性名称长度是否合规
        			if(name.length() > 40){
        				return EntryError.TOO_LONG(PROPERTY_NAME);
        			}
        			
        			//判断属性名类型是否为空
        			PropertyNameType type = goodsPropertyNameForm.getType();
        			if(type == null){
        				return EntryError.EMPTY(PROPERTY_NAME_TYPE);
        			}
        			
        			//判断排列序号是否为空
        			Integer squence = goodsPropertyNameForm.getSquence();
        			if(squence == null){
        				return EntryError.EMPTY(PROPERTY_NAME_SQUENCE);
        			}
        			
        			//判断“是否搜索属性”是否为空
        			Boolean isSearch = goodsPropertyNameForm.getIsSearch();
        			if(isSearch == null){
        				return EntryError.EMPTY(IS_SEARCH);
        			}
        			
        			//判断是“是否单选框”是否为空
        			Boolean isRadio = goodsPropertyNameForm.getIsRadio();
        			if(isRadio == null){
        				return EntryError.EMPTY(IS_RADIO);
        			}
        			
        			//判断是“是否多选框”是否为空
        			Boolean isCheckbox = goodsPropertyNameForm.getIsCheckbox();
        			if(isCheckbox == null){
        				return EntryError.EMPTY(IS_CHECKBOX);
        			}
        			
        			//判断是“是否输入框”是否为空
        			Boolean isInput = goodsPropertyNameForm.getIsInput();
        			if(isInput == null){
        				return EntryError.EMPTY(IS_INPUT);
        			}
        			
        			//判断是“是否下拉框”是否为空
        			Boolean isSelect = goodsPropertyNameForm.getIsSelect();
        			if(isSelect == null){
        				return EntryError.EMPTY(IS_SELECT);
        			}
        			
        			//判断是“是否文本编辑器”是否为空
        			Boolean isVisualEditor = goodsPropertyNameForm.getIsVisualEditor();
        			if(isVisualEditor == null){
        				return EntryError.EMPTY(IS_VISUAL_EDITOR);
        			}
        			
        			//判断是“是否颜色属性”是否为空
        			Boolean isColor = goodsPropertyNameForm.getIsColor();
        			if(isColor == null){
        				return EntryError.EMPTY(IS_COLOR);
        			}
        			
        			//判断是“是否必须属性”是否为空
        			Boolean isRequired = goodsPropertyNameForm.getIsRequired();
        			if(isRequired == null){
        				return EntryError.EMPTY(IS_REQUIRED);
        			}
        			
        			//判断是“是否销售属性”是否为空
        			Boolean isSales = goodsPropertyNameForm.getIsSales();
        			if(isSales == null){
        				return EntryError.EMPTY(IS_SALES);
        			}
        			
        			//判断是“是否基础属性”是否为空
        			Boolean isBasic = goodsPropertyNameForm.getIsBasic();
        			if(isBasic == null){
        				return EntryError.EMPTY(IS_BASIC);
        			}
        			
        			//判断是“是否关键属性”是否为空
        			Boolean isKey = goodsPropertyNameForm.getIsKey();
        			if(isKey == null){
        				return EntryError.EMPTY(IS_KEY);
        			}
        			
        			//判断是“是否图片属性”是否为空
        			Boolean isImage = goodsPropertyNameForm.getIsImage();
        			if(isImage == null){
        				return EntryError.EMPTY(IS_IMAGE);
        			}
        			
        			//创建商品属性值列表
        			List<GoodsPropertyValue> goodsPropertyValues = new ArrayList<>();
        			
        			//获取商品属性名ID
        			String goodsPropertyNameId = generateUUID();
        					
        			//封装属性名信息
        			GoodsPropertyName goodsPropertyName = new GoodsPropertyName();
        			goodsPropertyName.setId(goodsPropertyNameId);
        			goodsPropertyName.setPropertyNameId(propertyNameId);
        			goodsPropertyName.setName(name);
        			goodsPropertyName.setCategoryId(form.getCategoryId());
        			goodsPropertyName.setType(type);
        			goodsPropertyName.setSquence(squence);
        			goodsPropertyName.setIsSales(isSales);
        			goodsPropertyName.setIsBasic(isBasic);
        			goodsPropertyName.setIsCheckbox(isCheckbox);
        			goodsPropertyName.setIsColor(isColor);
        			goodsPropertyName.setIsInput(isInput);
        			goodsPropertyName.setIsKey(isKey);
        			goodsPropertyName.setIsRadio(isRadio);
        			goodsPropertyName.setIsRequired(isRequired);
        			goodsPropertyName.setIsSearch(isSearch);
        			goodsPropertyName.setIsSelect(isSelect);
        			goodsPropertyName.setIsVisualEditor(isVisualEditor);
        			goodsPropertyName.setIsImage(isImage);
        			goodsPropertyName.setCreaterId(userId);
        			goodsPropertyName.setCreateTime(getNow());
        			goodsPropertyName.setPropertyValues(goodsPropertyValues);
        			
        			//获取商品属性值表单列表
        			List<GoodsPropertyValueForm> goodsPropertyValueForms = goodsPropertyNameForm.getGoodsPropertyValueForms();
    			
        			//判断属性值表单列表是否为空
        			if(goodsPropertyValueForms == null || goodsPropertyValueForms.size() <=0){
        				return EntryError.EMPTY(PROPERTY_VALUE);
        			}
        			
        			//循环商品属性值表单列表
        			for (GoodsPropertyValueForm goodsPropertyValueForm : goodsPropertyValueForms) {
        				
        				//判断属性值ID是否为空
        				String propertyValueId = goodsPropertyValueForm.getPropertyValueId();
            			if(StringUtils.isBlank(propertyNameId)){
            				return EntryError.EMPTY(PROPERTY_VALUE_ID);
            			}
        				
            			//判断属性值名称是否为空
        				String propertyValueName = goodsPropertyValueForm.getName();
            			if(StringUtils.isBlank(propertyValueName)){
            				return EntryError.EMPTY(PROPERTY_VALUE_NAME);
            			}
            			
            			//判断属性名称长度是否合规
            			if(name.length() > 255){
            				return EntryError.TOO_LONG(PROPERTY_VALUE_NAME);
            			}
        				
            			//判断属性值类型是否为空
        				PropertyValueType propertyValueType = goodsPropertyValueForm.getType();
        				if(propertyValueType == null){
        					return EntryError.EMPTY(PROPERTY_VALUE_TYPE);
        				}
        				
        				//判断属性值排列序号是否为空
        				Integer propertyValueSquence = goodsPropertyValueForm.getSquence();
        				if(propertyValueSquence == null){
        					return EntryError.EMPTY(PROPERTY_VALUE_SQUENCE);
        				}
        				
        				//判断图片地址是否为空，为空则为默认图片。
        				Boolean isDefaultImage = null;
        				String imageUrl = goodsPropertyValueForm.getImageUrl();
        				if(StringUtils.isEmpty(imageUrl)){
        					isDefaultImage = true;
        				}else{
        					isDefaultImage = true;
        				}
        				
        				//判断备注长度是否合规
        				String remark = goodsPropertyValueForm.getRemark();
            			if(!StringUtils.isEmpty(remark)){
            				if(remark.length()>255){
            					EntryError.TOO_LONG(PROPERTY_VALUE_REMARK);
            				}
            			}
            			
            			//创建属性值实体类，封装属性值信息
            			GoodsPropertyValue goodsPropertyValue = new GoodsPropertyValue();
            			goodsPropertyValue.setId(generateUUID());
            			goodsPropertyValue.setGoodsPropertyNameId(goodsPropertyNameId);
            			goodsPropertyValue.setPropertyValueId(propertyValueId);
            			goodsPropertyValue.setPropertyNameId(goodsPropertyNameId);
            			goodsPropertyValue.setGoodsId(goodsId);
            			goodsPropertyValue.setPropertyNameType(type);
            			goodsPropertyValue.setType(propertyValueType);
            			goodsPropertyValue.setSquence(propertyValueSquence);
            			goodsPropertyValue.setName(propertyValueName);
            			goodsPropertyValue.setIsDefaultImage(isDefaultImage);
            			goodsPropertyValue.setCreaterId(userId);
            			goodsPropertyValue.setCreateTime(getNow());
            			goodsPropertyValue.setRemark(remark);
            			
            			//将属性值信息封装到List集合中
            			goodsPropertyValues.add(goodsPropertyValue);
        			}
        		}
    		}

    		//获取设置运费参数表单
    		SetFreightParamForm freightParamForm = form.getSetFreightParamForm();
    		
    		//创建运费参数实体类
    		FreightParam freightParam = new FreightParam();
    		
    		//封装运费参数信息到商品实体类中
    		goods.setFreightParam(freightParam);
    		
    		//判断运费参数表单是否为空
    		if(freightParamForm != null){
    			
    			//封装运费参数信息
        		freightParam.setGoodsId(goods.getId());
        		freightParam.setCreaterId(userId);
        		freightParam.setCreateTime(getNow());
        		freightParam.setUpdaterId(userId);
        		freightParam.setUpdateTime(getNow());
        		
        		//获取运费类型
        		FreightType type = freightParamForm.getType();
        		freightParam.setType(type);
        		
        		//判断运费类型为固定运费或不固定运费
        		if(type.equals(FreightType.FIXED)){
        			
        			//判断运费价格是否为空
        			if(freightParamForm.getPrice() == null){
        				return EntryError.EMPTY(PRICE);
        			}
        			
        			//设置运费价格
        			freightParam.setPrice(freightParamForm.getPrice());
        		}else{
        			
        			//判断运费模板ID是否为空
        			if(StringUtils.isEmpty(freightParamForm.getFreightId())){
        				return EntryError.EMPTY(FREIGHT_ID);
        			}
        			
        			//设置运费模板ID
        			freightParam.setFreightId(freightParamForm.getFreightId());
        			
        			//判断重量或体积是否符合格式规范
        			String amount = freightParamForm.getAmount();
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
    		}
    		
    		
    		//执行添加
    		ResponseCode responseCode = goodsService.save(goods);
        	
            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

	/**
	 * 编辑商品信息。
     * @param req 用户请求。
     * @param form 编辑商品表单。
	 * @return 成功返回商品ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_EDIT, method=RequestMethod.POST)
    public @ResponseBody ResponseCode edit(HttpServletRequest req, @RequestBody @Valid EditGoodsForm form, BindingResult result) {
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
    		
    		//创建商品实体类
    		Goods goods = new Goods();
    		
    		//创建分组列表
    		List<Group> groups = new ArrayList<>();
    		
    		//创建SKU列表
    		List<Sku> skus = new ArrayList<>();
    		
    		//创建商品属性名列表
    		List<GoodsPropertyName> goodsPropertyNames = new ArrayList<>();
    		
    		//获取商品ID
    		String goodsId = form.getId();
    		
    		//获取分组ID列表
    		List<String> groupIds = form.getGroupIds();
    		
    		//将分组信息封装到分组列表中
    		for (String groupId : groupIds) {
				Group group = new Group();
				group.setId(groupId);
				groups.add(group);
			}
    		
    		//获取SKU表单列表
    		List<SkuForm> skuForms = form.getSkuForms();
    		
    		//判断SKU表单列表是否为空
    		if(skuForms == null || skuForms.size()<=0){
    			return EntryError.EMPTY(SKU);
    		}
    		
    		//创建Set集合接收SKU描述
    		Set<String> descSet = new HashSet<>();
    		
    		//循环SKU表单列表
    		for (SkuForm skuForm : skuForms) {
    			
    			//判断SKU描述长度是否合规
    			String description = skuForm.getDescription();
    			if(description.length() > 270){
    				return EntryError.TOO_LONG(SALES_PROPERTY); // 销售属性过多
    			}
    			
    			//将SKU描述保存到Set集合中
    			descSet.add(description);
    			
    			//判断SKU库存是否为空
    			Integer inventory = skuForm.getInventory();
    			if(inventory == null){
        			if(form.getInventoryType().equals(InventoryType.LIMITED)){
        				return EntryError.EMPTY(INVENTORY);
        			}
    			}else{
    				
    				//判断SKU库存取值范围
    				if(inventory > 9999 || inventory<0){
    					return EntryError.OVERRANGE(INVENTORY);
    				}
    			}
    			
    			//创建价格正则表达式
    			Pattern pricePattern = Pattern.compile("^\\d{1,5}\\.(\\d\\d)$");
    			
    			//判断SKU原价是否为空
    			BigDecimal originalPrice = skuForm.getOriginalPrice();
    			if(originalPrice == null){
    				return EntryError.EMPTY(ORIGINAL_PRICE);
    			}
    			
    			//判断SKU原价格式是否正确
    			if(!pricePattern.matcher(originalPrice.toString()).matches() || originalPrice.compareTo(new BigDecimal("0.00")) <= 0){
    				return EntryError.FORMAT(ORIGINAL_PRICE); // SKU原价格式不正确
    			}
    			
    			//判断SKU售价是否为空
    			BigDecimal price = skuForm.getPrice();
    			if(price == null){
    				return EntryError.EMPTY(PRICE);
    			}
    			
    			//判断SKU原价格式是否正确
    			if(!pricePattern.matcher(price.toString()).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
    				return EntryError.FORMAT(PRICE); // SKU售价格式不正确
    			}
    			
    			//判断备注长度是否合规
    			String remark = skuForm.getRemark();
    			if(!StringUtils.isEmpty(remark)){
    				if(remark.length()>255){
    					EntryError.TOO_LONG(REMARK);
    				}
    			}
    			
    			//创建编码正则表达式
    			Pattern codePattern = Pattern.compile("^[0-9a-zA-Z]+$");
    			
    			//判断条码格式、长度是否合规
    			String barcode = skuForm.getBarcode();
    			if(!StringUtils.isEmpty(barcode)){
        			if(!codePattern.matcher(barcode).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
        				return EntryError.FORMAT(BARCODE); // 商品条码格式不正确
        			}
        			
        			if(barcode.length() > 255){
        				return EntryError.TOO_LONG(BARCODE); // 商品条码过长
        			}
    			}

    			//判断编码格式、长度是否合规
    			String code = skuForm.getCode();
    			if(!StringUtils.isEmpty(code)){
        			if(!codePattern.matcher(code).matches() || price.compareTo(new BigDecimal("0.00")) <= 0){
        				return EntryError.FORMAT(CODE); // 商品条码格式不正确
        			}
        			
        			if(code.length() > 255){
        				return EntryError.TOO_LONG(CODE); // 商品条码过长
        			}
    			}
    			
    			//判断起售量长度是否合规
    			Integer salesVolume = skuForm.getSalesVolume();
    			if(salesVolume != null){
    				if(salesVolume > 9999 || salesVolume <=0){
    					return EntryError.OVERRANGE(SALES_VOLUME); // 起售量长度不合规
    				}
    			}else{
    				salesVolume = 1; // 默认起售量为1
    			}
    			
    			//创建SKU实体类封装SKU信息
    			Sku sku = new Sku();
    			
    			if(StringUtils.isEmpty(skuForm.getId())){
    				sku.setId(generateUUID());
    			}else{
    				sku.setId(skuForm.getId());
    			}
    			
    			sku.setGoodsId(goodsId);
    			sku.setInventoryType(form.getInventoryType());
    			sku.setLockedInventory(0); // 设置锁定库存为零
    			sku.setSalesVolume(salesVolume);
    			sku.setOriginalPrice(originalPrice);
    			sku.setPrice(price);
    			sku.setDescription(description);
    			sku.setCode(code);
    			sku.setBarcode(barcode);
    			sku.setCreaterId(userId);
    			sku.setCreateTime(getNow());
    			sku.setRemark(remark);
    			
    			//添加SKU实体类到集合中
    			skus.add(sku);
			}
    		
    		//判断有无重复的SKU描述
    		if(descSet.size() != skus.size()){
    			return EntryError.DUPLICATE(SKU_DESCRIPTION);
    		}
    		
    		//对sku排序（按照原价正序），获取最低原价
    		List<Sku> sortPriceSkus = sortSkuByPriceAsc(skus);
    		BigDecimal minPrice = sortPriceSkus.get(0).getPrice();
    		
    		//对sku排序（按照售价正序），获取最低售价
    		List<Sku> sortOriginalPriceSkus = sortSkuByOriginalPriceAsc(skus);
    		BigDecimal minOriginalPrice = sortOriginalPriceSkus.get(0).getOriginalPrice();
    		
    		//封装商品信息
    		goods.setId(goodsId);
    		goods.setStoreId(form.getStoreId());
    		goods.setCategoryId(form.getCategoryId());
    		goods.setMinPrice(minPrice);
    		goods.setMinOriginalPrice(minOriginalPrice);
    		goods.setInventoryType(form.getInventoryType());
    		goods.setMainDiagram(form.getMainDiagram());
    		
    		//判断来源
    		if(!form.getSource().equals(SourceType.APPX)){
    			
    			//判断商品详情是否为空
    			if(StringUtils.isEmpty(form.getDetail())){
    				return EntryError.EMPTY(DETAIL);
    			}
    			
    			//判断商品售后服务是否为空
    			if(StringUtils.isEmpty(form.getService())){
    				return EntryError.EMPTY(SERVICE);
    			}
    		}
    		goods.setDetail(form.getDetail());
    		goods.setService(form.getService());
    		goods.setType(GoodsType.DEFAULT);
    		goods.setStatus(form.getStatus());
    		goods.setIsRecommend(form.getIsRecommend());
    		goods.setUpdaterId(userId);
    		goods.setUpdateTime(getNow());
    		goods.setRemark(form.getRemark());
    		goods.setGroups(groups);
    		goods.setSkus(sortPriceSkus);
    		goods.setPropertyNames(goodsPropertyNames);
    		
    		//获取属性名表单列表
    		List<GoodsPropertyNameForm> goodsPropertyNameForms = form.getGoodsPropertyNameForms();
    		
    		//判断属性名表单列表是否为空
    		if(goodsPropertyNameForms != null && goodsPropertyNameForms.size()>0){
        		
    			//循环属性名表单列表
        		for (GoodsPropertyNameForm goodsPropertyNameForm : goodsPropertyNameForms) {
        			
        			//判断属性名ID是否为空
        			String propertyNameId = goodsPropertyNameForm.getPropertyNameId();
        			if(StringUtils.isBlank(propertyNameId)){
        				return EntryError.EMPTY(PROPERTY_NAME_ID);
        			}
        			
        			//判断属性名称是否为空
        			String name = goodsPropertyNameForm.getName();
        			if(StringUtils.isBlank(name)){
        				return EntryError.EMPTY(PROPERTY_NAME);
        			}
        			
        			//判断属性名称长度是否合规
        			if(name.length() > 40){
        				return EntryError.TOO_LONG(PROPERTY_NAME);
        			}
        			
        			//判断属性名类型是否为空
        			PropertyNameType type = goodsPropertyNameForm.getType();
        			if(type == null){
        				return EntryError.EMPTY(PROPERTY_NAME_TYPE);
        			}
        			
        			//判断排列序号是否为空
        			Integer squence = goodsPropertyNameForm.getSquence();
        			if(squence == null){
        				return EntryError.EMPTY(PROPERTY_NAME_SQUENCE);
        			}
        			
        			//判断“是否搜索属性”是否为空
        			Boolean isSearch = goodsPropertyNameForm.getIsSearch();
        			if(isSearch == null){
        				return EntryError.EMPTY(IS_SEARCH);
        			}
        			
        			//判断是“是否单选框”是否为空
        			Boolean isRadio = goodsPropertyNameForm.getIsRadio();
        			if(isRadio == null){
        				return EntryError.EMPTY(IS_RADIO);
        			}
        			
        			//判断是“是否多选框”是否为空
        			Boolean isCheckbox = goodsPropertyNameForm.getIsCheckbox();
        			if(isCheckbox == null){
        				return EntryError.EMPTY(IS_CHECKBOX);
        			}
        			
        			//判断是“是否输入框”是否为空
        			Boolean isInput = goodsPropertyNameForm.getIsInput();
        			if(isInput == null){
        				return EntryError.EMPTY(IS_INPUT);
        			}
        			
        			//判断是“是否下拉框”是否为空
        			Boolean isSelect = goodsPropertyNameForm.getIsSelect();
        			if(isSelect == null){
        				return EntryError.EMPTY(IS_SELECT);
        			}
        			
        			//判断是“是否文本编辑器”是否为空
        			Boolean isVisualEditor = goodsPropertyNameForm.getIsVisualEditor();
        			if(isVisualEditor == null){
        				return EntryError.EMPTY(IS_VISUAL_EDITOR);
        			}
        			
        			//判断是“是否颜色属性”是否为空
        			Boolean isColor = goodsPropertyNameForm.getIsColor();
        			if(isColor == null){
        				return EntryError.EMPTY(IS_COLOR);
        			}
        			
        			//判断是“是否必须属性”是否为空
        			Boolean isRequired = goodsPropertyNameForm.getIsRequired();
        			if(isRequired == null){
        				return EntryError.EMPTY(IS_REQUIRED);
        			}
        			
        			//判断是“是否销售属性”是否为空
        			Boolean isSales = goodsPropertyNameForm.getIsSales();
        			if(isSales == null){
        				return EntryError.EMPTY(IS_SALES);
        			}
        			
        			//判断是“是否基础属性”是否为空
        			Boolean isBasic = goodsPropertyNameForm.getIsBasic();
        			if(isBasic == null){
        				return EntryError.EMPTY(IS_BASIC);
        			}
        			
        			//判断是“是否关键属性”是否为空
        			Boolean isKey = goodsPropertyNameForm.getIsKey();
        			if(isKey == null){
        				return EntryError.EMPTY(IS_KEY);
        			}
        			
        			//判断是“是否图片属性”是否为空
        			Boolean isImage = goodsPropertyNameForm.getIsImage();
        			if(isImage == null){
        				return EntryError.EMPTY(IS_IMAGE);
        			}
        			
        			//创建商品属性值列表
        			List<GoodsPropertyValue> goodsPropertyValues = new ArrayList<>();
        			
        			//获取商品属性名ID
        			String goodsPropertyNameId = generateUUID();
        			if(!StringUtils.isEmpty(goodsPropertyNameForm.getId())){
        				goodsPropertyNameId = goodsPropertyNameForm.getId();
        			}
        			
        					
        			//封装属性名信息
        			GoodsPropertyName goodsPropertyName = new GoodsPropertyName();
        			goodsPropertyName.setId(goodsPropertyNameId);
        			goodsPropertyName.setPropertyNameId(propertyNameId);
        			goodsPropertyName.setName(name);
        			goodsPropertyName.setCategoryId(form.getCategoryId());
        			goodsPropertyName.setType(type);
        			goodsPropertyName.setSquence(squence);
        			goodsPropertyName.setIsSales(isSales);
        			goodsPropertyName.setIsBasic(isBasic);
        			goodsPropertyName.setIsCheckbox(isCheckbox);
        			goodsPropertyName.setIsColor(isColor);
        			goodsPropertyName.setIsInput(isInput);
        			goodsPropertyName.setIsKey(isKey);
        			goodsPropertyName.setIsRadio(isRadio);
        			goodsPropertyName.setIsRequired(isRequired);
        			goodsPropertyName.setIsSearch(isSearch);
        			goodsPropertyName.setIsSelect(isSelect);
        			goodsPropertyName.setIsVisualEditor(isVisualEditor);
        			goodsPropertyName.setIsImage(isImage);
        			goodsPropertyName.setCreaterId(userId);
        			goodsPropertyName.setCreateTime(getNow());
        			goodsPropertyName.setPropertyValues(goodsPropertyValues);
        			
        			//获取商品属性值表单列表
        			List<GoodsPropertyValueForm> goodsPropertyValueForms = goodsPropertyNameForm.getGoodsPropertyValueForms();
    			
        			//判断属性值表单列表是否为空
        			if(goodsPropertyValueForms == null || goodsPropertyValueForms.size() <=0){
        				return EntryError.EMPTY(PROPERTY_VALUE);
        			}
        			
        			//循环商品属性值表单列表
        			for (GoodsPropertyValueForm goodsPropertyValueForm : goodsPropertyValueForms) {
        				
        				//判断属性值ID是否为空
        				String propertyValueId = goodsPropertyValueForm.getPropertyValueId();
            			if(StringUtils.isBlank(propertyNameId)){
            				return EntryError.EMPTY(PROPERTY_VALUE_ID);
            			}
        				
            			//判断属性值名称是否为空
        				String propertyValueName = goodsPropertyValueForm.getName();
            			if(StringUtils.isBlank(propertyValueName)){
            				return EntryError.EMPTY(PROPERTY_VALUE_NAME);
            			}
            			
            			//判断属性名称长度是否合规
            			if(name.length() > 255){
            				return EntryError.TOO_LONG(PROPERTY_VALUE_NAME);
            			}
        				
            			//判断属性值类型是否为空
        				PropertyValueType propertyValueType = goodsPropertyValueForm.getType();
        				if(propertyValueType == null){
        					return EntryError.EMPTY(PROPERTY_VALUE_TYPE);
        				}
        				
        				//判断属性值排列序号是否为空
        				Integer propertyValueSquence = goodsPropertyValueForm.getSquence();
        				if(propertyValueSquence == null){
        					return EntryError.EMPTY(PROPERTY_VALUE_SQUENCE);
        				}
        				
        				//判断图片地址是否为空，为空则为默认图片。
        				Boolean isDefaultImage = null;
        				String imageUrl = goodsPropertyValueForm.getImageUrl();
        				if(StringUtils.isEmpty(imageUrl)){
        					isDefaultImage = true;
        				}else{
        					isDefaultImage = true;
        				}
        				
        				//判断备注长度是否合规
        				String remark = goodsPropertyValueForm.getRemark();
            			if(!StringUtils.isEmpty(remark)){
            				if(remark.length()>255){
            					EntryError.TOO_LONG(PROPERTY_VALUE_REMARK);
            				}
            			}
            			
            			//创建属性值实体类，封装属性值信息
            			GoodsPropertyValue goodsPropertyValue = new GoodsPropertyValue();
            			goodsPropertyValue.setId(generateUUID());
            			if(!StringUtils.isEmpty(goodsPropertyValueForm.getId())){
            				goodsPropertyValue.setId(goodsPropertyValueForm.getId());
            			}
            			goodsPropertyValue.setGoodsPropertyNameId(goodsPropertyNameId);
            			goodsPropertyValue.setPropertyValueId(propertyValueId);
            			goodsPropertyValue.setPropertyNameId(goodsPropertyNameId);
            			goodsPropertyValue.setGoodsId(goodsId);
            			goodsPropertyValue.setPropertyNameType(type);
            			goodsPropertyValue.setType(propertyValueType);
            			goodsPropertyValue.setSquence(propertyValueSquence);
            			goodsPropertyValue.setName(propertyValueName);
            			goodsPropertyValue.setIsDefaultImage(isDefaultImage);
            			goodsPropertyValue.setCreaterId(userId);
            			goodsPropertyValue.setCreateTime(getNow());
            			goodsPropertyValue.setRemark(remark);
            			
            			//将属性值信息封装到List集合中
            			goodsPropertyValues.add(goodsPropertyValue);
        			}
        		}
    		}

    		//获取设置运费参数表单
    		SetFreightParamForm freightParamForm = form.getSetFreightParamForm();
    		
    		//创建运费参数实体类
    		FreightParam freightParam = new FreightParam();
    		
    		//封装运费参数信息到商品实体类中
    		goods.setFreightParam(freightParam);
    		
    		//判断运费参数表单是否为空
    		if(freightParamForm != null){
    			
    			//封装运费参数信息
        		freightParam.setGoodsId(goods.getId());
        		freightParam.setCreaterId(userId);
        		freightParam.setCreateTime(getNow());
        		freightParam.setUpdaterId(userId);
        		freightParam.setUpdateTime(getNow());
        		
        		//获取运费类型
        		FreightType type = freightParamForm.getType();
        		freightParam.setType(type);
        		
        		//判断运费类型为固定运费或不固定运费
        		if(type.equals(FreightType.FIXED)){
        			
        			//判断运费价格是否为空
        			if(freightParamForm.getPrice() == null){
        				return EntryError.EMPTY(PRICE);
        			}
        			
        			//设置运费价格
        			freightParam.setPrice(freightParamForm.getPrice());
        		}else{
        			
        			//判断运费模板ID是否为空
        			if(StringUtils.isEmpty(freightParamForm.getFreightId())){
        				return EntryError.EMPTY(FREIGHT_ID);
        			}
        			
        			//设置运费模板ID
        			freightParam.setFreightId(freightParamForm.getFreightId());
        			
        			//判断重量或体积是否符合格式规范
        			String amount = freightParamForm.getAmount();
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
    		}
    		
    		//执行更新
    		ResponseCode responseCode = goodsService.update(goods);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 批量删除商品。
     * @param req 用户请求。
     * @param form 删除商品表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_DELETE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode delete(HttpServletRequest req, @RequestBody @Valid DeleteGoodsForm form, BindingResult result) {
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
    		ResponseCode responseCode = goodsService.deleteBatch(form.getIds());
        	
            //返回删除结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 批量上架商品。
     * @param req 用户请求。
     * @param form 更新商品状态表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_SHELVE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode shelve(HttpServletRequest req, @RequestBody @Valid UpdateGoodsStatusForm form, BindingResult result) {
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
    		
    		//创建商品列表，封装请求参数
    		List<Goods> goodses = new ArrayList<>();
    		
    		//获取ID列表
    		List<String> ids = form.getIds();
    		
    		//循环ID列表
    		for (String id : ids) {
				
    			//创建商品实体类，封装商品ID
    			Goods goods = new Goods();
    			goods.setId(id);
    			goods.setStatus(GoodsStatus.DEFAULT);
      			goods.setUpdaterId(userId);
    			goods.setUpdateTime(getNow());
    			
    			//添加商品实体到商品列表
    			goodses.add(goods);
			}
    		
    		//执行更新
    		ResponseCode responseCode = goodsService.shelveGoodses(goodses);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 批量下架商品。
     * @param req 用户请求。
     * @param form 更新商品状态表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_APPLY, method=RequestMethod.POST)
    public @ResponseBody ResponseCode apply(HttpServletRequest req, @RequestBody @Valid UpdateGoodsStatusForm form, BindingResult result) {
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
    		
    		//创建商品列表，封装请求参数
    		List<Goods> goodses = new ArrayList<>();
    		
    		//获取ID列表
    		List<String> ids = form.getIds();
    		
    		//循环ID列表
    		for (String id : ids) {
				
    			//创建商品实体类，封装商品ID
    			Goods goods = new Goods();
    			goods.setId(id);
    			goods.setStatus(GoodsStatus.APPLIED);
      			goods.setUpdaterId(userId);
    			goods.setUpdateTime(getNow());
    			
    			//添加商品实体到商品列表
    			goodses.add(goods);
			}
    		
    		//执行更新
    		ResponseCode responseCode = goodsService.applyGoodses(goodses);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 批量移除商品。
     * @param req 用户请求。
     * @param form 更新商品状态表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_REMOVE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode remove(HttpServletRequest req, @RequestBody @Valid UpdateGoodsStatusForm form, BindingResult result) {
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
    		
    		//创建商品列表，封装请求参数
    		List<Goods> goodses = new ArrayList<>();
    		
    		//获取ID列表
    		List<String> ids = form.getIds();
    		
    		//循环ID列表
    		for (String id : ids) {
				
    			//创建商品实体类，封装商品ID
    			Goods goods = new Goods();
    			goods.setId(id);
    			goods.setStatus(GoodsStatus.REMOVED);
    			goods.setUpdaterId(userId);
    			goods.setUpdateTime(getNow());
    			
    			//添加商品实体到商品列表
    			goodses.add(goods);
			}
    		
    		//执行更新
    		ResponseCode responseCode = goodsService.removeGoodses(goodses);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 更新商品推荐状态。
     * @param req 用户请求。
     * @param form 更新商品推荐状态表单。
	 * @return 成功返回成功响应码，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_UPDATE_RECOMMEND, method=RequestMethod.POST)
    public @ResponseBody ResponseCode updateRecommend(HttpServletRequest req, @RequestBody @Valid UpdateGoodsRecommendForm form, BindingResult result) {
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
    		
    		//创建商品列表，封装请求参数
    		List<Goods> goodses = new ArrayList<>();
    		
    		//获取ID列表
    		List<String> ids = form.getIds();
    		
    		//循环ID列表
    		for (String id : ids) {
				
    			//创建商品实体类，封装商品ID
    			Goods goods = new Goods();
    			goods.setId(id);
    			goods.setIsRecommend(form.getIsRecommend());
    			goods.setUpdaterId(userId);
    			goods.setUpdateTime(getNow());
    			
    			//添加商品实体到商品列表
    			goodses.add(goods);
			}
    		
    		//执行更新
    		ResponseCode responseCode = goodsService.updateBatchRecommend(goodses);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    //TODO
    
	/**
     * 根据店铺ID、分组ID、商品状态、推荐状态、类目ID、模糊查询关键字分页查询商品列表(根据创建时间倒叙)
     * @param form  查询商品列表表单(店铺ID、分组ID、商品状态、推荐状态、类目ID、模糊查询关键字、分页起始（0为第一条记录）、每页显示记录数）。
     * @return 查询到结果返回商品列表，未查询到结果返回空，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_LIST, method=RequestMethod.POST)
    public @ResponseBody ResponseCode list(HttpServletRequest req, @RequestBody @Valid QueryGoodsesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = goodsService.findGoodses(form.getStoreId(), form.getGroupId(), GoodsType.DEFAULT, form.getStatus(), form.getIsRecommend(), form.getCategoryId(), form.getKeyword(), form.getStart(), form.getPageSize());
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据店铺ID、分组ID、商品状态、推荐状态、类目ID、模糊查询关键字分页查询商品列表(根据创建时间倒叙)
     * @param form  查询商品列表表单(店铺ID、分组ID、商品状态、推荐状态、类目ID、模糊查询关键字、分页起始（0为第一条记录）、每页显示记录数）。
     * @return 查询到结果返回商品列表，未查询到结果返回空，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_SELLING_LIST, method=RequestMethod.POST)
    public @ResponseBody ResponseCode sellingList(HttpServletRequest req, @RequestBody @Valid QuerySellingGoodsesForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = goodsService.findSellingGoodses(form.getStoreId(), form.getGroupId(), GoodsType.DEFAULT, form.getIsRecommend(), form.getCategoryId(), form.getKeyword(), form.getStart(), form.getPageSize());
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据商品ID查询商品详细信息（根据属性名排列序号正序、属性值排列序号正序）。
     * @param id 商品ID。
     * @return 查询到结果返回商品详情，未查询到结果返回空，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_DETAIL, method=RequestMethod.GET)
    public @ResponseBody ResponseCode detail(HttpServletRequest req, @RequestParam(ID)String id) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = goodsService.findGoodsDetail(id, GoodsType.DEFAULT);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据购买信息列表查询购买详情列表。
     * @param form 购买信息列表表单。
     * @return 成功返回购买信息列表，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_PURCHASE_DETAIL, method=RequestMethod.POST)
    public @ResponseBody ResponseCode purchaseDetail(HttpServletRequest req, @RequestBody @Valid PurchaseInfosForm form, BindingResult result) {
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
    		
    		//获取购买信息表单列表
    		List<PurchaseInfoForm> purchaseInfoForms = form.getPurchaseInfoForms();
    		
    		//创建购买信息列表
    		List<PurchaseInfo> purchaseInfos = new ArrayList<>();
    		
    		//循环购买信息表单列表
    		for (PurchaseInfoForm purchaseInfoForm : purchaseInfoForms) {
				
    			//判断商品ID是否为空
    			String goodsId = purchaseInfoForm.getGoodsId();
    			if(StringUtils.isEmpty(goodsId)){
    				EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//判断SKUID是否为空
    			String skuId = purchaseInfoForm.getSkuId();
    			if(StringUtils.isEmpty(skuId)){
    				EntryError.EMPTY(SKU_ID);
    			}
    			
    			//判断购买数量是否为空
    			Integer quantity = purchaseInfoForm.getQuantity();
    			if(quantity == null){
    				EntryError.EMPTY(QUANTITY);
    			}
    			
    			//创建购买信息实体类封装购买信息
    			PurchaseInfo purchaseInfo = new PurchaseInfo();
    			purchaseInfo.setGoodsId(goodsId);
    			purchaseInfo.setSkuId(skuId);
    			purchaseInfo.setQuantity(quantity);
    			
    			//将购买信息添加到List集合中
    			purchaseInfos.add(purchaseInfo);
			}
    		
    		//执行查询
    		ResponseCode responseCode = goodsService.getPurchaseDetails(purchaseInfos);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 锁定SKU库存。
     * @param form 库存管理表单。
     * @return  成功返回订单ID，失败返回相应的错误码
	 */
    @RequestMapping(value=REQ_LOCK_INVENTORY, method=RequestMethod.POST)
    public @ResponseBody ResponseCode lockInventory(HttpServletRequest req, @RequestBody @Valid InventoryOperationForm form, BindingResult result) {
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
    		
    		//获取购买信息表单列表
    		List<PurchaseInfoForm> purchaseInfoForms = form.getPurchaseInfoForms();
    		
    		//创建购买信息列表
    		List<PurchaseInfo> purchaseInfos = new ArrayList<>();
    		
    		//循环购买信息表单列表
    		for (PurchaseInfoForm purchaseInfoForm : purchaseInfoForms) {
				
    			//判断商品ID是否为空
    			String goodsId = purchaseInfoForm.getGoodsId();
    			if(StringUtils.isEmpty(goodsId)){
    				EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//判断SKUID是否为空
    			String skuId = purchaseInfoForm.getSkuId();
    			if(StringUtils.isEmpty(skuId)){
    				EntryError.EMPTY(SKU_ID);
    			}
    			
    			//判断购买数量是否为空
    			Integer quantity = purchaseInfoForm.getQuantity();
    			if(quantity == null){
    				EntryError.EMPTY(QUANTITY);
    			}
    			
    			//创建购买信息实体类封装购买信息
    			PurchaseInfo purchaseInfo = new PurchaseInfo();
    			purchaseInfo.setGoodsId(goodsId);
    			purchaseInfo.setSkuId(skuId);
    			purchaseInfo.setQuantity(quantity);
    			
    			//将购买信息添加到List集合中
    			purchaseInfos.add(purchaseInfo);
			}
    		
    		//执行锁定库存
    		ResponseCode responseCode = skuService.lockBatchInventory(purchaseInfos, form.getOrderId(), userId, getNow());
        	
            //返回处理结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 释放SKU库存。
     * @param form 库存管理表单。
     * @return  成功返回订单ID，失败返回相应的错误码
	 */
    @RequestMapping(value=REQ_RELEASE_INVENTORY, method=RequestMethod.POST)
    public @ResponseBody ResponseCode releaseInventory(HttpServletRequest req, @RequestBody @Valid InventoryOperationForm form, BindingResult result) {
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
    		
    		//获取购买信息表单列表
    		List<PurchaseInfoForm> purchaseInfoForms = form.getPurchaseInfoForms();
    		
    		//创建购买信息列表
    		List<PurchaseInfo> purchaseInfos = new ArrayList<>();
    		
    		//循环购买信息表单列表
    		for (PurchaseInfoForm purchaseInfoForm : purchaseInfoForms) {
				
    			//判断商品ID是否为空
    			String goodsId = purchaseInfoForm.getGoodsId();
    			if(StringUtils.isEmpty(goodsId)){
    				EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//判断SKUID是否为空
    			String skuId = purchaseInfoForm.getSkuId();
    			if(StringUtils.isEmpty(skuId)){
    				EntryError.EMPTY(SKU_ID);
    			}
    			
    			//判断购买数量是否为空
    			Integer quantity = purchaseInfoForm.getQuantity();
    			if(quantity == null){
    				EntryError.EMPTY(QUANTITY);
    			}
    			
    			//创建购买信息实体类封装购买信息
    			PurchaseInfo purchaseInfo = new PurchaseInfo();
    			purchaseInfo.setGoodsId(goodsId);
    			purchaseInfo.setSkuId(skuId);
    			purchaseInfo.setQuantity(quantity);
    			
    			//将购买信息添加到List集合中
    			purchaseInfos.add(purchaseInfo);
			}
    		
    		//执行释放库存
    		ResponseCode responseCode = skuService.releaseBatchInventory(purchaseInfos, form.getOrderId(), userId, getNow());
        	
            //返回处理结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 扣减SKU库存。
     * @param form 库存管理表单。
     * @return  成功返回订单ID，失败返回相应的错误码
	 */
    @RequestMapping(value=REQ_DEDUCTION_INVENTORY, method=RequestMethod.POST)
    public @ResponseBody ResponseCode deductionInventory(HttpServletRequest req, @RequestBody @Valid InventoryOperationForm form, BindingResult result) {
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
    		
    		//获取购买信息表单列表
    		List<PurchaseInfoForm> purchaseInfoForms = form.getPurchaseInfoForms();
    		
    		//创建购买信息列表
    		List<PurchaseInfo> purchaseInfos = new ArrayList<>();
    		
    		//循环购买信息表单列表
    		for (PurchaseInfoForm purchaseInfoForm : purchaseInfoForms) {
				
    			//判断商品ID是否为空
    			String goodsId = purchaseInfoForm.getGoodsId();
    			if(StringUtils.isEmpty(goodsId)){
    				EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//判断SKUID是否为空
    			String skuId = purchaseInfoForm.getSkuId();
    			if(StringUtils.isEmpty(skuId)){
    				EntryError.EMPTY(SKU_ID);
    			}
    			
    			//判断购买数量是否为空
    			Integer quantity = purchaseInfoForm.getQuantity();
    			if(quantity == null){
    				EntryError.EMPTY(QUANTITY);
    			}
    			
    			//创建购买信息实体类封装购买信息
    			PurchaseInfo purchaseInfo = new PurchaseInfo();
    			purchaseInfo.setGoodsId(goodsId);
    			purchaseInfo.setSkuId(skuId);
    			purchaseInfo.setQuantity(quantity);
    			
    			//将购买信息添加到List集合中
    			purchaseInfos.add(purchaseInfo);
			}
    		
    		//执行扣减库存
    		ResponseCode responseCode = skuService.deductionBatchInventory(purchaseInfos, form.getOrderId(), userId, getNow());
        	
            //返回处理结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
}
