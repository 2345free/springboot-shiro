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

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

/**
 * <br>创建日期：2017年1月22日
 *
 * @author luoxx
 * @version 1.0
 * @since 1.0
 */
public class MyAppStartedListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        SpringApplication app = event.getSpringApplication();
        app.setBannerMode(Banner.Mode.CONSOLE);
    }

}
