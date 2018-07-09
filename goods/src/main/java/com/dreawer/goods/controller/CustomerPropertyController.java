package com.dreawer.category.controller;

import com.dreawer.category.domain.CustomerPropertyName;
import com.dreawer.category.domain.CustomerPropertyValue;
import com.dreawer.category.form.*;
import com.dreawer.category.lang.PropertyNameType;
import com.dreawer.category.service.CustomerPropertyService;
import com.dreawer.responsecode.rcdt.EntryError;
import com.dreawer.responsecode.rcdt.ResponseCode;
import com.dreawer.responsecode.rcdt.ResponseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import static com.dreawer.category.constants.ControllerConstants.*;
import static com.dreawer.category.constants.DomainConstants.*;
import static com.dreawer.responsecode.rcdt.Error.APPSERVER;
import static com.dreawer.responsecode.rcdt.Error.ENTRY;

/**
 * <CODE>REQ_CUSTOMER_PROPERTY</CODE>
 * 客户属性信息控制器
 * @author kael
 */
@Controller
@RequestMapping(value = REQ_CUSTOMER_PROPERTY)
public class CustomerPropertyController extends BaseController{

    @Autowired
    private CustomerPropertyService customerPropertyService; //客户属性service

    /**
     * 添加客户属性（属性名和属性值）。
     * @param req 用户请求。
     * @param form 添加客户属性表单。
     * @return 添加结果。
     */
    @RequestMapping(value=REQ_ADD, method=RequestMethod.POST)
    public @ResponseBody ResponseCode add(HttpServletRequest req, @RequestBody @Valid AddCustomerPropertyForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {

            //校验并封装请求参数
            CustomerPropertyName propertyName = new CustomerPropertyName();//客户属性名

            //封装属性名数据
            propertyName.setCreaterId(form.getUserId());
            propertyName.setCreateTime(getNow());
            propertyName.setCategoryId(form.getCategoryId());
            propertyName.setStoreId(form.getStoreId());
            propertyName.setSquence(form.getSquence());
            propertyName.setName(form.getName());
            propertyName.setRemark(form.getRemark());
            propertyName.setIsSearch(form.getIsSearch());
            propertyName.setIsSelect(form.getIsSelect());
            propertyName.setIsCheckbox(form.getIsCheckbox());
            propertyName.setIsVisualEditor(form.getIsVisualEditor());
            propertyName.setIsColor(form.getIsColor());
            propertyName.setIsInput(form.getIsInput());
            propertyName.setIsRadio(form.getIsRadio());
            propertyName.setIsBasic(form.getIsBasic());
            propertyName.setIsRequired(form.getIsRequired());
            propertyName.setIsSales(form.getIsSales());
            propertyName.setIsKey(form.getIsKey());

            //判断属性值列表是否为空
            List<AddCustomerPropertyValueForm>  propertyValueForms = form.getAddPropertyValues(); // 获取属性值列表

            if(propertyValueForms != null && propertyValueForms.size()>0){
                List<CustomerPropertyValue> propertyValues = new ArrayList<>();//客户属性值列表

                //校验并封装属性值数据
                for (AddCustomerPropertyValueForm propertyValueForm : propertyValueForms) {

                    String propertyValueName = propertyValueForm.getName(); // 获取属性值名称

                    //判断属性值名称是否为空
                    if(propertyValueName == null){
                        return EntryError.EMPTY(PROPERTY_VALUE_NAME);
                    }

                    //判断属性值名称长度是否合规
                    if(propertyValueName.length()<=0 || propertyValueName.length()>40){
                        return EntryError.OVERRANGE(PROPERTY_VALUE_NAME);
                    }

                    String remark = propertyValueForm.getRemark(); // 获取备注
                    //判断备注长度是否合规
                    if(remark != null && (remark.length()<=0 || remark.length()>2000)){
                        return EntryError.OVERRANGE(PROPERTY_VALUE_REMARK);
                    }

                    //封装属性值数据
                    CustomerPropertyValue propertyValue = new CustomerPropertyValue();//客户属性值
                    propertyValue.setName(propertyValueName);
                    propertyValue.setPropertyNameType(PropertyNameType.CUSTOMER);
                    propertyValue.setRemark(remark);
                    propertyValue.setCategoryId(propertyName.getCategoryId());
                    propertyValue.setStoreId(propertyName.getStoreId());
                    propertyValue.setCreaterId(propertyName.getCreaterId());
                    propertyValue.setCreateTime(propertyName.getCreateTime());

                    propertyValues.add(propertyValue); // 添加到集合中
                }

                propertyName.setPropertyValues(propertyValues); // 将属性值列表封装到属性名信息中
            }

            //执行添加
            ResponseCode responseCode = customerPropertyService.add(propertyName);

            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 编辑客户属性（属性名和属性值）。
     * @param req 用户请求。
     * @param form 编辑客户属性表单。
     * @return 编辑结果。
     */
    @RequestMapping(value=REQ_EDIT, method=RequestMethod.POST)
    public @ResponseBody ResponseCode edit(HttpServletRequest req, @RequestBody @Valid EditCustomerPropertyForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {

            //校验并封装请求参数
            CustomerPropertyName propertyName = new CustomerPropertyName();//客户属性名

            //封装属性名数据
            propertyName.setId(form.getId());
            propertyName.setUpdaterId(form.getUserId());
            propertyName.setUpdateTime(getNow());
            propertyName.setCategoryId(form.getCategoryId());
            propertyName.setStoreId(form.getStoreId());
            propertyName.setSquence(form.getSquence());
            propertyName.setName(form.getName());
            propertyName.setRemark(form.getRemark());
            propertyName.setIsSearch(form.getIsSearch());
            propertyName.setIsSelect(form.getIsSelect());
            propertyName.setIsCheckbox(form.getIsCheckbox());
            propertyName.setIsVisualEditor(form.getIsVisualEditor());
            propertyName.setIsColor(form.getIsColor());
            propertyName.setIsInput(form.getIsInput());
            propertyName.setIsRadio(form.getIsRadio());
            propertyName.setIsBasic(form.getIsBasic());
            propertyName.setIsRequired(form.getIsRequired());
            propertyName.setIsSales(form.getIsSales());
            propertyName.setIsKey(form.getIsKey());

            //校验属性值列表是否为空
            List<EditCustomerPropertyValueForm>  propertyValueForms = form.getEditPropertyValues(); // 获取属性值列表
            if(propertyValueForms != null && propertyValueForms.size()>0){

                List<CustomerPropertyValue> propertyValues = new ArrayList<>();//客户属性值列表

                //校验并封装属性值数据
                for (EditCustomerPropertyValueForm propertyValueForm : propertyValueForms) {

                    String propertyValueName = propertyValueForm.getName(); // 获取属性值名称

                    //判断属性值名称是否为空
                    if(propertyValueName == null){
                        return EntryError.EMPTY(PROPERTY_VALUE_NAME);
                    }

                    //判断属性值名称长度是否合规
                    if(propertyValueName.length()<=0 || propertyValueName.length()>40){
                        return EntryError.OVERRANGE(PROPERTY_VALUE_NAME);
                    }

                    String remark = propertyValueForm.getRemark(); // 获取备注
                    //判断备注长度是否合规
                    if(remark != null && (remark.length()<=0 || remark.length()>2000)){
                        return EntryError.OVERRANGE(PROPERTY_VALUE_REMARK);
                    }

                    //封装属性值数据
                    CustomerPropertyValue propertyValue = new CustomerPropertyValue();//客户属性值

                    //判断ID是否为空（为空则为新增属性值）
                    if(propertyValueForm.getId() != null){
                        propertyValue.setId(propertyValueForm.getId());
                    }
                    propertyValue.setPropertyNameId(propertyName.getId());
                    propertyValue.setName(propertyValueName);
                    propertyValue.setPropertyNameType(PropertyNameType.CUSTOMER);
                    propertyValue.setRemark(remark);
                    propertyValue.setCategoryId(propertyName.getCategoryId());
                    propertyValue.setStoreId(propertyName.getStoreId());
                    propertyValue.setUpdaterId(propertyName.getUpdaterId());
                    propertyValue.setUpdateTime(propertyName.getUpdateTime());

                    propertyValues.add(propertyValue); // 添加到集合中
                }

                propertyName.setPropertyValues(propertyValues); // 将属性值列表封装到属性名信息中
            }

            //执行更新
            ResponseCode responseCode = customerPropertyService.edit(propertyName);

            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 删除客户属性（属性名和属性值）。
     * @param req 用户请求。
     * @param form 编辑客户属性表单。
     * @return 编辑结果。
     */
    @RequestMapping(value=REQ_DELETE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode delete(HttpServletRequest req, @RequestBody @Valid DeleteCustomerPropertyForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {
            //执行删除
            ResponseCode responseCode = customerPropertyService.delete(form.getPropertyNameId());

            //返回删除结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 添加客户属性值。
     * @param req 用户请求。
     * @param form 添加客户属性表单。
     * @return 添加结果。
     */
    @RequestMapping(value=REQ_ADD_PROPERTY_VALUE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode addPropertyValue(HttpServletRequest req, @RequestBody @Valid AddCustomerPropertyValueForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {

            //封装请求参数
            CustomerPropertyValue propertyValue = new CustomerPropertyValue();//客户属性值

            propertyValue.setPropertyNameId(form.getPropertyNameId());
            propertyValue.setName(form.getName());
            propertyValue.setPropertyNameType(form.getPropertyNameType());
            propertyValue.setRemark(form.getRemark());
            propertyValue.setCategoryId(form.getCategoryId());
            propertyValue.setStoreId(form.getStoreId());
            propertyValue.setCreaterId(form.getUserId());
            propertyValue.setCreateTime(getNow());

            //执行添加
            ResponseCode responseCode = customerPropertyService.addPropertyValue(propertyValue);

            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 编辑客户属性值。
     * @param req 用户请求。
     * @param form 编辑客户属性值表单。
     * @return 编辑结果。
     */
    @RequestMapping(value=REQ_EDIT_PROPERTY_VALUE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode editPropertyValue(HttpServletRequest req, @RequestBody @Valid EditCustomerPropertyValueForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {

            //封装请求参数
            CustomerPropertyValue propertyValue = new CustomerPropertyValue();//客户属性值

            propertyValue.setId(form.getId());
            propertyValue.setPropertyNameId(form.getPropertyNameId());
            propertyValue.setName(form.getName());
            propertyValue.setPropertyNameType(form.getPropertyNameType());
            propertyValue.setRemark(form.getRemark());
            propertyValue.setCategoryId(form.getCategoryId());
            propertyValue.setStoreId(form.getStoreId());
            propertyValue.setUpdaterId(form.getUserId());
            propertyValue.setUpdateTime(getNow());

            //执行更新
            ResponseCode responseCode = customerPropertyService.editPropertyValue(propertyValue);

            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

    /**
     * 删除客户属性值。
     * @param req 用户请求。
     * @param form 删除客户属性值表单。
     * @return 编辑结果。
     */
    @RequestMapping(value=REQ_DELETE_PROPERTY_VALUE, method=RequestMethod.POST)
    public @ResponseBody ResponseCode deletePropertyValue(HttpServletRequest req, @RequestBody @Valid DeleteCustomerPropertyValueForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseCodeRepository.fetch(result.getFieldError().getDefaultMessage(), result.getFieldError().getField(), ENTRY);
        }
        try {

            //执行删除
            ResponseCode responseCode = customerPropertyService.deletePropertyValue(form.getPropertyValueId());

            //返回添加结果
            return responseCode;

        } catch ( Exception e) {
            e.printStackTrace();
            return APPSERVER;
        }
    }

}
