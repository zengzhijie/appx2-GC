package com.dreawer.category.controller;

import com.dreawer.category.service.CategoryPropertyService;
import com.dreawer.responsecode.rcdt.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import static com.dreawer.category.constants.ControllerConstants.*;
import static com.dreawer.category.constants.DomainConstants.*;
import static com.dreawer.responsecode.rcdt.Error.APPSERVER;

/**
 * <CODE>REQ_CATEGORY_PROPERTY</CODE>
 * 类目属性信息控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_CATEGORY_PROPERTY)
public class CategoryPropertyController extends BaseController{

    @Autowired
    private CategoryPropertyService categoryPropertyService; //类目属性service


    /**
     * 通过类目ID和店铺ID查询属性名列表（排列序号正序）。
     * @param req 用户请求。
     * @param categoryId 类目ID。
     * @param storeId 店铺ID。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_PROPERTY_NAMES, method=RequestMethod.GET)
    public @ResponseBody
    ResponseCode propertyNames(HttpServletRequest req, @RequestParam(CATEGORY_ID) String categoryId, @RequestParam(STORE_ID) String storeId) {
        try {

            //执行查询
            ResponseCode responseCode = categoryPropertyService.findPropertyNames(categoryId,storeId);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 通过类目ID、店铺ID、模糊查询关键字查询属性名列表（排列序号正序）。
     * @param req 用户请求。
     * @param categoryId 类目ID。
     * @param storeId 店铺ID。
     * @param keyword 模糊查询关键字。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_SUGGEST_PROPERTY_NAMES, method=RequestMethod.GET)
    public @ResponseBody ResponseCode suggestPropertyNames(HttpServletRequest req, @RequestParam(CATEGORY_ID) String categoryId, @RequestParam(STORE_ID) String storeId, @RequestParam(KEYWORD) String keyword) {
        try {

            //执行查询
            ResponseCode responseCode = categoryPropertyService.suggestPropertyNames(categoryId,storeId,keyword);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 通过类目ID、店铺ID和属性名ID查询属性值列表（创建时间倒叙）。
     * @param req 用户请求。
     * @param categoryId 类目ID。
     * @param storeId 店铺ID。
     * @param propertyNameId 属性名ID。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_PROPERTY_VALUES, method=RequestMethod.GET)
    public @ResponseBody ResponseCode propertyValues(HttpServletRequest req, @RequestParam(CATEGORY_ID) String categoryId, @RequestParam(STORE_ID) String storeId, @RequestParam(PROPERTY_NAME_ID) String propertyNameId) {
        try {

            //执行查询
            ResponseCode responseCode = categoryPropertyService.findPropertyValues(categoryId,storeId,propertyNameId);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 通过类目ID、店铺ID、属性名ID和模糊查询关键字查询属性值列表（创建时间倒叙）。
     * @param req 用户请求。
     * @param categoryId 类目ID。
     * @param storeId 店铺ID。
     * @param propertyNameId 属性名ID。
     * @param keyword 模糊查询关键字。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_SUGGEST_PROPERTY_VALUES, method=RequestMethod.GET)
    public @ResponseBody ResponseCode suggestPropertyValues(HttpServletRequest req, @RequestParam(CATEGORY_ID) String categoryId, @RequestParam(STORE_ID) String storeId, @RequestParam(PROPERTY_NAME_ID) String propertyNameId, @RequestParam(KEYWORD) String keyword) {
        try {

            //执行查询
            ResponseCode responseCode = categoryPropertyService.suggestPropertyValues(categoryId,storeId,propertyNameId,keyword);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

}
