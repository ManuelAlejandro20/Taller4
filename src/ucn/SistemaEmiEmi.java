package ucn;

import java.util.ArrayList;

public interface SistemaEmiEmi {

	public boolean RegistrarUsuario(Usuario usuario);
	public boolean EliminarUsuario (Usuario usuario);
	public void A�adirProducto(Producto producto);
	public void A�adirStock(Producto producto, int stock);
	public ArrayList<Producto> BuscarProductos(String fraseClave);
	public void DesplegarMercancias(boolean filtrarStock);
	public void DesplegarFiguras(boolean filtrarStock);
	public void A�adirFiguraUsada (String SKU, String condicion, int precio, Cliente due�o);
	public void ExportarDatos();
}
