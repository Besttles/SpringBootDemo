package com.xiaour.spring.boot.disruptor;

import java.util.Random;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {
 
	@Override
	public void onEvent(TradeTransaction event, long sequence,
			boolean endOfBatch) throws Exception {
		System.out.println("doSomeThing");
		event.setPrice(Math.random());
		if(event.getPrice()>100) {
			Thread.currentThread().sleep(1000);
			event.setPrice(100);
		}
	}
	
}