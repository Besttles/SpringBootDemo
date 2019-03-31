package com.xiaour.spring.boot.controller;

import com.xiaour.spring.boot.serverImpl.ZkPandaLock;

public class ZookeeperRunner implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
        	ZkPandaLock zkPandaLock = new ZkPandaLock();
        	zkPandaLock.connectZooKeeper("127.0.0.1:2181", "jason");
        	zkPandaLock.lock();
        	System.out.println(Thread.currentThread().getName()+"在做事，做完就释放锁");
        	Thread.sleep(1000);
        	System.out.println(Thread.currentThread().getName()+"我做完事情了");
        	zkPandaLock.releaseLock();
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
