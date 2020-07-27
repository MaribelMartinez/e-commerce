package ar.com.gl.shop.product.repository;
import java.util.ArrayList;
import ar.com.gl.shop.product.model.*;

public class RepositoryImpl implements Repository{

	private ArrayList<Product> listaProductos;
	private ArrayList<Category> listaCategorias;
	private ArrayList<Stock> listaStock;
	private static RepositoryImpl instance = new RepositoryImpl();
	

	private RepositoryImpl() {
		listaProductos = new ArrayList<Product>();
		listaCategorias = new ArrayList<Category>();
		listaStock = new ArrayList<Stock>();
	}
	
	public ArrayList<Product> getListaProductos() {
		return listaProductos;
	}

	public ArrayList<Category> getListaCategorias() {
		return listaCategorias;
	}

	public ArrayList<Stock> getListaStock() {
		return listaStock;
	}
	
	public static RepositoryImpl getInstance() {
		return instance;
	}

}
