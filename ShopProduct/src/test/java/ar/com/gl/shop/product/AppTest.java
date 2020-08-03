package ar.com.gl.shop.product;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

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
				
				Integer quantity = 50;				
				stock.setQuantity(quantity);
				
				String expected = "Stock [id=" + 23 + ", quantity=" + quantity + ", locationCode=" + location + "]";
				String actual = stock.toString();
				
				assertEquals(expected, actual);
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
	
	@Nested
	@DisplayName("Testeo de services")
	class ServiceTest{
		
		@Nested
		@DisplayName("Category Service")
		class CategoryServiceTest{
			
			@Test
			@DisplayName("Create category test")
			void createCategoryTest() {
				Long id = 1l;
				String name = "Nombre generico";
				String description = "Descripcion generica";
				
				categoryService.create(id, name, description);
				
				category = categoryService.findOneByiD(id, true);
				
				assertEquals(id + name + description, category.getId() + category.getName() + category.getDescription());
				
			}
			
			@Test
			@DisplayName("Crear primeros objetos")
			void createFirstObjects() {
				categoryService.agregarPrimerosObjetos();
				Category theCategories[] = new Category[3];
				
				theCategories[0]= categoryService.findOneByiD(1l, true);
				theCategories[1]= categoryService.findOneByiD(2l, true);
				theCategories[2]= categoryService.findOneByiD(3l, true);
				
				assertArrayEquals(theCategories, repository.findAllCategory().toArray(), "deberian ser los mismos objetos");
				
			}
			
			@Nested
			@DisplayName("Find one By id method")
			class FindOneByIdMethod{
				
				@Test
				@DisplayName("using true as a param")
				void findOneByIdTrueTest() {
					
					Long id = 1l;
					category = new Category(id);
					
					repository.saveCategory(category);
					
					Category theCategory = categoryService.findOneByiD(id, true);
					
					assertTrue(category.equals(theCategory), "deberían ser el mismo objeto");				
					
				}
				
				@Test
				@DisplayName("using false as param")
				void findOneByIdFalseTest() {
					
					Long id = 1l;
					category = new Category(id);
					
					category.setEnabled(false);
					
					repository.saveCategory(category);
					
					Category theCategory = categoryService.findOneByiD(id, false);				
					
					assertTrue(category.equals(theCategory), "deberían ser el mismo objeto");				
					
				}
				
				@Test
				@DisplayName("Find one by id exception")
				void findOneByIdExceptionTest() {
					
					Long id = 1l;
					category = new Category(id);
					
					category.setEnabled(true);
					
					repository.saveCategory(category);
					
					Category theCategory = categoryService.findOneByiD(2l, true);
					
					assertNull(theCategory, "debería retornar null si no se encuentra");

				}
				
			}
			
		 @Nested
		 @DisplayName("find all method")
		 class findAllMethod{
			 
			 @Test
			 @DisplayName("find all true as param")
			 void findAllTrue() {
				 
				 List<Category> categoryList = new ArrayList<>();
				  				 
				 category = new Category(1l);
				 categoryList.add(category);
				 repository.saveCategory(category);
				 category = new Category(2l);
				 categoryList.add(category);
				 repository.saveCategory(category);
				 
				 
				 
				 assertArrayEquals(categoryList.toArray(), categoryService.findAll(true).toArray());				 
				 
			 }
			 
			 @Test
			 @DisplayName("find all false as param")
			 void findAllFlase() {
				 
				 List<Category> categoryList = new ArrayList<>();
				  				 
				 category = new Category(1l);
				 category.setEnabled(false);
				 categoryList.add(category);
				 repository.saveCategory(category);
				 
				 category = new Category(2l);
				 category.setEnabled(false);
				 categoryList.add(category);
				 repository.saveCategory(category);
				 
				 assertArrayEquals(categoryList.toArray(), categoryService.findAll(false).toArray(), "Deberian contener los mismos objetos");				 
				 
			 }
			 
		 }
		 
		 @Test
		 @DisplayName("Update by id")
		 void updateByIdTest() {
			 
			 Long id = 1l;
			 
			 category = new Category(id, "nombre generico", "descripcion generica");
			 repository.saveCategory(category);
			 
			 String newName= "Nombre generico cambiado";
			 
			 category = new Category(id, newName, "descripcion generica");
			 
			 categoryService.updateById(category);
			 
			 assertEquals(newName, categoryService.findOneByiD(id, true).getName());
			 
		 }
		 
		 @Nested
		 @DisplayName("Delete By Id Method")
		 class DeleteByIdMethod{
			 
			 @Test
			 @DisplayName("if enabled = true")
			 void deleteByIdTrue() {
				 
				 Boolean bool = true;
				 Long id = 1l;
				 
				 category = new Category(id);
				 category.setEnabled(bool);
				 repository.saveCategory(category);
				 
				 categoryService.deleteById(categoryService.findOneByiD(id, false));
				 
				 assertNull(categoryService.findOneByiD(id, true), "Tiene que devolver null si es eliminado corectamente");
				 
				 
			 }
			 
			 @Test
			 @DisplayName("if enabled = false")
			 void deleteByIdFalse() {
				 
				 Boolean bool = false;
				 Long id = 1l;
				 
				 category = new Category(id);
				 category.setEnabled(bool);
				 repository.saveCategory(category);
				 
				 categoryService.deleteById(categoryService.findOneByiD(id, false));
				 
				 assertNotNull(categoryService.findOneByiD(id, true), "Tiene que retornar un objeto si se recupero correctamente");
				 
				 
			 }
		 }
		 
		 @Test
		 @DisplayName("Force delete, delete fisico")
		 void forceDelete() {
			 
			 Boolean bool = false;
			 Long id = 1l;
			 
			 category = new Category(id);
			 category.setEnabled(bool);
			 repository.saveCategory(category);
			 
			 categoryService.forceDeleteById(categoryService.findOneByiD(id, false));
			 
			 assertNull(categoryService.findOneByiD(id, false), "Tiene que retornar un objeto si se recupero correctamente");
			 
		 }

		}
		
		@Nested
		@DisplayName("Product Service")
		class ProductServiceTest{
			
			@Test
			@DisplayName("Create product test")
			void createProductTest() {
				Long id = 1l;
				String name = "Nombre generico";
				String description = "Descripcion generica";
				Double price = 20d;
				
				product = new Product(id, name, description, price, category);
				
				productService.create(product);
				
				product = productService.findOneByiD(id, true);
				
				assertEquals(id + name + description + price + category, 
						product.getId() + product.getName() + product.getDescription() + product.getPrice() + product.getCategory());
				
			}
			
			
			@Nested
			@DisplayName("Find one By id method")
			class FindOneByIdMethod{
				
				@Test
				@DisplayName("using true as a param")
				void findOneByIdTrueTest() {
					
					Long id = 1l;
					product = new Product(id);
					
					repository.saveProduct(product);
					
					Product theProduct = productService.findOneByiD(id, true);
					
					assertTrue(product.equals(theProduct), "deberían ser el mismo objeto");				
					
				}
				
				@Test
				@DisplayName("using false as param")
				void findOneByIdFalseTest() {
					
					Long id = 1l;
					product = new Product(id);
					
					repository.saveProduct(product);
					
					Product theProduct = productService.findOneByiD(id, false);
					
					assertTrue(product.equals(theProduct), "deberían ser el mismo objeto");			
					
				}
				
				@Test
				@DisplayName("Find one by id exception")
				void findOneByIdExceptionTest() {
					
					Long id = 1l;
					product = new Product(id);
					
					product.setEnabled(true);
					
					repository.saveProduct(product);
					
					Product theProduct = productService.findOneByiD(2l, false);
					
					assertNull(theProduct, "debería retornar null si no se encuentra");

				}
				
			}
			
		 @Nested
		 @DisplayName("find all method")
		 class findAllMethod{
			 
			 @Test
			 @DisplayName("find all true as param")
			 void findAllTrue() {
				 
				 List<Product> productList = new ArrayList<>();
				  				 
				 product = new Product(1l);
				 productList.add(product);
				 repository.saveProduct(product);
				 product = new Product(2l);
				 productList.add(product);
				 repository.saveProduct(product);
				 
				 
				 
				 assertArrayEquals(productList.toArray(), productService.findAll(true).toArray(), "Deberian contener los mismos objetos");				 
				 
			 }
			 
			 @Test
			 @DisplayName("find all false as param")
			 void findAllFlase() {
				 
				 List<Product> productList = new ArrayList<>();
  				 
				 product = new Product(1l);
				 product.setEnabled(false);
				 productList.add(product);				 
				 repository.saveProduct(product);
				 
				 product = new Product(2l);
				 product.setEnabled(false);
				 productList.add(product);
				 repository.saveProduct(product);
				 
				 
				 
				 assertArrayEquals(productList.toArray(), productService.findAll(false).toArray(), "Deberian contener los mismos objetos");				 
				 
			 }
			 
		 }
		 
		 @Test
		 @DisplayName("Update by id")
		 void updateByIdTest() {
			 
			Long id = 1l;
			String name = "Nombre generico";
			String description = "Descripcion generica";
			Double price = 20d;
			 
			 product = new Product(id, name, description, price, category);
			 repository.saveProduct(product);
			 
			 String newName= "Nombre generico cambiado";
			 
			 product = new Product(id, newName, description, price, category);
			 
			 productService.updateById(product);
			 
			 assertEquals(newName, productService.findOneByiD(id, true).getName());
			 
		 }
		 
		 @Nested
		 @DisplayName("Delete By Id Method")
		 class DeleteByIdMethod{
			 
			 @Test
			 @DisplayName("if enabled = true")
			 void deleteByIdTrue() {
				 
				 Boolean bool = true;
				 Long id = 1l;
				 
				 product = new Product(id);
				 product.setEnabled(bool);
				 repository.saveProduct(product);
				 
				 productService.deleteById(productService.findOneByiD(id, false));
				 
				 assertNull(productService.findOneByiD(id, true), "Tiene que devolver null si es eliminado corectamente");
				 
				 
			 }
			 
			 @Test
			 @DisplayName("if enabled = false")
			 void deleteByIdFalse() {
				 
				 Boolean bool = false;
				 Long id = 1l;
				 
				 product = new Product(id);
				 product.setEnabled(bool);
				 repository.saveProduct(product);
				 
				 productService.deleteById(productService.findOneByiD(id, false));
				 
				 assertNotNull(productService.findOneByiD(id, true), "Tiene que retornar un Product si se recupero correctamente");				 
				 
			 }
		 }
		 
		 @Test
		 @DisplayName("Force delete, delete fisico")
		 void forceDelete() {
			 
			 Boolean bool = false;
			 Long id = 1l;
			 
			 product = new Product(id);
			 product.setEnabled(bool);
			 repository.saveProduct(product);
			 
			 productService.forceDeleteById(productService.findOneByiD(id, false));
			 
			 assertNull(productService.findOneByiD(id, false), "Tiene que retornal null");
			 
		 }

		}
		

	}
}



