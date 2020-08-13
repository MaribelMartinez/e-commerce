package ar.com.gl.shop.product.repositoryimpl;


import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repository.Repository;
import ar.com.gl.shop.product.repository.datasource.CategoryDatasource;
import ar.com.gl.shop.product.repository.datasource.ProductDatasource;

public class RepositoryImpl implements Repository {


	private CategoryDatasource categoryDatasource = CategoryDatasource.getInstance();
	private ProductDatasource productDatasource = ProductDatasource.getInstance();

	public RepositoryImpl() {
	}

	//Category
	@Override
	public Category createCategory(Category category) {
		return categoryDatasource.create(category);
	}
	
	@Override
	public Category findCategoryById(Long id) {
		return categoryDatasource.findById(id);
	}
	
	@Override
	public List<Category> findAllCategory() {
		return categoryDatasource.findAll();
	}
	
	@Override
	public Category updateCategory(Category category) {
		return categoryDatasource.update(category);
	}
	
	@Override
	public Category softDeleteCategory(Category category) {
		return categoryDatasource.softDelete(category);

	}

	@Override
	public Category deleteCategory(Category category) {
		return categoryDatasource.delete(category);

	}
		
	// PRODUCT

	@Override
	public Product createProduct(Product product) {		
		return productDatasource.create(product);
	}
	
	@Override
	public Product findProductById(Long id) {
		return productDatasource.findById(id);
	}

	@Override
	public List<Product> findAllProduct() {
		return productDatasource.findAll();
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productDatasource.update(product);
	}

	@Override
	public Product softDeleteProduct(Product product) {
		return productDatasource.softDelete(product);
	}
	
	@Override
	public Product deleteProduct(Product product) {
		return productDatasource.delete(product);
	}
	




}
