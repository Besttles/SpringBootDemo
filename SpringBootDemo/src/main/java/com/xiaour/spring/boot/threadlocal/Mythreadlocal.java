package com.xiaour.spring.boot.threadlocal;

public class Mythreadlocal {

	private static ThreadLocal<Object> local = new ThreadLocal<Object>() {
		
		@Override
		protected Object initialValue() {
			System.out.println("initial value");
			return super.initialValue();
		}
	};
	
	
	public static void main(String[] args) {
		
	}
	
	public static class IntegerTask implements Runnable{
		
		private String name;
		
		IntegerTask(String name){
			this.name = name;
		}
		
		
		@Override
		public void run() {
             
			
			if(null == Mythreadlocal.local.get()) {
				Mythreadlocal.local.set("newWord");
			}else {
				String str = (String) Mythreadlocal.local.get();
				System.out.println("");
			}
			
			
		}
	}
}

