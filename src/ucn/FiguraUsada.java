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
	
	/**
	 * @return a String with the figure information
	 */
	@Override
	public String deployProducto() {
		String condicionDesplegar = "Usado";
		switch(this.getCondicion().toUpperCase()) {
			case "A":
				condicionDesplegar = "Sellado";
				break;
			case "B":
				condicionDesplegar = "Aparentemente nuevo";
				break;
			case "C":
				condicionDesplegar = "Abierto";
				break;
			case "D":
				condicionDesplegar = "Da�os menores";
				break;
			case "E":
				condicionDesplegar = "Da�os visibles";
				break;
			case "F":
				condicionDesplegar = "Piezas extraviadas";
				break;
		}
		return ("SKU: " + this.getSKU() + "; Nombre: " + this.getNombre() + "; Precio: " + this.getPrecio() + "; Condicion: " + condicionDesplegar + "; Stock: 1");
	}
}

