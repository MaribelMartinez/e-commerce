package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;

import ar.com.gl.shop.product.exceptions.IdAlreadyExistException;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.RepositoryImpl;
import ar.com.gl.shop.product.services.CategoryService;


public class CategoryServiceImpl implements CategoryService{

	ArrayList<Category> categories = RepositoryImpl.getInstance().getListaCategorias();
	
	public boolean createCategory(Long id, String name, String description)
	{
		try {
				for(Category category : categories)
				{
					if(category.getId() == id)
						throw new IdAlreadyExistException();
				}
			return categories.add(new Category(id,name,description));
		}catch(IdAlreadyExistException exception)
		{
			System.err.println(exception.getMessage());
			return false;
		}
	}
	
	public Category readCategory(Long id)
	{
		for(Category category : categories)
		{
			if(category.getId() == id && category.getIsActive())
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
			if(category.getId() == id && category.getIsActive())
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
	
	public boolean deleteCategoryLogically(Long id)
	{
		for(Category category : categories)
		{
			if(category.getId() == id && category.getIsActive())
			{
				category.setIsActive(false);
				return true;
			}
		}
		return false;
	}
	
	public boolean restore(Long id)
	{
		for(Category category : categories)
		{
			if(category.getId() == id && !category.getIsActive())
			{
				category.setIsActive(true);
				return true;
			}
		}
		return false;
	}
}
