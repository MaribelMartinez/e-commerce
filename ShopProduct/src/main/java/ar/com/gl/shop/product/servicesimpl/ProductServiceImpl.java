package ar.com.gl.shop.product.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repositoryimpl.ProductRepositoryImpl;
import ar.com.gl.shop.product.services.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductRepositoryImpl repositoryImpl;
	private StockServiceImpl stockService;
	
	private Product theProduct;	
	
	
	public ProductServiceImpl() {
		
		repositoryImpl = new ProductRepositoryImpl();
		stockService = new StockServiceImpl();
		
		theProduct = new Product();
	}

	public ProductRepositoryImpl getProductRepositoryImpl() {
		return repositoryImpl;
	}

	public List<Product> getTheProducts() {
		return repositoryImpl.getAll();
	}

	public Product getTheProduct() {
		return theProduct;
	}

	@Override
	public void create(Product product) {
		
		theProduct = new Product(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getCategory());		
		
		theProduct.setStock(stockService.create(product.getStock()));
		repositoryImpl.save(theProduct);
		
		//ordernar por id
		repositoryImpl.getAll()
		.sort((o1,o2)->o1.getId()
		.compareTo(o2.getId()));
		
	}
	@Override
	public List<Product> findAll() {	
		
		List<Product> theProducts = new ArrayList<>();	
		
		int listSize = repositoryImpl.getAll().size();
		
		
		for (int i = 0; i < listSize; i++) {
			
			theProduct = repositoryImpl.getAll().get(i);
			
			if (theProduct.getEnabled()) {
				
				theProducts.add(theProduct);
			}
			
			
		}
		
		/*if (bool) {
			return repositoryImpl.findAllProduct().stream()
					.filter(Product->Product.getEnabled())
					.collect(Collectors.toList());
		}
		
		return repositoryImpl.findAllProduct();*/
		
		return theProducts;
	}
	
	public List<Product> findAllDisabled(){
		
		return repositoryImpl.getAll();
	}
	
	@Override
	public Product findById(Long id, Boolean searchEnable){	
		Product product = repositoryImpl.getProduct(id);	
		try {
			if(product == null) {
				throw new ItemNotFound("No se encontr� producto con este id");
			}
			if(searchEnable) {
				product = product.getEnabled() ? product : null;
			}			
		}catch (ItemNotFound e) {
			System.out.println(e.getMessage());	
		}
		return product;		
	}
	
	

	@Override
	public Product updateById(Product product){
		
		
		Product theProduct = repositoryImpl.getProduct(product.getId());
		
		String newName = product.getName();
		
		theProduct.setName(newName);		

		String newDescription = product.getDescription();
		
		theProduct.setDescription(newDescription);
		
		Double newPrice = product.getPrice();
		
		theProduct.setPrice(newPrice);
		
		Category newCategory = product.getCategory();
		
		theProduct.setCategory(newCategory);		

		theProduct.setStock(stockService.update(product.getStock()));
		
		return theProduct;		
		
	}
	

	@Override
	public void  deleteById(Product theProduct){
		
		if (theProduct.getEnabled()) {
			theProduct.setEnabled(false);
		}else {
			theProduct.setEnabled(true);
			}
	}
	
	@Override
	public void  forceDeleteById(Product theProduct){
		
		repositoryImpl.delete(theProduct);
				
	}

}
