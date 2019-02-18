package com.xiaour.spring.boot.folkJoin.sycRecursiveTask;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class FolderProcessor extends RecursiveTask<List<String>>{
	private static final long serialVersionUID = 1L;
	private String path;
	private String extension;
	public FolderProcessor(String path, String extension) {
		super();
		this.path = path;
		this.extension = extension;
	}
	@Override
	protected List<String> compute() {
		List<String> list = new ArrayList<String>();
		List<FolderProcessor> tasks = new ArrayList<>();
		File file = new File(path);
		File[] contends = file.listFiles();
		if(contends != null) {
			for (int i = 0; i < contends.length; i++) {
				if(contends[i].isDirectory()) {
					FolderProcessor task = new FolderProcessor(contends[i].getAbsolutePath(), extension);
					task.fork();
					tasks.add(task);
					
				}else {
					if(checkFile(contends[i].getName())) {
						list.add(contends[i].getAbsolutePath());
						System.out.println(contends[i].getAbsolutePath());
					}
				}
			}
			if(tasks.size() > 50) {
				System.out.println("task"+tasks.size()+"run");
			}
			addResultFromTask(list,tasks);
		}
		return list;
	}
	private boolean checkFile(String name) {
		return name.endsWith(extension);
	}
	private void addResultFromTask(List<String> list, List<FolderProcessor> tasks) {
		for (FolderProcessor folderProcessor : tasks) {
			list.addAll(folderProcessor.join());
		}
	}
	public static void main(String[] args) {
	    ForkJoinPool pool = new ForkJoinPool();
	    FolderProcessor system = new FolderProcessor("/Users/biwh/Desktop/blue_whale", "md");
	    FolderProcessor apps = new FolderProcessor("/Users/biwh/Desktop/blue_whale/文档", "md");
	    FolderProcessor document = new FolderProcessor("/Users/biwh/Desktop/blue_whale/文档/concurrent", "md");
	    pool.execute(system);
	    pool.execute(apps);
	    pool.execute(document);
	    do {
	    	System.out.println("++++++++++++++++++++++++++");
			System.out.println("Main:paralleism:"+pool.getParallelism());
			System.out.println("Main:ActiveCount"+pool.getActiveThreadCount());
			System.out.println("Main:steal count"+pool.getStealCount());
			System.out.println("++++++++++++++++++++++++++");
		    try {
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
	    } while ((!system.isDone())||(!apps.isDone())||(!document.isDone()));
	    pool.shutdown();
	    List<String> result;
	    result = system.join();
	    System.out.println("System size" + result.size());
	    result = apps.join();
	    System.out.println("System size" + result.size());
	    result = document.join();
	    System.out.println("System size" + result.size()); 
	}
}
