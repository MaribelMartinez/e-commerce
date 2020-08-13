package ar.com.gl.shop.product.repositoryimpl;

import java.util.ArrayList;
import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repository.Repository;

public class RepositoryImpl implements Repository {

	private static List<Category> listaCategorias;
	private static List<Product> listaProductos;
	private CategoryDatasource categoryDatasource = CategoryDatasource.getInstance();
	private ProductDatasource productDatasource = ProductDatasource.getInstance();

	public RepositoryImpl() {
		listaCategorias = new ArrayList<Category>();
		listaProductos = new ArrayList<Product>();

	}

	//Category
	@Override
	public Category saveCategory(Category category) {
		return categoryDatasource.createCategory(category);
	}
	
	@Override
	public Category updateCategory(Category category) {
		return categoryDatasource.updateCategory(category);
	}

	@Override
	public Category forceDeleteCategory(Category category) {
		return categoryDatasource.forceDeleteCategory(category);

	}
	
	@Override
	public Category deleteCategory(Category category) {
		return categoryDatasource.deleteCategory(category);

	}

	@Override
	public List<Category> findAllCategory() {
		return categoryDatasource.getAll();
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoryDatasource.getById(id);
	}
		
	// PRODUCT

	@Override
	public Product saveProduct(Product product) {
		
		return productDatasource.createProduct(product);
	}

	@Override
	public List<Product> findAllProduct() {
		return productDatasource.getAll();
	}

	@Override
	public Product deleteProduct(Product product) {
		return productDatasource.deleteProduct(product);
	}
	
	@Override
	public Product forceDeleteProduct(Product product) {
		return productDatasource.forceDeleteProduct(product);
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productDatasource.updateProduct(product);
	}

	@Override
	public Product findProductById(Long id) {

		return productDatasource.getById(id);
	}

}
