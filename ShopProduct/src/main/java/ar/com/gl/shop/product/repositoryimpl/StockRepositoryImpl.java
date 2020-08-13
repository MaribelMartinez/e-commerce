package ar.com.gl.shop.product.repositoryimpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;

public class StockRepositoryImpl implements Serializable,StockRepository {

	private static final long serialVersionUID = 3876426318410983253L;
	private static StockRepositoryImpl INSTANCE;
	private StockDatasource stockDatasource = StockDatasource.getInstance();
	private  List<Stock> list;
	
	private StockRepositoryImpl() {
		list = new ArrayList<Stock>();
	}
	
	public static StockRepositoryImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new StockRepositoryImpl();
		}
		return INSTANCE;
	}
	
	@Override
	public Stock save(Stock stock) {
		
		return stockDatasource.createStock(stock);
	}
	
	@Override
	public Stock update(Stock stock) {
		
		return stockDatasource.updateStock(stock);
	}

	@Override
	public List<Stock> getAll() {
		return list;
	}
	
	@Override
	public void delete(Stock stock) {
		list.remove(stock);
	}
	
	@Override
	public Stock getById(Long id) {
		for (Stock stock : list) {
			if (stock.getId().equals(id)) {
				return stock;
			}
		}
		return null;
	}
}
