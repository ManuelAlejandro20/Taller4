package ucn;
/**
 * Esta clase por defecto representa a los administradores del sistema
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Usuario {

	// El email del usuario
	private String email;
	
	// La contraseña del usuario
	private String contraseña;
	
	// El nombre del usuario
	private String nombre;
	
	/**
	 * Rutina constructura de un usuario
	 * 
	 * @param email:
	 * 				El email del usuario
	 * @param contraseña:
	 * 				La contraseña del usuario
	 * @param nombre:
	 * 				El nombre del usuario
	 */
	public Usuario(String email, String contraseña, String nombre) {
		this.email = email;
		this.contraseña = contraseña;
		this.nombre = nombre;
	}
	
	/**
	 * @return Un string con el email del usuario
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @return Un String con la contraseña del usuario
	 */
	public String getContraseña() {
		return contraseña;
	}
	
	/**
	 * @return Un String con el nombre del usuario
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return a String with the user information
	 */
	public String deployUsuario () {
		return ("Email: " + this.email + "; Nombre: " + this.nombre);
	}
}
