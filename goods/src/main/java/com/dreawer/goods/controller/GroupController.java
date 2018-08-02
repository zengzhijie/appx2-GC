package com.dreawer.goods.controller;

import com.dreawer.goods.domain.Goods;
import com.dreawer.goods.domain.Group;
import com.dreawer.goods.form.AddGroupForm;
import com.dreawer.goods.form.AddGroupGoodsesForm;
import com.dreawer.goods.form.DeleteGroupForm;
import com.dreawer.goods.form.EditGroupForm;
import com.dreawer.goods.form.UpdateGroupGoodsForm;
import com.dreawer.goods.form.UpdateGroupGoodsesForm;
import com.dreawer.goods.form.UpdateGroupSquenceForm;
import com.dreawer.goods.form.UpdateGroupStatusForm;
import com.dreawer.goods.lang.GroupStatus;
import com.dreawer.goods.lang.SourceType;
import com.dreawer.goods.service.GroupService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <CODE>GroupController</CODE>
 * 分组信息控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_GROUP)
public class GroupController extends BaseController{

    @Autowired
    private GroupService groupService; //分组信息service

	/**
	 * 添加分组信息。
     * @param req 用户请求。
     * @param form 添加分组信息表单。
	 * @return 成功返回分组ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_ADD, method=RequestMethod.POST)
    public @ResponseBody ResponseCode add(HttpServletRequest req, @RequestBody @Valid AddGroupForm form, BindingResult result) {
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
    		
    		//创建分组实体类
    		Group group = new Group();
    		
    		//封装分组信息数据
    		group.setId(generateUUID());
    		group.setStoreId(form.getStoreId());
    		group.setName(form.getName());
    		group.setParentId(form.getParentId());
    		group.setIsParent(false);
    		group.setSquence(form.getSquence());
    		group.setLogo(form.getLogo());
    		group.setIntro(form.getIntro());
    		group.setStatus(form.getStatus());
    		
    		//判断请求来源
    		if(form.getSource().equals(SourceType.APPX)){
    			
    			//判断跳转地址是否为空
    			if(StringUtils.isEmpty(form.getUrl())){
    				return EntryError.EMPTY(URL); // 跳转地址为空
    			}
    		}
    		group.setGoodsQuantity(0);
    		group.setCreaterId(userId);
    		group.setCreateTime(getNow());
    		group.setRemark(form.getRemark());
    		
    		//执行添加
    		ResponseCode responseCode = groupService.save(group);
    		
            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

	/**
	 * 添商品列表。
     * @param req 用户请求。
     * @param form 添商品列表表单。
	 * @return 成功返回分组ID，失败则返回相应错误码。
	 */
    @RequestMapping(value=REQ_ADD_GOODSES, method=RequestMethod.POST)
    public @ResponseBody ResponseCode addGoodses(HttpServletRequest req, @RequestBody @Valid AddGroupGoodsesForm form, BindingResult result) {
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
    		
    		//创建分组对象
    		Group group = new Group();
    		
    		//创建商品列表
    		List<Goods> goodses = new ArrayList<>();
    		
    		//循环商品ID列表
    		List<String> goodsIds = form.getGoodsIds();
    		for (String goodsId : goodsIds) {
				
    			//创建商品对象封装商品ID
    			Goods goods = new Goods();
    			goods.setId(goodsId);
    			
    			//将商品对象添加到商品列表中
    			goodses.add(goods);
			}
    		
    		//封装分组数据
    		group.setId(form.getGroupId());
    		group.setCreaterId(userId);
    		group.setCreateTime(getNow());
    		group.setGoodses(goodses);
    		
    		
    		//执行添加
    		ResponseCode responseCode = groupService.saveBatchGroupGoods(group);
        	
            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    /**
     * 根据分组ID删除分组信息、商品和分组关系。
     * @param req 用户请求。
     * @param form 删除分组表单。
     * @return 成功返回成功码、失败返回相应错误码。
     */
    @RequestMapping(value=REQ_DELETE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode delete(HttpServletRequest req, @RequestBody @Valid DeleteGroupForm form, BindingResult result) {
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
    		ResponseCode responseCode = groupService.delete(form.getId(), form.getStoreId());
        	
            //返回删除结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    /**
     * 更新分组、商品列表。
     * @param req 用户请求。
     * @param form 更新分组、商品列表表单。
     * @return 成功返回成功码、失败返回相应错误码。
     * @author kael
     * @since 1.0
     */
    @RequestMapping(value=REQ_UPDATE_GOODSES, method=RequestMethod.POST)
    public @ResponseBody ResponseCode updateGoodses(HttpServletRequest req, @RequestBody @Valid UpdateGroupGoodsesForm form, BindingResult result) {
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
    		
    		//获取分组、商品列表
    		List<UpdateGroupGoodsForm> groupGoodses = form.getGroupGoodses();
    		
    		//创建List集合接收请求参数
    		List<Map<String, Object>> groupGoodsMaps = new ArrayList<>();
    		
    		//循环列表
    		for (UpdateGroupGoodsForm groupGoods : groupGoodses) {
				
    			//判断请求参数是否为空
    			String currentGroupId = groupGoods.getCurrentGroupId();
    			String originalGroupId = groupGoods.getOriginalGroupId();
    			String goodsId = groupGoods.getGoodsId();
    			
    			//判断当前分组ID是否为空
    			if(StringUtils.isEmpty(currentGroupId)){
    				return EntryError.EMPTY(CURRENT_GROUP_ID);
    			}
    			
    			//判断原分组ID是否为空
    			if(StringUtils.isEmpty(originalGroupId)){
    				return EntryError.EMPTY(ORIGINAL_GROUP_ID);
    			}
    			
    			//判断商品ID是否为空
    			if(StringUtils.isEmpty(goodsId)){
    				return EntryError.EMPTY(GOODS_ID);
    			}
    			
    			//创建Map集合接收请求参数
    			Map<String, Object> groupGoodsMap = new HashMap<>();
    			groupGoodsMap.put(CURRENT_GROUP_ID, currentGroupId);
    			groupGoodsMap.put(ORIGINAL_GROUP_ID, originalGroupId);
    			groupGoodsMap.put(GOODS_ID, goodsId);
    			
    			//添加到List集合中
    			groupGoodsMaps.add(groupGoodsMap);
			}
    		
    		//执行更新
    		ResponseCode responseCode = groupService.updateBatchGroupGoodses(groupGoodsMaps, userId, getNow());
    		
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    /**
     * 更新分组排序。
     * @param req 用户请求。
     * @param form 更新分组排序表单。
	 * @return 成功返回分组ID，失败则返回相应错误码。
     */
    @RequestMapping(value=REQ_UPDATE_SQUENCE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode updateSquence(HttpServletRequest req, @RequestBody @Valid UpdateGroupSquenceForm form, BindingResult result) {
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
    		
    		//创建分组对象封装请求参数
    		Group group = new Group();
    		group.setId(form.getId());
    		group.setSquence(form.getSquence());
    		group.setUpdaterId(userId);
    		group.setUpdateTime(getNow());
    		
    		//执行更新
    		ResponseCode responseCode = groupService.updateSquence(group);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    /**
     * 更新分组状态。
     * @param req 用户请求。
     * @param form 更新分组状态表单。
	 * @return 成功返回成功码，失败则返回相应错误码。
     */
    @RequestMapping(value=REQ_UPDATE_STATUS, method=RequestMethod.POST)
    public @ResponseBody ResponseCode updateStatus(HttpServletRequest req, @RequestBody @Valid UpdateGroupStatusForm form, BindingResult result) {
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
    		
    		//获取分组ID列表
    		List<String> ids = form.getIds();
    		
    		//创建分组列表封装请求参数
    		List<Group> groups = new ArrayList<>();
    		
    		//循环分组ID列表
    		for (String id : ids) {
				
    			//创建分组实体类封装分组信息
    			Group group = new Group();
    			group.setId(id);
    			group.setStatus(form.getStatus());
    			group.setUpdaterId(userId);
    			group.setUpdateTime(getNow());
    			
    			//添加到分组列表中
    			groups.add(group);
			}
    		
    		//执行更新
    		ResponseCode responseCode = groupService.updateBatchStatus(groups);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
    /**
     * 编辑分组信息（名称、是否父分组属性、排列序号、LOGO、简介、状态、URL、商品数）。
     * @param req 用户请求。
     * @param form 编辑分组信息表单。
	 * @return 成功返回分组ID，失败则返回相应错误码。
     */
    @RequestMapping(value=REQ_EDIT, method=RequestMethod.POST)
    public @ResponseBody ResponseCode edit(HttpServletRequest req, @RequestBody @Valid 	EditGroupForm form, BindingResult result) {
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
    		
    		//创建分组实体类
    		Group group = new Group();
    		
    		//封装分组信息数据
    		group.setId(form.getId());
    		group.setStoreId(form.getStoreId());
    		group.setName(form.getName());
    		group.setParentId(form.getParentId());
    		group.setIsParent(false);
    		group.setSquence(form.getSquence());
    		group.setLogo(form.getLogo());
    		group.setIntro(form.getIntro());
    		group.setStatus(form.getStatus());
    		
    		//判断请求来源
    		if(form.getSource().equals(SourceType.APPX)){
    			
    			//判断跳转地址是否为空
    			if(StringUtils.isEmpty(form.getUrl())){
    				return EntryError.EMPTY(URL); // 跳转地址为空
    			}
    		}
    		group.setGoodsQuantity(0);
    		group.setUpdaterId(userId);
    		group.setUpdateTime(getNow());
    		group.setRemark(form.getRemark());
    		
    		//执行更新
    		ResponseCode responseCode = groupService.update(group);
        	
            //返回更新结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据父ID、店铺ID、分组状态查询分组列表(根据排列序号正序)。
     * @param req 用户请求。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态（可为NULL）。
     * @return 查询到结果返回分组列表，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_LIST, method=RequestMethod.GET)
    public @ResponseBody ResponseCode list(HttpServletRequest req, @RequestParam(name=PARENT_ID ,defaultValue="0")String parentId, @RequestParam(STORE_ID)String storeId, @RequestParam(name=STATUS, required=false)String status) {
    	try {
    		
    		//获取分组状态
    		GroupStatus groupStatus = GroupStatus.get(status);
    		
    		//判断分组状态是否为空
    		if(groupStatus == null){
    			return EntryError.EMPTY(STATUS);
    		}
    		
    		//执行查询
    		ResponseCode responseCode = groupService.findGroups(parentId, storeId, groupStatus);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
	 * 查询分组总数。
     * @param req 用户请求。
     * @param parentId 父分组ID。
     * @param storeId  店铺ID。
     * @param status   分组状态。
     * @return 查询到结果返回分组总数，未查询到结果返回0，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_COUNT, method=RequestMethod.GET)
    public @ResponseBody ResponseCode count(HttpServletRequest req, @RequestParam(name=PARENT_ID ,defaultValue="0")String parentId, @RequestParam(STORE_ID)String storeId, @RequestParam(name=STATUS, required=false, defaultValue= "DEFAULT")String status) {
    	try {
    		
    		//获取分组状态
    		GroupStatus groupStatus = GroupStatus.get(status);
    		
    		//判断分组状态是否为空
    		if(groupStatus == null){
    			return EntryError.EMPTY(STATUS);
    		}
    		
    		//执行查询
    		ResponseCode responseCode = groupService.getGroupsCount(parentId, storeId, groupStatus);
        	
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据分组ID查询分组信息。
     * @param req 用户请求。
     * @param id 分组ID。
     * @return 查询到结果返回分组信息，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_DETAIL, method=RequestMethod.GET)
    public @ResponseBody ResponseCode detail(HttpServletRequest req, @RequestParam(ID)String id) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = groupService.findGroup(id);
    		
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
	/**
     * 根据商品ID查询分组列表(分组状态为正常)。
     * @param req 用户请求。
     * @param goodsId 商品ID。
     * @return 查询到结果返回分组列表，未查询到结果返回空，失败返回相应错误码。
	 */
    @RequestMapping(value=REQ_GOODS_GROUPS, method=RequestMethod.GET)
    public @ResponseBody ResponseCode goodsGroups(HttpServletRequest req, @RequestParam(GOODS_ID)String goodsId) {
    	try {
    		
    		//执行查询
    		ResponseCode responseCode = groupService.findGoodsGroups(goodsId);
    		
            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
    
}
