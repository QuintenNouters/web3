package main.model.domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Person {
	private String userId;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private Role role;

	//NEW PERSON
	public Person(String userId, String email, String password, String firstName, String lastName, Role role) {
		createSalt();
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPasswordHashed(password);
		setRole(role);
	}
	
	//EXISTING PERSON
	public Person(String userId, String email, String password, String firstName, String lastName, String salt, Role role) {
		setUserId(userId);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setSalt(salt);
		setPassword(password);
		setRole(role);
	}
	
	public Person() {
		createSalt();
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		if(userId.isEmpty()){
			throw new IllegalArgumentException("No userId given");
		}
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}
	
	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		String passwordToCheck = hashPassword(password, this.salt);
		return getPassword().equals(passwordToCheck);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}
	
	public void setPasswordHashed(String password) {
		if(password.isEmpty() || password == null) throw new IllegalArgumentException("No password given");
		this.password = hashPassword(password, salt);
	}
	
	private String hashPassword(String password, String salt) {
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-512");
			crypt.reset();
			
			byte[] seed = salt.getBytes("UTF-8");
			crypt.update(seed);
			
			byte[] passwordBytes = password.getBytes("UTF-8");
			crypt.update(passwordBytes);
			
			byte[] digest = crypt.digest();
			BigInteger digestAsBigInteger = new BigInteger(1, digest);
			return digestAsBigInteger.toString(16);
			
		} catch (Exception e) {
			throw new DomainException("hashing failed: " + e.getMessage());
		}
	}
	
	private void setSalt(String salt) {
		if (salt == null  || salt.trim().isEmpty()) {
			throw new DomainException("empty salt given, class person");
		} else {
			this.salt= salt;
		}
	}
	
	public String getSalt() {
		return this.salt;
	}
	
	private void createSalt() {
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(20);
		BigInteger digestAsBigInteger = new BigInteger(1, seed);
		this.salt = digestAsBigInteger.toString();
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserId() + ", " + getEmail();
	}	
}
