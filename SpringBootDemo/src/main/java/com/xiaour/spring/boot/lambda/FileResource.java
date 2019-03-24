package com.xiaour.spring.boot.lambda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FileResource {
	public static String processFile(BufferReaderProcess p) throws FileNotFoundException, IOException {
		try (BufferedReader bf = new BufferedReader(new FileReader("/Users/biwh/Desktop/blue_whale/文档/everyday.md"))) {
			return p.process(bf);
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {

		// System.out.println(processFile((BufferedReader br) ->
		// br.readLine()+br.readLine()));

		Function<BufferedReader,String> f= (BufferedReader br) ->{
			try {
				return br.readLine();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		};
		
		int port = 100;
		Runnable a = () -> System.out.println(port);
		
		
		Predicate<String> notEmpty = (String s) -> !s.isEmpty();
		List<String> listofString = new ArrayList<String>();
		listofString.add("");
		listofString.add("1");
		listofString.add("");
		listofString.add("3");
		listofString.add("");
		List<String> non = filter(listofString, notEmpty);
		non.forEach((String s) -> System.out.println(s));
		forEach(listofString, (String i) -> System.out.println(i));

		map(listofString, (String s) -> s.length()).forEach((Integer i) -> System.out.println(i));
		
		IntPredicate pre = (int i) -> i%2 == 0;
		boolean test = pre.test(100);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<T>();
		for (T t : list) {
			if (p.test(t)) {
				results.add(t);
			}
		}
		return results;
	}

	public static <T> void forEach(List<T> t, Consumer<T> c) {
		for (T t2 : t) {
			c.accept(t2);
		}
	}

	public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
		List<R> r = new ArrayList<>();
		for (T r2 : list) {
			r.add(f.apply(r2));
		}
		return r;
	}

}
