package ar.com.gl.shop.product.exceptions;

public class IdAlreadyExistException extends Exception {

	private static final long serialVersionUID = 2L;
	private String message = "El id especificado pertenece a otro elemento, debe introducir uno distinto";
	
	public IdAlreadyExistException()
	{
		
	}
	
	public IdAlreadyExistException(String message)
	{
		this.message = message;
	}
	public String getMessage()
	{
		return message;
	}
}
