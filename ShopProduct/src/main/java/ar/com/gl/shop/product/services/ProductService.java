package ar.com.gl.shop.product.services;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;

interface ProductService {

	public boolean createProduct(Long id, String name, String description, Double price, Stock stock, Category category);
	public Product readProduct(Long id);
	public boolean updateProduct(Long id, String name, String description, Double price, Stock stock, Category category);
	public boolean deleteProduct(Long id);
	public boolean deleteProductLogically(Long id);
	public boolean restore(Long id);
}
