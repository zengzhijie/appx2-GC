package com.dreawer.category.controller;

import com.dreawer.category.service.CategoryService;
import com.dreawer.responsecode.rcdt.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import static com.dreawer.category.constants.ControllerConstants.*;
import static com.dreawer.category.constants.DomainConstants.KEYWORD;
import static com.dreawer.category.constants.DomainConstants.PARENT_ID;
import static com.dreawer.responsecode.rcdt.Error.APPSERVER;

/**
 * <CODE>CategoryController</CODE>
 * 类目信息控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_CATEGORY)
public class CategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService; //类目service

    /**
     * 查询类目列表（排列序号正序）。
     * @param req 用户请求。
     * @param parentId 父类目ID。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_LIST, method=RequestMethod.GET)
    public @ResponseBody
    ResponseCode list(HttpServletRequest req, @RequestParam(value = PARENT_ID,required = false,defaultValue = "0") String parentId) {
        try {

            //执行查询
            ResponseCode responseCode = categoryService.findChildCategories(parentId);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 模糊查询类目列表（排列序号正序）。
     * @param req 用户请求。
     * @param parentId 父类目ID。
     * @param keyword 模糊查询关键字。
     * @return 查询结果。
     */
    @RequestMapping(value=REQ_SUGGEST, method=RequestMethod.GET)
    public @ResponseBody ResponseCode suggest(HttpServletRequest req, @RequestParam(value=PARENT_ID,required = false,defaultValue = "0") String parentId, @RequestParam(KEYWORD) String keyword) {
        try {

            //执行查询
            ResponseCode responseCode = categoryService.suggestChildCategories(parentId,keyword);

            //返回查询结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }
}
