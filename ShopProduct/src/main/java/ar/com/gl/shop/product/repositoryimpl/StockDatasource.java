package ar.com.gl.shop.product.repositoryimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Resources;
import ar.com.gl.shop.product.model.Stock;

public class StockDatasource extends DataSource{
	
	private static StockDatasource instance;
	
	public static StockDatasource getInstance() {
		if(Objects.isNull(instance)) {
			instance = new StockDatasource();
		}
		
		return instance;
	}
	
	public Resources getById(final long id){
		
		Resources theStock = new Stock();
		
		final String query = "SELECT * FROM stock WHERE id=" + id + ";";
		
		return connection(query, theStock);
	}
	
	public Stock createStock(Stock stock) {
		
		Integer quantity = stock.getQuantity();
		String locationCode = stock.getLocationCode();
		
		final String query = "INSERT INTO stock (quantity, locationCode, enabled) "
				+ "values ('"+ quantity +"', '" + locationCode +"', 1);";
		
		
		return (Stock)connectionCreate(query, stock);
	}
	
	public Stock updateStock(Stock stock) {
		
		Long id = stock.getId();
		Integer quantity = stock.getQuantity();
		String locationCode = stock.getLocationCode();
		
		final String query = "UPDATE stock set quantity ='"+quantity+"', locationCode='"+locationCode+"' where id="+id+";";
		
		return (Stock)connectionCreate(query, stock);
	}
	
	public Stock forceDeleteStock(Stock Stock) {
		
		Long id = Stock.getId();
		
		final String query = "DELETE FROM stock where id="+id+";";
		
		connectionCreate(query, Stock);
		
		return Stock;
	}


	@Override
	public Stock setAttributesForSqlReturnedObject(ResultSet resultSet) throws SQLException {		
		
		Stock theStock = new Stock();
		
		theStock.setId(resultSet.getLong("id"));
		theStock.setQuantity(resultSet.getInt("quantity"));
		theStock.setLocationCode(resultSet.getString("locationCode"));
		theStock.setEnabled(resultSet.getBoolean("enabled"));
		
		return theStock;

	}

	
}
