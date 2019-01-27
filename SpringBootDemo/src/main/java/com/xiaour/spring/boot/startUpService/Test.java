package com.xiaour.spring.boot.startUpService;

public class Test {
public static void main(String[] args) {
    //预先加载组件
 	try {
 		boolean isStart = StartUpService.chechService();
 		if(isStart) {
 			System.out.println("已经加载组件");
 		}else {
 			System.out.println("未加载组件");
 		}
		} catch (Exception e) {
			e.getMessage();
		}
}
}
