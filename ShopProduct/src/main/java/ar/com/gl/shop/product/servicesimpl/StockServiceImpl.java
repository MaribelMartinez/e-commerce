package ar.com.gl.shop.product.servicesimpl;

import java.util.Objects;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.StockRepository;
import ar.com.gl.shop.product.repositoryimpl.StockRepositoryImpl;
import ar.com.gl.shop.product.services.StockService;

public class StockServiceImpl implements StockService {
	
    private StockRepository stockRepositoryImpl;
	
	private Stock theStock;	
	
	private static StockServiceImpl INSTANCE;
	
	
	private StockServiceImpl() {
		
		stockRepositoryImpl = StockRepositoryImpl.getInstance();
		theStock = new Stock();
	}
	
	public static StockServiceImpl getInstance() {
		
		if (Objects.isNull(INSTANCE)) {
			return INSTANCE = new StockServiceImpl();
		}
		
		return INSTANCE;
	}
	
	@Override
	public Stock create(Stock stock){
		return stockRepositoryImpl.save(stock);
    }

	
	@Override
	public Stock findById(Long id, Boolean searchEnable){	
		Stock stock = stockRepositoryImpl.findById(id);	
		try {
			if(Objects.isNull(stock)) {
				throw new ItemNotFound("No se encontró stock con este id");
			}
			if(searchEnable) {
				stock = stock.getEnabled() ? stock : null;
			}			
		}catch (ItemNotFound e) {
			System.out.println(e.getMessage());	
		}
		return stock;		
	}
	
	
	@Override
	public Stock update(Stock stock){	
		
		return stockRepositoryImpl.update(stock);	
	}

	@Override
	public void delete(Stock stock){
		stockRepositoryImpl.delete(stock);
	}


	@Override
	public Stock softDelete(Stock stock){

		return stockRepositoryImpl.softDeleteStock(stock);
	}


}
