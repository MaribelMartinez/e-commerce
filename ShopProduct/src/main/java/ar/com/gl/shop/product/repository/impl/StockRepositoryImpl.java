package ar.com.gl.shop.product.repository.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;
import ar.com.gl.shop.product.repository.datasource.StockDatasource;

public class StockRepositoryImpl implements Serializable,StockRepository {

	private static final long serialVersionUID = 3876426318410983253L;
	private static StockRepositoryImpl INSTANCE;
	private StockDatasource stockDatasource = StockDatasource.getInstance();
	private  List<Stock> list;
	
	private StockRepositoryImpl() {
		list = new ArrayList<Stock>();
	}
	
	public static StockRepositoryImpl getInstance() {
		if(Objects.isNull(INSTANCE)) {
			INSTANCE = new StockRepositoryImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public Stock save(Stock stock) {
		
		return stockDatasource.create(stock);
	}
	
	@Override
	public Stock findById(Long id) {
		
		return stockDatasource.findById(id);
	}
	
	//no deberia existir
	@Override
	public List<Stock> getAll() {
		return list;
	}
	
	@Override
	public Stock update(Stock stock) {
		
		return stockDatasource.update(stock);
	}


	@Override
	public Stock softDeleteStock(Stock stock) {		
		return stockDatasource.softDelete(stock);
	}
	
	@Override
	public Stock delete(Stock stock) {
		return stockDatasource.delete(stock);		
	}
}
