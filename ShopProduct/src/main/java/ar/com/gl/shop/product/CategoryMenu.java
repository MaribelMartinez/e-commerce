package ar.com.gl.shop.product;

import java.util.List;
import java.util.Scanner;

import ar.com.gl.shop.product.model.Category;
import ar.com.gl.shop.product.services.CategoryServiceImpl;

public final class CategoryMenu {

	CategoryServiceImpl categoryService = new CategoryServiceImpl();

	public void mainMenuCategories() {

		Scanner input = new Scanner(System.in);
		Integer section = 0;

		while (section <= 3) {

			switch (section) {

			case 0:
				section = 0;
				System.out.println("==========CATEGORIAS==============");
				System.out.println("1. Nueva categoria\n2. Lista de categorias\n3.Menu principal ");
				System.out.println("Select an option:  ");
				section = input.nextInt();

				while (section < 0 || section > 3) {
					System.out.println("Ingrese un numero valido (1 - 2 - 3): ");
					section = input.nextInt();
				}

				break;

			case 1:
				section = createCategory();
				break;

			case 2:
				section = categoryList();
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

	
	public Integer createCategory() {

		Category category = categoryData();

		if (categoryService.create(category)) {
			System.out.println("------------------------------" + "\nCategoria creada:\n" + category);
			return 0;

		} else {
			System.err.println("Error creando categoria");
			return 1;
		}

	}

	
	public Category categoryData() {

		Scanner input = new Scanner(System.in);

		Category category = new Category();

		System.out.println("Name: ");
		String name = input.nextLine();
		category.setName(name);

		System.out.println("Description: ");
		String description = input.nextLine();
		category.setDescription(description);

		return category;
	}

	
	public Integer categoryList() {

		List<String> categories = categoryService.findAllCategory();

		if (categories.size() > 0) {

			for (String category : categories) {
				System.out.println(category);

			}

			Scanner input = new Scanner(System.in);
			Long id = input.nextLong();

			if (categoryService.existCategoryById(id)) {

				System.out.println("Modificar: 1, Deshabilitar:2, Eliminar: 3, Salir: Otro");
				Integer option = input.nextInt();

				switch (option) {

				case 1:
					Category category = categoryData();
					category.setId(id);
					categoryService.update(category);
					break;

				case 2:
					categoryService.disable(id);
					break;

				case 3:
					categoryService.delete(id);
					break;

				default:
					break;

				}
			} else {
				return 0;
			}

		} else {
			System.out.println("No se han registrado categorias");
		}

		return 0;
	}

}
