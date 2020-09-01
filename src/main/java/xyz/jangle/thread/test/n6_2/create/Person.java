package xyz.jangle.thread.test.n6_2.create;

import java.util.Date;

/**
 * 	普通类，雇员信息
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年9月1日 下午6:03:57
 * 
 */
public class Person implements Comparable<Person> {

	private int id;
	private String firstName, lastName;
	private Date birthDate;
	private int salary;
	private double coeficient;

	@Override
	public int compareTo(Person o) {
		int compareLastNames = this.getLastName().compareTo(o.getLastName());
		if (compareLastNames != 0) {
			return compareLastNames;
		} else {
			return this.getFirstName().compareTo(o.getFirstName());
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(double coeficient) {
		this.coeficient = coeficient;
	}

}
