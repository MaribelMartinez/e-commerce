package ar.com.gl.shop.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import ar.com.gl.shop.product.exceptions.ItemNotFound;
import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.repository.Repository;
import ar.com.gl.shop.product.repositoryimpl.RepositoryImpl;
import ar.com.gl.shop.product.services.CategoryService;
import ar.com.gl.shop.product.services.ProductService;
import ar.com.gl.shop.product.servicesimpl.CategoryServiceImpl;
import ar.com.gl.shop.product.servicesimpl.ProductServiceImpl;



public class AppTest{
	
	static Repository repository;
	static CategoryService categoryService;
	static ProductService productService;
	static Category category;
	static Product product;
	static Stock stock;
	static ItemNotFound itemNotFound;

	
	@BeforeEach
	void beforeEachTest() {
		repository = new RepositoryImpl();
		categoryService = new CategoryServiceImpl();
		productService = new ProductServiceImpl();
		category = new Category();
		product = new Product();
		stock = new Stock();
		
	}
	
	
	
	
	@Nested
	@DisplayName("Exception")
	class CustomException{
		@Test
		@DisplayName("Custom exception")
		void testCustomException() {
			itemNotFound = new ItemNotFound("Error");
			try {
				throw itemNotFound;
			} catch (ItemNotFound e) {
				assertEquals("Error", e.getMessage());
			}
		}
		
		@Test
		@DisplayName("Custom exception no Arg constructor")
		void testCustomExceptionNoArgCosntructor() {
			itemNotFound = new ItemNotFound();
			try {
				throw itemNotFound;
			} catch (ItemNotFound e) {
				assertEquals("Item no encontrado", e.getMessage());
			}
		}
		
	}
	
	
	
	@Nested
	@DisplayName("Testeo de modelos")
	class ModelsTest{
		
		
		@Nested
		@DisplayName("Metodos clase repository")
		class RepositoryTest{
			
			
			
		}

		
		@Nested
		@DisplayName("Metodos clase Category")
		class CategoryTest{
			
			
			@Test
			@DisplayName("Constructor with id arg")
			void constructorWithId() {
				
				Long id = 1l;
				category = new Category(id);
				
				assertEquals(id, category.getId());
			}
			
			@Test
			@DisplayName("Constructor")
			void categoryConstructor() {
				
				Long id = 1l;
				String name = "Nombre categoria";
				String description = "Una descripcion";
				Category theCategory = new Category(id, name, description);
				
				assertEquals(id + name + description, theCategory.getId() + theCategory.getName() + theCategory.getDescription());
				
			}

			
			@Test
			@DisplayName("Setter, Getter and toString")
			void categorySetAndGetName() {
				
				Long id = 1l;
				category.setId(id);
				
				String name = "Nombre Generico";				
				category.setName(name);
				
				String description = "Descripcion generica";				
				category.setDescription(description);
				

				
				String expected = "Categoria ---> ID: "+ category.getId() + ""
						 + "\n               Nombre: " + category.getName() + ""
						 + "\n               Descripción: " + category.getDescription() + "\n";
				String actual = category.toString();
				
				assertEquals(expected, actual);
			}
			
			@Test
			@DisplayName("Set and Get enabled")
			void setAndGetEnabled() {
				
				Boolean enabled = true;				
				category.setEnabled(enabled);
				
				assertEquals(enabled, category.getEnabled());
				
			}
			

			
			
			
		}

		@Nested
		@DisplayName("Metodos clase Product")
		class ProductTest{
			
			
			@Test
			@DisplayName("Setter, Getter and toString")
			void settersAndGettersAndToString() {
				
				Long id = 1l;				
				product.setId(id);
				
				String name = "Nombre Generico";				
				product.setName(name);
				
				String description = "Descripcion generica";				
				product.setDescription(description);
				
				Double price = 20.0;				
				product.setPrice(price);
				
				product.setStock(stock);
				
				product.setCategory(category);
				
				String expect = "Producto ---> ID: "+ product.getId() +"\n"
						  + "              Nombre: " + product.getName() + "\n"
						  + "              Descripción: " + product.getDescription() + "\n"
						  + "              Precio: $" + product.getPrice() + "\n"
						  + "              Stock: " + product.getStock().getQuantity() + "\n"
						  + "              Locacion: " + product.getStock().getLocationCode() + "\n"
						  + "              Categoria: " + product.getCategory().getName();
				
				String actual = product.toString();
				
				assertEquals(expect, actual);

				
				
			}
			
			@Test
			@DisplayName("Constructor")
			void productCosntructor() {
				
				Long id = 1l;
				String name = "Nombre Generico";
				String description = "Descripcion generica";
				Boolean enabled = true;
				Double price = 20.0;
				
				product = new Product(id, name, description, price, category);
				
				assertEquals(id + name + description + enabled + price + category, 
						product.getId() + product.getName() + product.getDescription() + 
						product.getEnabled() + product.getPrice() + product.getCategory());
			}
			
			@Test
			@DisplayName("Set and Get enabled")
			void setAndGetEnabled() {
				
				Boolean enabled = true;				
				product.setEnabled(enabled);
				
				assertEquals(enabled, product.getEnabled());
				
			}
			
			
			
		}
		
		@Nested
		@DisplayName("Metodos clase Stock")
		class StockTest{

			
			@Test
			@DisplayName("setName y getName")
			void categorySetAndGetLocation() {
				
				String location = "Locacion Generica";
				
				stock.setLocationCode(location);
				
				assertEquals(location, stock.getLocationCode());
			}
			
			@Test
			@DisplayName("setQuantity y getQuantity")
			void categorySetAndGetQuantity() {
				
				Integer quantity = 50;
				
				stock.setQuantity(quantity);
				
				assertEquals(quantity, stock.getQuantity());
			}
			
			
			
		
		
		
	}


	
	
	}
}



