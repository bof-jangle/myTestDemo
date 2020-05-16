package xyz.jangle.list.test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		boolean contains = list.contains("1");
		System.out.println(contains);

	}

}
