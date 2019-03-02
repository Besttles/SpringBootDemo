package com.xiaour.spring.boot.folkJoin.cancleTask;

import java.util.List;
import java.util.concurrent.ForkJoinTask;

public class TaskManager {

	private List<ForkJoinTask<Integer>> tasks;

	public TaskManager(List<ForkJoinTask<Integer>> tasks) {
		super();
		this.tasks = tasks;
	}
	
	public TaskManager() {
		// TODO Auto-generated constructor stub
	}

	public void addTask(ForkJoinTask<Integer> task) {
		tasks.add(task);
	}
	
	public void cancleTasks(ForkJoinTask<Integer> cancleTask) {
		for (ForkJoinTask<Integer> forkJoinTask : tasks) {
			if(forkJoinTask != cancleTask) {
				forkJoinTask.cancel(true);
				((SearchNumberTask)forkJoinTask).writeCancelMessge();
			}
		}
	}
}
