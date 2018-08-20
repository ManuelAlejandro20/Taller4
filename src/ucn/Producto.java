package ucn;
/**
 * Esta clase por defecto representa a la mercancia de la tienda
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Producto {
	
	// El SKU identificador del producto
	private String SKU;
	
	// El nombre del producto
	private String nombre;
	
	// El precio del producto
	private int precio;
	
	// El codigo de relacion del producto. Permite relacionar a este producto con otros de igual codigo
	private String codigoRelacion;
	
	// La cantidad de ejemplares que hay de este producto en la tienda
	private int stock;
	
	/**
	 * Rutina constructora para un producto
	 * 
	 * @param SKU:
	 * 			El SKU del producto
	 * @param nombre:
	 * 			El nombre del producto
	 * @param precio:
	 * 			El precio del producto
	 * @param codigoRelacion:
	 * 			El codigo de relacion del producto
	 * @param stock:
	 * 			El stock del producto
	 */
	public Producto(String SKU, String nombre, int precio, String codigoRelacion, int stock) {
		this.SKU = SKU;
		this.nombre = nombre;
		this.precio = precio;
		this.codigoRelacion = codigoRelacion;
		this.stock = stock;
	}
	
	/**
	 * @return un String  con el SKU del producto
	 */
	public String getSKU() {
		return SKU;
	}
	
	/**
	 * @return un String con el nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return un int con el precio del producto
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @return un String con el codigo de relacion
	 */
	public String getCodigoRelacion() {
		return codigoRelacion;
	}
	
	/**
	 * @return un int con el stock actual del producto
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * Aumenta el stock del prodcuto
	 * 
	 * @param stock:
	 * 			En cuanto se va a aumentar el stock
	 */
	public void AgregarStock (int stock) {
		this.stock += stock;
	}
	
	/**
	 * Disminuye el stock del producto
	 * 
	 * @param stock:
	 * 			En cuanto se va a disminuir el stock
	 */
	public void DisminuirStock (int stock) {
		this.stock -= stock;
	}
}
