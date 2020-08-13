package ar.com.gl.shop.product.servicesimpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.services.DataSource;

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
	
	public Product create(Product product) {
		
		String name = product.getName();
		String description = product.getDescription();
		Double price = product.getPrice();
		Long stockId = product.getStock().getId();
		Long categoryId = product.getCategory().getId();
		
		final String query = "INSERT INTO product (name, description, price, stock, category, enabled) "
				+ "values ('"+ name +"', '" + description +"','"+price+"', "+stockId+", "+categoryId+", 1);";
		
		
		return (Product)connectionPost(query, product);
	}
	
	public Product findById(final long id){
		
		final String query = "SELECT * FROM product WHERE id=" + id + ";";		
		
		return (Product)connectionGet(query);
	}
	
	public List<Product> findAll(){		
		
		final String query = "SELECT * FROM product";
		
		List<Resources> resourcesList = connectionGetAll(query);
		
		List<Product> categorylist = new ArrayList<>();		
						
		for (Resources product : resourcesList) {
			categorylist.add((Product) product);
		}
		
		return categorylist;
	}
	

	
	public Product update(Product product) {
		
		Long id = product.getId();
		String name = product.getName();
		String description = product.getDescription();
		Double price = product.getPrice();
		Long categoryId = product.getCategory().getId();
		
		
		final String query = "UPDATE product set name ='"+name+"', description='"+description+"'"
				+ ", price='"+price+"', category='"+categoryId+"' where id="+id+";";
		
		return (Product)connectionPost(query, product);
	}
	
	public Product softDelete(Product product) {
		
		Long id = product.getId();
		
		Integer enabled = product.getEnabled() ? 0 : 1;
		
		final String query = "UPDATE product set enabled = "+enabled+" where id="+id+";";		
		
		return (Product)connectionPost(query, product);
	}
	
	public Product delete(Product product) {
		
		Long id = product.getId();		
		
		final String query = "DELETE FROM product where id="+id+";";
		
		connectionPost(query, product);
		
		return product;
	}
	
	@Override
	public Product setAttributesFromQueryResult(ResultSet resultSet) throws SQLException {		
		
		Product theProduct = new Product();
		
		theProduct.setId(resultSet.getLong("id"));
		theProduct.setName(resultSet.getString("name"));
		theProduct.setDescription(resultSet.getString("description"));
		theProduct.setPrice(resultSet.getDouble("price"));
		theProduct.setStock(stockDataSource.findById(resultSet.getLong("stock")));
		
		Boolean enabled = resultSet.getInt("enabled") == 1  ? true : false;
		
		theProduct.setEnabled(enabled);;
		
		theProduct.setCategory(categoryDataSource.findById(resultSet.getLong("category")));
		
		return theProduct;
	}

}
