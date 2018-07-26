/* @(#)
 *
 * Project:shiro-web
 *
 * Modify Information:
 * =============================================================================
 *   Author         Date           Description
 *   ------------ ---------- ---------------------------------------------------
 *   luoxx      2017年1月22日        first release
 *
 *
 * Copyright Notice:
 * =============================================================================
 *       Copyright 2017 luoxiaoxiao All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       luoxiaoxiao ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with luoxiaoxiao.
 *
 * Warning:
 * =============================================================================
 *
 */
package cn.luoxx.shiro.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * <br>创建日期：2017年1月22日
 * <br><b>Copyright 2017 luoxiaoxiao All Rights Reserved</b>
 *
 * @author luoxx
 * @version 1.0
 * @since 1.0
 */
@WebListener//自动注册监听器
public class SessionCounter implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent arg0) {
        System.out.println("创建session>>>");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0) {
        System.out.println("销毁session>>>");
    }

}
