package ar.com.gl.shop.product;

import java.util.Scanner;
import ar.com.gl.shop.product.model.*;
import ar.com.gl.shop.product.services.*;


public class App 
{
	private static CategoryServiceImpl categoryService;
	private static ProductServiceImpl productService;
	private static StockServiceImpl stockService;
	private static Scanner keyboard;
	
    public static void main( String[] args )
    {
    	 categoryService = new CategoryServiceImpl();
    	 productService = new ProductServiceImpl();
    	 stockService = new StockServiceImpl();
    	 keyboard = new Scanner(System.in);
    	
    	while(true)
    	{
    		switch(menu()) 
    		{
    		case 1: { 
    			inputCategoryData(false);
    			break;}
    		case 2: { 
    			inputProductData(false);
    			break;}
    		case 3: { 
    			inputStockData(false);
    			break;}
    		case 4: { 
    			outputCategoryData();
    			break;}
    		case 5: { 
    			outputProductData();
    			break;}
    		case 6: { 
    			outputStockData();
    			break;}
    		case 7: { 
    			inputCategoryData(true);
    			break;}
    		case 8: { 
    			inputProductData(true);
    			break;}
    		case 9: { 
    			inputStockData(true);
    			break;}
    		case 10: { 
    			System.out.println("Ingrese el id de la categoria que desea borrar");
    			if(categoryService.deleteCategory(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 11: { 
    			System.out.println("Ingrese el id del producto que desea borrar");
    			if(productService.deleteProduct(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 12: { 
    			System.out.println("Ingrese el id del stock que desea borrar");
    			if(stockService.deleteStock(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 13: { 
    			System.out.println("Ingrese el id de la categoria que desea borrar");
    			if(categoryService.deleteCategoryLogically(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 14: { 
    			System.out.println("Ingrese el id  del producto que desea borrar");
    			if(productService.deleteProductLogically(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 15: { 
    			System.out.println("Ingrese el id del stock que desea borrar");
    			if(stockService.deleteStockLogically(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Borrado con exito");
    			else
    				System.out.println("No se pudo borrar");
    			break;}
    		case 16: {
    			System.out.println("Ingrese el id dela categoria que desea restaurar");
    			if(categoryService.restore(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Restaurado con exito");
    			else
    				System.out.println("No se pudo restaurar");
    			break;}
    		case 17: { 
    			System.out.println("Ingrese el id del producto que desea restaurar");
    			if(productService.restore(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Restaurado con exito");
    			else
    				System.out.println("No se pudo restaurar");
    			break;}
    		case 18: { 
    			System.out.println("Ingrese el id del stock que desea restaurar");
    			if(stockService.restore(Long.parseLong(keyboard.nextLine())))
    				System.out.println("Restaurado con exito");
    			else
    				System.out.println("No se pudo restaurar");
    			break;}
    		}
    	}
    	
    
    }
    
    private static int menu()
    {
    	clear();
    	System.out.println(" --- MENU PRINCIPAL ---");
    	System.out.println("1 - Crear categoria");
    	System.out.println("2 - Crear producto");
    	System.out.println("2 - Crear stock");
    	System.out.println("4 - Leer categoria");
    	System.out.println("5 - Leer producto");
    	System.out.println("6 - Leer stock");
    	System.out.println("7 - Modificar categoria");
    	System.out.println("8 - Modificar producto");
    	System.out.println("9 - Modificar stock");
    	System.out.println("10- Eliminar categoria");
    	System.out.println("11- Eliminar producto");
    	System.out.println("12- Eliminar stock");
    	System.out.println("13- Eliminar (logicamente) categoria");
    	System.out.println("14- Eliminar (logicamente) producto");
    	System.out.println("15- Eliminar (logicamente) stock");
    	System.out.println("16- Recuperar categoria");
    	System.out.println("17- Recuperar producto");
    	System.out.println("18- Recuperar stock");
    	return Integer.parseInt(keyboard.nextLine());
    }
    
    private static void clear() 
    {
    	try {
    		
        } catch (Exception e) 
    	{
            System.out.println(e.getMessage());
        }
    }
    
    private static long inputCategoryData(boolean modify)
    {
    	clear();
    	if(modify)
    		System.out.println(" --- MODIFICAR CATEGORIA --- ");
    	else
    		System.out.println(" --- CREAR CATEGORIA --- ");
		System.out.println("Ingrese el id: ");
		Long id = Long.parseLong(keyboard.nextLine());
		System.out.println("Ingrese el nombre: ");
		String name = keyboard.nextLine();
		System.out.println("Ingrese la descripcion: ");
		String description = keyboard.nextLine();
		if(modify)
		{
			if(categoryService.updateCategory(id, name, description))
				System.out.println("LA CATEGORIA FUE MODIFICADA CON EXITO ");
			else
				System.out.println("OCURRIO UN ERROR");
		}else
		{
			if(categoryService.createCategory(id, name, description))
				System.out.println("LA CATEGORIA FUE CREADA CON EXITO ");
			else
				System.out.println("OCURRIO UN ERROR");
		}
		keyboard.nextLine();
		return id;
    }
    
    private static void inputProductData(boolean modify)
    {
    	clear();
    	if(modify)
    		System.out.println(" --- MODIFICAR PRODUCTO --- ");
    	else
    		System.out.println(" --- CREAR PRODUCTO --- ");
		System.out.println("Ingrese el id: ");
		Long id = Long.parseLong(keyboard.nextLine());
		System.out.println("Ingrese el nombre: ");
		String name = keyboard.nextLine();
		System.out.println("Ingrese la descripcion: ");
		String description = keyboard.nextLine();
		System.out.println("Ingrese el precio: ");
		Double price = Double.parseDouble(keyboard.nextLine());
		System.out.println("Desea crear un nuevo stock?");
		Character option = keyboard.nextLine().charAt(0);
		Long stockId;
		if(option == 's')
		{
			stockId = inputStockData(false);
		}else
		{
			System.out.println("Ingrese el id del stock");
			stockId = Long.parseLong(keyboard.nextLine());
		}
		Stock stock = stockService.readStock(stockId);
		
		System.out.println("Desea crear una nueva categoria?");
		option = keyboard.nextLine().charAt(0);
		Long categoryId;
		if(option == 's')
		{
			categoryId = inputCategoryData(false);
		}else
		{
			System.out.println("Ingrese el id de la categoria");
			categoryId = Long.parseLong(keyboard.nextLine());
		}
		Category category = categoryService.readCategory(categoryId);
		if(modify)
		{
			if(productService.updateProduct(id, name, description, price, stock, category))
				System.out.println("EL PRODUCTO FUE MODIFICADO CON EXITO");
		}else
		{
			if(productService.createProduct(id, name, description, price, stock, category))
				System.out.println("EL PRODUCTO FUE CREADO CON EXITO");
		}
		keyboard.nextLine();
    }
    
    private static long inputStockData(boolean modify)
    {
    	clear();
    	if(modify)
    		System.out.println(" --- MODIFICAR STOCK --- ");
    	else
    		System.out.println(" --- CREAR STOCK --- ");
		System.out.println("Ingrese el id: ");
		Long id = Long.parseLong(keyboard.nextLine());
		System.out.println("Ingrese la cantidad: ");
		Integer quantity = Integer.parseInt(keyboard.nextLine());
		System.out.println("Ingrese el codigo de locacion: ");
		String locationCode = keyboard.nextLine();
		if(modify)
		{
			if(stockService.updateStock(id, quantity, locationCode))
				System.out.println("STOCK MODIFICADO CON EXITO ");
			else
				System.out.println("OCURRIO UN ERROR");
		}else 
		{
			if(stockService.createStock(id, quantity, locationCode))
				System.out.println("STOCK CREADO CON EXITO ");
			else
				System.out.println("OCURRIO UN ERROR");
		}
		keyboard.nextLine();
		return id;
    }
    
    
    private static void outputCategoryData()
    {
    	clear();
		System.out.println(" --- MOSTRAR CATEGORIA --- ");
		System.out.println("Ingrese el codigo de categoria: ");
		
		Long id = Long.parseLong(keyboard.nextLine());
		Category category = categoryService.readCategory(id);
		if(category != null)
		{
		System.out.println("id: " + category.getId());
		System.out.println("nombre: " + category.getName());
		System.out.println("descripcion: " + category.getDescription());
		}else
			System.out.println("Categoria no encontrada :/");
		keyboard.nextLine();
    }
    
    private static void outputProductData()
    {
    	clear();
		System.out.println(" --- MOSTRAR PRODUCTO --- ");
		System.out.println("Ingrese el codigo de producto: ");
		
		Long id = Long.parseLong(keyboard.nextLine());
		Product producto = productService.readProduct(id);
		if(producto != null)
		{
		System.out.println("id: " + producto.getId());
		System.out.println("nombre: " + producto.getName());
		System.out.println("descripcion: " + producto.getDescription());
		System.out.println("precio: " + producto.getPrice());
		System.out.println("categoria: " + producto.getCategory().getName());
		System.out.println("stock: " + producto.getStock().getQuantity());
		}else
			System.out.println("Producto no encontrado :/");
		keyboard.nextLine();
    }
    
    private static void outputStockData()
    {
    	clear();
		System.out.println(" --- MOSTRAR STOCK --- ");
		System.out.println("Ingrese el codigo de stock: ");
		
		Long id = Long.parseLong(keyboard.nextLine());
		Stock stock= stockService.readStock(id);
		if(stock != null)
		{
		System.out.println("id: " + stock.getId());
		System.out.println("cantidad: " + stock.getQuantity());
		System.out.println("locacion: " + stock.getLocationCode());
		}else
			System.out.println("Categoria no encontrada :/");
		keyboard.nextLine();
    }
}
