package ar.com.gl.shop.product;

import java.util.Scanner;

public final class MainMenu {

	public static void menu() {
		
		Scanner input = new Scanner(System.in);
		Integer section = 0;
		
		while (section != 3) {
			
			switch (section) {
			
			case 0:
				System.out.println("==========MENU==============");
				System.out.println("1. Categorias \n2. Productos");
				section = input.nextInt();
				
				while (section != 1 && section != 2) {
					System.out.println("Ingrese un numero valido (1 or 2): ");
					section = input.nextInt();
				}
				
				break;
				
			case 1:
				
				CategoryMenu categoryMenu = new CategoryMenu();
				categoryMenu.mainMenuCategories();
				break;
				
			case 2:
				
				ProductMenu productMenu = new ProductMenu();
				productMenu.mainMenuProduct();
				break;

			default:
				break;
			}
		}
		
		input.close();
	}
}
