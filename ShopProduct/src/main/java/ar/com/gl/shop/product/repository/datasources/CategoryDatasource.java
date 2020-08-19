package ar.com.gl.shop.product.repository.datasources;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class CategoryDatasource extends DBUtil{


	public static MysqlDataSource getCategoryDatasource() {
		return getDatasource();
	}
}
