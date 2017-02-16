/* @(#)
 *
 * Project:demo-web
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
 *       Copyright 2017 UTOUU All rights reserved.
 *
 *       This software is the confidential and proprietary information of
 *       UTOUU ("Confidential Information").
 *       You shall not disclose such Confidential Information and shall use it
 *       only in accordance with the terms of the license agreement you entered
 *       into with UTOUU.
 *
 * Warning:
 * =============================================================================
 *
 */
package cn.luoxx.shiro.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.luoxx.shiro.interceptor.UserSessionInterceptor;

/**
 * 注册 拦截/过滤器
 * <br>创建日期：2017年1月22日
 * @author luoxx
 * @since 1.0
 * @version 1.0
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userSessionInterceptor()).addPathPatterns("/**");
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
		// TODO Auto-generated method stub
		super.addResourceHandlers(registry);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//添加请求路径与controller的对应关系
		// TODO Auto-generated method stub
		super.addViewControllers(registry);
	}
	
	

}
