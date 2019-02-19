package com.xiaour.spring.boot.folkJoin.Exception;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
	public static void main(String[] args) {
		int array[] = new int[100];

		Task t = new Task(array, 0, 100);

		ForkJoinPool pool = new ForkJoinPool();

		pool.execute(t);

		pool.shutdown();

		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (t.isCompletedNormally()) {
			System.out.println(t.getException());
		}
		System.out.println(t.join());
	}
}
