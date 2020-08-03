package ar.com.gl.shop.product.services;

import java.util.List;

import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.RepositoryImpl;

public class StockServiceImpl implements StockService{

	RepositoryImpl repository = new RepositoryImpl();
	
	@Override
	public Stock create(Stock stock) {
		
		if(stock != null) {
			repository.saveStock(stock);
			return stock;
		}else {
			return null;
		}
		
	}

	@Override
	public Boolean update(Stock stock) {
		
		if(stock.getId() != null) {
			
			if(existsStockById(stock.getId())) {
				
				Stock stockUpdate = findStockById(stock.getId());
				stockUpdate.setLocationCode(stock.getLocationCode());
				stockUpdate.setQuantity(stock.getQuantity());
				repository.saveStock(stockUpdate);
				
			}
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Boolean delete(Long id) {
		
		repository.deleteStock(findStockById(id));
		
		if (existsStockById(id)) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public Stock findStockById(Long id) {
		
		List<Stock> listStock = repository.findAllStock();
		Stock stock = null;
		
		for(int i = 0; i<listStock.size(); i++) {
			if(listStock.get(i).getId().equals(id)) {
				stock = listStock.get(i);
			}
		}
		
		return stock;
	}

	@Override
	public Boolean existsStockById(Long id) {
		
		List<Stock> listStock = repository.findAllStock();
		Boolean exists = false;
		
		for(Stock productList : listStock) {
			if(productList.getId().equals(id)) {
				exists = true;
			}
		}
		
		return exists;
	}

}
