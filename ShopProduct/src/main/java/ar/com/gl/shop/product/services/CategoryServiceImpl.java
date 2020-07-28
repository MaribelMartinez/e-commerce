package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.RepositoryImpl;

public class CategoryServiceImpl implements CategoryService{
	
	RepositoryImpl repository = new RepositoryImpl();

	@Override
	public Boolean create(Category newCategory) {
		if(newCategory != null) {
			repository.saveCategory(newCategory);
			return true;
		}else {
			return false;
		}
	}
	

	@Override
	public Boolean update(Category category) {
		if(category.getId() != null) {
			if(existCategoryById(category.getId())) {
				Category categoryUpdate = findCategoryById(category.getId());
				categoryUpdate.setDescription(category.getDescription());
				categoryUpdate.setName(category.getName());
				repository.saveCategory(categoryUpdate);
			}
			return true;
		}else {
			return false;
		}
	}

	//Borrado fisico
	@Override
	public Boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Category> findAllCategory() {
		return repository.findAllCategory();
	}

	@Override
	public Category findCategoryById(Long id) {
		List<Category> listCategory = repository.findAllCategory();
		Category category = null;
		for(Category categoryList : listCategory) {
			if(categoryList.getId().equals(id)) {
				category = categoryList;
			}
		}
		return category;
	}


	@Override
	public Boolean existCategoryById(Long id) {
		List<Category> listCategory = repository.findAllCategory();
		Boolean exists = false;
		for(Category categoryList : listCategory) {
			if(categoryList.getId().equals(id)) {
				exists = true;
			}
		}
		return exists;
	}	

}
