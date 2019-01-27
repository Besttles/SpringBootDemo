package com.xiaour.spring.boot.startUpService;

import java.util.concurrent.CountDownLatch;

public abstract class HealthyCheck implements Runnable{

	private CountDownLatch countDownLatch;
	private boolean _serviceUP;
	private String serviceName;
	
	
	
	public HealthyCheck(CountDownLatch countDownLatch, String serviceName) {
		super();
		this.countDownLatch = countDownLatch;
		this.serviceName = serviceName;
	}

	@Override
	public void run() {
		try {
			if(vertifyService()) {
				_serviceUP = true;
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			_serviceUP = false;
		} finally {
            if(countDownLatch != null) {
            	countDownLatch.countDown();
            }
		}
	}
	
	public boolean isStartUP() {
		return _serviceUP;
	}
	
	abstract boolean vertifyService();

}
