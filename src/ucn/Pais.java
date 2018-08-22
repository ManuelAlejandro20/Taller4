package ucn;

public class Pais {
	
	// The name of the country
	private String nombre;
	
	// The amount of money that EmiEmi has received from this country
	private int ingresos;
	
	private int puntos;

	/**
	 * Construction routine of a country
	 * 
	 * @param nombre:
	 * 			The country name
	 */
	public Pais(String nombre) {
		this.nombre = nombre;
		this.ingresos = 0;
		this.puntos = 0;
	}
	
	/**
	 * Increase the money that the class stores
	 * @param monto:
	 * 			How much it going to increase
	 */
	public void AumentarMonto (int monto) {
		this.ingresos += monto;
	}
	
	/**
	 * @return a String with the name of the country
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return an int with the amount of money that this country is storing
	 */
	public int getIngresos() {
		return ingresos;
	}
	
	public void setPuntos(int puntosAgregar) {this.puntos += puntosAgregar;}
	
	public int getPuntos() {return this.puntos;}

	/**
	 * @return a String with the country information
	 */
	public String deployPais() {
		return ("Pais: " + this.nombre + "; Ingresos: " + this.ingresos);
	}
	
}
