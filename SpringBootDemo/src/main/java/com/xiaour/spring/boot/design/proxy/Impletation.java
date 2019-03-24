package com.xiaour.spring.boot.design.proxy;

public class Impletation implements SomeMethod{

	@Override
	public void boring1() {
System.out.println("boring1");		
	}

	@Override
	public void boring2() {
		System.out.println("boring2");				
	}

	@Override
	public void interesting(String args) {
		System.out.println("interesting" + args);		
	}

	@Override
	public void boring3() {
		System.out.println("boring3");				
	}
}
