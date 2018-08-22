package ucn;

import java.util.ArrayList;

public interface SistemaEmiEmi {

	public boolean RegistrarUsuario(Usuario usuario);
	public boolean EliminarUsuario (Usuario usuario);
	public void AñadirProducto(Producto producto);
	public void AñadirStock(Producto producto, int stock);
	public ArrayList<Producto> BuscarProductos(String fraseClave);
	public void DesplegarMercancias(boolean filtrarStock);
	public void DesplegarFiguras(boolean filtrarStock);
	public void AñadirFiguraUsada (String SKU, String condicion, int precio, Cliente dueño);
	public void ExportarDatos();
	public void ConfirmarCompra(Cliente cliente);
}
