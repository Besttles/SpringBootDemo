package com.xiaour.spring.boot.folkJoin.recursiveAction;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import com.sun.tools.extcheck.Main;

public class Task extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private List<Product> products;
	private int first;
	private int last;
	private double increment;

	public Task(List<Product> products, int first, int last, double increment) {
		super();
		this.products = products;
		this.first = first;
		this.last = last;
		this.increment = increment;
	}

	@Override
	protected void compute() {
		if(last - first<10) {
			updatePrices();
		}else {
			int middle = (last + first)/2;
			System.out.printf("task:pading task %s\n", getQueuedTaskCount());
			Task t1 = new Task(products,first,middle,increment);
			Task t2 = new Task(products,middle+1,last,increment);
			invokeAll(t1,t2);
		}
	}

	private void updatePrices() {
		for(int i = first; i<last;i++) {
			Product product = products.get(i);
			product.setPrice(product.getPrice()*(1+increment));
		}
	}
	
	public static void main(String[] args) {
		ProductListGenerator generator = new ProductListGenerator();
		List<Product> products = generator.generator(10000);
		Task task = new Task(products, 0, products.size(), 0.2);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.execute(task);
		System.out.println("====================");
		do {
			System.out.printf("mainThread: ThreadCount: %d \n", forkJoinPool.getActiveThreadCount());
			System.out.printf("main:stealCount: %d \n",forkJoinPool.getStealCount());
			System.out.printf("mian:parallesime:%d \n", forkJoinPool.getParallelism());
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (!task.isDone());
		
		forkJoinPool.shutdown();
		
		if(task.isCompletedNormally()) {
			System.out.println("the task is completlly normal");
		}
		for(int i = 0;i<products.size();i++) {
			Product product = products.get(i);
			if(product.getPrice() != 12) {
				//System.out.printf("product : %s %f \n",product.getName(),product.getPrice());
			}
		}
		System.out.println("the end of the task!");
	}
}
