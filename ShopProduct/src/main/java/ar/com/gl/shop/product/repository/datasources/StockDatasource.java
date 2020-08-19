package ar.com.gl.shop.product.repository.datasources;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class StockDatasource extends DBUtil{


	public static MysqlDataSource getStockDatasource() {
		return getDatasource();
	}
	

}
