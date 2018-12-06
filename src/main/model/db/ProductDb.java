package main.model.db;

import java.util.List;

import main.model.domain.Product;

public interface ProductDb {
	
	public Product get(int id);
	public List<Product> getAll();
	public void add(Product product);
	public void update(Product product);
	public void delete(int id);
}
