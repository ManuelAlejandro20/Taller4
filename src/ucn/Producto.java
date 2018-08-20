package ucn;
/**
 * Esta clase por defecto representa a la mercancia de la tienda
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Producto {
	
	// The product SKU, serves to identify it
	private String SKU;
	
	// The product name
	private String nombre;
	
	// The product price
	private int precio;
	
	// The product relationship code. It allows to relate this product with others of the same code
	private String codigoRelacion;
	
	// The number of copies of this product in the store
	private int stock;
	
	// The number of copies of this product in the shopping cart
	private int stockCarrito;
	
	/**
	 * Construction routine for a figure
	 * 
	 * @param SKU:
	 * 			The figure SKU
	 * @param nombre:
	 * 			The figure name
	 * @param precio:
	 * 			The figure price
	 * @param codigoRelacion:
	 * 			The figure relationship code
	 * @param stock:
	 * 			The figure stock
	 */
	public Producto(String SKU, String nombre, int precio, String codigoRelacion, int stock) {
		this.SKU = SKU;
		this.nombre = nombre;
		this.precio = precio;
		this.codigoRelacion = codigoRelacion;
		this.stock = stock;
		this.stockCarrito = 0; // A product isn't created in a shopping cart
	}
	
	/**
	 * @return a Strig with the product SKU
	 */
	public String getSKU() {
		return SKU;
	}
	
	/**
	 * @return a String with the product name
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * @return an it with the product price
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @return a String with the relationship code
	 */
	public String getCodigoRelacion() {
		return codigoRelacion;
	}
	
	/**
	 * @return an int with the actual stock of the product
	 */
	public int getStock() {
		return stock;
	}
	
	/**
	 * @return an int with the amount of products that are in the shopping cart
	 */
	public int getStockCarrito() {
		return this.stockCarrito;
	}
	
	/**
	 * Increase the amount of products in the shopping cart and decrease those in the store
	 * 
	 * @param stock:
	 * 			How much the shopping cart stock will increase
	 */
	public void AumentarStockCarrito (int stock) {
		this.stockCarrito += stock;
		this.stock -= stock;
	}
	
	/**
	 * Returns the products that are in the shopping cart to the store
	 */
	public void ResetearStock () {
		this.stock += this.stockCarrito;
		this.stockCarrito = 0;
	}
	/**
	 * Increase the stock of the product
	 * 
	 * @param stock:
	 * 			The amount of stock that is going to be added
	 */
	public void AgregarStock (int stock) {
		this.stock += stock;
	}
	
	/**
	 * @return a String with the product information
	 */
	public String deployProducto() {
		if (this.stock <= 0) {
			return ("SKU: " + this.SKU + "; Nombre: " + this.nombre + "; Precio: " + this.precio + "; SIN STOCK");
		} else {
			return ("SKU: " + this.SKU + "; Nombre: " + this.nombre + "; Precio: " + this.precio + "; Stock: " + this.stock);
		}
	}
}
