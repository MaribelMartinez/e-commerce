package ar.com.gl.shop.product.repository;

import java.util.ArrayList;
import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;

public class RepositoryImpl implements Repository{

	private List<Product> listaProductos = new ArrayList<Product>();
	private List<Category> listaCategorias = new ArrayList<Category>();
	private List<Stock> listaStock = new ArrayList<Stock>();
	
	@Override
	public Category saveCategory(Category category) {
		listaCategorias.add(category);
		return category;
	}

	@Override
	public List<Category> findAllCategory() {
		return listaCategorias;
	}

	@Override
	public Stock saveStock(Stock stock) {
		listaStock.add(stock);
		return stock;
	}

	@Override
	public List<Stock> findAllStock() {
		return listaStock;
	}

	@Override
	public Product saveProduct(Product product) {
		listaProductos.add(product);
		return product;
	}

	@Override
	public List<Product> findAllProduct() {
		return listaProductos;
	}

	@Override
	public void deleteCategory(Category category) {
		listaCategorias.remove(category);
		
	}


}
