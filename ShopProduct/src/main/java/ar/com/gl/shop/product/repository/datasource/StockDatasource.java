package ar.com.gl.shop.product.repository.datasource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import ar.com.gl.shop.product.model.Stock;

public class StockDatasource extends DataSource{
	
	private static StockDatasource instance;
	
	public static StockDatasource getInstance() {
		if(Objects.isNull(instance)) {
			instance = new StockDatasource();
		}
		
		return instance;
	}
	
	public Stock create(Stock stock) {
		
		Integer quantity = stock.getQuantity();
		String locationCode = stock.getLocationCode();
		
		final String query = "INSERT INTO stock (quantity, locationCode, enabled) "
				+ "values ('"+ quantity +"', '" + locationCode +"', 1);";
		
		
		return (Stock)connectionPost(query, stock);
	}
	
	public Stock findById(final long id){
		
		final String query = "SELECT * FROM stock WHERE id=" + id + ";";
		
		return (Stock)connectionGet(query);
	}	

	
	public Stock update(Stock stock) {
		
		Long id = stock.getId();
		Integer quantity = stock.getQuantity();
		String locationCode = stock.getLocationCode();
		
		final String query = "UPDATE stock set quantity ='"+quantity+"', locationCode='"+locationCode+"' where id="+id+";";
		
		return (Stock)connectionPost(query, stock);
	}
	
	public Stock softDelete(Stock stock) {
		
		Long id = stock.getId();
		
		Integer enabled = stock.getEnabled() ? 0 : 1;
		
		final String query = "UPDATE stock set enabled = "+enabled+" where id="+id+";";		
		
		return (Stock)connectionPost(query, stock);
	}
	
	public Stock delete(Stock Stock) {
		
		Long id = Stock.getId();
		
		final String query = "DELETE FROM stock where id="+id+";";
		
		connectionPost(query, Stock);
		
		return Stock;
	}

	@Override
	public Stock setAttributesFromQueryResult(ResultSet resultSet) throws SQLException {		
		
		Stock theStock = new Stock();
		
		theStock.setId(resultSet.getLong("id"));
		theStock.setQuantity(resultSet.getInt("quantity"));
		theStock.setLocationCode(resultSet.getString("locationCode"));
		
		Boolean enabled = resultSet.getInt("enabled") == 1  ? true : false;		
		theStock.setEnabled(enabled);;
		
		return theStock;

	}

	
}
