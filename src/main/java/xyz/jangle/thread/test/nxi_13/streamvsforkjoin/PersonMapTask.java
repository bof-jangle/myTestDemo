package xyz.jangle.thread.test.nxi_13.streamvsforkjoin;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.RecursiveAction;

/**
 *  forkjoin框架的任务
 * @author jangle
 * @email jangle@jangle.xyz
 * @time 2020年11月30日 下午7:39:42
 * 
 */
public class PersonMapTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Person> persons;
	private ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap;

	public PersonMapTask(List<Person> persons, ConcurrentHashMap<String, ConcurrentLinkedDeque<Person>> personMap) {
		super();
		this.persons = persons;
		this.personMap = personMap;
	}

	@Override
	protected void compute() {
		if (persons.size() < 1000) {
			for (Person person : persons) {
				ConcurrentLinkedDeque<Person> personList = personMap.computeIfAbsent(person.getArea(),
						area -> new ConcurrentLinkedDeque<Person>());
				personList.add(person);
			}
			return;
		}
		var t1 = new PersonMapTask(persons.subList(0, persons.size() / 2), personMap);
		var t2 = new PersonMapTask(persons.subList(persons.size() / 2, persons.size()), personMap);
		invokeAll(t1, t2);
	}

}
