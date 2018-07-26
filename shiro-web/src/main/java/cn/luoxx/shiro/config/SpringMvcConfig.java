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
package cn.luoxx.shiro.config;

import cn.luoxx.shiro.interceptor.UserSessionInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注册 拦截/过滤器
 * <br>创建日期：2017年1月22日
 *
 * @author luoxx
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.userSessionInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/", "/health");
    }

    @Bean
    public HandlerInterceptor userSessionInterceptor() {
        return new UserSessionInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**");
        super.addCorsMappings(registry);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加注册默认的转换器和格式化
        registry.addConverter(new Converter<String, Date>() {

            @Override
            public Date convert(String source) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(source);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return date;
            }
        });
        super.addFormatters(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //自定义静态资源路径映射
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //添加请求路径与controller的对应关系
        super.addViewControllers(registry);
    }


}
