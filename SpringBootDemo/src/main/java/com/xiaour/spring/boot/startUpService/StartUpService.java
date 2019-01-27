package com.xiaour.spring.boot.startUpService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javassist.bytecode.analysis.Executor;



public class StartUpService {

	private final static StartUpService service = new StartUpService();
	private static CountDownLatch latch;
	private boolean isStartUP;
	
	private static List<HealthyCheck> check;
	public boolean startUP() {
		return isStartUP;
	}
	
	public static boolean chechService() throws InterruptedException {
		
		latch = new CountDownLatch(2);
		check = new ArrayList<>();
		check.add(new NetWorkService(latch));
		check.add(new DatabaseCheck(latch));
		
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(check.size());
		
		for (final HealthyCheck healthyCheck : check) {
			newFixedThreadPool.execute(healthyCheck);
		}
		latch.await();
		for (HealthyCheck healthyCheck : check) {
			if(healthyCheck.isStartUP()) {
				continue;
			}else {
				return false;
			}
		}
		return true;
	}
	
}
