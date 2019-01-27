package com.xiaour.spring.boot.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import com.xiaour.spring.boot.disruptor.TradeTransaction;
import com.xiaour.spring.boot.disruptor.TradeTransactionInDBHandler;
import com.xiaour.spring.boot.disruptor.TradeTransactionJMSNotifyHandler;
import com.xiaour.spring.boot.disruptor.TradeTransactionPublisher;
import com.xiaour.spring.boot.disruptor.TradeTransactionVasConsumer;

@Configuration
@EnableScheduling // 启用定时任务
public class Task {
	
    @Scheduled(cron="0 0/1 * * * ?")
	public void run() throws InterruptedException{
		System.out.println("Scheduled Running...");
		int bufferSize = 1024;
		long beginTime = System.currentTimeMillis();
		ExecutorService executor=Executors.newFixedThreadPool(4);
		//定义生产者
		//disraptor的执行是在单线程实现的队列形式的
		Disruptor<TradeTransaction> disruptor=new Disruptor<TradeTransaction>(new EventFactory<TradeTransaction>() {
			@Override
			public TradeTransaction newInstance() {
				return new TradeTransaction();
			}
		}, bufferSize, executor, ProducerType.MULTI, new BusySpinWaitStrategy());
	    //消费者的执行
		EventHandlerGroup<TradeTransaction> handlerGroup=disruptor.handleEventsWith(new TradeTransactionVasConsumer(),new TradeTransactionInDBHandler());
	    
		TradeTransactionJMSNotifyHandler jmsConsumer=new TradeTransactionJMSNotifyHandler();
		//声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
		handlerGroup.then(jmsConsumer);
		
		disruptor.start();
		//CountDownLatch
		CountDownLatch latch = new CountDownLatch(1);
		executor.submit(new TradeTransactionPublisher(latch, disruptor));
		executor.submit(new TradeTransactionPublisher(latch, disruptor));

		latch.await();//等待生产者完事.
		disruptor.shutdown();
		executor.shutdown();
		
		System.out.println("总耗时:"+(System.currentTimeMillis()-beginTime));
	}
}
