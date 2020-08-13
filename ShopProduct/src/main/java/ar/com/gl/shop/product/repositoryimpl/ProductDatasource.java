package ar.com.gl.shop.product.repositoryimpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.model.Stock;

public class ProductDatasource extends DataSource{
	
	private static ProductDatasource instance;
	private CategoryDatasource categoryDataSource = CategoryDatasource.getInstance();
	private StockDatasource stockDataSource = StockDatasource.getInstance();
	
	public static ProductDatasource getInstance() {
		if(Objects.isNull(instance)) {
			instance = new ProductDatasource();
		}
		
		return instance;
	}
	
	public Product getById(final long id){
		
		Resources theProduct = new Product();
		
		final String query = "SELECT * FROM product WHERE id=" + id + ";";		
		
		return (Product)connection(query, theProduct);
	}
	
	public List<Product> getAll(){
		
		List<Resources> resourcesList = new ArrayList<>();
		
		Resources theProduct = new Product();
		
		final String query = "SELECT * FROM product";
		
		List<Product> categorylist = new ArrayList<>();		
						
		for (Resources product : connection(query, theProduct, resourcesList)) {
			categorylist.add((Product) product);
		}
		
		return categorylist;
	}
	
	public Product createProduct(Product product) {
		
		String name = product.getName();
		String description = product.getDescription();
		Double price = product.getPrice();
		Long stockId = product.getStock().getId();
		Long categoryId = product.getCategory().getId();
		
		final String query = "INSERT INTO product (name, description, price, stock, category, enabled) "
				+ "values ('"+ name +"', '" + description +"','"+price+"', "+stockId+", "+categoryId+", 1);";
		
		
		return (Product)connectionCreate(query, product);
	}
	
	public Product updateProduct(Product product) {
		
		Long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		Double price = product.getPrice();
		Long categoryId = product.getCategory().getId();
		
		
		final String query = "UPDATE product set name ='"+name+"', description='"+description+"'"
				+ ", price='"+price+"', category='"+categoryId+"' where id="+id+";";
		
		return (Product)connectionCreate(query, product);
	}
	
	public Product deleteProduct(Product product) {
		
		Long id = product.getId();
		
		Integer enabled = product.getEnabled() ? 0 : 1;
		
		final String query = "UPDATE product set enabled = "+enabled+" where id="+id+";";		
		
		return (Product)connectionCreate(query, product);
	}
	
	public Product forceDeleteProduct(Product product) {
		
		Long id = product.getId();		
		
		stockDataSource.forceDeleteStock(product.getStock());
		
		final String query = "DELETE FROM product where id="+id+";";
		
		connectionCreate(query, product);
		
		return product;
	}
	
	@Override
	public Product setAttributesForSqlReturnedObject(ResultSet resultSet) throws SQLException {		
		
		Product theProduct = new Product();
		
		theProduct.setId(resultSet.getLong("id"));
		theProduct.setName(resultSet.getString("name"));
		theProduct.setDescription(resultSet.getString("description"));
		theProduct.setPrice(resultSet.getDouble("price"));
		theProduct.setStock((Stock)stockDataSource.getById(resultSet.getLong("stock")));
		theProduct.setEnabled(resultSet.getBoolean("enabled"));
		theProduct.setCategory((Category)categoryDataSource.getById(resultSet.getLong("category")));
		
		return theProduct;
	}

}
