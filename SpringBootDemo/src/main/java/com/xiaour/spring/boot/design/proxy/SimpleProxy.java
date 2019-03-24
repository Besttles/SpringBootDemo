package com.xiaour.spring.boot.design.proxy;

public class SimpleProxy implements Interface{

	
	private Interface proxid;
	
	public SimpleProxy(Interface proxid) {
		this.proxid = proxid;
	}

	@Override
	public void doSomething() {
		System.out.println("simpleProxy dosomething");
	}

	@Override
	public void someThingElse(String args) {
		System.out.println("SimpleProxy Dosomething else" + args);
	}
}
