package ar.com.gl.shop.product.repository;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;

public interface Repository {

	public Category saveCategory(Category category);

	public Category deleteCategory(Category category);

	public List<Category> findAllCategory();
	
	public Category findCategoryById(Long id);

	public Product saveProduct(Product category);

	public List<Product> findAllProduct();

	public Product deleteProduct(Product product);
	
	public Product findProductById(Long id);

	public Category updateCategory(Category category);

	public Category forceDeleteCategory(Category category);

	public Product updateProduct(Product product);

	Product forceDeleteProduct(Product product);

}
