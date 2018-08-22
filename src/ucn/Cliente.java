package ucn;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Hereda de Usuario y representa a los clientes del sistema
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Cliente extends Usuario {
	
	// The client country
	private String pais;
	
	// The client points
	private int puntos;
	
	// Thew client shopping cart
	private ArrayList<Producto> carritoCompras;
	
	/**
	 * Construction routine for a client
	 * 
	 * @param email:
	 * 				The client email
	 * @param contraseña:
	 * 				The client password
	 * @param nombre:
	 * 				The client name
	 * @param pais:
	 * 				The client country
	 * @param puntos:
	 * 				The client points
	 */
	public Cliente(String email, String contraseña, String nombre, String pais, int puntos) {
		super(email, contraseña, nombre);
		this.pais = pais;
		this.puntos = puntos;
		this.carritoCompras = new ArrayList<Producto>();
	}

	/**
	 * @return a String with the client country
	 */
	public String getPais() {
		return pais;
	}
	
	/**
	 * @return an int with the client points
	 */
	public int getPuntos() {
		return puntos;
	}
	
	/**
	 * Give poinst to the client
	 * 
	 * @param puntos:
	 * 				The amount of points
	 */
	public void AumentarPuntos(int puntos) {
		this.puntos += puntos;
	}
	
	/**
	 * Decrease the points of the client
	 *  
	 * @param puntos:
	 * 				The amount of points
	 */
	public void DisminuirPuntos(int puntos) {
		this.puntos -= puntos;
	}
	
	/**
	 * Add a product to the shopping cart
	 * 
	 * @param producto:
	 * 				The product
	 * @param stock:
	 * 				How many products will be added to the shopping cart
	 */
	public void AñadirAlCarrito(Producto producto, int stock) {
		producto.AumentarStockCarrito(stock);
		this.carritoCompras.add(producto);
	}
	
	/**
	 * @return an int with the total price of the shopping cart
	 */
	public int getMontoCarritoCompras () {
		
		int montoTotal = 0;
		
		Iterator<Producto> it = carritoCompras.iterator();
		
		while (it.hasNext()) {
			Producto producto = (Producto)it.next();
			
			montoTotal += producto.getPrecio() * producto.getStockCarrito();
		}
		
		return montoTotal;
	}
	
	/**
	 * Empty the shopping cart
	 */
	public void VaciarCarrito () {
		
		Iterator<Producto> it = carritoCompras.iterator();
		
		while (it.hasNext()) {
			Producto producto = (Producto)it.next();
			producto.ResetearStock();
		}
		
		this.carritoCompras.clear();
	}
	
	/**
	 * @return an String with the client information
	 */
	@Override
	public String deployUsuario () {
		return ("Email: " + this.getEmail() + "; Nombre: " + this.getNombre() + "; Pais: " + this.pais + "; Puntos: " + this.puntos);
	}
}
