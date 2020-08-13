package ar.com.gl.shop.product.repository;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;

public interface Repository {

	public Category createCategory(Category category);

	public Category softDeleteCategory(Category category);

	public List<Category> findAllCategory();
	
	public Category findCategoryById(Long id);

	public Product createProduct(Product category);

	public List<Product> findAllProduct();

	public Product softDeleteProduct(Product product);
	
	public Product findProductById(Long id);

	public Category updateCategory(Category category);

	public Category deleteCategory(Category category);

	public Product updateProduct(Product product);

	Product deleteProduct(Product product);

}
