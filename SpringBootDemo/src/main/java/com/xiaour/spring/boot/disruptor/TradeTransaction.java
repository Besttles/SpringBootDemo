package com.xiaour.spring.boot.disruptor;

public class TradeTransaction {
	private String id; //交易ID
	private double price;//交易金额
	
	public TradeTransaction(){}
	
	public TradeTransaction(String id,double price){
		super();
		this.id = id;
		this.price = price;
	}
	
	public void prepare() {
		System.out.println("数据准备");
	}
 
	public String getId() {
		return id;
	}
 
	public void setId(String id) {
		this.id = id;
	}
 
	public double getPrice() {
		return price;
	}
 
	public void setPrice(double price) {
		this.price = price;
	}
	
}