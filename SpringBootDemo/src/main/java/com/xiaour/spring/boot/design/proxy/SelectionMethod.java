package com.xiaour.spring.boot.design.proxy;

import java.lang.reflect.Proxy;

public class SelectionMethod {
	public static void main(String[] args) {

		SomeMethod proxy = (SomeMethod) Proxy.newProxyInstance(SomeMethod.class.getClassLoader(),
				new Class[] { SomeMethod.class }, new MethodHandler(new Impletation()));
		proxy.boring1();
		proxy.boring2();
		proxy.interesting("yeah");
		proxy.boring3();
		
	}
}
