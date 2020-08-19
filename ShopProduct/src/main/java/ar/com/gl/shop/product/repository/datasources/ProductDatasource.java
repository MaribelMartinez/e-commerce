package ar.com.gl.shop.product.repository.datasources;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class ProductDatasource extends DBUtil{


	public static MysqlDataSource getProductDatasource() {
		return getDatasource();
	}
	
}
