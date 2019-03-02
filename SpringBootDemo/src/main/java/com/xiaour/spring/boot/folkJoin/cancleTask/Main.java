package com.xiaour.spring.boot.folkJoin.cancleTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		ArrayGeneration generator = new ArrayGeneration();
		int array[] = generator.generationArray(1000);
		List<ForkJoinTask<Integer>> tasks = new ArrayList<ForkJoinTask<Integer>>();
		TaskManager task = new TaskManager(tasks);
		ForkJoinPool pool = new ForkJoinPool();
		SearchNumberTask search = new SearchNumberTask(array, 0, 1000, 5, task);
		pool.execute(search);
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("the task is finashed");
	}
}
