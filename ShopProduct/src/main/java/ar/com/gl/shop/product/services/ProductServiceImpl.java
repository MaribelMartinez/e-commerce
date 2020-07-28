package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.RepositoryImpl;

public class ProductServiceImpl implements ProductService{

	RepositoryImpl repository = new RepositoryImpl();

	@Override
	public Boolean create(Product newProduct) {
		if(newProduct != null) {
			repository.saveProduct(newProduct);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Product> findAllProduct() {
		return repository.findAllProduct();
	}

	@Override
	public Boolean update(Product product) {
		if(product.getId() != null) {
			if(existsProductById(product.getId())) {
				Product productUpdate = findProductById(product.getId());
				productUpdate.setDescription(product.getDescription());
				productUpdate.setPrice(product.getPrice());
				productUpdate.setStock(new Stock());
				productUpdate.setCategory(new Category());
				productUpdate.setName(product.getName());
				repository.saveProduct(productUpdate);
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean delete(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product findProductById(Long id) {
		List<Product> listProducts = repository.findAllProduct();
		Product product = null;
		for(int i = 0; i<listProducts.size(); i++) {
			if(listProducts.get(i).getId().equals(id)) {
				product = listProducts.get(i);
			}
		}
		return product;
	}

	@Override
	public Boolean existsProductById(Long id) {
		List<Product> listProduct = repository.findAllProduct();
		Boolean exists = false;
		for(Product productList : listProduct) {
			if(productList.getId().equals(id)) {
				exists = true;
			}
		}
		return exists;
	}

}
