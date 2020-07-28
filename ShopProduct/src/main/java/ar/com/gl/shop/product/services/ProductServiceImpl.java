package ar.com.gl.shop.product.services;

import java.util.ArrayList;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.RepositoryImpl;

public class ProductServiceImpl implements ProductService{

	private ArrayList<Product> products = RepositoryImpl.getInstance().getListaProductos();
	
	public boolean createProduct(Long id, String name, String description, Double price, Stock stock, Category category) 
	{
		products.add(new Product(id,name,description,price, stock, category));
		return true;
	}
	
	public Product readProduct(Long id) 
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getId() == id  && products.get(i).getIsActive())
				return products.get(i);
			i++;
		}
		return null;
	}
	
	public boolean updateProduct(Long id, String name, String description, Double price, Stock stock, Category category) 
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getId() == id  && products.get(i).getIsActive())
				{
				products.get(i).setId(id);
				products.get(i).setName(name);
				products.get(i).setDescription(description);
				products.get(i).setPrice(price);
				products.get(i).setStock(stock);
				products.get(i).setCategory(category);
				return true;
				}
			i++;
		}
		return false;
	}
	
	public boolean deleteProduct(Long id) 
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getId() == id)
				{
					products.remove(products.get(i));
					return true;
				}
			i++;
		}
		return false;
	}
	
	public boolean deleteProductLogically(Long id) 
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getId() == id && products.get(i).getIsActive())
				{
					products.get(i).setIsActive(false);
					return true;
				}
			i++;
		}
		return false;
	}
	
	public boolean restore(Long id) 
	{
		int i = 0;
		while(i < products.size())
		{
			if(products.get(i).getId() == id && !products.get(i).getIsActive())
				{
					products.get(i).setIsActive(true);
					return true;
				}
			i++;
		}
		return false;
	}
	
}
