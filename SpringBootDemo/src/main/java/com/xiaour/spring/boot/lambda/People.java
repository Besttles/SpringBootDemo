package com.xiaour.spring.boot.lambda;

public class People {

	String name;
	
	private double tall;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTall() {
		return tall;
	}

	public void setTall(double tall) {
		this.tall = tall;
	}

	public People(String name, double tall) {
		super();
		this.name = name;
		this.tall = tall;
	}
	public People() {
		
	}
	
}
