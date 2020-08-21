package ar.com.gl.shop.product.service;

import java.util.List;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;

public interface CategoryService {
	
	public Category create(Category category);
	public List<Category> findAll();
	/**
	 * Este metodo sirve para buscar un elemento por id pasado por parametro,
	 * el segundo parametro es la manera de buscar, si se pasa true va a hacer una busqueda
	 * solamente en los elementos con el atributo enabled == true,
	 * si pasamos por parametro false, entonces busca en todos los elementos en memoria.
	 * 
	 * @param id
	 * @param bool
	 * @return Repository
	 */
	public Category getById(Long id, Boolean enable);
	public Category getByName(String name);	
	public Category update(Category category);
	public Category softDelete(Long id);
	public void delete(Long id);
	public List<Category> findAllDisabled();


}
