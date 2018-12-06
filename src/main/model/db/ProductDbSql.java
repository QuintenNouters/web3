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

import main.model.domain.Product;

public class ProductDbSql implements ProductDb {
	
	private Properties prop;
	private Connection conn;
	private String url;
	
	public ProductDbSql(Properties prop) {
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
			throw new DbException("Connection failure");
		}
	}
	
	@Override
	public Product get(int id) {
		Product product = new Product();
		String sql = "SELECT * FROM person WHERE productid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setLong(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int productId = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				product = new Product(productId, name, description, price);
			}
			return product;
		}catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public List<Product> getAll() {
		List<Product> products = new ArrayList<>();
		try (Statement statement = conn.createStatement()){
			ResultSet result = statement.executeQuery("SELECT * FROM product");
			while (result.next()) {
				int productId = Integer.parseInt(result.getString("productid"));
				String name = result.getString("name");
				String description = result.getString("description");
				double price = Double.parseDouble(result.getString("price"));
				Product product = new Product(productId, name, description, price);
				products.add(product);
			}
			return products;
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	}

	@Override
	public void add(Product product) {
		String sql = "INSERT INTO product (name, description, price)" + "VALUES (?,?,?)";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}
	

	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE product SET name = ?, description = ?, price = ? WHERE productid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setLong(4, product.getProductId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM product WHERE productid = ?";
		try (PreparedStatement statement = conn.prepareStatement(sql)){
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage(), e);
		}

	}

}
