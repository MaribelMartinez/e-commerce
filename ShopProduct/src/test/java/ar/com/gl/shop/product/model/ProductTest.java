package ar.com.gl.shop.product.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProductTest {

	Product product;
	private static final Long id = 1l;
	private static final String name = "Camara";
	private static final String description = "Nikon";
	private static final Double price = 40000.0;
	private static final Stock stock = new Stock();
	private static final Category category = new Category();
	
	@BeforeEach
	void setup() {
		product = new Product();
		product.setId(id);
	}
	
	@Test
	@DisplayName(value = "Test product id")
	void test() {
		assertEquals(1l,product.getId());
	}

}
