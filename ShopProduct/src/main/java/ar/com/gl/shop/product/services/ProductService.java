package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Product;

public interface ProductService {

	public Boolean create(Product product);
	public List<Product> findAllProduct();
	public Boolean update(Product product);
	public Boolean delete(Product product);
	public Product findProductById(Long id);
	public Boolean existsProductById(Long id);
}
