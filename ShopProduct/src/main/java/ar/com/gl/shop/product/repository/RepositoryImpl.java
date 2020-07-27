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
}
