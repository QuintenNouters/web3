package main.model.db;

import java.util.List;

import main.model.domain.Person;

public interface PersonDb {
	
	public Person get(String personId);
	public List<Person> getAll();
	public void add(Person person);
	public void update(Person person);
	public void delete(String personId);
	
}
