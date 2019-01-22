package com.xiaour.spring.boot.disruptor;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction>{
 
	@Override
	public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("发送消息");
	}
 
}