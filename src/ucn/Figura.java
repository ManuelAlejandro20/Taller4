package ucn;
/**
 * Hereda de producto y posee atributos relevante solo para las figuras
 * 
 * @author Matthew Gonzalez Mansilla - 
 *
 */
public class Figura extends Producto {
	
	// The date of sale of the product
	public String fechaVenta;
	
	// Indicates the contidion of the figure. It can be new or pre-purchase
	public String condicion;
	
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
	 * @param fechaVenta:
	 * 			The date of sale of the product
	 * @param condicion:
	 * 			The figure condition
	 */
	public Figura(String SKU, String nombre, int precio, String codigoRelacion, int stock, String fechaVenta, String condicion) {
		super(SKU, nombre, precio, codigoRelacion, stock);
		this.fechaVenta = fechaVenta;
		this.condicion = condicion;
	}
	
	/**
	 * @return a String with the date of sale
	 */
	public String getFechaVenta() {
		return fechaVenta;
	}
	
	/**
	 * @return a String with the figure condition
	 */
	public String getCondicion() {
		return condicion;
	}
	
	/**
	 * @param condicion
	 */
	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}
	/**
	 * @return a String with the product information
	 */
	@Override
	public String deployProducto() {
		if (this.getStock() <= 0) {
			if (this.condicion.equals("pre-venta")) {
				return ("SKU: " + this.getSKU() + "; Nombre: " + this.getNombre() + "; Precio Oferta: " + this.getPrecio() +  "; Condicion: Pre-venta; SIN STOCK");
			} else {
				return ("SKU: " + this.getSKU() + "; Nombre: " + this.getNombre() + "; Precio: " + this.getPrecio() + "; Condicion: Nuevo; SIN STOCK");
			}
		} else {
			if (this.condicion.equals("pre-venta")) {
				return ("SKU: " + this.getSKU() + "; Nombre: " + this.getNombre() + "; Precio Oferta: " + this.getPrecio() +  "; Condicion: Pre-venta; Stock" + this.getStock());
			} else {
				return ("SKU: " + this.getSKU() + "; Nombre: " + this.getNombre() + "; Precio: " + this.getPrecio() + "; Condicion: Nuevo; Stock: " + this.getStock());
			}
		}
	}
}
