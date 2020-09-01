package xyz.jangle.thread.test.n6_2.create;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 雇员工厂类
 * 
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月1日 下午6:09:56
 * 
 */
public class PersonGenerator {

	public static List<Person> generatePersonList(int size) {
		List<Person> ret = new ArrayList<>();
		String firstNames[] = { "A", "B", "C", "D", "E", "F", "G" };
		String lastNames[] = { "Z", "Y", "X", "W", "V", "U", "T" };
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			Person person = new Person();
			person.setId(i);
			person.setFirstName(firstNames[r.nextInt(7)]);
			person.setLastName(lastNames[r.nextInt(7)]);
			person.setSalary(r.nextInt(100000));
			person.setCoeficient(r.nextDouble() * 10);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.YEAR, -r.nextInt(30));
			Date date = calendar.getTime();
			person.setBirthDate(date);
			ret.add(person);
		}
		return ret;
	}

	public static void main(String[] args) {
		Random r = new Random();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -r.nextInt(30));
		Date date = calendar.getTime();
		System.out.println(date);
	}
}
