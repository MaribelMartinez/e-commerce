package ar.com.gl.shop.product.repository;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;

public interface Repository {

	public Category saveCategory(Category category);

	public void deleteCategory(Category category);

	public List<Category> findAllCategory();

	public Stock saveStock(Stock category);

	public List<Stock> findAllStock();

	public Product saveProduct(Product category);

	public List<Product> findAllProduct();

	public void deleteProduct(Product product);

	public void deleteStock(Stock stock);
}
