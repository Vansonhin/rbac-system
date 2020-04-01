package com.baizhan.basic.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
	
	
	

	@Override //<mvc:resource >
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/lib/**").addResourceLocations("classpath:/static/lib/");
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("manager/index");
		registry.addViewController("/index").setViewName("manager/index");
		registry.addViewController("/login").setViewName("login");
		
	}

}
