package com.xiaour.spring.boot.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortList {

	public static void main(String[] args) {
		People p = new People("lili",164);
		People p1 = new People("huahua",175);
		People p2 = new People("fengfeng",180);
		People p3 = new People("huanhuan",170);
		People p4 = new People("luoluo",185);
		
		List<People> list = new ArrayList<People>();
		
		list.add(p);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		
		list.sort(new Comparator<People>(){
			@Override
			public int compare(People o1, People o2) {
				
				return (int) (o1.getTall()-o2.getTall());
			}
		});
		
//		list.sort((people a, people b) -> (int)(a.getTall()-b.getTall()));
		
		Thread thread = new Thread(() -> System.out.println("1111"));
		
		thread.start();   //System.out.println(e.getName()+e.getTall()
		
		list.forEach(e -> System.out.println(e.getName()+e.getTall()));
	}
}
