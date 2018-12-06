package main.model.domain;

import java.util.List;
import java.util.Properties;

import main.model.db.PersonDb;
import main.model.db.PersonDbInMemory;
import main.model.db.ProductDb;
import main.model.db.ProductDbInMemory;
import main.model.db.ProductDbSql;

public class ShopService {
	private PersonDb personDb;
	private ProductDb productDb;
	
	
	public ShopService(Properties prop){
		personDb = new PersonDbInMemory();
		productDb = new ProductDbInMemory();
	}
	
	
	
	
	
	public Person getPerson(String personId) {
		return personDb.get(personId);
	}

	public List<Person> getPersons() {
		return personDb.getAll();
	}

	public void addPerson(Person person) {
		personDb.add(person);
	}

	public void updatePerson(Person person) {
		personDb.update(person);
	}

	public void deletePerson(String id) {
		personDb.delete(id);
	}

	public boolean checkPassword(String userid, String password) {
		return getPerson(userid).isCorrectPassword(password);
	}
	
	public Person getUserIfAuthenticated(String userId, String password) {
		if(checkPassword(userId, password)) {
			return getPerson(userId);
		}
		return null;
	}
	
	
	
	
	
	public Product getProduct(int productId){
		return productDb.get(productId);
	}
	
	public List<Product> getProducts() {
		return productDb.getAll();
	}

	public void addProduct(Product product) {
		productDb.add(product);
	}

	public void updateProduct(Product product) {
		productDb.update(product);
	}

	public void deleteProduct(int id) {
		productDb.delete(id);
	}
	
}
