package ar.com.gl.shop.product.repository;

import java.sql.SQLException;
import java.util.List;

import ar.com.gl.shop.product.model.Product;

public interface ProductRepository {

	public Product save(Product product);
	public Product getProduct(final Long id);
	public void delete(Product product);
	public List<Product> getAll();
}
