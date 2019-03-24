package com.xiaour.spring.boot.lambda;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

public class Main {

	public static void main(String[] args) {
		// int port = 100;
		// Runnable a = () -> System.out.println(port);
		// a.run();
		// port = 200;
		People p = new People("lili", 180);
		People p1 = new People();
		BeanUtils.copyProperties(p, p1);
		System.out.println(p1.name + "====" + p.getName());
		// PropertyDescriptor[] targetPds = getPropertyDescriptors(People.class);
		// BeanUtils.copyProperties(source, target);
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(People.class);
	}

	public static void CopyPropertiesForMap(Map<String, Object> map, Object o)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 获取所有该对象类型的属性
		PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(o.getClass());
		// 遍历该属性数组
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();
			Object object = map.get(name);
			if (object != null) {
				// 获取读的方法
				Method writeMethod = propertyDescriptor.getWriteMethod();
				// 判断是否为空，对应map中的属性类型是否可以转换成writeMethod的入参类型
				if (writeMethod != null
						&& ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], object.getClass())) {
					// 若为私有属性，开启写入的权限
					if (Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
						writeMethod.setAccessible(true);
					}
					// 执行方法
					writeMethod.invoke(o, object);
				}
			}
		}
	}

}
