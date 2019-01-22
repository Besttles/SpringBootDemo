package com.xiaour.spring.boot.controllerConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
//@EnableWebMvc
public class MyMvcConfig extends WebMvcConfigurerAdapter{

	static {
		System.out.println("+++静态代码块+++");
	}
	//手动配置Spring MVC 拓展
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//super.addViewControllers(registry);
		registry.addViewController("/welcome").setViewName("welcome");
	}
	
	//所有的WebMvcConfigurerAdapter都会一起起作用
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter config = new WebMvcConfigurerAdapter() {
			
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
//				super.addViewControllers(registry);
				registry.addViewController("/get").setViewName("welcome");
				registry.addViewController("/take").setViewName("welcome");
			}
		};
		return config;
	}
	
	
}
