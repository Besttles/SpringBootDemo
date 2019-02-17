package com.xiaour.spring.boot.folkJoin.recursiveTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer>{

	private String[][] document;
	private int start,end;
	private String word;
	
	public DocumentTask(String[][] document, int start, int end, String word) {
		super();
		this.document = document;
		this.start = start;
		this.end = end;
		this.word = word;
	}

	@Override
	protected Integer compute() {
		int result = 0;
		if(end - start < 100) {
			result = processWord(document,start,end,word);
		}else {
			int mid = (start+end)/2;
			DocumentTask task1 = new DocumentTask(document, start, mid, word);
			DocumentTask task2 = new DocumentTask(document, mid, end, word);
			invokeAll(task1,task2);
			try {
				result = groupResults(task1.get(),task2.get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private Integer groupResults(Integer integer, Integer integer2) {
		Integer result = 0;
      	result = integer + integer2;
      	return result;
	}

	private int processWord(String[][] document, int start, int end, String word) {

		List<LineTask> tasks = new ArrayList<LineTask>();
		for(int i=start;i<end;i++) {
			LineTask task = new LineTask(document[i],0,document[i].length,word);
			tasks.add(task);
			invokeAll(tasks);
		}
		int result = 0;
		for (int i = 0; i < tasks.size(); i++) {
			LineTask lineTask = tasks.get(i);
			try {
				result += lineTask.get();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}		
		return result;
	}
}
