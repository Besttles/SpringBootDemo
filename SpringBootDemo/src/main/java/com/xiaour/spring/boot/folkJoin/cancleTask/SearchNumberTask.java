package com.xiaour.spring.boot.folkJoin.cancleTask;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class SearchNumberTask extends RecursiveTask<Integer>{

	private int numbers[];
	private int start , end;
	private int number;
	private TaskManager manager;
	private final static int NOT_FOUND = -1;
	
	
	
	public SearchNumberTask(int[] numbers, int start, int end, int number, TaskManager manager) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
		this.number = number;
		this.manager = manager;
	}



	@Override
	protected Integer compute() {
		System.out.println("Task:"+start+"to"+end);
		int ret = 0;
		if(end-start>10) {
			ret = latchTask();
		}else {
			ret = lookForNum();
		}
		return ret;
	}
	private int lookForNum() {
		for(int i=start; i<end;i++) {
			if(numbers[i] == number) {
				manager.cancleTasks(this);
			}
			return i;
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NOT_FOUND;
	}
	private int latchTask() {
		int middle = (start+end)/2;
		SearchNumberTask task1 = new SearchNumberTask(numbers, start, middle, number, manager);
		SearchNumberTask task2 = new SearchNumberTask(numbers, middle, end, number, manager);
		//添加任务到任务列表中
		manager.addTask(task1);
		manager.addTask(task2);
		//执行任务
		task1.fork();
		task2.fork();
		int returnValue;
		returnValue = task1.join();
		if(returnValue == -1) {
			return returnValue;
		}
		
		returnValue = task2.join();
		return returnValue;
	}
	public void writeCancelMessge() {
		System.out.println("Task cancel from :=="+start+"==TO=="+end);
	}
}
