package com.xiaour.spring.boot.design.proxy;

public class SimpleProxyDemp {

	public static void consumer(Interface iface) {
		
		iface.doSomething();
		iface.someThingElse("bonabo");
		
	}
	
	public static void main(String[] args) {
		consumer(new RealObject());
		consumer(new SimpleProxy(new RealObject()));
	}
}
