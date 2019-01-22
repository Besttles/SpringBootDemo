package com.xiaour.spring.boot.disruptor;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
 
 
public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>{
 
	@Override
	public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
		this.onEvent(event);
		System.out.println("=======================");
		event.prepare();
		//单线程执行批量的处理
		System.out.println(Thread.currentThread().getName().toString());
		System.out.println(event.getPrice());
		System.out.println("=======================");
	}
 
	public void onEvent(TradeTransaction event) throws Exception {
		event.setId(UUID.randomUUID().toString());
		System.out.println(event.getId());
		
	}
 
}