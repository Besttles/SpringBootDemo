package com.xiaour.spring.boot.folkJoin.cancleTask;

import java.util.Random;

public class ArrayGeneration {

	public int [] generationArray(int size) {
		int [] array = new int[size];
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			array[i] = random.nextInt(10);
		}
		return array;
	}
}
