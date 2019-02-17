package com.xiaour.spring.boot.folkJoin.recursiveTask;

import java.util.Date;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Date d = new Date();
		Document mock = new Document();
		String [][] document = mock.generateDocument(1000, 10000, "the");
		
		int counter = 0;
		for (int i = 0; i < 1000; i++) {
			for(int j=0;j<10000;j++) {
				if(document[i][j].equals("the")) {
					counter++;
				}
			}
		}
		System.out.printf("documentMock: the wprd appends %d times in the document \n",counter);
		Date d1 = new Date();
		long time1 = d1.getTime();
		long time = d.getTime();
		System.out.println("所需时间"+(time1-time));
	}
}
