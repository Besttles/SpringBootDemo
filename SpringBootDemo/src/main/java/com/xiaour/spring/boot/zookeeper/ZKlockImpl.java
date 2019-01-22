package com.xiaour.spring.boot.zookeeper;

public class ZKlockImpl extends AbstractZookeeperLock{

	@Override
	protected boolean tryLock() {
		//尝试获取锁，创建zNode节点
		
		
		return false;
	}

	@Override
	protected void waitforLock() {
		// TODO Auto-generated method stub
		
	}

}
