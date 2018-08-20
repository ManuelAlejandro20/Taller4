package ucn;

public class FiguraUsada extends Figura {
	
	// The owner of the figure
	private Cliente due�o;
	
	/**
	 * Construction routine of a preowned figure
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
	 * @param due�o:
	 * 			The owner of the figure
	 */
	public FiguraUsada(String SKU, String nombre, int precio, String codigoRelacion, int stock, String fechaVenta, String condicion, Cliente due�o) {
		super(SKU, nombre, precio, codigoRelacion, stock, fechaVenta, condicion);
		this.due�o = due�o;
	}
	
	/**
	 * @return a client object with the owner of the figure
	 */
	public Cliente getDue�o () {
		return this.due�o;
	}	
}
