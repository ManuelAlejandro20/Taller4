package Excepciones;

public class ExcepcionUsuarioRepetido extends Exception {

	public ExcepcionUsuarioRepetido() {
		
	}
	
	public String mensaje() {
		return "\nXXXXXXX ESTE USUARIO YA EXISTE, INTENTALO DE NUEVO XXXXXXXX\n";
	}
	
	private static final long serialVersionUID = 1L;
	
}
