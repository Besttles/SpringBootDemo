package com.xiaour.spring.boot.folkJoin.Exception;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class Task extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 1L;
	private int array[];
	private int start,end;
	public Task(int[] array, int start, int end) {
		super();
		this.array = array;
		this.start = start;
		this.end = end;
	}
	@Override
	protected Integer compute() {
		
		System.out.println("the task is start");
		
		if(end-start < 10) {
			if((3>start) && (3<end)) {
				throw new RuntimeException("the task throw "+start+"to"+end);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			int mid = (end+start) /2;
			Task t1 = new Task(array, start, mid);
			Task t2 = new Task(array, mid, end);
			invokeAll(t1,t2);
		}
		System.out.println("the task to the end");
		return 0;
	}
}
