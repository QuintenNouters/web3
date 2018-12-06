package test;

import static org.junit.Assert.*;

import java.util.Properties;

import javax.management.relation.Role;

import org.junit.Before;
import org.junit.Test;

import main.model.domain.Person;
import main.model.domain.ShopService;

public class ShopServiceTest {
	private static Properties prop;
	
	private static ShopService service = new ShopService(prop);
	private Person person;
	private String userid;
	private String PASSWORD = "1234";
	@Before
	public void setUp() {
		userid = generateRandomUseridInOrderToRunTestMoreThanOnce("sinterklaas");
		//person = new Person(userid, "klaas@klaas.be", PASSWORD, "Klaas", "Claesens", );
	}

	@Test
	public void getPerson_should_return_the_person_if_registered() {
		service.addPerson(person);

		Person personRetrieved = service.getPerson(userid);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserId());
		assertEquals(person.getEmail(), personRetrieved.getEmail());
		assertEquals(person.getFirstName(), personRetrieved.getFirstName());
		assertEquals(person.getLastName(),personRetrieved.getLastName());
	}

	@Test
	public void getPerson_should_return_null_if_person_not_registered() {
		service.addPerson(person);
		
		Person personRetrieved = service.getPerson("Unknown");

		assertNull(personRetrieved);
	}

	@Test
	public void getUserIfAuthenticated_should_return_the_person_if_registered_and_correct_password() {
		service.addPerson(person);

		Person personRetrieved = service.getUserIfAuthenticated(userid, PASSWORD);

		assertNotNull(personRetrieved);
		assertTrue(personRetrieved.isCorrectPassword(PASSWORD));
		assertEquals(userid, personRetrieved.getUserId());
	}

	@Test
	public void getUserIfAuthenticated_should_return_null_if_person_not_registered() {
		service.addPerson(person);
		
		Person personRetrieved = service.getUserIfAuthenticated("Unknown", PASSWORD);

		assertNull(personRetrieved);
	}

	@Test
	public void getUserIfAuthenticated_should_return_null_if_person_is_registered_but_incorrect_password() {
		service.addPerson(person);
		
		Person personRetrieved = service.getUserIfAuthenticated(userid, "WrongPassword");

		assertNull(personRetrieved);
	}

	private String generateRandomUseridInOrderToRunTestMoreThanOnce(String component) {
		int random = (int) (Math.random() * 1000 + 1);
		return random + component;
	}

}
