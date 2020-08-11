package ar.com.gl.shop.product.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.repositoryimpl.CategoryRepositoryImpl;
import ar.com.gl.shop.product.services.CategoryService;

public class CategoryServiceImpl implements CategoryService {	
	
	
	CategoryRepositoryImpl repositoryImpl;
	
	Category theCategory;
	
	public CategoryServiceImpl() {
		
		repositoryImpl = new CategoryRepositoryImpl();
		
		theCategory = new Category();
	}
	
	//Categorias iniciales
	public void agregarPrimerosObjetos() {		
		
		repositoryImpl.save(new Category(2l, "Consumibles", "Para-comer"));
		repositoryImpl.save(new Category(3l,"Limpieza", "Para-limpiar"));
		repositoryImpl.save(new Category(4l,"Ropa", "Para-vestir"));	
		
	}
	

	@Override
	public void create(Long id, String name, String description) {	
		
		
		theCategory = new Category(id,name,description);
		
		repositoryImpl.save(theCategory);
		
		//ordernar por id
		repositoryImpl.getAll()
		.sort((o1,o2)->o1.getId()
		.compareTo(o2.getId()));
		
	}
	
	@Override
	public List<Category> findAll() {	
		
		List<Category> theCategoriesEnabled = new ArrayList<>();

		for (Category category : repositoryImpl.getAll()) {
			if (category.getEnabled()) {
				theCategoriesEnabled.add(category);
			}
		}	
		
		return theCategoriesEnabled;
	}
	
	@Override
	public List<Category> findAllDisabled(){		
		
		return repositoryImpl.getAll();
		
	}

	
	@Override
	public Category findById(Long id, Boolean searchEnable){	
		Category category = repositoryImpl.getCategory(id);	
		try {
			if(category == null) {
				throw new ItemNotFound("No se encontró categoria con este id");
			}
			if(searchEnable) {
				category = category.getEnabled() ? category : null;
			}			
		}catch (ItemNotFound e) {
			System.out.println(e.getMessage());	
		}
		return category;		
	}
	
	

	@Override
	public Category updateById(Category category){		

		theCategory = findById(category.getId(), true);
	
		repositoryImpl.delete(theCategory);
		
		repositoryImpl.save(category);

		
		return theCategory;		
		
	}

	@Override
	public void  deleteById(Category theCategory){
		
		if (theCategory.getEnabled()) {
			theCategory.setEnabled(false);
		}else {
			theCategory.setEnabled(true);
		}
		
	}
	
	@Override
	public void  forceDeleteById(Category theCategory){
		
		repositoryImpl.delete(theCategory);
			
	}
	




}
