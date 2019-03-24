package com.xiaour.spring.boot.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodHandler implements InvocationHandler{

	private Object proxid;
	
	public MethodHandler(Object proxid) {
		this.proxid = proxid;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().equals("interesting")) {
			System.out.println("invoke the methos");
		}
		return method.invoke(proxid, args);
	}

}
