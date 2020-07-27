package ar.com.gl.shop.product.services;
import java.util.ArrayList;
import ar.com.gl.shop.product.model.Category;

import ar.com.gl.shop.product.repository.RepositoryImpl;

public class CategoryServiceImpl implements CategoryService{

	ArrayList<Category> categories = RepositoryImpl.getInstance().getListaCategorias();
	
	public void createCategory()
	{
		//Pedir datos por teclado
		categories.add(new Category());
	}
	
	public Category readCategory(Long id)
	{
		for(Category category : categories)
		{
			if(category.getId() == id )
			{
				return category;
			}
		}
		return null;
	}
	
	public boolean updateCategory(Long id, String name, String description)
	{
		for(Category category : categories)
		{
			if(category.getId() == id )
			{
				category.setId(id);
				category.setName(name);
				category.setDescription(description);
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteCategory(Long id)
	{
		for(Category category : categories)
		{
			if(category.getId() == id )
			{
				categories.remove(category);
				return true;
			}
		}
		return false;
	}
}
