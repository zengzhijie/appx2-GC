package com.dreawer.category.service;

import com.dreawer.category.persistence.SystemPropertyNameDao;
import com.dreawer.category.persistence.SystemPropertyValueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <CODE>SystemPropertyService</CODE>
 * 系统属性信息service
 */

@Service
public class SystemPropertyService extends BaseService{

    @Autowired
    private SystemPropertyNameDao propertyNameDao;

    @Autowired
    private SystemPropertyValueDao propertyValueDao;



}
