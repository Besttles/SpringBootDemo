package com.xiaour.spring.boot.disruptor;

import java.util.concurrent.CountDownLatch;

import com.lmax.disruptor.dsl.Disruptor;
 
public class TradeTransactionPublisher implements Runnable{
	Disruptor<TradeTransaction> disruptor;
	private CountDownLatch latch;
	private static int LOOP=1000000;//模拟一千万次交易的发生
	
	public TradeTransactionPublisher(CountDownLatch latch,Disruptor<TradeTransaction> disruptor){
			this.disruptor = disruptor;
			this.latch = latch;
	}
	@Override
	public void run() {
		TradeTransactionEventTranslator tradeTransloator=new TradeTransactionEventTranslator();
		for(int i=0;i<LOOP;i++){
//			System.out.println(Thread.currentThread().getName().toString());
//			ThreadGroup group = Thread.currentThread().getThreadGroup();  
//			ThreadGroup topGroup = group;  
//			// 遍历线程组树，获取根线程组  
//			while (group != null) {  
//			    topGroup = group;  
//			    group = group.getParent();  
//			}  
//			// 激活的线程数加倍  
//			int estimatedSize = topGroup.activeCount() * 2;  
//			Thread[] slackList = new Thread[estimatedSize];  
//			// 获取根线程组的所有线程  
//			int actualSize = topGroup.enumerate(slackList);  
//			// copy into a list that is the exact size  
//			Thread[] list = new Thread[actualSize];  
//			System.arraycopy(slackList, 0, list, 0, actualSize);  
//			System.out.println("Thread list size == " + list.length);  
//			for (Thread thread : list) {  
//			    System.out.println(thread.getName());  
//			}
			
			disruptor.publishEvent(tradeTransloator);
		}
		latch.countDown();
	}
}