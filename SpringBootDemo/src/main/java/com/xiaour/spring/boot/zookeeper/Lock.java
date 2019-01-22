package com.xiaour.spring.boot.zookeeper;

public interface Lock {

	public void lock();
	
	public void unlock();
}
