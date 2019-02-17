package com.xiaour.spring.boot.threadlocal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsideThreadLocal {

	//对于一个单例的ThreadLocal的对象，其中get,set,remove只能调用自己线程内的变量
	@Bean
	public ThreadLocal<Object> getThreadLocal(){
		return new ThreadLocal<Object>() {
			@Override
			protected Object initialValue() {
				//当remove之后调用其get方法，就会返回此值
				System.out.println("initial");
				return "小宇";
			}
		};
	}
}
