package xyz.jangle.thread.test.nxi_13.streamvsforkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 	Model
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年11月30日 下午7:30:02
 * 
 */
public class Person {

	private String name;

	private String area;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public static List<Person> generatorPersons(int size){
		String[] names = {"A","B","C"};
		String[] areas = {"北","上","深","杭"};
		var persons = new ArrayList<Person>();
		
		var random = new Random();
		for (int i = 0; i < size; i++) {
			var p = new Person();
			p.setName(names[random.nextInt(names.length)]);
			p.setArea(areas[random.nextInt(areas.length)]);
			persons.add(p);
		}
		return persons;
	}
}
