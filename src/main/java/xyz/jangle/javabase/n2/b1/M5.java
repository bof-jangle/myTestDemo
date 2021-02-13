package xyz.jangle.javabase.n2.b1;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * 	2.1.7、以文本格式存储对象
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月13日 上午10:29:49
 * 
 */
public class M5 {

	public static void main(String[] args) throws IOException {
		Employee eArr[] = new Employee[3];
		eArr[0] = new Employee("名称", 11999, new Date());
		eArr[1] = new Employee("名称2", 11998, new Date());
		eArr[2] = new Employee("名称3", 11997, new Date());
		
		try(var out = new PrintWriter(Files.newOutputStream(Paths.get(M3.pathFileStr)))				)
		{
			writerDate(eArr, out);
		}
		try(var in = new Scanner(Files.newInputStream(Paths.get(M3.pathFileStr)))){
			Employee[] employees = readData(in);
			for (Employee employee : employees) {
				System.out.println(employee.getName()+"|"+employee.getSalary()+"|"+employee.getHireDay());
			}
		}
	}
	
	private static void writerDate(Employee[] employees, PrintWriter out) {
		for (Employee employee : employees) {
			writerEmployee(employee, out);
		}
	}
	
	private static void writerEmployee(Employee e, PrintWriter out) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(e.getHireDay());
		out.println(e.getName()+"|"+e.getSalary()+"|"+format);
	}
	
	private static Employee readEmployee(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		String name = tokens[0];
		Integer salary = Integer.valueOf(tokens[1]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(tokens[2]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Employee(name,salary,date);
	}
	
	private static Employee[] readData(Scanner in) {
		var list = new ArrayList<Employee>();
		int i = 0;
		for (;in.hasNext();i++) {
			list.add(readEmployee(in));
		}
		return list.toArray(new Employee[i]);
	}

}
