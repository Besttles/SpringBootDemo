package com.xiaour.spring.boot.startUpService;

import java.util.concurrent.CountDownLatch;

public class DatabaseCheck extends HealthyCheck{


	public DatabaseCheck(CountDownLatch countDownLatch) {
		super(countDownLatch, "Database sevice");
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean vertifyService() {
		if(1>0) {
			try {
				Thread.currentThread().sleep(1000);
				return true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
}
