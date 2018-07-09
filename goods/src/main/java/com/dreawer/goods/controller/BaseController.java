package com.dreawer.category.controller;

import java.sql.Timestamp;

/**
 * <code>BaseController</code> 它是本系统中所有控制器的基类，提供控制器通用方法的实现。
 * @author kael
 */
public class BaseController{

    // --------------------------------------------------------------------------------
    // 其他
    // --------------------------------------------------------------------------------
    
    /**
     * 获取当前系统时间。
     * @return 当前系统时间。
     * @author kael
     */
    protected Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }

}
