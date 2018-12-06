package main.model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import main.model.domain.Person;
import main.model.domain.Role;

public class PersonDbSql implements PersonDb {
	
	private Connection conn;
	private Properties prop;
	private String url;
	
	public PersonDbSql(Properties prop) {
		this.prop = prop;
		this.url = prop.getProperty("url");
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			throw new DbException(e.getMessage(), e);
		}
		try {
			conn = DriverManager.getConnection(url, prop);
		} catch (SQLException e) {
			throw new DbException("Connection failure with the database", e);
		}
	}
	
	@Override
	public Person get(String personId) {
		Person person = new Person();
		String sql = "SELECT * FROM person WHERE userid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, personId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String userId = result.getString("userid");
				String firstName = result.getString("firstname");
				String lastName = result.getString("lastname");
				String email = result.getString("email");
				String password = result.getString("password");
				String salt = result.getString("salt");
				String role = result.getString("role");
				person = new Person(userId, email, password, firstName, lastName, salt, Role.valueOf(role.toUpperCase()));
			}
			return person;
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public List<Person> getAll() {
		List<Person> users = new ArrayList<>();
		try (Statement statement = conn.createStatement()){
			ResultSet result = statement.executeQuery("SELECT * FROM person");
			while (result.next()) {
				String userId = result.getString("userid");
				String firstName = result.getString("firstname");
				String lastName = result.getString("lastname");
				String email = result.getString("email");
				String password = result.getString("password");
				String salt = result.getString("salt");
				String role = result.getString("role");
				Person person = new Person(userId, email, password, firstName, lastName, salt, Role.valueOf(role.toUpperCase()));
				users.add(person);
			}
			return users;
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void add(Person person) {
		String sql = "INSERT INTO person (userid, firstname, lastname, email, password, salt, role)" + "VALUES (?,?,?,?,?,?,?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, person.getUserId());
			statement.setString(2, person.getFirstName());
			statement.setString(3, person.getLastName());
			statement.setString(4, person.getEmail());
			statement.setString(5, person.getPassword());
			statement.setString(6, person.getSalt());
			statement.setString(7, person.getRole().toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void update(Person person) {
		String sql = "UPDATE person SET firstname = ?, lastname = ?, email = ?, WHERE userid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setString(3, person.getEmail());
			statement.setString(4, person.getUserId());
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}

	@Override
	public void delete(String personId) {
		String sql = "DELETE FROM person WHERE userid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, personId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}

}
