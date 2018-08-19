package ucn;
/**
 * Hereda de Usuario y representa a los clientes del sistema
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Cliente extends Usuario {
	
	// El pais del usuario
	private String pais;
	
	// Los puntos que tiene el usuario
	private int puntos;
	
	/**
	 * Rutina constructora de un cliente
	 * 
	 * @param email:
	 * 				El email del cliente
	 * @param contraseña:
	 * 				La contraseña del cliente
	 * @param nombre:
	 * 				El nombre del cliente
	 * @param pais:
	 * 				El pais del cliente
	 * @param puntos:
	 * 				Los puntos del cliente
	 */
	public Cliente(String email, String contraseña, String nombre, String pais, int puntos) {
		super(email, contraseña, nombre);
		this.pais = pais;
		this.puntos = puntos;
	}

	/**
	 * @return Un String con el pais del cliente
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * @return Un int con los puntos del cliente
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Cambia los puntos de un cliente
	 * 
	 * @param puntos:
	 * 				Los nuevos puntos del cliente
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}	
}
