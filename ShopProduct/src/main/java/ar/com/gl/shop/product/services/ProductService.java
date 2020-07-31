package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Product;

public interface ProductService {

	public Boolean create(Product product);

	public List<String> findAllProduct();
	
	public List<String> findEnabledProducts();

	public Boolean update(Product product);

	public Boolean delete(Long id);

	public Product findProductById(Long id);

	public Boolean existsProductById(Long id);

	public Boolean disable(Long id);
	
	public Boolean updateStock(Product product);
	
	public String findStock(Long id);
}
