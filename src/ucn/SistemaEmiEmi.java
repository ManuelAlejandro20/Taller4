package ucn;

public interface SistemaEmiEmi {

	public boolean RegistrarUsuario(Usuario usuario);
	public boolean EliminarUsuario (Usuario usuario);
	public void A�adirProducto(Producto producto);
	public void A�adirStock(Producto producto, int stock);
	
}
