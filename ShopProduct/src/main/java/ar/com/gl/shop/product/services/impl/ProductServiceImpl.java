package ar.com.gl.shop.product.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.RepositoryImpl;
import ar.com.gl.shop.product.services.ProductService;

public class ProductServiceImpl implements ProductService {

	RepositoryImpl repository = new RepositoryImpl();
	CategoryServiceImpl categoryService = new CategoryServiceImpl();
	StockServiceImpl stockService = new StockServiceImpl();

	@Override
	public Boolean create(Product product) {

		if (product != null) {
			repository.saveProduct(product);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> findAllProduct() {

		repository.findAllProduct().sort((prod1, prod2) -> prod1.getId().compareTo(prod2.getId()));
		List<String> productListString = new ArrayList<>();

		for (Product product : repository.findAllProduct()) {

			if (product.getDisabledDate() != null) {
				productListString.add(product.getId() + ". Name:" + product.getName() + " Description:"
						+ product.getDescription() + " Price:" + product.getPrice() + " Category:"
						+ product.getCategory().getName() + " Disabled date:" + product.getDisabledDate());
			} else {
				productListString.add(
						product.getId() + ". Name:" + product.getName() + " Description:" + product.getDescription()
								+ " Price:" + product.getPrice() + " Category:" + product.getCategory().getName());
			}

		}
		return productListString;
	}

	@Override
	public Boolean update(Product product) {

		if (product.getId() != null) {

			if (existsProductById(product.getId())) {

				Product productUpdate = findProductById(product.getId());
				productUpdate.setName(product.getName());
				productUpdate.setDescription(product.getDescription());
				productUpdate.setPrice(product.getPrice());
				// Actualiza categoria
				productUpdate.setCategory(categoryService.findCategoryById(product.getCategory().getId()));
				repository.deleteProduct(productUpdate);
				repository.saveProduct(productUpdate);

			}
			return true;
		} else {
			return false;
		}

	}

	@Override
	public Boolean delete(Long id) {
		
		repository.deleteProduct(findProductById(id));
		
		if (existsProductById(id)) {
			
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public Boolean updateStock(Product product) {
		
		try {
			
			Stock stock = stockService.create(product.getStock());
			product.setStock(stock);
			return true;
			
		} catch (NullPointerException e) {
			
			System.err.println("No existe stock");
			return false;
			
		}
	}

	@Override
	public List<String> findEnabledProducts() {
		
		repository.findAllProduct().sort((Product prod1, Product prod2) -> prod1.getId().compareTo(prod2.getId()));
		List<String> productListString = new ArrayList<>();
		
		for (Product product : repository.findAllProduct()) {
			
			if (product.getDisabledDate() == null) {
				
				productListString.add(
						product.getId() + ". Name:" + product.getName() + " Description:" + product.getDescription()
								+ " Price:" + product.getPrice() + " Category:" + product.getCategory().getName());
			}
		}

		return productListString;
	}

	@Override
	public Product findProductById(Long id) {
		
		List<Product> listProduct = repository.findAllProduct();
		Product product = null;
		
		for (Product productList : listProduct) {
			if (productList.getId().equals(id)) {
				product = productList;
			}
		}
		
		return product;
	}

	@Override
	public Boolean existsProductById(Long id) {
		
		List<Product> listProduct = repository.findAllProduct();
		Boolean exists = false;
		
		for (Product productList : listProduct) {
			if (productList.getId().equals(id)) {
				exists = true;
			}
		}
		
		return exists;
	}

	
	@Override
	public Boolean disable(Long id) {
		
		if (existsProductById(id)) {
			findProductById(id).setDisabledDate(new Date());
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public String findStock(Long id) {
		
		String stockString;
		Product product = findProductById(id);
		
		Stock stock = stockService.findStockById(product.getStock().getId());
		stockString = "Quantity: " + stock.getQuantity() + " Location code: " + stock.getLocationCode();
		return stockString;
		
	}

}
