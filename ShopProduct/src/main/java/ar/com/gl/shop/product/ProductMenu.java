package ar.com.gl.shop.product;

import java.util.List;
import java.util.Scanner;

import ar.com.gl.shop.product.model.Product;
import ar.com.gl.shop.product.model.Stock;
import ar.com.gl.shop.product.services.CategoryServiceImpl;
import ar.com.gl.shop.product.services.ProductServiceImpl;

public class ProductMenu {

	ProductServiceImpl productService = new ProductServiceImpl();
	CategoryServiceImpl categoryService = new CategoryServiceImpl();

	public void mainMenuProduct() {

		Scanner input = new Scanner(System.in);
		Integer section = 0;

		while (section <= 3) {

			switch (section) {

			case 0:
				section = 0;
				System.out.println("==========PRODUCTOS==============");
				System.out.println("1. Nuevo producto\n2. Lista de productos\n3.Menu principal ");
				section = input.nextInt();
				while (section < 0 || section > 3) {
					System.out.println("Ingresar un numero valido (1 - 2 - 3): ");
					section = input.nextInt();
				}
				break;

			case 1:
				section = createProduct();
				break;

			case 2:
				section = productList();
				break;

			case 3:
				MainMenu.menu();
				break;

			default:
				MainMenu.menu();
				break;
			}
		}

		input.close();
	}

	public Product productData() {

		List<String> categories = categoryService.findAllCategory();
		Product product = new Product();

		if (categories.size() > 0) {

			categoryService.findEnabledCategories();
			Scanner input = new Scanner(System.in);

			System.out.println("Product name: ");
			String name = input.nextLine();
			product.setName(name);

			System.out.println("Product description: ");
			String description = input.nextLine();
			product.setDescription(description);

			System.out.println("Product price: ");
			Double price = input.nextDouble();
			product.setPrice(price);

			System.out.println("Product category: ");

			for (String category : categories) {
				System.out.println(category);
			}

			Long categoryId = input.nextLong();
			product.setCategory(categoryService.findCategoryById(categoryId));

			return product;
		} else {

			System.err.println("Primero crear categorias");
			return null;
		}

	}

	public Integer createProduct() {
		Product product = productData();
		if (product == null) {
			return 3;
		}
		if (productService.create(product)) {
			return 0;
		} else {
			System.err.println("Error creando productos");
			return 1;
		}

	}

	public Integer productList() {

		List<String> products = productService.findAllProduct();

		if (products.size() > 0) {

			for (String product : products) {
				System.out.println(product);
			}

			Scanner input = new Scanner(System.in);
			Long id = input.nextLong();

			if (productService.existsProductById(id)) {

				System.out.println("Modificar: 1, Deshabilitar:2, Eliminar: 3, Stock: 4, Salir: Otro");
				Integer option = input.nextInt();

				switch (option) {

				case 1:
					Product product = productData();
					product.setId(id);
					productService.update(product);
					break;

				case 2:
					productService.disable(id);
					break;

				case 3:
					productService.delete(id);
					break;

				case 4:
					Product prod = productService.findProductById(id);
					prod.setStock(stockData());
					productService.updateStock(prod);
					break;

				default:
					break;
				}

			} else {
				return 0;
			}

		} else {
			System.out.println("No se han registrado productos");
		}

		return 0;
	}

	public Stock stockData() {

		Stock stock = new Stock();
		Scanner input = new Scanner(System.in);

		System.out.println("Quantity: ");
		Integer quantity = input.nextInt();
		stock.setQuantity(quantity);

		System.out.println("Location code: ");
		String locationCode = input.next();
		stock.setLocationCode(locationCode);

		return stock;

	}

}
