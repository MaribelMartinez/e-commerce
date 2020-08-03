package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.gl.shop.product.exceptions.CustomException;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repository.RepositoryImpl;
import ar.com.gl.shop.product.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	RepositoryImpl repository = new RepositoryImpl();

	@Override
	public Boolean create(Category category) {

		if (category != null) {
			repository.saveCategory(category);
			return true;
		} else {
			return false;
		}

	}

	
	@Override
	public Boolean update(Category category) {

		try {

			if (category.getId() != null) {

				if (existCategoryById(category.getId())) {
					Category categoryUpdate = findCategoryById(category.getId());
					categoryUpdate.setDescription(category.getDescription());
					categoryUpdate.setName(category.getName());
					delete(categoryUpdate.getId());
					repository.saveCategory(categoryUpdate);
				}

				return true;
			} else {
				throw new Exception("Id de categoria no puede ser nulo");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	
	// Borrado fisico
	@Override
	public Boolean delete(Long id) {

		try {

			System.out.println(id + "ID");
			if (existCategoryById(id)) {
				System.out.println(id);
				repository.deleteCategory(findCategoryById(id));
				return true;

			} else {
				throw new CustomException("La categoría no existe");
			}

		} catch (CustomException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	
	@Override
	public List<String> findAllCategory() {

		repository.findAllCategory().sort((cat1, cat2) -> cat1.getId().compareTo(cat2.getId()));
		List<String> categoryListString = new ArrayList<>();

		for (Category category : repository.findAllCategory()) {

			if (category.getDisabledDate() != null) {
				categoryListString.add(category.getId() + ". Name:" + category.getName() + " Description:"
						+ category.getDescription() + " Disabled date:" + category.getDisabledDate());

			} else {
				categoryListString.add(category.getId() + ". Name:" + category.getName() + " Description:"
						+ category.getDescription());
			}

		}

		return categoryListString;
	}

	
	@Override
	public List<String> findEnabledCategories() {

		repository.findAllCategory().sort((cat1, cat2) -> cat1.getId().compareTo(cat2.getId()));
		List<String> categoryListString = new ArrayList<>();

		for (Category category : repository.findAllCategory()) {
			if (category.getDisabledDate() == null) {
				categoryListString.add(category.getId() + ". Name:" + category.getName() + " Description:"
						+ category.getDescription());
			}
		}

		return categoryListString;
	}

	
	@Override
	public Category findCategoryById(Long id) {

		List<Category> listCategory = repository.findAllCategory();
		Category category = null;

		for (Category categoryList : listCategory) {
			if (categoryList.getId().equals(id)) {
				category = categoryList;
			}
		}

		return category;
	}

	
	@Override
	public Boolean existCategoryById(Long id) {

		List<Category> listCategory = repository.findAllCategory();
		Boolean exists = false;

		for (Category categoryList : listCategory) {
			if (categoryList.getId().equals(id)) {
				exists = true;
			}
		}

		return exists;
	}

	
	// Borrado logico
	@Override
	public Boolean disable(Long id) {

		if (existCategoryById(id)) {
			findCategoryById(id).setDisabledDate(new Date());
		}

		return false;
	}

}
