package com.xiaour.spring.boot.startUpService;

import java.util.concurrent.CountDownLatch;

public class NetWorkService extends HealthyCheck{

	public NetWorkService(CountDownLatch countDownLatch) {
		super(countDownLatch, "network Service");
	}

	@Override
	boolean vertifyService() {
		if(1>0) {
			try {
				Thread.currentThread().sleep(3000);
				return true;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
