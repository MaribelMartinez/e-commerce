package ar.com.gl.shop.product.servicesimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.repositoryimpl.RepositoryImpl;
import ar.com.gl.shop.product.repositoryimpl.StockRepositoryImpl;
import ar.com.gl.shop.product.services.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private RepositoryImpl repositoryImpl;
	
	private StockServiceImpl stockService;
	
	private StockRepositoryImpl stockRepositoryImpl = StockRepositoryImpl.getInstance();
	
	private Product theProduct;	
	
	private static ProductServiceImpl INSTANCE;
	
	private ProductServiceImpl() {
		
		repositoryImpl = new RepositoryImpl();
		stockService = StockServiceImpl.getInstance();
		
		theProduct = new Product();
	}
	
	public static ProductServiceImpl getInstance() {
		
		if (Objects.isNull(INSTANCE)) {
			return INSTANCE = new ProductServiceImpl();
		}
		
		return INSTANCE;
	}

	/*public RepositoryImpl getRepositoryImpl() {
		return repositoryImpl;
	}*/

	/*public List<Product> getTheProducts() {
		return repositoryImpl.findAllProduct();
	}*/

	/*public Product getTheProduct() {
		return theProduct;
	}*/

	@Override
	public Product create(Product product) {		
		
		return repositoryImpl.createProduct(product);
		
	}
	
	@Override
	public Product findById(Long id, Boolean searchEnable){	
		Product product = repositoryImpl.findProductById(id);	
		try {
			if(Objects.isNull(product)) {
				throw new ItemNotFound("No se encontró producto con este id");
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
	public List<Product> findAll() {	
		
		List<Product> productList = repositoryImpl.findAllProduct();	
		
		List<Product> theProducts = new ArrayList<>();
		
		
		
		for (int i = 0; i < productList.size(); i++) {
			
			theProduct = productList.get(i);
			
			if (theProduct.getEnabled()) {
				
				theProducts.add(theProduct);
			}
			
			
		}
		
		return theProducts;
	}
	
	public List<Product> findAllDisabled(){
		
		return repositoryImpl.findAllProduct();
	}
	

	@Override
	public Product update(Product product){
		
		return repositoryImpl.updateProduct(product);		
		
	}
	

	@Override
	public Product softDelete(Product theProduct){
		
		stockRepositoryImpl.softDeleteStock(theProduct.getStock());
		
		return repositoryImpl.softDeleteProduct(theProduct);
	}
	
	@Override
	public void  delete(Product theProduct){
		
		stockRepositoryImpl.delete(theProduct.getStock());
		
		repositoryImpl.deleteProduct(theProduct);
				
	}

}
