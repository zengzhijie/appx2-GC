package com.dreawer.category.service;

import com.dreawer.category.domain.Category;
import com.dreawer.category.persistence.CategoryDao;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.List;

/**
 * <CODE>CategoryService</CODE>
 * 类目信息service
 */

@Service
public class CategoryService extends BaseService{

    @Autowired
    private CategoryDao categoryDao;

    /**
     * 通过父类目ID查询子类目列表
     * @param parentId 父类目ID
     * @return 子类目列表
     */
    public ResponseCode findChildCategories(String parentId){

        //父类目ID为空则默认为顶级类目ID
        if(StringUtils.isEmpty(parentId)){
            parentId = "0";
        }

        //执行查询
        List<Category> categories = categoryDao.findCategories(parentId, null);

        //返回处理结果
        return Success.SUCCESS(categories);
    }

    /**
     * 通过父类目ID和模糊查询关键字查询子类目列表
     * @param parentId 父类目ID
     * @param keyword 模糊查询关键字
     * @return 子类目列表
     */
    public ResponseCode suggestChildCategories(String parentId, String keyword){

        //父类目ID为空则默认为顶级类目ID
        if(StringUtils.isEmpty(parentId)){
            parentId = "0";
        }

        //执行查询
        List<Category> categories = categoryDao.findCategories(parentId, keyword);

        //返回处理结果
        return Success.SUCCESS(categories);
    }
}
