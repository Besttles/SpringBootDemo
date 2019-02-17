package com.xiaour.spring.boot.folkJoin.recursiveTask;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;

public class LineTask extends RecursiveTask<Integer>{

	private static final long serialVersionUID = 1L;

	private String line[];
	private int start,end;
	private String word;
	
	
	public LineTask(String[] line, int start, int end, String word) {
		super();
		this.line = line;
		this.start = start;
		this.end = end;
		this.word = word;
	}


	@Override
	protected Integer compute() {
		int result = 0;
		if(end - start < 10) {
			result = count(line,start,end,word);
		}else {
			int mid = (start+end)/2;
			LineTask task1 = new LineTask(line, start, mid, word);
			LineTask task2 = new LineTask(line, mid, end, word);
			invokeAll(task1,task2);
			try {
				result = groupResults(task1.get(),task2.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	private int groupResults(Integer integer, Integer integer2) {
		Integer result = 0;
      	result = integer + integer2;
      	return result;
	}


	private int count(String[] line2, int start2, int end2, String word2) {
		
		int counter = 0;
		for(int i=start;i<end;i++) {
			if(line[i].equals(word2)) {
				counter++;
			}
		}
		try {
			//Thread.sleep(10);
		} catch (Exception e) {
			e.printStackTrace();
		}
  		return counter;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Date d = new Date();
		Document mock = new Document();
		String [][] document = mock.generateDocument(1000, 10000, "the");
		DocumentTask task = new DocumentTask(document, 0, 1000, "the");
		
		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(task);
		Date d1 = new Date();
		long time1 = d1.getTime();
		long time = d.getTime();
		System.out.println("所需时间"+(time1-time)+"+++"+(task.get()));
//		do {
//			System.out.println("====================");
//			System.out.println("Main : parallelism %d \n"+pool.getParallelism());
//			System.out.println("Main : ActiveThreads %d \n"+pool.getActiveThreadCount());
//			System.out.println("Main : getStealCount %d \n"+ pool.getStealCount());
//			System.out.println("====================");
//		} while (!task.isDone());
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.DAYS);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			System.out.printf("Main : The word appears %s in the document" , task.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
