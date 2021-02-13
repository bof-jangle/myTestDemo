
package xyz.jangle.javabase.n2.b1;

import java.util.Date;

/**
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2021年2月13日 上午10:31:47
 * 
 */
public class Employee {
	
	private String name;
	
	private Integer salary;
	
	private Date hireDay;

	public Employee(String name2, Integer salary2, Date date) {
		this.name = name2;
		this.salary = salary2;
		this.hireDay = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}
	
	
	
	

}
