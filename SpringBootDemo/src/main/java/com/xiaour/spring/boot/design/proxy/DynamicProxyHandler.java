package com.xiaour.spring.boot.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler{
	
	private Interface iface;
	
	public DynamicProxyHandler( Interface iface) {
		this.iface = iface;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy----");
		if(args != null) {
			for (Object object : args) {
				System.out.println(object);
			}
		}
		return method.invoke(iface, args);
	}

}
