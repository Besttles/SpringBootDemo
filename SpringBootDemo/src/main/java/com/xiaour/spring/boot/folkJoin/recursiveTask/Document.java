package com.xiaour.spring.boot.folkJoin.recursiveTask;

import java.util.Random;

public class Document {

	private String words[] = {"the","hello","goodbay","packet","java","thread","pool","random","class","main"};
	public String [][] generateDocument(int numlines,int numWords,String word){
		
		int counter = 0;
		String document[][] = new String [numlines][numWords];
		Random random = new Random();
		
		for (int i = 0; i < numlines; i++) {
			for(int j=0;j<numWords;j++) {
				int index = random.nextInt(words.length);
				document[i][j] = words[index];
				if(document[i][j].equals(word)) {
					counter++;
				}
				
			}
		}
		System.out.printf("documentMock: the wprd appends %d times in the document \n",counter);
		return document;
	}
}
