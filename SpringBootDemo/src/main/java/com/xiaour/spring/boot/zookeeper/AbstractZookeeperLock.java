package com.xiaour.spring.boot.zookeeper;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public abstract class AbstractZookeeperLock implements Lock{

	//桥接模式  打印日志
//	private static Logger logger = new LoggerFactory.getLogger(AbstractZookeeperLock.class);
	//服务器地址
	private static String host = "127.0.0.1";
	//服务器的端口
	private static String port = "2181";
	//反问的节点地址
	private static String address = "/zkclient";
	/**
	 * lock获取锁的行为
	 * 高并发的情况下获取锁
	 * 获取锁成功
	 * 十万加的客户端来抢占锁
	 * */
	@Override
	public void lock() {
		// TODO Auto-generated method stub
		if(tryLock()) {
			Logger.getLogger(AbstractZookeeperLock.class).info(Thread.currentThread().getName()+"获取锁成功");
		    lock();
		}else {
			//等待唤醒
			waitforLock();
			//递归调用
			lock();
		}
	}


	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}
	//包装一个尝试获取锁的情况
	//等待锁的释放
	protected abstract boolean tryLock();
    //等待锁
	protected abstract void waitforLock();
}
